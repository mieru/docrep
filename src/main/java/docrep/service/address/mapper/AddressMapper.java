package docrep.service.address.mapper;

import docrep.db.tables.pojos.Address;
import docrep.service.address.dto.AddressDTO;

public class AddressMapper {
    public static Address mapAddresDTOToAddres(AddressDTO addressDTO, Address address) {
        if(address==null) address = new Address();
        address.setId(addressDTO.getId() != null ? addressDTO.getId() : address.getId());
        address.setStreetName(addressDTO.getStreetName() != null ? addressDTO.getStreetName() : address.getStreetName());
        address.setBuildingNumber(addressDTO.getBuildingNumber() != null ? addressDTO.getBuildingNumber() : address.getBuildingNumber());
        address.setPremisesNumber(addressDTO.getPremisesNumber() != null ? addressDTO.getPremisesNumber() : address.getPremisesNumber());
        address.setPostalCode(addressDTO.getPostalCode() != null ? addressDTO.getPostalCode() : address.getPostalCode());
        address.setId(addressDTO.getId() != null ? addressDTO.getId() : address.getId());
        address.setCity(addressDTO.getCity() != null ? addressDTO.getCity() : address.getCity());
        return address;
    }
}
