package com.identityservice.parser;

import com.identityservice.model.Person;
import com.identityservice.util.LoggerUtil;
import com.identityservice.model.Address;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
	public static List<Person> parse(String filePath) throws Exception {
		List<Person> people = new ArrayList<>();
		File xmlFile = new File(filePath);

		// Initialize the DocumentBuilderFactory and DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		// Normalize the XML file structure
		document.getDocumentElement().normalize();

		// Get all <person> elements
		NodeList nodeList = document.getElementsByTagName("person");

		// Iterate through all <person>
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				// Extract details for the person
				String firstNames = getTagValue("firstNames", element);
				String lastName = getTagValue("lastName", element);
				String dateOfBirth = getTagValue("dateOfBirth", element);
				String buildingNumber = getTagValue("buildingNumber", element);
				String streetName = getTagValue("streetName", element);
				String town = getTagValue("town", element);
				String postcode = getTagValue("postcode", element);
				String line1 = getTagValue("line1", element); // Check if line1 is directly provided
				String yearsAtAddressStr = getTagValue("yearsAtAddress", element, "0"); // Default to 0 if not found
				String passportNumber = getTagValue("passportNumber", element);
				String nationalInsuranceNumber = getTagValue("nationalInsuranceNumber", element);

				// Ensure line1 is constructed from buildingNumber, streetName, and town if not
				// provided
				if (line1 == null && (buildingNumber != null || streetName != null || town != null)) {
					line1 = buildingNumber + " " + streetName + " " + town;
				}

				// Construct Address object
				Address address = new Address();
				address.setLine1(line1);
				// May be null, validator will check later
				address.setPostcode(postcode);

				// Construct Person object with extracted data
				Person person = new Person(firstNames, lastName, dateOfBirth, address,
						Integer.parseInt(yearsAtAddressStr), passportNumber, nationalInsuranceNumber);

				// Add the person to the list
				people.add(person);
				LoggerUtil.LOGGER.info("person " +person );

			}
		}
		return people;
	}

	//  function to get the value of a tag from XML
	private static String getTagValue(String tag, Element element) {
		return getTagValue(tag, element, null);
	}

	// Overloaded helper function to provide default value for missing tag
	private static String getTagValue(String tag, Element element, String defaultValue) {
		NodeList nodeList = element.getElementsByTagName(tag);
		if (nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent().trim();
		}
		return defaultValue; 
	}
}
