package com.training;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)  // Without this The JAXB will consider Fruit.getId() and Fruit.id (because we annotated with @XmlAttribute or @XmlElement) as the same property;
public class Fruit {

    @XmlAttribute
    int id;

    @XmlElement(name = "n")
    String name;

    @Override
    public String toString() {
        return "Fruit [id=" + id + ", name=" + name + ", price=" + price + "]";
    }

    String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // getter, setter and toString...
}