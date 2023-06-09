package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiDep")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentDto> showAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public DepartmentDto getDepartment(@PathVariable int id){
        return departmentService.getDepartment(id);
    }

    @PostMapping("/departments")
    public Department addNewDepartment(@RequestBody Department department){
        departmentService.saveDepartment(department);
        return department;
    }

    @PutMapping("/departments")
    public Department updateEmployee(@RequestBody Department department){
        departmentService.saveDepartment(department);
        return department;
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartment(id);

        return "Department with ID = " + id + " was deleted";
    }
}