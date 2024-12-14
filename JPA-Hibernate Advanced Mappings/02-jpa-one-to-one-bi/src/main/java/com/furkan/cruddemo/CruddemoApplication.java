package com.furkan.cruddemo;

import com.furkan.cruddemo.dao.AppDAO;
import com.furkan.cruddemo.entity.Instructor;
import com.furkan.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
            createInstructor(appDAO);

            // findInstructor(appDAO);

            // deleteInstructor(appDAO);

            // findInstructorDetail(appDAO);

//             deleteInstructorDetail(appDAO);
        };
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
        int id = 1;
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
