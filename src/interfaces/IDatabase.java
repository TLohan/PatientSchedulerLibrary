/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import customDataStructures.DoubleLinkedList;
import entityClasses.HospitalEmployee;
import entityClasses.Patient;
import java.io.IOException;

/**
 *
 * @author TLohan
 */
public interface IDatabase {
    
    // Both methods return a Patient object. This object is removed from waiting list.
    public Patient getNextPatientForTriageNurse();
    public Patient getNextPatientForDoctor();
    
    // Adds patient to waiting lists
    public void addPatientToTriageNurseWaitingList(Patient patient);
    public void addPatientToDoctorWaitingList(Patient patient);
    
    // Return a DLL
    public DoubleLinkedList<Patient> getTriageNurseWaitingList();
    public DoubleLinkedList<Patient> getDoctorWaitingList();
    
    public HospitalEmployee getHospitalEmployee(int employee_id);
    public HospitalEmployee getHospitalEmployeeByUsername(String employee_username);
    
    //public int addToDB(String statement);
    
    public void savePatient(Patient patient);
    public void saveHospitalEmployee(HospitalEmployee hospitalEmployee);
    
    //public Object get(Class<?> objectClass, int primaryKey);
    
}
