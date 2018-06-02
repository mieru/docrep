package docrep.controller;

import docrep.service.account.AccountService;
import docrep.service.authorization.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/account/logged")
    public AccountDTO getAccountInformationForLoggedUser(Authentication authentication){
        return accountService.getAccountInfo(authentication);
    }

}
