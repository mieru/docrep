package docrep.service.document.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DocumentSearchDTO {
    private String number;
    private String title;
    private String barcode;
    private String description;
    private Date searchDateFrom;
    private Integer storageLocationId;
    private Date searchDateTo;
    private Integer ownerId;
    private Integer holderUserId;


    public boolean areAllFieldsNull() {
        return this.getBarcode() == null
                && this.getDescription() == null
                && this.getHolderUserId() == null
                && this.getNumber() == null
                && this.getStorageLocationId() == null
                && this.getOwnerId() == null
                && this.getTitle() == null
                && this.getSearchDateFrom() == null
                && this.getSearchDateTo() == null;
    }

}
