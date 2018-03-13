package account;

import docrep.db.tables.daos.AccountDao;
import docrep.db.tables.daos.AddressDao;
import docrep.db.tables.daos.ContactDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.pojos.Account;
import docrep.component.ValidatorComponent;
import docrep.db.tables.pojos.Address;
import docrep.db.tables.pojos.Contact;
import docrep.db.tables.pojos.Person;
import docrep.service.address.dto.AddressDTO;
import docrep.service.authorization.AuthorizationService;
import docrep.service.authorization.dto.AuthAccountDTO;
import docrep.service.authorization.dto.AccountDTO;
import docrep.service.authorization.mapper.AccountMapper;
import docrep.service.contact.dto.ContactDTO;
import docrep.service.contact.mapper.ContactMapper;
import docrep.service.person.dto.PersonDTO;
import docrep.service.person.mapper.PersonMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AuthorizationTests {
    @TestConfiguration
    static class AuthorizationTestContextConfiguration {

        @Bean
        public AuthorizationService authorizationService() {
            return new AuthorizationService();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public ValidatorComponent validatorComponent() {
            return new ValidatorComponent();
        }

    }

    @Autowired
    AuthorizationService authorizationService;

    @MockBean
    AccountDao accountDao;
    @MockBean
    PersonDao personDao;
    @MockBean
    AddressDao addressDao;
    @MockBean
    ContactDao contactDao;

    private static Account account;
    private static Address address;
    private static Person person;
    private static Contact contact;

    private static AccountDTO accountDTO;

    @BeforeClass
    public static void setUp() throws Exception {
        address = new Address();
        address.setId(1);
        address.setStreetName("Rynek");
        address.setBuildingNumber("12B");
        address.setCity("Kielce");
        address.setPostalCode("25-001");
        address.setPremisesNumber("2");

        person = new Person();
        person.setId(1);
        person.setFirstname("Jan");
        person.setLastname("Kowalski");
        person.setAddressId(1);
        person.setIdNumber("AVD12345");
        person.setJobPosition("MENAGER");
        person.setPesel("92123408111");

        contact = new Contact();
        contact.setId(1);
        contact.setDescription("Tel. kom.");
        contact.setRegexp("");
        contact.setType("TK");
        contact.setValue("700800900");
        contact.setPersonId(1);

        account = new Account();
        account.setId(1);
        account.setPassword("$2a$10$ba.3QZLPQ9yAJaCnKk7KteQqia5MsK.BDPPYcHv4JYCnezJBQkZV6");
        account.setUsername("user1");
        account.setLastLoginDate(Timestamp.valueOf(LocalDateTime.now()));
        account.setStatus("ACTIVE");
        account.setPersonId(1);

        accountDTO = AccountDTO.builder()
                .username("user1")
                .password("user1")
                .person(PersonDTO.builder()
                        .firstname("Jan")
                        .lastname("Kowalski")
                        .idNumber("XYZ12345")
                        .jobPosition("BOSS")
                        .pesel("98121200192")
                        .address(AddressDTO.builder()
                                .streetName("Rynek")
                                .buildingNumber("1B")
                                .premisesNumber("1")
                                .postalCode("25-001")
                                .city("Kielce")
                                .build())
                        .contacts(Collections.singletonList(ContactDTO.builder()
                                .type("PHONE")
                                .description("Telefon kom.")
                                .value("944555888")
                                .build()))
                        .build())
                .build();

    }

    @Test
    public void shouldNotAuthorizedWhenIncorrectPassword() throws Exception {
        //  given
        AuthAccountDTO authAccountDTO = AuthAccountDTO.builder()
                .username("user1")
                .password("niepoprawne")
                .build();
        account.setStatus("ACTIVE");
        when(accountDao.fetchOneByUsername(authAccountDTO.getUsername())).thenReturn(account);
        //  when
        boolean isAuthorized = authorizationService.checkLoginDataAndGenerateToken(authAccountDTO);
        //  then
        Assert.assertFalse(isAuthorized);
    }

    @Test
    public void shouldAuthorizedWhenPasswordCorrect() throws Exception {
        //  given
        AuthAccountDTO authAccountDTO = AuthAccountDTO.builder()
                .username("user1")
                .password("user1")
                .build();
        account.setStatus("ACTIVE");
        when(accountDao.fetchOneByUsername(authAccountDTO.getUsername())).thenReturn(account);
        //  when
        boolean isAuthorized = authorizationService.checkLoginDataAndGenerateToken(authAccountDTO);
        //  then
        Assert.assertTrue(isAuthorized);
    }

    @Test
    public void shouldNotAuthorizedWhenDataNull() throws Exception {
        //  given
        AuthAccountDTO authAccountDTO = AuthAccountDTO.builder().build();
        account.setStatus("ACTIVE");
        when(accountDao.fetchOneByUsername(authAccountDTO.getUsername())).thenReturn(account);
        //  when
        boolean isAuthorized = authorizationService.checkLoginDataAndGenerateToken(authAccountDTO);
        //  then
        Assert.assertFalse(isAuthorized);
    }

    @Test
    public void shouldNotAuthorizedWhenAccountNotActive() throws Exception {
        //  given
        AuthAccountDTO authAccountDTO = AuthAccountDTO.builder()
                .username("user1")
                .password("user1")
                .build();
        account.setStatus("NOT_ACTIVE");
        when(accountDao.fetchOneByUsername(authAccountDTO.getUsername())).thenReturn(account);
        //  when
        boolean isAuthorized = authorizationService.checkLoginDataAndGenerateToken(authAccountDTO);
        //  then
        Assert.assertFalse(isAuthorized);
    }


    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionWhenIncorrectAccountDTO() throws Exception {
        //  given
        AccountDTO accountDTO = AccountDTO.builder().build();
        //  when
        authorizationService.registerNewAccount(accountDTO);
        //  then
        // throw ValidationException
    }

    @Test()
    public void shoultNotThrowExceptionWhenRegisterAccount() throws Exception {
        //  given
        // accountDTO
        //  when
        authorizationService.registerNewAccount(accountDTO);
        //  then
        // not throw
    }

    @Test()
    public void shoultNotThrowExceptionWhenRegisterAccountWithoutContactList() throws Exception {
        //  given
        accountDTO.getPerson().setContacts(null);
        //  when
        authorizationService.registerNewAccount(accountDTO);
        //  then
        // not throw
    }

    @Test()
    public void shoultNotThrowExceptionWhenRegisterAccountWithEmptyContactList() throws Exception {
        //  given
        accountDTO.getPerson().setContacts(Collections.emptyList());
        //  when
        authorizationService.registerNewAccount(accountDTO);
        //  then
        // not throw
    }

}
