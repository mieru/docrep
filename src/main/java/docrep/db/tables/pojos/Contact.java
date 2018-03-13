/*
 * This file is generated by jOOQ.
*/
package docrep.db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class Contact implements Serializable {

    private static final long serialVersionUID = 1587842402;

    private Integer id;
    private String  value;
    private String  regexp;
    private String  description;
    private String  type;
    private Integer personId;

    public Contact() {}

    public Contact(Contact value) {
        this.id = value.id;
        this.value = value.value;
        this.regexp = value.regexp;
        this.description = value.description;
        this.type = value.type;
        this.personId = value.personId;
    }

    public Contact(
        Integer id,
        String  value,
        String  regexp,
        String  description,
        String  type,
        Integer personId
    ) {
        this.id = id;
        this.value = value;
        this.regexp = regexp;
        this.description = description;
        this.type = type;
        this.personId = personId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegexp() {
        return this.regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Contact (");

        sb.append(id);
        sb.append(", ").append(value);
        sb.append(", ").append(regexp);
        sb.append(", ").append(description);
        sb.append(", ").append(type);
        sb.append(", ").append(personId);

        sb.append(")");
        return sb.toString();
    }
}
