package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.DepartmentRepository;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);

    }
    @Override
    public Department getDepartment(int id) {
        Department department = null;
        Optional<Department> optional = departmentRepository.findById(id);
        if(optional.isPresent()){
            department = optional.get();
        }
        return department;
    }

    @Override
    public void deleteDepartment(int id) {
    departmentRepository.deleteById(id);
    }
}
