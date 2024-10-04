package com.xmlvsjson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.School;
import classes.Student;

public class Help_Functions {

    public static List<School> readSchoolDataFromTextFile(String filePath) throws IOException {
        List<School> schools = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        School school = null;
        List<Student> students = new ArrayList<>();
        String line;
        
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("School")) {
                if (school != null) {
                    school.setList(students);
                    schools.add(school);
                }
                school = new School();
                students = new ArrayList<>();
                school.setname(line);  // School name, e.g. "School XYZ"
            } else {
                String[] parts = line.split(" ");
                Student student = new Student();
                student.setId(Integer.parseInt(parts[0])); // First part is student ID
                student.setName(parts[1]);                 // Second part is student name
                students.add(student);
            }
        }

        // Add the last school
        if (school != null) {
            school.setList(students);
            schools.add(school);
        }
        reader.close();
        return schools;
    }
}
