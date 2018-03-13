package docrep.service.authorization.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AuthAccountDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}

