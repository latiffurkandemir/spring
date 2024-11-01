package com.furkan.springboot.thymeleafdemo.repository;

import com.furkan.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByOrderByLastNameAsc();

    // that's it ... no need to write any code LOL!

}
