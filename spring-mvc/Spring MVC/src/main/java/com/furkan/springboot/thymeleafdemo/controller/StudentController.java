package com.furkan.springboot.thymeleafdemo.controller;

import com.furkan.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        //create a student object
        Student student = new Student();//object is null to be filled in form

        // add the list of countries to the model
        model.addAttribute("countries", countries);

        //add student object to the model to send to the view
        model.addAttribute("student", student);

        // add the list of languages to the model
        model.addAttribute("languages", languages);

        // add the list of systems to the model
        model.addAttribute("systems",systems);
        return "student-form";
    }


    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {//take the added model attribute before as parameter
      //@ModelAttribute automatically sends student object to returned view no need to use model.addAttribute here
        //log the input data
//        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());

        return "student-confirmation";
    }

}
