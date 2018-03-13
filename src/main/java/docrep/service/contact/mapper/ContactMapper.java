package docrep.service.contact.mapper;

import docrep.db.tables.pojos.Contact;
import docrep.service.contact.dto.ContactDTO;

public class ContactMapper {
    public static Contact mapContactDTOToContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setValue(contactDTO.getValue());
        contact.setType(contactDTO.getType());
        contact.setDescription(contactDTO.getDescription());
        contact.setRegexp(contactDTO.getRegexp());
        return contact;
    }
}
