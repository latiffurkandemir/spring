package com.furkan.cruddemo.dao;

import com.furkan.cruddemo.entity.Instructor;
import com.furkan.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailsById(int id);
}
