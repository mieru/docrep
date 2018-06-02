package docrep.service.storagelocation;

import docrep.component.ValidatorComponent;
import docrep.db.tables.daos.AccountDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.daos.StorageLocationDao;
import docrep.db.tables.pojos.Account;
import docrep.db.tables.pojos.Person;
import docrep.db.tables.pojos.StorageLocation;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import docrep.service.storagelocation.dto.StorageLocationDTO;
import docrep.service.storagelocation.dto.StrageLocationTreeNode;
import docrep.service.storagelocation.enums.StorageLocationType;
import docrep.service.storagelocation.mapper.StorageLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageLocationService {


    @Autowired
    StorageLocationDao storageLocationDao;
    @Autowired
    PersonDao personDao;
    @Autowired
    ValidatorComponent validatorComponent;

    @Transactional
    public Integer insertStorageLocation(StorageLocationDTO storageLocationDTO) throws Exception {
        validatorComponent.valid(storageLocationDTO);
        additionalValidation(storageLocationDTO);
        StorageLocation storageLocation = StorageLocationMapper.mapStorageLocationDtoToStorageLocation(storageLocationDTO);
        storageLocationDao.insert(storageLocation);
        return storageLocation.getId();
    }

    @Transactional
    public StorageLocationDTO updateStorageLocation(StorageLocationDTO storageLocationDTO) throws Exception {
        if (storageLocationDTO.getId() == null) throw new ValidationException("Id cannot be null");

        validatorComponent.valid(storageLocationDTO);
        additionalValidation(storageLocationDTO);
        StorageLocation storageLocation = storageLocationDao.fetchOneById(storageLocationDTO.getId());
        storageLocation = StorageLocationMapper.mapStorageLocationDtoToStorageLocation(storageLocation, storageLocationDTO);
        storageLocationDao.update(storageLocation);
        return StorageLocationMapper.mapStorageLocationToStorageLocationDto(storageLocation);
    }

    @Transactional
    public void deleteStorageLocation(Integer storageLocationId) throws Exception {
        if (storageLocationId == null) throw new ValidationException("Id cannot be null");
        storageLocationDao.deleteById(storageLocationId);
    }

    public CompleteStorageLocationStructureDTO getCompleteStructureStorageLocationById(Integer storageLocationId) throws Exception {
        if (storageLocationId == null) return null;
        StorageLocationDTO storageLocationDTO = getStorageLocationById(storageLocationId);
        return CompleteStorageLocationStructureDTO.builder()
                .superiorStorageLocation(getCompleteStructureStorageLocationById(storageLocationDTO.getSuperiorStorageLocId()))
                .accountId(storageLocationDTO.getAccountId())
                .longitude(storageLocationDTO.getLongitude())
                .latitude(storageLocationDTO.getLatitude())
                .description(storageLocationDTO.getDescription())
                .name(storageLocationDTO.getName())
                .id(storageLocationDTO.getId())
                .type(storageLocationDTO.getType())
                .build();
    }

    public StorageLocationDTO getStorageLocationById(Integer storageLocationId) throws Exception {
        StorageLocation storageLocation = storageLocationDao.fetchOneById(storageLocationId);
        return StorageLocationMapper.mapStorageLocationToStorageLocationDto(storageLocation);
    }


    private void additionalValidation(StorageLocationDTO storageLocationDTO) {
        if (StorageLocationType.EMPLOYEE.equals(storageLocationDTO.getType()) && storageLocationDTO.getAccountId() == null) {
            throw new ValidationException("AccountId cannot be null when type is Employee");
        }
    }


    @Transactional
    public StorageLocation findOrAddStorageLocationByAccount(Account account) {
            StorageLocation storageLocation = null;
            List<StorageLocation> storageLocations = storageLocationDao.fetchByAccountId(account.getId());
            if (storageLocations == null || storageLocations.isEmpty()) {
                StorageLocation storageLocationToAdd = new StorageLocation();
                storageLocationToAdd.setType(StorageLocationType.EMPLOYEE.toString());
                storageLocationToAdd.setAccountId(account.getId());
                Person person = personDao.fetchOneById(account.getPersonId());
                storageLocationToAdd.setName(person.getFirstname() + " " + person.getLastname());
                storageLocationDao.insert(storageLocationToAdd);
                storageLocation = storageLocationToAdd;
            } else {
                storageLocation = storageLocations.get(0);
            }
            return storageLocation;
        }

    public List<StorageLocationDTO> getAllStorageLocations() {
        return storageLocationDao.findAll().stream()
                .map(StorageLocationMapper::mapStorageLocationToStorageLocationDto)
                .collect(Collectors.toList());
    }

    public StorageLocation getStorageLocationByAccountId(Integer accountId) {
        return storageLocationDao.fetchByAccountId(accountId).get(0);
    }

    public List<StrageLocationTreeNode> getAllStorageLocationsAsTree() {
        return fillTreeStructure(storageLocationDao.findAll().stream()
                .filter(storageLocation -> storageLocation.getSuperiorStorageLocId() == null)
                .collect(Collectors.toList()));
    }

    public List<StrageLocationTreeNode> fillTreeStructure(List<StorageLocation> list) {
        return list.stream()
                .map(storageLocation -> {return StrageLocationTreeNode.builder()
                        .label(storageLocation.getName())
                        .data(storageLocation.getId().toString())
                        .children(fillTreeStructure(storageLocationDao.fetchBySuperiorStorageLocId(storageLocation.getId())))
                        .build();}).collect(Collectors.toList());
    }

}
