package com.furkan.cruddemo;

import com.furkan.cruddemo.dao.AppDAO;
import com.furkan.cruddemo.entity.*;
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
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
//            createCourseAndStudents(appDAO);
//            findCourseAndStudents(appDAO);
//            findStudentAndCourses(appDAO);
//            addMoreCoursesForStudent(appDAO);
//            deleteCourse(appDAO);
            deleteStudent(appDAO);
        };
    }

    private void deleteStudent(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Done!");
    }
    private void addMoreCoursesForStudent(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("associated courses: " + tempStudent.getCourseList());

        appDAO.update(tempStudent);

        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loaded student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourseList());

        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {

        int theId = 1;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudentList());

        System.out.println("Done!");
    }


    private void createCourseAndStudents(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("HST101");

        // create the students
        Student tempStudent1 = new Student("Ahmet", "Erkan", "ahmeterkan@gmail.com");
        Student tempStudent2 = new Student("Melike", "Güngör", "melikegüngör@gmail.com");

        // add students to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudentList());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int id = 1;

        System.out.println("Deleting the course id: " + id);
        appDAO.deleteCourseById(id);//this will delete the all reviews because of CascadeTYPE.ALL
        System.out.println("Done!");

    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        // get the course and reviews
        int id = 1;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(id);

        // print the course
        System.out.println(tempCourse);

        // print the reviews
        System.out.println(tempCourse.getReviewList());
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Furkan-Spring Boot Course");

        // add some reviews
        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done."));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course ... and leverage the cascade all
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviewList());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {

        int id = 4;

        System.out.println("Deleting course id: " + id);

        appDAO.deleteCourseById(id);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {

        int id = 4;

        // find the course
        System.out.println("Finding course id: " + id);
        Course tempCourse = appDAO.findCourseById(id);

        // update the course
        System.out.println("Updating course id: " + id);
        tempCourse.setTitle("TEST101");

        appDAO.update(tempCourse);

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {

        int id = 2;

        // find the instructor
        System.out.println("Finding instructor id: " + id);
        Instructor tempInstructor = appDAO.findInstructorById(id);

        // update the instructor
        System.out.println("Updating instructor id: " + id);
        tempInstructor.setLastName("TESTER");//change the data by setter method

        appDAO.update(tempInstructor);

        System.out.println("Done!");
    }


    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 2;

        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);//the code will still retrieve instructor and courses (JOIN FETCH)

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");


    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding the instructor id: " + id);
        Instructor instructor = appDAO.findInstructorById(id);//retrieving the instructors without associated courses(lazy fetch type)
        System.out.println("Instructor: " + instructor);

        //find courses for instructor

        System.out.println("Finding courses for instructor id: " + id);
        List<Course> courseList = appDAO.findCoursesByInstructorId(id); //retrieving the courses without associated instructor

        //associate the objects
        instructor.setCourses(courseList);

        System.out.println("the associated courses: " + instructor.getCourses());
        System.out.println("Done");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 2;

        System.out.println("Finding the instructor id: " + id);
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        System.out.println("The associated courses: " + instructor.getCourses());//if fetch type is lazy in one to many relation, we cannot retrieve the courses data

        System.out.println("Done");

    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        //create the instructor
        Instructor tempInstructor =
                new Instructor(
                        "Furkan",
                        "Demir",
                        "furkan@demir.com");

        //create instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail(
                        "http://www.furkan.com/youtube",
                        "basketball"
                );

        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        //create some courses
        Course course = appDAO.findCourseById(3);
        Course course2 = appDAO.findCourseById(4);
        //add courses to instructor
        tempInstructor.add(course);
        tempInstructor.add(course2);

        //save the instructor
        //
        //NOTE: this will ALSO save the courses
        //because of the CascadeType.PERSIST
        System.out.println("Saving the instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("Done");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 4;
        System.out.println("Deleting the instructor detail id: " + id);
        appDAO.deleteInstructorDetailsById(id);
        System.out.println("Done");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        //get the instructor detail object
        int id = 2;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        //print the instructor detail
        System.out.println("Temporary instructor detail: " + instructorDetail);
        //print the associated instructor
        System.out.println("The associated instructor: " + instructorDetail.getInstructor());

        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Deleting instructor id: " + id);
        appDAO.deleteInstructorById(id);
        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding instructor id: " + id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("tempInstructor" + tempInstructor);
        System.out.println("the associate instructorDetail only: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        //create the instructor
        Instructor tempInstructor =
                new Instructor(
                        "Furkan",
                        "Demir",
                        "furkan@demir.com");

        //create instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail(
                        "http://www.furkan.com/youtube",
                        "basketball"
                );
        //create the instructor
        /*Instructor tempInstructor =
                new Instructor(
                        "Ahmet",
                        "Çelik",
                        "ahmet@çelik.com");

        //create instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail(
                        "http://www.çelik.com/youtube",
                        "playing a guitar"
                );*/
        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor
        //
        //NOTE: this will ALSO save the details object
        //because of the CascadeType.ALL
        //
        System.out.println("Saving Instructor: " + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done");
    }
}
