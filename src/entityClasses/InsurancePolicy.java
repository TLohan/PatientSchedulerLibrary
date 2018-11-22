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
public class InsurancePolicy extends ObjectMapper implements Serializable{
    
    @PrimaryKey
    private int insurancePolicy_id;
    private String policyId;
    private String insuranceCompany;
    private String expiryDate;

    public InsurancePolicy(int insurancePolicy_id, String policyId, String insuranceCompany, String expiryDate) {
        this.insurancePolicy_id = insurancePolicy_id;
        this.policyId = policyId;
        this.insuranceCompany = insuranceCompany;
        this.expiryDate = expiryDate;
    }
    
    
    
    
    
    
    public InsurancePolicy(String policyId, String insuranceCompany, String expiryDate) {
        setPolicyId(policyId);
        setInsuranceCompany(insuranceCompany);
        setExpiryDate(expiryDate);
    }

    public InsurancePolicy() {
        
    }
    
    public InsurancePolicy(ResultSet rs){
        super(rs);
    }

    public int getInsurancePolicy_id() {
        return insurancePolicy_id;
    }

    public void setInsurancePolicy_id(int insurancePolicy_id) {
        this.insurancePolicy_id = insurancePolicy_id;
    }
    
    
    
    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
