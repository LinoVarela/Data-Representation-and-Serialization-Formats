package com.xmlvsjson;

import java.io.IOException;
import java.util.List;
import jakarta.xml.bind.JAXBException;
import classes.School;

public class XmlvsJson {

    public static void main(String[] args) throws IOException, JAXBException {
        String basePath = "files/";

        String[] fileNames = {
            "schoolData_10.txt",
            "schoolData_100.txt",
            "schoolData_500.txt",
            "schoolData_1000.txt",
            "schoolData_2500.txt",
            "schoolData_5000.txt",
            "schoolData_7500.txt",
            "schoolData_10000.txt",
            "schoolData_15000.txt"
        };

        // Iterate through the files and process each one
        for (String fileName : fileNames) {
            System.out.println("\nProcessing file: " + fileName);

            // Read school and student data from the text file
            List<School> schools = Help_Functions.readSchoolDataFromTextFile(basePath + fileName);

            // Initialize the timers for XML and JSON
            long totalXmlSerializationTime = 0;
            long totalXmlDeserializationTime = 0;
            long totalJsonSerializationTime = 0;
            long totalJsonDeserializationTime = 0;

            // JSON: Serialize and deserialize the entire list of schools
            totalJsonSerializationTime = JsonFunctions.serializeJSON(schools);
            totalJsonDeserializationTime = JsonFunctions.deserializeJSON();

            // XML: Serialize and deserialize the entire list of schools
            totalXmlSerializationTime = XmlFunctions.serializeXML(schools);
            totalXmlDeserializationTime = XmlFunctions.deserializeXML();

            // Output the times for the file
            System.out.println("Total JSON Serialization Time: " + totalJsonSerializationTime + " ns");
            System.out.println("Total JSON Deserialization Time: " + totalJsonDeserializationTime + " ns");
            System.out.println("Total XML Serialization Time: " + totalXmlSerializationTime + " ns");
            System.out.println("Total XML Deserialization Time: " + totalXmlDeserializationTime + " ns");
        }
    }
}
