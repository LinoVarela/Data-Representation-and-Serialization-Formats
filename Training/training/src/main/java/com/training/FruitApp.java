package com.training;

/**
 * Hello world!
 *
 */

import com.training.Fruit;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class FruitApp {

    public static void main(String[] args) {

        JAXBContext jaxbContext = null;
        try {

            // Normal JAXB RI
            //jaxbContext = JAXBContext.newInstance(Fruit.class);

            // EclipseLink MOXy needs jaxb.properties at the same package with Fruit.class
            // Alternative, I prefer define this via eclipse JAXBContextFactory manually.
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Fruit.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Fruit o = new Fruit();
            o.setId(1);
            o.setName("Banana");
            o.setPrice("9.99");

            // output to a xml file
            jaxbMarshaller.marshal(o, new File("fruit.xml"));

            // output to console
            jaxbMarshaller.marshal(o, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

     jaxbContext = null;
      try {

          // Normal JAXB RI
          //jaxbContext = JAXBContext.newInstance(Fruit.class);

          // EclipseLink MOXy needs jaxb.properties at the same package with Fruit.class
          // Alternative, I prefer define this via eclipse JAXBContextFactory manually.
          jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                  .createContext(new Class[]{Fruit.class}, null);

          File file = new File("fruit.xml");

          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

          Fruit o = (Fruit) jaxbUnmarshaller.unmarshal(file);
         
          System.out.println(o.toString());

      } catch (JAXBException e) {
          e.printStackTrace();
      }


    }

}