package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dao.EmployeeRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import com.kirillvasilev.spring.springboot.my_pet.service.DepartmentService;
import com.kirillvasilev.spring.springboot.my_pet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiEmp")
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService,EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List<EmployeeDto> showAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody EmployeeDto employeeDto){

        Employee employee = new Employee();
        Department department = departmentService.findByDepartmentName(employeeDto.getDepartmentDto().getName());

        if(department != null){
            employee.setDepartment(department);
        } else {
            throw new NotFoundException("Сhoose another department");
        }
        employee.setName(employeeDto.getName());
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow(()-> new NotFoundException("Id not found"));
        Department department = departmentService.findByDepartmentName(employeeDto.getDepartmentDto().getName());

        if(department != null){
            employee.setDepartment(department);
        } else{
            throw new NotFoundException("Сhoose another department");
        }

        employee.setName(employeeDto.getName());
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);

        return "Employee with ID = " + id + " was deleted";
    }
}