package docrep.service.address.mapper;

import docrep.db.tables.pojos.Address;
import docrep.service.address.dto.AddressDTO;

public class AddressMapper {
    public static Address mapAddresDTOToAddres(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreetName(addressDTO.getStreetName());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        address.setPremisesNumber(addressDTO.getPremisesNumber());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        return address;
    }
}
