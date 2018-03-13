package docrep.service.authorization;

import docrep.db.tables.daos.AddressDao;
import docrep.db.tables.daos.ContactDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.pojos.Account;
import docrep.db.tables.daos.AccountDao;
import docrep.component.ValidatorComponent;
import docrep.db.tables.pojos.Address;
import docrep.db.tables.pojos.Person;
import docrep.service.address.mapper.AddressMapper;
import docrep.service.authorization.dto.AuthAccountDTO;
import docrep.service.authorization.dto.AccountDTO;
import docrep.service.authorization.mapper.AccountMapper;
import docrep.service.contact.dto.ContactDTO;
import docrep.service.contact.mapper.ContactMapper;
import docrep.service.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthorizationService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    ContactDao contactDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ValidatorComponent validatorComponent;

    public boolean checkLoginDataAndGenerateToken(AuthAccountDTO authAccountDTO) throws Exception {
        try {
            validatorComponent.valid(authAccountDTO);
            Account account = accountDao.fetchOneByUsername(authAccountDTO.getUsername());
            return account.getStatus().equals("ACTIVE") ? bCryptPasswordEncoder.matches(authAccountDTO.getPassword(), account.getPassword()) : false;
        } catch (ValidationException e) {
            return false;
        }
    }

    @Transactional
    public Integer registerNewAccount(AccountDTO accountDTO) throws Exception {
        validatorComponent.valid(accountDTO);
        Integer addressId = addAddress(accountDTO);
        Integer personId = addPerson(accountDTO, addressId);
        addContactsForPerson(accountDTO.getPerson().getContacts(), personId);
        return addAccount(accountDTO, personId);
    }

    private void addContactsForPerson(List<ContactDTO> contacts, Integer personId) {
        if (contacts == null || contacts.size() == 0) return;
        contactDao.insert(contacts.stream()
                .map(ContactMapper::mapContactDTOToContact)
                .peek(contact -> contact.setPersonId(personId)).collect(Collectors.toList()));
    }

    private Integer addAccount(AccountDTO accountDTO, Integer personId) {
        Account account = AccountMapper.mapAccountDTOToAccount(accountDTO);
        account.setPersonId(personId);
        account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        account.setStatus("ACTIVE");
        accountDao.insert(account);
        return account.getId();
    }

    private Integer addPerson(AccountDTO accountDTO, Integer addressId) {
        Person person = PersonMapper.mapPersonDTOToPerson(accountDTO.getPerson());
        person.setAddressId(addressId);
        personDao.insert(person);
        return person.getId();
    }

    private Integer addAddress(AccountDTO accountDTO) {
        Address address = AddressMapper.mapAddresDTOToAddres(accountDTO.getPerson().getAddress());
        addressDao.insert(address);
        return address.getId();
    }
}
