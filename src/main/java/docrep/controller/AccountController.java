package docrep.controller;

import docrep.service.account.AccountService;
import docrep.service.account.dto.ChangePasswordDTO;
import docrep.service.authorization.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/account/logged")
    public AccountDTO getAccountInformationForLoggedUser(Authentication authentication){
        return accountService.getAccountInfo(authentication);
    }

    @RequestMapping("/account/all")
    public List<AccountDTO> getAccountInformationForLoggedUser(){
        return accountService.getAllAccount();
    }


    @RequestMapping(value = "/account/" , method = RequestMethod.PUT)
    public void updateAccount(@RequestBody AccountDTO accountDTO){
        accountService.update(accountDTO);
    }
    @RequestMapping(value = "/account/changePassword" , method = RequestMethod.POST)
    public void addAccount(@RequestBody ChangePasswordDTO changePasswordDTO, Authentication authentication){
        accountService.changePassword(changePasswordDTO, authentication);
    }

    @RequestMapping(value = "/account/" , method = RequestMethod.POST)
    public void addAccount(@RequestBody AccountDTO accountDTO){
        accountService.add(accountDTO);
    }

    @RequestMapping(value = "/account/delete" , method = RequestMethod.POST)
    public void deleteAccount(@RequestBody AccountDTO accountDTO){
        accountService.delete(accountDTO);
    }




}
