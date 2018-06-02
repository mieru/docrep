package docrep.service.contact.mapper;

import docrep.db.tables.pojos.Contact;
import docrep.service.contact.dto.ContactDTO;

public class ContactMapper {
    public static Contact mapContactDTOToContact(ContactDTO contactDTO,Contact contact) {
        if(contact==null) contact = new Contact();
        if(contactDTO.getId() != null)
            contact.setId(contactDTO.getId());
        contact.setValue(contactDTO.getValue());
        contact.setType(contactDTO.getType());
        contact.setDescription(contactDTO.getDescription());
        contact.setRegexp(contactDTO.getRegexp());
        contact.setPersonId(contactDTO.getPersonId() != null ? contactDTO.getPersonId() : contact.getPersonId());
        return contact;
    }
}
