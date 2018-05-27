package docrep.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import docrep.service.document.DocumentService;
import docrep.service.document.dto.DocumentAttachment;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.document.dto.DocumentSearchDTO;
import docrep.service.document.dto.DocumentToAdd;
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

    @RequestMapping(value = "/api/document/search", method = RequestMethod.POST)
    public Collection<DocumentDTO> searchDocuments(@RequestBody DocumentSearchDTO documentSearchDTO) throws Exception {
        return documentService.searchDocuments(documentSearchDTO);
    }

    @RequestMapping(value = "/api/document/search/all", method = RequestMethod.POST)
    public Collection<DocumentDTO> searchAll(@RequestBody DocumentSearchDTO documentSearchDTO) throws Exception {
        return documentService.searchAll(documentSearchDTO);
    }

    @RequestMapping(value = "/api/document/search/fuzzy/{searchPhrase}", method = RequestMethod.GET)
    public Collection<DocumentDTO> searchAll(@PathVariable String searchPhrase) throws Exception {
        return documentService.searchFuzzy(searchPhrase);
    }

    @RequestMapping(value = "/api/document/clipboard", method = RequestMethod.POST)
    public void addToClipboard(Authentication authentication, @RequestBody DocumentDTO documentDTO) throws Exception {
        documentService.addToClipboard(authentication, documentDTO);
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
            return paths
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        return DocumentAttachment.builder()
                                .name(path.getFileName().toString())
                                .build();
                    }).collect(Collectors.toList());
        }


    }

    @RequestMapping(value = "/api/document/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(Authentication authentication, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "documentToadd", required = false) String formValues) throws IllegalStateException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        DocumentToAdd documentToAdd = mapper.readValue(formValues, DocumentToAdd.class);
        Integer documentId = documentService.addDocument(authentication, documentToAdd);
        final Path path = Paths.get("C:\\Users\\Czarek\\IdeaProjects\\mgr\\docrep-files\\" + documentId);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            Files.delete(path);
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
