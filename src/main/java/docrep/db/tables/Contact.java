/*
 * This file is generated by jOOQ.
*/
package docrep.db.tables;


import docrep.db.Indexes;
import docrep.db.Keys;
import docrep.db.Public;
import docrep.db.tables.records.ContactRecord;

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
public class Contact extends TableImpl<ContactRecord> {

    private static final long serialVersionUID = -2002521196;

    /**
     * The reference instance of <code>public.contact</code>
     */
    public static final Contact CONTACT = new Contact();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContactRecord> getRecordType() {
        return ContactRecord.class;
    }

    /**
     * The column <code>public.contact.id</code>.
     */
    public final TableField<ContactRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('contact_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.contact.value</code>.
     */
    public final TableField<ContactRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.VARCHAR(60), this, "");

    /**
     * The column <code>public.contact.regexp</code>.
     */
    public final TableField<ContactRecord, String> REGEXP = createField("regexp", org.jooq.impl.SQLDataType.VARCHAR(150), this, "");

    /**
     * The column <code>public.contact.description</code>.
     */
    public final TableField<ContactRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.contact.type</code>.
     */
    public final TableField<ContactRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(10), this, "");

    /**
     * The column <code>public.contact.person_id</code>.
     */
    public final TableField<ContactRecord, Integer> PERSON_ID = createField("person_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>public.contact</code> table reference
     */
    public Contact() {
        this(DSL.name("contact"), null);
    }

    /**
     * Create an aliased <code>public.contact</code> table reference
     */
    public Contact(String alias) {
        this(DSL.name(alias), CONTACT);
    }

    /**
     * Create an aliased <code>public.contact</code> table reference
     */
    public Contact(Name alias) {
        this(alias, CONTACT);
    }

    private Contact(Name alias, Table<ContactRecord> aliased) {
        this(alias, aliased, null);
    }

    private Contact(Name alias, Table<ContactRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONTACT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ContactRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CONTACT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ContactRecord> getPrimaryKey() {
        return Keys.CONTACT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ContactRecord>> getKeys() {
        return Arrays.<UniqueKey<ContactRecord>>asList(Keys.CONTACT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ContactRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ContactRecord, ?>>asList(Keys.CONTACT__ID_PERSON_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact as(String alias) {
        return new Contact(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact as(Name alias) {
        return new Contact(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Contact rename(String name) {
        return new Contact(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Contact rename(Name name) {
        return new Contact(name, null);
    }
}