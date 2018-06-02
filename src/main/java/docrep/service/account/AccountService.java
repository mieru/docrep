package docrep.service.account;

import docrep.auth.JwtAuthenticatedUser;
import docrep.db.tables.daos.AccountDao;
import docrep.db.tables.daos.AddressDao;
import docrep.db.tables.daos.ContactDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.pojos.Account;
import docrep.db.tables.pojos.Address;
import docrep.db.tables.pojos.Contact;
import docrep.db.tables.pojos.Person;
import docrep.service.address.dto.AddressDTO;
import docrep.service.authorization.dto.AccountDTO;
import docrep.service.contact.dto.ContactDTO;
import docrep.service.person.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    PersonDao personDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    ContactDao contactDao;


    public AccountDTO getAccountInfo(Authentication authentication) {
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        Account account = accountDao.fetchOneByUsername(user.getUsername());
        Person person = personDao.fetchOneById(account.getPersonId());
        Address address = addressDao.fetchOneById(person.getAddressId());
        List<Contact> contacts = contactDao.fetchByPersonId(person.getId());

        return AccountDTO.builder()
                .id(account.getId())
                .username(account.getUsername())
                .lastLoginDate(account.getLastLoginDate())
                .status(account.getStatus())
                .person(PersonDTO.builder()
                        .contacts(contacts.stream().map(contact -> {return ContactDTO.builder()
                                .description(contact.getDescription())
                                .type(contact.getType())
                                .value(contact.getValue())
                                .regexp(contact.getRegexp())
                                .build();})
                                .collect(Collectors.toList()))
                        .address(AddressDTO.builder()
                                .buildingNumber(address.getBuildingNumber())
                                .postalCode(address.getPostalCode())
                                .city(address.getCity())
                                .premisesNumber(address.getPremisesNumber())
                                .streetName(address.getStreetName())
                                .id(address.getId())
                                .build())
                        .firstname(person.getFirstname())
                        .id(person.getId())
                        .idNumber(person.getIdNumber())
                        .jobPosition(person.getJobPosition())
                        .lastname(person.getLastname())
                        .pesel(person.getPesel())
                        .build())
                .build();
    }
}
