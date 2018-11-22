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
public class BloodPressure implements Serializable {
        
    private double systolicPressure;
    private double diastolicPressure;

    public BloodPressure(double systolicPressure, double diastolicPressure) {
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
    }

    public double getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(double systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public double getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(double diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    @Override
    public String toString() {
        return String.format("%s / %s", getSystolicPressure(), getDiastolicPressure());
    }
    
    
}
