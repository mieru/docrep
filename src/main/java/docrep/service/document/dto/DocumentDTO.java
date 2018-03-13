package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentDTO {
    private Integer id;
    private String number;
    private String title;
    private String barcode;
    private LocalDateTime registerDate;
    private Integer storageLocationId;
    private Integer ownerId;
    private String description;
    private Integer version;
    private LocalDateTime editDate;
}
