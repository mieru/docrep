package docrep.controller;

import docrep.service.storagelocation.StorageLocationService;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import docrep.service.storagelocation.dto.StorageLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StorageLocationController {

    @Autowired
    StorageLocationService storageLocationService;

    @RequestMapping(value = "/storagelocation/", method = RequestMethod.POST)
    public Integer addStorageLocation(@RequestBody StorageLocationDTO storageLocationDTO) throws Exception {
        return storageLocationService.insertStorageLocation(storageLocationDTO);
    }

    @RequestMapping(value = "/storagelocation/", method = RequestMethod.PUT)
    public StorageLocationDTO updateStorageLocation(@RequestBody StorageLocationDTO storageLocationDTO) throws Exception {
        return storageLocationService.updateStorageLocation(storageLocationDTO);
    }

    @RequestMapping(value = "/storagelocation/{storageLocationId}/", method = RequestMethod.GET)
    public StorageLocationDTO getStorageLocation(@PathVariable  Integer storageLocationId) throws Exception {
        return storageLocationService.getStorageLocationById(storageLocationId);
    }

    @RequestMapping(value = "/storagelocation/all", method = RequestMethod.GET)
    public List<StorageLocationDTO> getAllStorageLocations() throws Exception {
        return storageLocationService.getAllStorageLocations();
    }

    @RequestMapping(value = "/storagelocation/{storageLocationId}/", method = RequestMethod.DELETE)
    public void checkLoginData(@PathVariable  Integer storageLocationId) throws Exception {
        storageLocationService.deleteStorageLocation(storageLocationId);
    }

    @RequestMapping(value = "/storagelocation/{storageLocationId}/complete", method = RequestMethod.GET)
    public CompleteStorageLocationStructureDTO getCompleteStructureStorageLocationById(@PathVariable  Integer storageLocationId) throws Exception {
      return storageLocationService.getCompleteStructureStorageLocationById(storageLocationId);
    }
}
