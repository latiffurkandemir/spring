package com.furkan.demo.dao;

import com.furkan.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employeeList = query.getResultList();

        return employeeList;
    }

    @Override
    public Employee findById(int id) {

        Employee employee = entityManager.find(Employee.class, id);

        return employee;
    }

    @Override
    public Employee save(Employee employee) {

        Employee dbEmployee = entityManager.merge(employee); //If the ID is 0, save; if it is different from 0, update.

        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        //find the employee that has given id
        Employee employee = entityManager.find(Employee.class,id);

        //delete that employee

        entityManager.remove(employee);
    }


}
