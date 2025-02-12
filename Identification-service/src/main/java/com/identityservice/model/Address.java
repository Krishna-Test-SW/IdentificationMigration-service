package com.identityservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
//address class
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement(name = "line1")
    private String line1;
    @XmlElement(name = "postcode")
    private String postcode;
    @XmlElement(name = "buildingNumber")
    private String buildingNumber;
    @XmlElement(name = "streetName")
    private String streetName;
    @XmlElement(name = "town")
    private String town;

    // Getters and setters
    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getComputedLine1() {
        if (line1 == null || line1.isEmpty()) {
            if (buildingNumber != null || streetName != null || town != null) {
                return buildingNumber + " " + streetName + " " + town;
            }
        }
        return line1; 
    }

    @Override
    public String toString() {
        return "Line1: " + (getComputedLine1() != null ? getComputedLine1() : "N/A") +
               ", Postcode: " + (postcode != null ? postcode : "N/A") +
               ", Building Number: " + (buildingNumber != null ? buildingNumber : "N/A") +
               ", Street Name: " + (streetName != null ? streetName : "N/A") +
               ", Town: " + (town != null ? town : "N/A");
    }
    
}
