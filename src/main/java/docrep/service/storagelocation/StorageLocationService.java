package docrep.service.storagelocation;

import docrep.component.ValidatorComponent;
import docrep.db.tables.daos.StorageLocationDao;
import docrep.db.tables.pojos.StorageLocation;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import docrep.service.storagelocation.dto.StorageLocationDTO;
import docrep.service.storagelocation.enums.StorageLocationType;
import docrep.service.storagelocation.mapper.StorageLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;

@Service
public class StorageLocationService {


    @Autowired
    StorageLocationDao storageLocationDao;
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
}
