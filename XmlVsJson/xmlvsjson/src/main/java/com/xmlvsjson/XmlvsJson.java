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
        // Read school and student data from Excel using the helper function
        List<School> schools = Functions.readSchoolDataFromTextFile("schoolData.xlsx");

        for (School school : schools) {
            // Serialize and deserialize using XML and JSON
            long xmlTime = serializeDeserializeXML(school);
            long jsonTime = serializeDeserializeJSON(school);

            System.out.println("XML Serialization/Deserialization Time: " + xmlTime + " nanoseconds for school: " + school.getNome());
            System.out.println("JSON Serialization/Deserialization Time: " + jsonTime + " nanoseconds for school: " + school.getNome());
        }
    }

    // XML Serialization/Deserialization
    private static long serializeDeserializeXML(School school) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(School.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // Measure serialization time
        long startTime = System.nanoTime();
        File xmlFile = new File(school.getNome() + ".xml");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(school, xmlFile);

        // Measure deserialization time
        School deserializedSchool = (School) unmarshaller.unmarshal(xmlFile);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    // JSON Serialization/Deserialization using the same style from your previous code
    private static long serializeDeserializeJSON(School school) throws IOException {
        long startTime = System.nanoTime();

        // JSON serialization (same style as the code you provided)
        JSONObject jsonObject = new JSONObject(school);
        JSONArray jsonArray = new JSONArray(school.getList());
        jsonObject.put("alunos", jsonArray);

        File jsonFile = new File(school.getNome() + ".json");
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
