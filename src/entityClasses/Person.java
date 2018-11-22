/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import ORM.ObjectMapper;
import interfaces.ForeignKey;
import interfaces.Ignore;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author TLohan
 */
public class Person extends ObjectMapper implements Serializable {
    @Ignore
    private final int FORENAME_MIN_LENGTH = 2;
    
    @Ignore
    private final int LASTNAME_MIN_LENGTH = 2;
    

    
    private Title title;
    private String forename;
    private String lastname;
    @ForeignKey(cls=Address.class, columnName = "address_id")
    private Address address;
    private String dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    
    
    public Person(){
          
    }
    
    public Person(ResultSet rs){
        super(rs);
    }
    
    public Person(String title, String forename, String lastname){
        setTitle(title);
        setForename(forename);
        setLastname(lastname);  
    }
    
        
    public void setTitle(String title){
        switch (title){
            case ("Mr."):
                this.title = Title.MR;
                break;
            case ("Mrs."):
                this.title = Title.MRS;
                break;
            case ("Ms."):
                this.title = Title.MS;
                break;
            case ("Dr."):
                this.title = Title.DR;
                break;
            case ("Prof."):
                this.title = Title.PROF;
                break;
            default:
                throw new IllegalArgumentException("Invalid value for title field: " + title);
        }
    }
    
    
    
    public String getTitle(){
       
        return title.getTitleString();
    }
    
    
    public void setForename(String forename){
        if (forename.length() > FORENAME_MIN_LENGTH){
            this.forename = forename;
        } else {
            throw new IllegalArgumentException(String.format("Illegal value for forename. Cannot be less than {} characters.", FORENAME_MIN_LENGTH));
        }
    }
    
    
    public String getForename(){
        return this.forename;
    }
    
    public void setLastname(String lastname){
        if (lastname.length() > LASTNAME_MIN_LENGTH){
            this.lastname = lastname;
        } else {
            throw new IllegalArgumentException(String.format("Illegal value for lastname. Cannot be less than {} characters.", LASTNAME_MIN_LENGTH));
        }
    }
    
    public String getLastname(){
        return this.lastname;
    }
    
     public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 5){
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Phone number must be over 5 digits.");
        }
    }
    
    
    
     public String getFullName(){
        return String.format("%s %s %s", getTitle(), getForename(), getLastname());
    }
    
}
