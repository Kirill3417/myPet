package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.DepartmentRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;


    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dep -> new DepartmentDto(dep.getId(), dep.getDepartmentName()))
                .collect(Collectors.toList());
    }

    @Override
    public Department saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentName(departmentDto.getName());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Department updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentService.findByDepartmentName(departmentDto.getName())
                .orElseThrow(() -> new NotFoundException("Department with this name is not found, please choose another department"));
        department.setDepartmentName(departmentDto.getName());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public DepartmentDto getDepartment(int id) {
        return departmentRepository.findById(id)
                .map(dep -> new DepartmentDto(dep.getId(), dep.getDepartmentName()))
                .orElseThrow(() -> new NotFoundException("Department with this id is not found, please choose another department"));
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public Optional<Department> findByDepartmentName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }
}
