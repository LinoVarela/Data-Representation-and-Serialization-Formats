package classes;


import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"name", "list"})
@XmlAccessorType(XmlAccessType.FIELD)
public class School {

    @XmlElement(name ="student")
    List<Student> list;
    
    String name;

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

}
