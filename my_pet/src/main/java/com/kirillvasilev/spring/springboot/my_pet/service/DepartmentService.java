package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    public List<DepartmentDto> getAllDepartments();

    public Department saveDepartment(DepartmentDto departmentDto);

    public Department updateDepartment(DepartmentDto departmentDto);

    public DepartmentDto getDepartment(int id);

    public void deleteDepartment(int id);

    public Optional<Department> findByDepartmentName(String name);
}
