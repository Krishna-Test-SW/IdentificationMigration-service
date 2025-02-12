package com.identityservice.validator;

import com.identityservice.model.Person;
import com.identityservice.model.Address;
import com.identityservice.exception.ValidationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Validator {
	private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s'\\-]+$");
	private static final Pattern ID_PATTERN = Pattern.compile("[A-Z0-9]+");

	public static void validate(Person person) throws ValidationException {
		// Validating first name length should not be more than 45
		if (person.getFirstNames().length() > 45) {
			throw new ValidationException("First name cannot be more than 45 characters.");
		}

		// Validating first name format
		if (!NAME_PATTERN.matcher(person.getFirstNames()).matches()) {
			throw new ValidationException(
					"Invalid first name format. It should only contain alphabetic characters, apostrophes, hyphens, and spaces.");
		}

		// Validating last name length should not be more than 45
		if (person.getLastName().length() > 45) {
			throw new ValidationException("Last name cannot be more than 45 characters.");
		}

		// Validating last name format
		if (!NAME_PATTERN.matcher(person.getLastName()).matches()) {
			throw new ValidationException(
					"Invalid last name format. It should only contain alphabetic characters, apostrophes, hyphens, and spaces.");
		}

		// Validating age
		if (!isValidAge(person.getDateOfBirth())) {
			throw new ValidationException("Person must be at least 18 years old.");
		}

		// Validating address
		if (!isValidAddress(person)) {
			throw new ValidationException("Invalid address format.");
		}

		// Validating years at address
		if (person.getYearsAtAddress() < 5) {
			throw new ValidationException("Person must have lived at their address for at least 5 years.");
		}

		// If nationalInsuranceNumber is not there, passportNumber must be provided
		if (person.getPassportNumber() == null && person.getNationalInsuranceNumber() == null) {
			throw new ValidationException("Either passportNumber or nationalInsuranceNumber must be provided.");
		}

		// Case 2: Passport number format check (if provided)
		if (person.getPassportNumber() != null && !ID_PATTERN.matcher(person.getPassportNumber()).matches()) {
			throw new ValidationException("Passport number must be uppercase.");
		}

//        // Case 3: National Insurance number  check (if provided)
//        if (person.getNationalInsuranceNumber() != null ) {
//            throw new ValidationException("National Insurance number must b.");
//        }
	}

	// to validate age
	private static boolean isValidAge(String dob) {
		LocalDate birthDate = LocalDate.parse(dob);
		return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
	}

	private static boolean isValidAddress(Person person) {
		Address address = person.getAddress();

		if (address == null) {
			return false; // Address must not be null
		}

		// Checking if both line1 and postcode are already present
		// Validating if both line1 and postcode are provided
		if (address.getLine1() != null && !address.getLine1().isEmpty() && address.getPostcode() != null
				&& !address.getPostcode().isEmpty()) {
			return true;
		}

		// If line1 is missing, constructing it from buildingNumber, streetName, and
		// town
		if (address.getBuildingNumber() != null || address.getStreetName() != null || address.getTown() != null) {
			// Construct line1 by joining buildingNumber, streetName, and town with
			// whitespace
			address.setLine1(address.getBuildingNumber() + " " + address.getStreetName() + " " + address.getTown());
		}

		// Now checking if line1 and postcode are both present
		return address.getLine1() != null && !address.getLine1().isEmpty() && address.getPostcode() != null
				&& !address.getPostcode().isEmpty();
	}
}
