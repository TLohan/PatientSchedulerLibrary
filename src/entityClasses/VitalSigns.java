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
public class VitalSigns extends ObjectMapper implements Serializable {
    
    @PrimaryKey
    private int vitalSigns_id;
    
    
    private double systolicBP;
    private double diastolicBP;
    private double weightInKg;
    private double heightInCm;
    private double temperatureInCelcius;
    private double pulseInBPM;
    
    
    public VitalSigns(){
        
    }
    
    public VitalSigns(ResultSet rs){
        super(rs);
    }


    public VitalSigns(int vitalSigns_id, double systolicBP, double diatolicBP, double weightInKg, double heightInCm, double temperatureInCelcius, double pulseInBPM) {
        this.vitalSigns_id = vitalSigns_id;
        this.systolicBP = systolicBP;
        this.diastolicBP = diatolicBP;
        this.weightInKg = weightInKg;
        this.heightInCm = heightInCm;
        this.temperatureInCelcius = temperatureInCelcius;
        this.pulseInBPM = pulseInBPM;
    }
    
    
    
    
    public int getVitalSigns_id() {
        return vitalSigns_id;
    }

    public void setVitalSigns_id(int vitalSigns_id) {
        this.vitalSigns_id = vitalSigns_id;
    }

    public double getSystolicBP() {
        return systolicBP;
    }

    public void setSystolicBP(double systolicBP) {
        this.systolicBP = systolicBP;
    }

    public double getDiastolicBP() {
        return diastolicBP;
    }

    public void setDiastolicBP(double diastolicBP) {
        this.diastolicBP = diastolicBP;
    }
    
    

    

    public double getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(double weightInKg) {
        this.weightInKg = weightInKg;
    }

    public double getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(double heightInCm) {
        this.heightInCm = heightInCm;
    }

    public double getTemperatureInCelcius() {
        return temperatureInCelcius;
    }

    public void setTemperatureInCelcius(double temperatureInCelcius) {
        this.temperatureInCelcius = temperatureInCelcius;
    }

    public double getPulseInBPM() {
        return pulseInBPM;
    }

    public void setPulseInBPM(double pulseInBPM) {
        this.pulseInBPM = pulseInBPM;
    }

    @Override
    public String toString() {
        String report = String.format("Blood Pressure:\t%s over %s\nWeight:\t%s kg\nHeight:\t%s cm\n\tTemperature:\t%s Celcius\nPulse:\t%s BPM\n",
                getSystolicBP(), getDiastolicBP(), getWeightInKg(), getHeightInCm(), getTemperatureInCelcius(), getPulseInBPM());
        return report;
    }

    public void setBloodPressure(BloodPressure bloodPressure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
