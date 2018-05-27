package docrep.service.document.dto;

import docrep.db.tables.Account;
import docrep.db.tables.pojos.Person;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class DocumentDTO {
    private Integer id;
    private String number;
    private String title;
    private String barcode;
    private Timestamp registerDate;
    private CompleteStorageLocationStructureDTO storageLocation;
    private Person lastModifier;
    private Person creator;
    private String description;
    private Integer version;
    private Timestamp editDate;
}
