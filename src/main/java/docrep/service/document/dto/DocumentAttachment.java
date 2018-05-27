package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentAttachment {
   private String name;
    private String extension;
    private String size;
}
