package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;

import java.util.List;

public interface DepartmentService {
    public List<DepartmentDto> getAllDepartments();
    public void saveDepartment(Department department);
    public DepartmentDto getDepartment(int id);
    public void deleteDepartment(int id);
}
