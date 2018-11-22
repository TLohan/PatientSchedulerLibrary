/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

/**
 *
 * @author TLohan
 */
public enum ValidServerRequest {
    SAVE_PATIENT,
    SAVE_HOSPITAL_EMPLOYEE,
    GET_HOSPITAL_EMPLOYEE, 
    GET_HOSPITAL_EMPLOYEE_BY_USERNAME,
    GET_NEXT_PATIENT_FOR_TRIAGE_NURSE,
    GET_NEXT_PATIENT_FOR_DOCTOR,
    ADD_PATIENT_TO_TRIAGE_NURSE_WAITING_LIST,
    ADD_PATIENT_TO_DOCTOR_WAITING_LIST,
    GET_TRIAGE_NURSE_WAITING_LIST,
    GET_DOCTOR_WAITING_LIST;
}
