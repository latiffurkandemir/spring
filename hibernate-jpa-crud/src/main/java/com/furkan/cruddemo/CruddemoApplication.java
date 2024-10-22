package com.furkan.cruddemo;

import com.furkan.cruddemo.dao.StudentDAO;
import com.furkan.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
//            readStudent(studentDAO);
//            queryForStudents(studentDAO);
//            queryForStudentsByLastName(studentDAO,"Vahit");
//            updateStudent(studentDAO,2);
//            deleteStudent(studentDAO,3);
//            deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO, Integer id) {
        Student deletedStudent = studentDAO.findById(id);
        System.out.println("The student that will be deleted: " + deletedStudent);
        studentDAO.delete(id);
    }

    private void updateStudent(StudentDAO studentDAO, int id) {
        Student student = studentDAO.findById(id);
        student.setFirstName("Muhammet");
        studentDAO.update(student);
        System.out.println("Updated Student: " + student);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO, String lastName) {
        List<Student> studentList = studentDAO.findByLastName(lastName);

        for (Student tempStudent : studentList) {
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        //get a list of students
        List<Student> theStudents = studentDAO.findAll();

        //display it
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Lütfü", "Kemer", "lütfü.kemer@gmail.com");

        // save the student object
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());

        // retrieve student based on the id: primary key
        System.out.println("\nRetrieving student with id: " + tempStudent.getId());
        Student myStudent = studentDAO.findById(tempStudent.getId());
        System.out.println("Found the student: " + myStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        System.out.println("Creating 4 student objects ...");
        Student tempStudent1 = new Student("Furkan", "Demir", "furkan.demir@gmail.com");
        Student tempStudent2 = new Student("İrfan", "Demir", "irfan.demir@gmail.com");
        Student tempStudent3 = new Student("Ahmet", "Elmas", "ahmet.elmas@gmail.com");
        Student tempStudent4 = new Student("Mehmet", "Vahit", "Mehmet.vait@gmail.com");

        System.out.println("Saving the students ...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
        studentDAO.save(tempStudent4);
    }

    private void createStudent(StudentDAO studentDAO) {
        //create the student object
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Furkan", "Demir", "latiffurkan.demir@gmail.com");

        //save the student object
        System.out.println("Saving the object ...");
        studentDAO.save(tempStudent);

        //display the id of saved object
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }
}
