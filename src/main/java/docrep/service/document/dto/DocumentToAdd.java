package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentToAdd {
    String title;
    String number;
    String barcode;
    String description;

}
