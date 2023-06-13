package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiDep")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentDto> showAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public DepartmentDto getDepartment(@PathVariable int id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping("/departments")
    public Department addNewDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.saveDepartment(departmentDto);
    }

    @PutMapping("/departments")
    public Department updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartment(departmentDto);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return "Department with ID = " + id + " was deleted";
    }
}