package com.xmljson;


import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"name", "list"})
@XmlAccessorType(XmlAccessType.FIELD)
public class School {
    

    @XmlElement(name ="aluno")
    List<Aluno> list;
    
    String nome;

    public List<Aluno> getList() {
        return list;
    }

    public void setList(List<Aluno> list) {
        this.list = list;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
