package com.training;


//import javax.xml.bind.annotation.*;

// @Since 3.0.0
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"name", "list"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

  @XmlElement(name = "staff")
  List<Staff> list;

  String name;

public List<Staff> getList() {
    return list;
}

public void setList(List<Staff> list) {
    this.list = list;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

  // getters, and setters
}
