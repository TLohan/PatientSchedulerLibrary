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
public enum PatientStatus implements Serializable{
    
    WAITING_FOR_TRIAGE_NURSE,
    WITH_TRIAGE_NURSE,
    WAITING_FOR_DOCTOR,
    WITH_DOCTOR,
    DISCHARGED,
    ;
    
    
    
    private PatientStatus(){
        
    }
    
    
}
