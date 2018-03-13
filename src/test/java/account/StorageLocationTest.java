package account;

import docrep.component.ValidatorComponent;
import docrep.db.tables.daos.StorageLocationDao;
import docrep.db.tables.pojos.StorageLocation;
import docrep.service.storagelocation.StorageLocationService;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import docrep.service.storagelocation.dto.StorageLocationDTO;
import docrep.service.storagelocation.enums.StorageLocationType;
import docrep.service.storagelocation.mapper.StorageLocationMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class StorageLocationTest {

    @TestConfiguration
    static class AuthorizationTestContextConfiguration {

        @Bean
        public ValidatorComponent validatorComponent() {
            return new ValidatorComponent();
        }

        @Bean
        public StorageLocationService storageLocationService() {
            return new StorageLocationService();
        }

    }

    @Autowired
    StorageLocationService storageLocationService;

    @MockBean
    StorageLocationDao storageLocationDao;


    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenNameOrTypeIsNull() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder()
                .build();
        //  when
        storageLocationService.insertStorageLocation(storageLocationDTO);
        //  then
        // throw ValidationException
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenTypeIsEmployeeAndAccountIdisNull() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder()
                .name("Jan Kowalski")
                .accountId(null)
                .type(StorageLocationType.EMPLOYEE).build();
        //  when
        storageLocationService.insertStorageLocation(storageLocationDTO);
        //  then
        // throw Exception
    }

    @Test
    public void shouldNotThrowWhenStorageLocationDTOIsCorrect() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder()
                .name("Magazyn 1")
                .description("Magazyn główny")
                .latitude(51.5313)
                .longitude(20.0086)
                .type(StorageLocationType.WAREHOUSE).build();
        //  when
        storageLocationService.insertStorageLocation(storageLocationDTO);
        //  then
    }

    @Test
    public void shouldNotThrowWhenUpdateDTOIsCorrect() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder()
                .id(1)
                .name("Magazyn 1")
                .description("Magazyn główny")
                .latitude(51.5313)
                .longitude(20.0086)
                .type(StorageLocationType.WAREHOUSE).build();
        //  when
        storageLocationService.updateStorageLocation(storageLocationDTO);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenUpdateAndIdIsNull() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder().build();
        //  when
        storageLocationService.updateStorageLocation(storageLocationDTO);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowWhenDeleteAndIdIsNull() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder().build();
        //  when
        storageLocationService.deleteStorageLocation(storageLocationDTO.getId());
    }

    @Test
    public void shouldReturnFlatStructureWhenSuperiorIdIsNull() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder().id(1).build();
        StorageLocation storageLocation = new StorageLocation();
        storageLocation.setId(1);
        storageLocation.setName("TEST");
        storageLocation.setType(StorageLocationType.ROOM.toString());
        storageLocation.setSuperiorStorageLocId(null);
        when(storageLocationDao.fetchOneById(1)).thenReturn(storageLocation);
        //  when
        CompleteStorageLocationStructureDTO completeStorageLocationStructureDTO = storageLocationService.getCompleteStructureStorageLocationById(storageLocationDTO.getId());
        //then
        Assert.assertNotNull(completeStorageLocationStructureDTO);
        Assert.assertNull(completeStorageLocationStructureDTO.getSuperiorStorageLocation());
    }

    @Test
    public void shouldReturnCompleteStructureWhenSuperiorIdIsNotNull() throws Exception {
        //  given
        StorageLocationDTO storageLocationDTO = StorageLocationDTO.builder().id(1).build();
        StorageLocation storageLocation = new StorageLocation();
        storageLocation.setId(1);
        storageLocation.setName("TEST");
        storageLocation.setType(StorageLocationType.ROOM.toString());
        storageLocation.setSuperiorStorageLocId(2);
        when(storageLocationDao.fetchOneById(1)).thenReturn(storageLocation);
        StorageLocation storageLocation2 = new StorageLocation();
        storageLocation2.setId(1);
        storageLocation2.setName("TEST");
        storageLocation2.setType(StorageLocationType.ROOM.toString());
        storageLocation2.setSuperiorStorageLocId(null);
        when(storageLocationDao.fetchOneById(2)).thenReturn(storageLocation2);
        //  when
        CompleteStorageLocationStructureDTO completeStorageLocationStructureDTO = storageLocationService.getCompleteStructureStorageLocationById(storageLocationDTO.getId());
        //then
        Assert.assertNotNull(completeStorageLocationStructureDTO);
        Assert.assertNotNull(completeStorageLocationStructureDTO.getSuperiorStorageLocation());
    }

}