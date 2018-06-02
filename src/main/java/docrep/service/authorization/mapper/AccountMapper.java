package docrep.service.authorization.mapper;

import docrep.db.tables.pojos.Account;
import docrep.service.authorization.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

public class AccountMapper {


    public static Account mapAccountDTOToAccount(AccountDTO accountDTO, Account account) {
        if(account==null){
            account = new Account();
        }

        if(accountDTO.getId() != null)
            account.setId(accountDTO.getId());
        account.setUsername(accountDTO.getUsername() != null ? accountDTO.getUsername(): account.getUsername());
        account.setLastLoginDate(accountDTO.getLastLoginDate() != null ?Timestamp.valueOf(accountDTO.getLastLoginDate()): account.getLastLoginDate());
        account.setPassword(accountDTO.getPassword() != null ? accountDTO.getPassword() : account.getPassword());
        account.setStatus(accountDTO.getStatus() != null ? accountDTO.getStatus() : account.getStatus());
        return account;
    }
}
