package com.furkan.cruddemo.dao;

import com.furkan.cruddemo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);//this will also save details object because of CascadeType.ALL
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        // get the courses
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);//removing the instructor from the courses
        }

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);

        //remove the associated object reference
        //break bi-directional link

        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
        //this will also delete the associated instructor if cascade is CascadeType.ALL
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        //execute the query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data"
                , Instructor.class
        );//This code will retrieve the Instructor entity and eagerly fetch the associated courses

        query.setParameter("data", id);

        //execute the query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor); // merge updates the existed data
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, id);

        // delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);//it will save the reiews also because of the cascade type all
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviewList "
                        + "where c.id = :data", Course.class);

        query.setParameter("data",id);

        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.studentList "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }


    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courseList "
                        + "where s.id = :data", Student.class);

        query.setParameter("data", theId);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void addReviewsToCourse(int courseId, List<Review> reviews) {
        // Retrieve the course
        Course course = entityManager.find(Course.class, courseId);

        if (course == null) {
            throw new IllegalArgumentException("Course with ID " + courseId + " not found");
        }

        // Add reviews to the course
        for (Review review : reviews) {
            course.addReview(review);
        }

        // Save the course (merge is used to persist changes to an already existing entity)
        entityManager.merge(course);
    }
    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        // delete the student
        entityManager.remove(tempStudent);
    }


}
