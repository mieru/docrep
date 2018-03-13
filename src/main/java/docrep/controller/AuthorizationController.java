package docrep.controller;

import docrep.auth.JwtTokenUtil;
import docrep.service.authorization.AuthorizationService;
import docrep.service.authorization.dto.AccountDTO;
import docrep.service.authorization.dto.AuthAccountDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/")
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public ResponseEntity<String> checkLoginData(@RequestBody AuthAccountDTO authAccountDTO) throws Exception {
        boolean isAuthorized = authorizationService.checkLoginDataAndGenerateToken(authAccountDTO);
        return isAuthorized ? generateResponseWithToken(new ResponseEntity<>(HttpStatus.OK)) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/api/account/register", method = RequestMethod.POST)
    public Integer registerNewAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        return authorizationService.registerNewAccount(accountDTO);
    }

    private ResponseEntity<String> generateResponseWithToken(ResponseEntity<String> responseEntity) {
        return new ResponseEntity<>(jwtTokenUtil.generateToken(), responseEntity.getStatusCode());
    }
}
