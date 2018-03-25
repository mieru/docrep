package docrep.service.document.dto;

import docrep.db.tables.pojos.Person;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DocumentDTO {
    private Integer id;
    private String number;
    private String title;
    private String barcode;
    private Date registerDate;
    private CompleteStorageLocationStructureDTO storageLocation;
    private Person lastModifier;
    private String description;
    private Integer version;
    private Date editDate;
}
