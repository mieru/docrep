package docrep.service.document;

import docrep.component.ValidatorComponent;
import docrep.dao.document.DocumentDAO;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.document.dto.DocumentSearchDTO;
import docrep.service.document.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    ValidatorComponent validatorComponent;
    @Autowired
    DocumentDAO documentDAO;


    public Collection<DocumentDTO> searchDocuments(DocumentSearchDTO documentSearchDTO) throws Exception {
        validatorComponent.valid(documentSearchDTO);
        if (documentSearchDTO.areAllFieldsNull()) throw new Exception("All search fields are null.");
        return documentDAO.search(documentSearchDTO).stream()
                .map(DocumentMapper::mapDocumentToDocumentDTO)
                .collect(Collectors.toList());
    }
}
