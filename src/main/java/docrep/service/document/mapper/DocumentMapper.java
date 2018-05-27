package docrep.service.document.mapper;

import docrep.db.tables.pojos.Document;
import docrep.db.tables.pojos.Person;
import docrep.db.tables.pojos.StorageLocation;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;

import java.util.Date;

public class DocumentMapper {




    public static DocumentDTO mapDocumentToDocumentDTO(Document document, Person person, CompleteStorageLocationStructureDTO storageLocation) {

        return DocumentDTO.builder()
                .id(document.getId())
                .lastModifier(person)
                .barcode(document.getBarcode())
                .description(document.getDescription())
                .number(document.getNumber())
                .title(document.getTitle())
                .creator(person)
                .editDate(document.getEditDate())
                .registerDate(document.getRegisterDate())
                .version(document.getVersion())
                .storageLocation(storageLocation)
                .build();
    }
}
