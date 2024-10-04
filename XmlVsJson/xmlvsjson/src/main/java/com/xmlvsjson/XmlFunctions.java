package com.xmlvsjson;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;
import classes.School;
import classes.SchoolsWrapper;

public class XmlFunctions {

    // XML Serialization for the entire list of School objects
    public static long serializeXML(List<School> schools) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SchoolsWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            long serializationStartTime = System.nanoTime();
            File xmlFile = new File("schools.xml");

            // Wrap the list of schools in a container class
            SchoolsWrapper wrapper = new SchoolsWrapper();
            wrapper.setSchools(schools);

            marshaller.marshal(wrapper, xmlFile);
            long serializationEndTime = System.nanoTime();

            // Print serialized XML
          //  System.out.println("Serialized XML:");
           // marshaller.marshal(wrapper, System.out);

            return serializationEndTime - serializationStartTime;

        } catch (JAXBException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // XML Deserialization for the entire list of School objects
    public static long deserializeXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SchoolsWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("schools.xml");
            long deserializationStartTime = System.nanoTime();

            SchoolsWrapper wrapper = (SchoolsWrapper) unmarshaller.unmarshal(xmlFile);
            List<School> deserializedSchools = wrapper.getSchools();
            long deserializationEndTime = System.nanoTime();

            // Print deserialized list
           // System.out.println("Deserialized XML List: " + deserializedSchools);

            return deserializationEndTime - deserializationStartTime;

        } catch (JAXBException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
