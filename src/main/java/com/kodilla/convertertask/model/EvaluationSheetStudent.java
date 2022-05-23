package com.kodilla.convertertask.model;

import java.util.Map;

import static java.lang.String.format;

public class EvaluationSheetStudent {
    private String name;
    private String surname;
    private Map<String, String> evaluationSheet;

    public EvaluationSheetStudent(String name, String surname, Map<String, String> evaluationSheet) {
        this.name = name;
        this.surname = surname;
        this.evaluationSheet = evaluationSheet;
    }

    public void displayData(){
        System.out.println(format("Student: %s %s.", name,surname));
        for(Map.Entry<String, String> entry: evaluationSheet.entrySet()){
            System.out.println(format("Lesson: %s, Mark: %s", entry.getKey(), entry.getValue()));
        }
    }
}
