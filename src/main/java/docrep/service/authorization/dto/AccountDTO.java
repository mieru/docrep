package docrep.service.authorization.dto;

import docrep.service.person.dto.PersonDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountDTO {
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private Timestamp lastLoginDate;
    private String status;
    @NotNull
    @Valid
    private PersonDTO person;

}
