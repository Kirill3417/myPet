package com.kirillvasilev.spring.springboot.my_pet.dao;

import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByName(String name);
}
