package docrep.service.storagelocation.dto;

import docrep.service.storagelocation.enums.StorageLocationType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class StorageLocationDTO {
    private Integer id;
    @NotNull
    private String  name;
    private String  description;
    private Double  longitude;
    private Double  latitude;
    private Integer superiorStorageLocId;
    @NotNull
    private StorageLocationType type;
    private Integer accountId;
}
