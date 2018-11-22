/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import java.io.Serializable;

/**
 *
 * @author TLohan
 */
public enum Department implements Serializable{
    
    RECEPTION ("Receptionist"),
    TRIAGE_NURSE ("Triage Nurse"),
    DOCTOR ("Doctor")
    ;
    
    
    private String jobTitle;
    
    private Department(String jobTitle){
        this.jobTitle = jobTitle;
    }
    
    public String getJobTitle(){
        return this.jobTitle;
    }
}
