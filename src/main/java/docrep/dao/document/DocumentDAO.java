package docrep.dao.document;

import docrep.db.tables.daos.DocumentDao;
import docrep.db.tables.pojos.Document;
import docrep.service.document.dto.DocumentSearchDTO;
import org.jooq.Configuration;

import java.util.List;

import static docrep.db.tables.Document.DOCUMENT;

public class DocumentDAO extends DocumentDao {

    public DocumentDAO(Configuration configuration) {
        super(configuration);
    }

    public List<Document> search(DocumentSearchDTO documentSearchDTO) {
        return super.configuration().dsl()
                .selectFrom(DOCUMENT)
                .where(documentSearchDTO.getBarcode() != null ? DOCUMENT.BARCODE.eq(documentSearchDTO.getBarcode()) : null
                        ,documentSearchDTO.getTitle() != null ? DOCUMENT.TITLE.eq(documentSearchDTO.getTitle()) : null
                        ,documentSearchDTO.getDescription() != null ? DOCUMENT.DESCRIPTION.eq(documentSearchDTO.getDescription()) : null
                        ,documentSearchDTO.getOwnerId() != null ? DOCUMENT.OWNER_ID.eq(documentSearchDTO.getOwnerId()) : null
                        ,documentSearchDTO.getNumber() != null ? DOCUMENT.NUMBER.eq(documentSearchDTO.getNumber()) : null)
                .fetch()
                .map(mapper());
    }
}
