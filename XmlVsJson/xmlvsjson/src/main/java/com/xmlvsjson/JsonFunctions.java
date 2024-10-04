package com.xmlvsjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;
import classes.School;

public class JsonFunctions {

    // JSON Serialization for the entire list of School objects
    public static long serializeJSON(List<School> schools) throws IOException {
        long serializationStartTime = System.nanoTime();

        // Serialize the list of schools
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(schools);

        // Print serialized JSON
        //System.out.println("Serialized JSON: " + jsonString);

        // Save to file
        File jsonFile = new File("schools.json");
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(jsonFile))) {
            fileWriter.write(jsonString);
        }

        long serializationEndTime = System.nanoTime();
        return serializationEndTime - serializationStartTime;
    }

    // JSON Deserialization for the entire list of School objects
    public static long deserializeJSON() throws IOException {
        File jsonFile = new File("schools.json");

        long deserializationStartTime = System.nanoTime();
        try (BufferedReader jsonReader = new BufferedReader(new FileReader(jsonFile))) {
            Gson gson = new GsonBuilder().create();
            List<School> deserializedSchools = gson.fromJson(jsonReader, List.class);

            // Print deserialized list
         //   System.out.println("Deserialized JSON List: " + deserializedSchools);
        }
        long deserializationEndTime = System.nanoTime();

        return deserializationEndTime - deserializationStartTime;
    }
}
