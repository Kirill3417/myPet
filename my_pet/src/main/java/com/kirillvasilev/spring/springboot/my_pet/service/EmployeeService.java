package com.kirillvasilev.spring.springboot.my_pet.service;




import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
}
