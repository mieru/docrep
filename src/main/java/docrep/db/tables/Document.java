/*
 * This file is generated by jOOQ.
*/
package docrep.db.tables;


import docrep.db.Indexes;
import docrep.db.Keys;
import docrep.db.Public;
import docrep.db.tables.records.DocumentRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.DateAsTimestampBinding;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Document extends TableImpl<DocumentRecord> {

    private static final long serialVersionUID = -1746349582;

    /**
     * The reference instance of <code>public.document</code>
     */
    public static final Document DOCUMENT = new Document();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DocumentRecord> getRecordType() {
        return DocumentRecord.class;
    }

    /**
     * The column <code>public.document.id</code>.
     */
    public final TableField<DocumentRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('document_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.document.number</code>.
     */
    public final TableField<DocumentRecord, String> NUMBER = createField("number", org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>public.document.title</code>.
     */
    public final TableField<DocumentRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(150), this, "");

    /**
     * The column <code>public.document.barcode</code>.
     */
    public final TableField<DocumentRecord, String> BARCODE = createField("barcode", org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>public.document.register_date</code>.
     */
    public final TableField<DocumentRecord, Timestamp> REGISTER_DATE = createField("register_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "", new DateAsTimestampBinding());

    /**
     * The column <code>public.document.storage_location_id</code>.
     */
    public final TableField<DocumentRecord, Integer> STORAGE_LOCATION_ID = createField("storage_location_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.document.owner_id</code>.
     */
    public final TableField<DocumentRecord, Integer> OWNER_ID = createField("owner_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.document.description</code>.
     */
    public final TableField<DocumentRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.document.version</code>.
     */
    public final TableField<DocumentRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.document.edit_date</code>.
     */
    public final TableField<DocumentRecord, Timestamp> EDIT_DATE = createField("edit_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new DateAsTimestampBinding());

    /**
     * Create a <code>public.document</code> table reference
     */
    public Document() {
        this(DSL.name("document"), null);
    }

    /**
     * Create an aliased <code>public.document</code> table reference
     */
    public Document(String alias) {
        this(DSL.name(alias), DOCUMENT);
    }

    /**
     * Create an aliased <code>public.document</code> table reference
     */
    public Document(Name alias) {
        this(alias, DOCUMENT);
    }

    private Document(Name alias, Table<DocumentRecord> aliased) {
        this(alias, aliased, null);
    }

    private Document(Name alias, Table<DocumentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DOCUMENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DocumentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DOCUMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DocumentRecord> getPrimaryKey() {
        return Keys.DOCUMENT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DocumentRecord>> getKeys() {
        return Arrays.<UniqueKey<DocumentRecord>>asList(Keys.DOCUMENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<DocumentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DocumentRecord, ?>>asList(Keys.DOCUMENT__OWNER_ID_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document as(String alias) {
        return new Document(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document as(Name alias) {
        return new Document(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Document rename(String name) {
        return new Document(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Document rename(Name name) {
        return new Document(name, null);
    }
}
