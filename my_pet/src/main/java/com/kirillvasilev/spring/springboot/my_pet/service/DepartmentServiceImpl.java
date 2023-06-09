package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.DepartmentRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dep-> new DepartmentDto(dep.getId(), dep.getDepartmentName()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);

    }
    @Override
    public DepartmentDto getDepartment(int id) {
        return departmentRepository.findById(id)
                .map(dep-> new DepartmentDto(dep.getId(), dep.getDepartmentName()))
                .orElseThrow();
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
