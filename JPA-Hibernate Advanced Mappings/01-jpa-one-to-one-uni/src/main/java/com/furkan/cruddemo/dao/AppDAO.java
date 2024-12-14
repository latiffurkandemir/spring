package com.furkan.cruddemo.dao;

import com.furkan.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
