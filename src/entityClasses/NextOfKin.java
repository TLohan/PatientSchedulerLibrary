/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import ORM.ObjectMapper;
import interfaces.PrimaryKey;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author TLohan
 */
public class NextOfKin extends ObjectMapper implements Serializable {
    
    @PrimaryKey
    private int nextOfKin_id;
    private String fullName;
    private String phoneNumber;
    private  String relationshipToPatient;
    
    public NextOfKin(){
        
    }
    
    public NextOfKin(ResultSet rs){
        super(rs);
    }
    
    
    public NextOfKin(String fullName, String phoneNumber, String relationshipToPatient){
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setRelationshipToPatient(relationshipToPatient);
    }

    
    public NextOfKin(int nextOfKin_id, String fullName, String phoneNumber, String relationshipToPatient){
        setNextOfKin_id(nextOfKin_id);
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setRelationshipToPatient(relationshipToPatient);
    }
    
    public int getNextOfKin_id() {
        return nextOfKin_id;
    }

    public void setNextOfKin_id(int nextOfKin_id) {
        this.nextOfKin_id = nextOfKin_id;
    }
    
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRelationshipToPatient() {
        return relationshipToPatient;
    }

    public void setRelationshipToPatient(String relationshipToPatient) {
        this.relationshipToPatient = relationshipToPatient;
    }
    
 
}
