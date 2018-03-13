package docrep.service.storagelocation.mapper;

import docrep.db.tables.pojos.StorageLocation;
import docrep.service.storagelocation.dto.StorageLocationDTO;
import docrep.service.storagelocation.enums.StorageLocationType;

public class StorageLocationMapper {
    public static StorageLocation mapStorageLocationDtoToStorageLocation(StorageLocationDTO storageLocationDTO) {
        return mapStorageLocationDtoToStorageLocation(null, storageLocationDTO);
    }


    public static StorageLocationDTO mapStorageLocationToStorageLocationDto(StorageLocation storageLocation) {
        return StorageLocationDTO.builder()
                .accountId(storageLocation.getAccountId())
                .superiorStorageLocId(storageLocation.getSuperiorStorageLocId())
                .longitude(storageLocation.getLongitude())
                .latitude(storageLocation.getLatitude())
                .description(storageLocation.getDescription())
                .name(storageLocation.getName())
                .id(storageLocation.getId())
                .type(StorageLocationType.valueOf(storageLocation.getType()))
                .build();
    }

    public static StorageLocation mapStorageLocationDtoToStorageLocation(StorageLocation storageLocation, StorageLocationDTO storageLocationDTO) {
        if (storageLocation == null) storageLocation = new StorageLocation();
        if (storageLocationDTO.getId() != null)
            storageLocation.setId(storageLocationDTO.getId());
        if (storageLocationDTO.getAccountId() != null)
            storageLocation.setAccountId(storageLocationDTO.getAccountId());
        if (storageLocationDTO.getName() != null)
            storageLocation.setName(storageLocationDTO.getName());
        if (storageLocationDTO.getDescription() != null)
            storageLocation.setDescription(storageLocationDTO.getDescription());
        if (storageLocationDTO.getLatitude() != null)
            storageLocation.setLatitude(storageLocationDTO.getLatitude());
        if (storageLocationDTO.getLongitude() != null)
            storageLocation.setLongitude(storageLocationDTO.getLongitude());
        if (storageLocationDTO.getSuperiorStorageLocId() != null)
            storageLocation.setSuperiorStorageLocId(storageLocationDTO.getSuperiorStorageLocId());
        if (storageLocationDTO.getType() != null)
            storageLocation.setType(storageLocationDTO.getType().toString());
        return storageLocation;
    }
}
