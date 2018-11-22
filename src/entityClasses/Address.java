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





public class Address extends ObjectMapper implements Serializable {
    
    
    @PrimaryKey
    private int address_id;
    
    private String identifier;
    private String buildingName;
    private String streetName;
    private String townName;
    private String cityName;
    private String countyName;
    private String countryName;
    
    public Address(){
        
    }
    
    public Address(ResultSet rs){
        
    }

    public Address(int address_id, String identifier, String buildingName, String streetName, String townName, String cityName, String countyName, String countryName) {
        this.address_id = address_id;
        this.identifier = identifier;
        this.buildingName = buildingName;
        this.streetName = streetName;
        this.townName = townName;
        this.cityName = cityName;
        this.countyName = countyName;
        this.countryName = countryName;
    }
    
    
    
    
    

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
}
