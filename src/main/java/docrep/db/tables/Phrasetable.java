/*
 * This file is generated by jOOQ.
*/
package docrep.db.tables;


import docrep.db.Public;
import docrep.db.tables.records.PhrasetableRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class Phrasetable extends TableImpl<PhrasetableRecord> {

    private static final long serialVersionUID = -1772392832;

    /**
     * The reference instance of <code>public.phrasetable</code>
     */
    public static final Phrasetable PHRASETABLE = new Phrasetable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PhrasetableRecord> getRecordType() {
        return PhrasetableRecord.class;
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    public final TableField<PhrasetableRecord, Object> PHRASE = createField("phrase", org.jooq.impl.DefaultDataType.getDefaultDataType("tsvector"), this, "");

    /**
     * Create a <code>public.phrasetable</code> table reference
     */
    public Phrasetable() {
        this(DSL.name("phrasetable"), null);
    }

    /**
     * Create an aliased <code>public.phrasetable</code> table reference
     */
    public Phrasetable(String alias) {
        this(DSL.name(alias), PHRASETABLE);
    }

    /**
     * Create an aliased <code>public.phrasetable</code> table reference
     */
    public Phrasetable(Name alias) {
        this(alias, PHRASETABLE);
    }

    private Phrasetable(Name alias, Table<PhrasetableRecord> aliased) {
        this(alias, aliased, null);
    }

    private Phrasetable(Name alias, Table<PhrasetableRecord> aliased, Field<?>[] parameters) {
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
    public Phrasetable as(String alias) {
        return new Phrasetable(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phrasetable as(Name alias) {
        return new Phrasetable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Phrasetable rename(String name) {
        return new Phrasetable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Phrasetable rename(Name name) {
        return new Phrasetable(name, null);
    }
}