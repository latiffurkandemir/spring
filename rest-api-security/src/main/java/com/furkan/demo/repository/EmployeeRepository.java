package com.furkan.demo.repository;

import com.furkan.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {//entity type and primary key type

}
