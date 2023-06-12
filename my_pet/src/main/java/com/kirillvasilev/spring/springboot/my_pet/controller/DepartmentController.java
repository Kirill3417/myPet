package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dao.DepartmentRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import com.kirillvasilev.spring.springboot.my_pet.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiDep")
public class DepartmentController {

    private DepartmentService departmentService;
    private DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public List<DepartmentDto> showAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public DepartmentDto getDepartment(@PathVariable int id){
        return departmentService.getDepartment(id);
    }

    @PostMapping("/departments")
    public Department addNewDepartment(@RequestBody DepartmentDto departmentDto){
        Department department = new Department();
        department.setDepartmentName(departmentDto.getName());
        departmentService.saveDepartment(department);
        return department;
    }

    @PutMapping("/departments")
    public Department updateEmployee(@RequestBody DepartmentDto departmentDto){
        Department department = departmentRepository.findById(departmentDto.getId()).orElseThrow(()-> new NotFoundException("Id not found"));
        department.setDepartmentName(departmentDto.getName());
        departmentService.saveDepartment(department);
        return department;
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartment(id);

        return "Department with ID = " + id + " was deleted";
    }
}