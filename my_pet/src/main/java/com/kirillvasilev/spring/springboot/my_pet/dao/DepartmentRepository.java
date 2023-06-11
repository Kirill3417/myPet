package com.kirillvasilev.spring.springboot.my_pet.dao;

import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
