package docrep.service.authorization.mapper;

import docrep.db.tables.pojos.Account;
import docrep.service.authorization.dto.AccountDTO;

public class AccountMapper {
    public static Account mapAccountDTOToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        return account;
    }
}
