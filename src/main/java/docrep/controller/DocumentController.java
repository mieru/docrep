package docrep.controller;

import docrep.service.document.DocumentService;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.document.dto.DocumentSearchDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Log4j
@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/document/search", method = RequestMethod.POST)
    public Collection<DocumentDTO> searchDocuments(@RequestBody DocumentSearchDTO documentSearchDTO) throws Exception {
        return documentService.searchDocuments(documentSearchDTO);
    }

    @RequestMapping(value = "/document/clipboard", method = RequestMethod.POST)
    public void addToClipboard(Authentication authentication , @RequestBody DocumentDTO documentDTO) throws Exception {
         documentService.addToClipboard(authentication,documentDTO);
    }

    @RequestMapping(value = "/document/take", method = RequestMethod.POST)
    public void takeDocument(Authentication authentication , @RequestBody DocumentDTO documentDTO) throws Exception {
        documentService.takeDocument(authentication,documentDTO);
    }



}
