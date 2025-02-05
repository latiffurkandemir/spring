package com.furkan.cruddemo.dao;

import com.furkan.cruddemo.entity.Course;
import com.furkan.cruddemo.entity.Instructor;
import com.furkan.cruddemo.entity.InstructorDetail;
import com.furkan.cruddemo.entity.Review;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailsById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);
    void addReviewsToCourse(int courseId, List<Review> reviews);


    void update(Instructor tempInstructor);

    void update(Course tempCourse);

    Course findCourseById(int id);


    void deleteCourseById(int theId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);
}
