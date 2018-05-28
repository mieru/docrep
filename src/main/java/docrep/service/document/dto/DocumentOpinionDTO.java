package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class DocumentOpinionDTO {
    Integer id;
    String content;
    String user;
    Timestamp addDate;
}
