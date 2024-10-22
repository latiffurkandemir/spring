package com.furkan.cruddemo.dao;

import com.furkan.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//a special marker for classes that interact with the database
public class StudentDAOImpl implements StudentDAO{

    //define field for entity manager (used for DAOs)
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional//since we are performing an update
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName desc",Student.class);
        //lastName is the field of JPA entity not the column name from the database
        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=: theData",Student.class);

        //set query parameters
        theQuery.setParameter("theData",lastName);
        //return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional//since we are performing an update
    public void update(Student student) {
        entityManager.merge(student);//Update an existing entity(merge)
    }

    @Override
    @Transactional//since we are performing a delete operation
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class,id);

        entityManager.remove(student);
    }

    @Override
    @Transactional//since we are performing a delete operation
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
