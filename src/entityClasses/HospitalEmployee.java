/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import interfaces.PrimaryKey;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author TLohan
 */
public class HospitalEmployee extends Person implements Serializable{
    
    
    @PrimaryKey
    public int hospitalemployee_id;
    private String employee_id;
    private Department department;
    private String password;
    private int accessLevel;
    
    public HospitalEmployee(){
       
    }
    
    
    public HospitalEmployee(ResultSet rs){
        super(rs);
    }
    
    public HospitalEmployee(String title, String fname, String sname, String hospitalId, String password){
        super(title, fname, sname);
        setEmployee_id(hospitalId);
        setPassword(password);
    }

    public HospitalEmployee(int hospitalEmployee_id, String hospital_id, Department department, String password, int accessLevel, String title, String forename, String lastname) {
        super(title, forename, lastname);
        this.hospitalemployee_id = hospitalEmployee_id;
        this.employee_id = hospital_id;
        this.department = department;
        this.password = password;
        this.accessLevel = accessLevel;
    }
    
    public int getHospitalemployee_id() {
        return hospitalemployee_id;
    }

    public void setHospitalemployee_id(int hospitalemployee_id) {
        this.hospitalemployee_id = hospitalemployee_id;
    }

    
    
    
    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(String jobTitle) {
        switch (jobTitle.toLowerCase()){
            case ("receptionist"):
                this.department= Department.RECEPTION;
                break;
            case ("triage nurse"):
                this.department = Department.TRIAGE_NURSE;
                break;
            case ("doctor"):
                this.department = Department.DOCTOR;
                break;
            default:
                System.out.println("Invalid job title.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    
    
}
