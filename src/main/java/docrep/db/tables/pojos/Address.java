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
public class Address implements Serializable {

    private static final long serialVersionUID = 2137928323;

    private Integer id;
    private String  streetName;
    private String  buildingNumber;
    private String  premisesNumber;
    private String  postalCode;
    private String  city;

    public Address() {}

    public Address(Address value) {
        this.id = value.id;
        this.streetName = value.streetName;
        this.buildingNumber = value.buildingNumber;
        this.premisesNumber = value.premisesNumber;
        this.postalCode = value.postalCode;
        this.city = value.city;
    }

    public Address(
        Integer id,
        String  streetName,
        String  buildingNumber,
        String  premisesNumber,
        String  postalCode,
        String  city
    ) {
        this.id = id;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.premisesNumber = premisesNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return this.buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPremisesNumber() {
        return this.premisesNumber;
    }

    public void setPremisesNumber(String premisesNumber) {
        this.premisesNumber = premisesNumber;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Address (");

        sb.append(id);
        sb.append(", ").append(streetName);
        sb.append(", ").append(buildingNumber);
        sb.append(", ").append(premisesNumber);
        sb.append(", ").append(postalCode);
        sb.append(", ").append(city);

        sb.append(")");
        return sb.toString();
    }
}
