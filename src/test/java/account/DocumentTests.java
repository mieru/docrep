package account;

import docrep.component.ValidatorComponent;
import docrep.dao.document.DocumentDAO;
import docrep.db.tables.pojos.Document;
import docrep.service.document.DocumentService;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.document.dto.DocumentSearchDTO;
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
        when(documentDAO.search(documentSearchDTO)).thenReturn(Arrays.asList(document));
        //  when
        Collection<DocumentDTO> documents = documentService.searchDocuments(documentSearchDTO);
        //  then
        Assert.assertEquals(1, documents.size());
    }
}