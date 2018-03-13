package docrep.service.address.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressDTO {
    private Integer id;
    @NotNull
    private String streetName;
    @NotNull
    private String buildingNumber;
    private String premisesNumber;
    @NotNull
    private String postalCode;
    @NotNull
    private String city;
}
