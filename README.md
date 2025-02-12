# Identification Service

## Overview
The Identity Service is a Java-based application that processes identity records stored in an XML file. It performs data validation and extraction, then:

- Saves valid records to an output file.
- Stores invalid records separately for further review.
- Maintains a log file to track processing details.

## Features
- Parses identity records from an XML file using JAXB.
- Processes and validates identity records.
- Stores valid and invalid records in separate text files.
- Uses SLF4J with Logback for structured logging.
- Built using Maven, ensuring easy dependency management.

### Prerequisites
- Java 11 or later installed on your system.
- A java development environment like Eclipse.
- Maven 4.0.0

### Clone the Repository
- git clone https://github.com/Krishna-Test-SW/IdentificationMigration-service.git
- cd IdentificationMigration-service

### Running the Project
- mvn exec:java
  or
- To run the Identity Service from Eclipse:
  Open the project in Eclipse.
  Navigate to IdentityServiceMain.java (located in src/main/java).
  Right-click on the file and select "Run As" → "Java Application".

### Configuration
Modify the file paths in IdentityServiceMain.java if needed:

- filePath = "src/main/resources/records.xml";
- outputFilePath = "src/main/resources/valid_records.txt";
- invalidOutputFilePath = "src/main/resources/invalid_records.txt";

### Usage

The application reads an XML file (records.xml), processes its records, and saves:
- Valid records → valid_records.txt
- Invalid records → invalid_records.txt
- Logging → application.log


Example XML File:
<person>
      <firstNames>hee</firstNames>
      <lastName>beatty</lastName>
      <dateOfBirth>####</dateOfBirth>
      <address>
        <buildingNumber>####</buildingNumber>
        <streetName>Elbert Mill</streetName>
        <town>Nicolaschester</town>
      </address>
      <yearsAtAddress type="integer">15</yearsAtAddress>
      <passportNumber>#####</passportNumber>
    </person>
