package account;

import docrep.component.ValidatorComponent;
import docrep.dao.document.DocumentDAO;
import docrep.db.tables.daos.AccountDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.pojos.Account;
import docrep.db.tables.pojos.Document;
import docrep.db.tables.pojos.Person;
import docrep.service.document.DocumentService;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.document.dto.DocumentSearchDTO;
import docrep.service.storagelocation.StorageLocationService;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class DocumentTests {
    @TestConfiguration
    static class AuthorizationTestContextConfiguration {

        @Bean
        public ValidatorComponent validatorComponent() {
            return new ValidatorComponent();
        }

        @Bean
        public DocumentService documentService() {
            return new DocumentService();
        }

    }

    @Autowired
    DocumentService documentService;

    @MockBean
    DocumentDAO documentDAO;
    @MockBean
    AccountDao accountDao;
    @MockBean
    PersonDao personDao;
    @MockBean
    StorageLocationService storageLocationService;


    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenAllSearchFieldsAreNull() throws Exception {
        //  given
        DocumentSearchDTO documentSearchDTO = DocumentSearchDTO.builder().build();
        //  when
        documentService.searchDocuments(documentSearchDTO);
        //  then
        //  throw Exception
    }

    @Test()
    public void shouldReturnEmptyCollectionWhenDocumentsNotFound() throws Exception {
        //  given
        DocumentSearchDTO documentSearchDTO = DocumentSearchDTO.builder()
                .number("737/02/2018/XWL")
                .build();
        //  when
        Collection<DocumentDTO> documents = documentService.searchDocuments(documentSearchDTO);
        //  then
        Assert.assertNotNull(documents);
    }

    @Test
    public void shouldReturnCollectionWithDocumentDTOWhenDocumentWasFound() throws Exception {
        //  given
        DocumentSearchDTO documentSearchDTO = DocumentSearchDTO.builder()
                .barcode("45676543453")
                .build();
        Document document = new Document();
        Account account = new Account();
        Person person = new Person();
        CompleteStorageLocationStructureDTO storageLocation = CompleteStorageLocationStructureDTO.builder().build();

        when(accountDao.fetchOneById(document.getOwnerId())).thenReturn(account);
        when(personDao.fetchOneById(account.getPersonId())).thenReturn(person);
        when(storageLocationService.getCompleteStructureStorageLocationById(document.getStorageLocationId())).thenReturn(storageLocation);
        when(documentDAO.search(documentSearchDTO)).thenReturn(Arrays.asList(document));
        //  when
        Collection<DocumentDTO> documents = documentService.searchDocuments(documentSearchDTO);
        //  then
        Assert.assertEquals(1, documents.size());
    }
}