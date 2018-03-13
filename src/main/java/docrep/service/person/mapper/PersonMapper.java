package docrep.service.person.mapper;

import docrep.db.tables.pojos.Person;
import docrep.service.person.dto.PersonDTO;

public class PersonMapper {
    public static Person mapPersonDTOToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setPesel(personDTO.getPesel());
        person.setJobPosition(personDTO.getJobPosition());
        person.setIdNumber(personDTO.getIdNumber());
        person.setLastname(personDTO.getLastname());
        person.setFirstname(personDTO.getFirstname());
        return person;
    }
}
