package com.kirillvasilev.spring.springboot.my_pet.dao;

import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
