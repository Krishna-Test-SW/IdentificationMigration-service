package com.identityservice.model;

import com.identityservice.util.StringUtil;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)

//person class
public class Person {
    @XmlElement(name = "firstNames")
    private String firstNames;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "dateOfBirth")
    private String dateOfBirth;
    @XmlElement(name = "address")
    private Address address;
    @XmlElement(name = "yearsAtAddress")
    private int yearsAtAddress;
    @XmlElement(name = "passportNumber")
    private String passportNumber;
    @XmlElement(name = "nationalInsuranceNumber")
    private String nationalInsuranceNumber;

    // Getters and setters for all fields
    public String getFirstNames() {
        return firstNames;
    }
    public Person(String firstNames, String lastName, String dateOfBirth, Address address, int yearsAtAddress, String passportNumber, String nationalInsuranceNumber) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.yearsAtAddress = yearsAtAddress;
        this.passportNumber = passportNumber;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }
    public void setFirstNames(String firstName) {
        this.firstNames = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getYearsAtAddress() {
        return yearsAtAddress;
    }

    public void setYearsAtAddress(int yearsAtAddress) {
        this.yearsAtAddress = yearsAtAddress;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    @Override
    public String toString() {
        return "First Names: " + StringUtil.toTitleCase(firstNames) + 
               ", Last Name: " + StringUtil.toTitleCase(lastName) + 
               ", Date of Birth: " + dateOfBirth + 
               ", Address: " + (address != null ? 
                    "Line1: " + address.getComputedLine1() + ", Postcode: " + address.getPostcode() : "N/A") +
               ", Years at Address: " + yearsAtAddress + 
               ", Passport Number: " + (passportNumber != null ? passportNumber : "N/A") + 
               ", National Insurance Number: " + (nationalInsuranceNumber != null ? nationalInsuranceNumber : "N/A");
    }
         
}


