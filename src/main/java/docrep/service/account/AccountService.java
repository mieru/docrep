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
import docrep.service.account.dto.ChangePasswordDTO;
import docrep.service.address.dto.AddressDTO;
import docrep.service.address.mapper.AddressMapper;
import docrep.service.authorization.dto.AccountDTO;
import docrep.service.authorization.mapper.AccountMapper;
import docrep.service.contact.dto.ContactDTO;
import docrep.service.contact.mapper.ContactMapper;
import docrep.service.person.dto.PersonDTO;
import docrep.service.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public AccountDTO getAccountInfo(Authentication authentication) {
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        Account account = accountDao.fetchOneByUsername(user.getUsername());
        Person person = personDao.fetchOneById(account.getPersonId());
        Address address = addressDao.fetchOneById(person.getAddressId());
        List<Contact> contacts = contactDao.fetchByPersonId(person.getId());

        return AccountDTO.builder()
                .id(account.getId())
                .username(account.getUsername())
                .lastLoginDate(account.getLastLoginDate() != null ? account.getLastLoginDate().toLocaleString() : "")
                .status(account.getStatus())
                .person(PersonDTO.builder()
                        .contacts(contacts.stream().map(contact -> {
                            return ContactDTO.builder()
                                    .description(contact.getDescription())
                                    .type(contact.getType())
                                    .value(contact.getValue())
                                    .regexp(contact.getRegexp())
                                    .personId(contact.getPersonId())
                                    .build();
                        })
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

    public List<AccountDTO> getAllAccount() {
        return accountDao.findAll().stream()
                .map(account -> {
                    Person person = personDao.fetchOneById(account.getPersonId());
                    Address address = addressDao.fetchOneById(person.getAddressId());
                    List<Contact> contacts = contactDao.fetchByPersonId(person.getId());
                    return AccountDTO.builder()
                            .id(account.getId())
                            .username(account.getUsername())
                            .lastLoginDate(account.getLastLoginDate() != null ? account.getLastLoginDate().toLocaleString() : "")
                            .status(account.getStatus())
                            .person(PersonDTO.builder()
                                    .contacts(contacts.stream().map(contact -> {
                                        return ContactDTO.builder()
                                                .description(contact.getDescription())
                                                .type(contact.getType())
                                                .value(contact.getValue())
                                                .regexp(contact.getRegexp())
                                                .personId(contact.getPersonId())
                                                .build();
                                    })
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
                }).collect(Collectors.toList());
    }

    @Transactional
    public void update(AccountDTO accountDTO) {
        Account account = accountDao.fetchOneByUsername(accountDTO.getUsername());
        Person person = personDao.fetchOneById(account.getPersonId());
        Address address = addressDao.fetchOneById(person.getAddressId());


        addressDao.update(AddressMapper.mapAddresDTOToAddres(accountDTO.getPerson().getAddress(), address));
        personDao.update(PersonMapper.mapPersonDTOToPerson(accountDTO.getPerson(), person));
        accountDTO.getPerson().getContacts().stream().forEach(contactDTO -> {
            contactDao.update(ContactMapper.mapContactDTOToContact(contactDTO, contactDao.fetchOneById(contactDTO.getId())));
        });
        if (accountDTO.getPassword() != null)
            accountDTO.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        accountDao.update(AccountMapper.mapAccountDTOToAccount(accountDTO, account));
    }

    @Transactional
    public void delete(AccountDTO accountDTO) {
        addressDao.deleteById(accountDTO.getPerson().getAddress().getId());
        accountDTO.getPerson().getContacts().stream().forEach(contactDTO -> {
            contactDao.deleteById(contactDTO.getId());
        });
        personDao.deleteById(accountDTO.getPerson().getId());
        accountDao.deleteById(accountDTO.getId());
    }

    @Transactional
    public void add(AccountDTO accountDTO) {
        accountDTO.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        Address address = new Address();
        addressDao.insert(address);

        Person person = new Person();
        person.setAddressId(address.getId());
        personDao.insert(person);

        Account account = AccountMapper.mapAccountDTOToAccount(accountDTO, null);

        account.setStatus("ACTIVE");
        account.setPersonId(person.getId());
        accountDao.insert(account);
    }
    @Transactional
    public void changePassword(ChangePasswordDTO changePasswordDTO, Authentication authentication) {
        changePasswordDTO.setNewPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        Account account = accountDao.fetchOneByUsername(user.getUsername());
        account.setPassword(changePasswordDTO.getNewPassword());
        accountDao.update(account);
    }
}
