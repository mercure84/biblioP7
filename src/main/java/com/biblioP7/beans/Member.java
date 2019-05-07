package com.biblioP7.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="firstname")
    private String firstName;

    @Column(name="email")
    private String email;

    @Column(name="encryptedpassword")
    private String encryptedPassword;

    @Column(name="dateinscription")
    private Date dateInscription;

    @Column(name="cellphonenb")
    private String cellPhoneNb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getCellPhoneNb() {
        return cellPhoneNb;
    }

    public void setCellPhoneNb(String cellPhoneNb) {
        this.cellPhoneNb = cellPhoneNb;
    }
}
