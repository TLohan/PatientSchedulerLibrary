/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import interfaces.ForeignKey;
import interfaces.IPriority;
import interfaces.PrimaryKey;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author TLohan
 */
public class Patient extends Person implements Serializable, IPriority {
    
    @PrimaryKey
    private int patient_id;

 
    private int priority;
    private String PPSNumber; 
    private PatientStatus status;
    
    @ForeignKey(cls=NextOfKin.class, columnName = "nextOfKin_id")
    private NextOfKin nextOfKin;
    
    @ForeignKey(cls=InsurancePolicy.class, columnName = "insurancePolicy_id")
    private InsurancePolicy insurancePolicy;
    private String summaryOnAdmittance;
    private String nursesReport;
    private String doctorsReport;
    
    @ForeignKey(cls=VitalSigns.class, columnName = "vitalSigns_id")
    private VitalSigns vitalSigns;
    private String timeOfArrival;  // should be DateTime
    private String timeOfDischarge; // should be DateTime
    
    public Patient(){
          
    }
    
    public Patient(ResultSet rs){
        super(rs);
    }
    
    public Patient(String title, String forename, String lastname){
        super(title, forename, lastname);
        setStatus(PatientStatus.WAITING_FOR_TRIAGE_NURSE);
    }
    
    
    public Patient(int patient_id, String title, String forename, String lastname){
        super(title, forename, lastname);
        setPatient_id(patient_id);
        setStatus(PatientStatus.WAITING_FOR_TRIAGE_NURSE);
    }
    
    
    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    
    
    
    public String getPPSNumber() {
        return PPSNumber;
    }

    public void setPPSNumber(String PPSNumber) {
        this.PPSNumber = PPSNumber;
    }
    
    public NextOfKin getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(NextOfKin nextOfKin) {
        this.nextOfKin = nextOfKin;
    }
    
    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getSummaryOnAdmittance() {
        return summaryOnAdmittance;
    }

    public void setSummaryOnAdmittance(String summaryOnAdmittance) {
        this.summaryOnAdmittance = summaryOnAdmittance;
    }

    public String getNursesReport() {
        return nursesReport;
    }

    public void setNursesReport(String nursesReport) {
        this.nursesReport = nursesReport;
    }

    public String getDoctorsReport() {
        return doctorsReport;
    }

    public void setDoctorsReport(String doctorsReport) {
        this.doctorsReport = doctorsReport;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getTimeOfDischarge() {
        return timeOfDischarge;
    }

    public void setTimeOfDischarge(String timeOfDischarge) {
        this.timeOfDischarge = timeOfDischarge;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    
    
    
}
