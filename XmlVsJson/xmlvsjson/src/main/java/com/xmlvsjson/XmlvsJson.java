package com.xmlvsjson;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

public class XmlvsJson {

    public static void main(String[] args) throws IOException, JAXBException {
        String basePath = "files/";

        // File names for different datasets
        String[] fileNames = {
            "schoolData_10.txt",   // Data for 10 students
            "schoolData_100.txt",  // Data for 100 students
            "schoolData_1000.txt"  // Data for 1000 students
        };

        // Iterate through the files and process each one
        for (String fileName : fileNames) {
            System.out.println("\nProcessing file: " + fileName);
            
            // Read school and student data from the text file
            List<School> schools = Functions.readSchoolDataFromTextFile(basePath + fileName);

            // Measure the total time for XML serialization and deserialization
            long xmlTotalTime = 0;
            long jsonTotalTime = 0;

            // Process each school in the file
            for (School school : schools) {
                // Serialize and deserialize using XML and JSON
                xmlTotalTime += serializeDeserializeXML(school);
                jsonTotalTime += serializeDeserializeJSON(school);
            }

            // Output total times for the file
            System.out.println("Total XML Serialization/Deserialization Time: " + xmlTotalTime + " nanoseconds for file: " + fileName);
            System.out.println("Total JSON Serialization/Deserialization Time: " + jsonTotalTime + " nanoseconds for file: " + fileName);
        }
    }

    // XML Serialization/Deserialization with EclipseLink MOXy
    private static long serializeDeserializeXML(School school) {
        JAXBContext jaxbContext = null;
        try {
            // Create JAXB context using EclipseLink MOXy for the School class
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{School.class}, null);

            // Create the marshaller for serialization
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Measure serialization time
            long startTime = System.nanoTime();
            File xmlFile = new File(school.getname() + ".xml");
            marshaller.marshal(school, xmlFile);
            long endSerializationTime = System.nanoTime();

            // XML Deserialization
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            School deserializedSchool = (School) unmarshaller.unmarshal(xmlFile);
            long endDeserializationTime = System.nanoTime();

            // Total time for serialization and deserialization
            return (endSerializationTime - startTime) + (endDeserializationTime - endSerializationTime);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return -1; // Return error code if any exception occurs
    }

    // JSON Serialization/Deserialization
    private static long serializeDeserializeJSON(School school) throws IOException {
        long startTime = System.nanoTime();

        // JSON serialization
        JSONObject jsonObject = new JSONObject(school);
        JSONArray jsonArray = new JSONArray(school.getList());
        jsonObject.put("students", jsonArray);

        File jsonFile = new File(school.getname() + ".json");
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            fileWriter.write(jsonObject.toString());
        }

        // Measure JSON deserialization time
        long endSerialization = System.nanoTime();

        // JSON deserialization using Gson
        try (BufferedReader jsonReader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = jsonReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

            startTime = System.nanoTime();
            Gson gson = new GsonBuilder().create();
            School deserializedSchool = gson.fromJson(jsonStringBuilder.toString(), School.class);
            long endTime = System.nanoTime();

            return endTime - endSerialization; // Return deserialization time
        }
    }
}
