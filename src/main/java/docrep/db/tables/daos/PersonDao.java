/*
 * This file is generated by jOOQ.
*/
package docrep.db.tables.daos;


import docrep.db.tables.Person;
import docrep.db.tables.records.PersonRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class PersonDao extends DAOImpl<PersonRecord, docrep.db.tables.pojos.Person, Integer> {

    /**
     * Create a new PersonDao without any configuration
     */
    public PersonDao() {
        super(Person.PERSON, docrep.db.tables.pojos.Person.class);
    }

    /**
     * Create a new PersonDao with an attached configuration
     */
    public PersonDao(Configuration configuration) {
        super(Person.PERSON, docrep.db.tables.pojos.Person.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(docrep.db.tables.pojos.Person object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchById(Integer... values) {
        return fetch(Person.PERSON.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public docrep.db.tables.pojos.Person fetchOneById(Integer value) {
        return fetchOne(Person.PERSON.ID, value);
    }

    /**
     * Fetch records that have <code>firstname IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchByFirstname(String... values) {
        return fetch(Person.PERSON.FIRSTNAME, values);
    }

    /**
     * Fetch records that have <code>lastname IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchByLastname(String... values) {
        return fetch(Person.PERSON.LASTNAME, values);
    }

    /**
     * Fetch records that have <code>pesel IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchByPesel(String... values) {
        return fetch(Person.PERSON.PESEL, values);
    }

    /**
     * Fetch records that have <code>ID_number IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchByIdNumber(String... values) {
        return fetch(Person.PERSON.ID_NUMBER, values);
    }

    /**
     * Fetch records that have <code>address_id IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchByAddressId(Integer... values) {
        return fetch(Person.PERSON.ADDRESS_ID, values);
    }

    /**
     * Fetch records that have <code>job_position IN (values)</code>
     */
    public List<docrep.db.tables.pojos.Person> fetchByJobPosition(String... values) {
        return fetch(Person.PERSON.JOB_POSITION, values);
    }
}
