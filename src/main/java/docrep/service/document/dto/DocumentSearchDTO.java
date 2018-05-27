package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class DocumentSearchDTO {
    private String number;
    private String title;
    private String barcode;
    private String description;
    private Date searchDateFrom;
    private Date searchDateTo;
    private Integer ownerId;
    private Integer holderUserId;


    public boolean areAllFieldsNull() {
            return this.getBarcode() == null
                && this.getDescription() == null
                && this.getHolderUserId() == null
                && this.getNumber() == null
                && this.getOwnerId() == null
                && this.getTitle() == null
                && this.getSearchDateFrom() == null
                && this.getSearchDateTo() == null;
    }

}
