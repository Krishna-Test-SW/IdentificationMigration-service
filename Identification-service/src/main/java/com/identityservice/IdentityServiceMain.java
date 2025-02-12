package com.identityservice;

import com.identityservice.model.Person;
import com.identityservice.parser.XMLParser;
import com.identityservice.service.IdentityService;
import com.identityservice.service.IdentityServiceImpl;
import com.identityservice.util.LoggerUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IdentityServiceMain {
    public static void main(String[] args) {
        LoggerUtil.LOGGER.info("Starting Identity Service...");

        //getting xml filepath
        String filePath = "src/main/resources/records.xml";
        String outputFilePath = "src/main/resources/valid_records.txt";
        String invalidOutputFilePath = "src/main/resources/invalid_records.txt";
        IdentityService service = new IdentityServiceImpl();

        try {
            List<Person> people = XMLParser.parse(filePath);
            service.process(people, outputFilePath, invalidOutputFilePath);
        } catch (Exception e) {
            LoggerUtil.LOGGER.error("Error processing the file: ", e);
        }
    }
}