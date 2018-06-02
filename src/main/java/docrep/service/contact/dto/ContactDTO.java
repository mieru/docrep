package docrep.service.contact.dto;

import docrep.service.person.dto.PersonDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ContactDTO {
    private Integer id;
    private String  regexp;
    @NotNull
    private String  type;
    @NotNull
    private String  description;
    @NotNull
    private String  value;
    private Integer  personId;
}
