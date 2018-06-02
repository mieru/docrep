package docrep.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import docrep.service.document.DocumentOpinionService;
import docrep.service.document.DocumentService;
import docrep.service.document.dto.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j
@RestController
@RequestMapping("/")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentOpinionService documentOpinionService;

    @RequestMapping(value = "/api/document/search", method = RequestMethod.POST)
    public Collection<DocumentDTO> searchDocuments(@RequestBody DocumentSearchDTO documentSearchDTO) throws Exception {
        return documentService.searchDocuments(documentSearchDTO);
    }

    @RequestMapping(value = "/api/document/{documentId}/opinions", method = RequestMethod.GET)
    public Collection<DocumentOpinionDTO> getDocumentOpinionsByDocumentID(@PathVariable Integer documentId) throws Exception {
        return documentOpinionService.getOpinionByDocumentId(documentId);
    }

    @RequestMapping(value = "/api/document/search/all", method = RequestMethod.POST)
    public Collection<DocumentDTO> searchAll(@RequestBody DocumentSearchDTO documentSearchDTO) throws Exception {
        return documentService.searchAll(documentSearchDTO);
    }

    @RequestMapping(value = "/api/document/search/fuzzy/{searchPhrase}", method = RequestMethod.GET)
    public Collection<DocumentDTO> searchAll(@PathVariable String searchPhrase) throws Exception {
        return documentService.searchFuzzy(searchPhrase);
    }

    @RequestMapping(value = "/api/document/forLoggedAccount", method = RequestMethod.GET)
    public Collection<DocumentDTO> findDocumentByAccountIdAsStorageLocation(Authentication authentication) throws Exception {
        return documentService.findForAccount(authentication);
    }

    @RequestMapping(value = "/api/document/clipboard", method = RequestMethod.POST)
    public void addToClipboard(Authentication authentication, @RequestBody DocumentDTO documentDTO) throws Exception {
        documentService.addToClipboard(authentication, documentDTO);
    }

    @RequestMapping(value = "/api/document/clipboard/{documentId}", method = RequestMethod.DELETE)
    public void addToClipboard(Authentication authentication, @PathVariable Integer documentId) throws Exception {
        documentService.deleteFromClipboard(authentication, documentId);
    }

    @RequestMapping(value = "/api/document/clipboard", method = RequestMethod.GET)
    public Collection<DocumentDTO> getAllFromClipboard(Authentication authentication) throws Exception {
       return documentService.getAllFromClipboard(authentication);
    }

    @RequestMapping(value = "/api/document/take", method = RequestMethod.POST)
    public void takeDocument(Authentication authentication, @RequestBody DocumentDTO documentDTO) throws Exception {
        documentService.takeDocument(authentication, documentDTO);
    }

    @RequestMapping(value = "/api/document/gen-barcode", method = RequestMethod.GET)
    public String genBarcode() throws Exception {
        return documentService.genBarcode();
    }

    @RequestMapping(value = "/api/document/attachments/{documentId}", method = RequestMethod.GET)
    public Collection<DocumentAttachment> getAttachments(@PathVariable Integer documentId) throws Exception {
        try (Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\Czarek\\IdeaProjects\\mgr\\docrep-files\\" + documentId))) {
            return paths.filter(Files::isRegularFile)
                    .map(path -> {
                        return DocumentAttachment.builder()
                                .name(path.getFileName().toString())
                                .build();
                    }).collect(Collectors.toList());
        }


    }

    @RequestMapping(value = "/api/document/{documentId}", method = RequestMethod.DELETE)
    public void addDocument(@PathVariable Integer documentId) throws IllegalStateException, IOException {
       documentService.deleteDocument(documentId);
    }


    @RequestMapping(value = "/api/document/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addDocument(Authentication authentication, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "documentToadd", required = false) String formValues) throws IllegalStateException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        DocumentToAdd documentToAdd = mapper.readValue(formValues, DocumentToAdd.class);
        Integer documentId = documentService.addDocument(authentication, documentToAdd);
        final Path path = Paths.get("C:\\Users\\Czarek\\IdeaProjects\\mgr\\docrep-files\\" + documentId);

        if(file != null){
            try {
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
            } catch (Exception e) {
                Files.delete(path);
            }
        }

    }

    @RequestMapping(value = "/api/document/", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void editDocument(Authentication authentication, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "documentToEdit", required = false) String formValues) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        DocumentToEdit documentToEdit = mapper.readValue(formValues, DocumentToEdit.class);

        documentService.update(authentication, documentToEdit);
        if(documentToEdit.getOpinion() != null)
        documentOpinionService.addOpinion(authentication,documentToEdit);
        final Path path = Paths.get("C:\\Users\\Czarek\\IdeaProjects\\mgr\\docrep-files\\" + documentToEdit.getId());

      if(file != null){
          try {
              if (!Files.exists(path)) {
                  Files.createDirectories(path);
              }
              Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
          } catch (Exception e) {
              Files.delete(path);
          }
      }


    }


    @RequestMapping("/file/{documentId}/{filename}/")
    public void downloadPDFResource(HttpServletResponse response, @PathVariable Integer documentId, @PathVariable String filename) {
        Path file = Paths.get("C:\\Users\\Czarek\\IdeaProjects\\mgr\\docrep-files\\"+documentId+"\\", filename);
        if (Files.exists(file)) {
            response.setContentType("application/vnd.oasis.opendocument.text");
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
