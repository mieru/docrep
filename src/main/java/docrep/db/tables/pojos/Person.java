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
public class Person implements Serializable {

    private static final long serialVersionUID = 1278223756;

    private Integer id;
    private String  firstname;
    private String  lastname;
    private String  pesel;
    private String  idNumber;
    private Integer addressId;
    private String  jobPosition;

    public Person() {}

    public Person(Person value) {
        this.id = value.id;
        this.firstname = value.firstname;
        this.lastname = value.lastname;
        this.pesel = value.pesel;
        this.idNumber = value.idNumber;
        this.addressId = value.addressId;
        this.jobPosition = value.jobPosition;
    }

    public Person(
        Integer id,
        String  firstname,
        String  lastname,
        String  pesel,
        String  idNumber,
        Integer addressId,
        String  jobPosition
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.addressId = addressId;
        this.jobPosition = jobPosition;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return this.pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getJobPosition() {
        return this.jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person (");

        sb.append(id);
        sb.append(", ").append(firstname);
        sb.append(", ").append(lastname);
        sb.append(", ").append(pesel);
        sb.append(", ").append(idNumber);
        sb.append(", ").append(addressId);
        sb.append(", ").append(jobPosition);

        sb.append(")");
        return sb.toString();
    }
}
