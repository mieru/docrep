/*
 * This file is generated by jOOQ.
*/
package docrep.db.tables.records;


import docrep.db.tables.Phrasetable;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.TableRecordImpl;


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
public class PhrasetableRecord extends TableRecordImpl<PhrasetableRecord> implements Record1<Object> {

    private static final long serialVersionUID = 1011533283;

    /**
     * Setter for <code>public.phrasetable.phrase</code>.
     */

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    public void setPhrase(Object value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.phrasetable.phrase</code>.
     */

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    public Object getPhrase() {
        return (Object) get(0);
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<Object> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<Object> valuesRow() {
        return (Row1) super.valuesRow();
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    @Override
    public Field<Object> field1() {
        return Phrasetable.PHRASETABLE.PHRASE;
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    @Override
    public Object component1() {
        return getPhrase();
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    @Override
    public Object value1() {
        return getPhrase();
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    @Override
    public PhrasetableRecord value1(Object value) {
        setPhrase(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhrasetableRecord values(Object value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PhrasetableRecord
     */
    public PhrasetableRecord() {
        super(Phrasetable.PHRASETABLE);
    }

    /**
     * Create a detached, initialised PhrasetableRecord
     */
    public PhrasetableRecord(Object phrase) {
        super(Phrasetable.PHRASETABLE);

        set(0, phrase);
    }
}
