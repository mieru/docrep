package docrep.service.person.dto;

import docrep.service.address.dto.AddressDTO;
import docrep.service.contact.dto.ContactDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class PersonDTO {
    private Integer id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String pesel;
    @NotNull
    private String idNumber;
    @NotNull
    private String jobPosition;
    @NotNull
    @Valid
    private AddressDTO address;
    @Valid
    private List<ContactDTO> contacts;
}
