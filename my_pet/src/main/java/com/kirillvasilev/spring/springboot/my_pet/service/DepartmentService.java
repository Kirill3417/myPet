package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.entity.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> getAllDepartments();
    public void saveDepartment(Department department);
    public Department getDepartment(int id);
    public void deleteDepartment(int id);
}
