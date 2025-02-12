package com.identityservice.service;

import com.identityservice.model.Person;
import com.identityservice.util.LoggerUtil;
import com.identityservice.validator.Validator;
import com.identityservice.exception.ValidationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IdentityServiceImpl implements IdentityService {
	@Override
	public void process(List<Person> people, String validOutputFile, String invalidOutputFile) throws IOException {
		int validCount = 0, invalidCount = 0, totalCount = 0;
		int noPassportNoNIN = 0, livedLessThanFiveYears = 0, moreThanTwoWords = 0;

		try (BufferedWriter validWriter = new BufferedWriter(new FileWriter(validOutputFile));
				BufferedWriter invalidWriter = new BufferedWriter(new FileWriter(invalidOutputFile))) {

			for (Person person : people) {
				totalCount++;
				List<String> validationErrors = new ArrayList<>();

				try {
					Validator.validate(person);
				} catch (ValidationException e) {
					validationErrors.add(e.getMessage());
				}

				// Metrics calculation
				if (person.getPassportNumber() == null && person.getNationalInsuranceNumber() == null) {
					noPassportNoNIN++;
					validationErrors.add("No passport or national insurance number.");
				}

				if (person.getYearsAtAddress() < 5) {
					livedLessThanFiveYears++;
					validationErrors.add("Lived at address for less than 5 years.");
				}

				// First name word count 
				if (person.getFirstNames().trim().split("\\s+").length > 2) {
					moreThanTwoWords++;
					validationErrors.add("More than two words in first name.");
				}

				// Process Valid or Invalid Records
				if (validationErrors.isEmpty()) {
					validCount++;
					validWriter.write(person.toString() + "\n");
				} else {
					invalidCount++;
					invalidWriter.write(person.getFirstNames() + " " + person.getLastName() + " - "
							+ String.join(", ", validationErrors) + "\n");
				}
			}

			
			LoggerUtil.LOGGER.info("Total Records Processed: " + totalCount);
			LoggerUtil.LOGGER.info("Valid Records count: " + validCount);
			LoggerUtil.LOGGER.info("Invalid Records count: " + invalidCount);
			LoggerUtil.LOGGER.info("People with No Passport & No NI: " + noPassportNoNIN);
			LoggerUtil.LOGGER.info("People who have lived at their address for less than five years: " + livedLessThanFiveYears);
			LoggerUtil.LOGGER.info("People with More Than Two Words in Their First Name: " + moreThanTwoWords);
			LoggerUtil.LOGGER.info("Valid Records saved to: " + validOutputFile);
			LoggerUtil.LOGGER.info("Invalid Records saved to: " + invalidOutputFile);
		}
	}
}
