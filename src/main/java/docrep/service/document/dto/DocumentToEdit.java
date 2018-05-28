package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentToEdit {
    Integer id;
    String title;
    String number;
    Integer storageLocationId;
    String description;

    String opinion;
}
