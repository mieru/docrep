package docrep.service.document.mapper;

import docrep.db.tables.pojos.Document;
import docrep.service.document.dto.DocumentDTO;

public class DocumentMapper {
    public static DocumentDTO mapDocumentToDocumentDTO(Document document) {
        return DocumentDTO.builder()
                .id(document.getId())
                .ownerId(document.getOwnerId())
                .barcode(document.getBarcode())
                .description(document.getDescription())
                .number(document.getNumber())
                .title(document.getTitle())
                .editDate(document.getEditDate() != null ? document.getEditDate().toLocalDateTime() : null)
                .registerDate(document.getRegisterDate() != null ? document.getRegisterDate().toLocalDateTime() : null)
                .version(document.getVersion())
                .storageLocationId(document.getStorageLocationId())
                .build();
    }
}
