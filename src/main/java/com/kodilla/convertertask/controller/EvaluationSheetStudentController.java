package com.kodilla.convertertask.controller;

import com.kodilla.convertertask.model.EvaluationSheetStudent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluation-sheet")
public class EvaluationSheetStudentController {

    @PostMapping("/student")
    public void displayEvaluationSheetStudent(@RequestBody EvaluationSheetStudent evaluationSheetStudent){
        evaluationSheetStudent.displayData();
    }

}
