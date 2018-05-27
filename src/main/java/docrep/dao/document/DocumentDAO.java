package docrep.dao.document;

import docrep.db.tables.daos.DocumentDao;
import docrep.db.tables.pojos.Document;
import docrep.service.document.dto.DocumentSearchDTO;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import static docrep.db.tables.Document.DOCUMENT;

public class DocumentDAO extends DocumentDao {

    public DocumentDAO(Configuration configuration) {
        super(configuration);
    }

    public List<Document> search(DocumentSearchDTO documentSearchDTO) {
        return super.configuration().dsl()
                .selectFrom(DOCUMENT)
                .where(documentSearchDTO.getBarcode() != null ? DOCUMENT.BARCODE.eq(documentSearchDTO.getBarcode()) : DSL.trueCondition()
                        ,documentSearchDTO.getTitle() != null ? DOCUMENT.TITLE.eq(documentSearchDTO.getTitle()) : DSL.trueCondition()
                        ,documentSearchDTO.getDescription() != null ? DOCUMENT.DESCRIPTION.eq(documentSearchDTO.getDescription()) : DSL.trueCondition()
                        ,documentSearchDTO.getOwnerId() != null ? DOCUMENT.OWNER_ID.eq(documentSearchDTO.getOwnerId()) : DSL.trueCondition()
                        ,documentSearchDTO.getSearchDateFrom() != null ? DOCUMENT.REGISTER_DATE.ge(new Timestamp(documentSearchDTO.getSearchDateFrom().getTime())) : DSL.trueCondition()
                        ,documentSearchDTO.getSearchDateTo() != null ? DOCUMENT.REGISTER_DATE.le(new Timestamp(documentSearchDTO.getSearchDateTo().getTime())) : DSL.trueCondition()
                        ,documentSearchDTO.getNumber() != null ? DOCUMENT.NUMBER.eq(documentSearchDTO.getNumber()) : DSL.trueCondition())
                .fetch()
                .map(mapper());
    }

    public List<Document> searchFuzzy(String searchPhrase) {
        return super.configuration().dsl()
                .selectFrom(DOCUMENT)
                .where("? <% TITLE OR ? <% DESCRIPTION ", searchPhrase, searchPhrase)
                .fetch()
                .map(mapper());
    }

    public Integer maxDocumentId() {
       return super.configuration().dsl()
                .select(DOCUMENT.ID.max())
                .from(DOCUMENT)
                .fetchOne().value1();
    }
}
