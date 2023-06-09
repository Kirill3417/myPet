package com.kirillvasilev.spring.springboot.my_pet.dto;


import lombok.Value;

@Value
public class EmployeeDto {

     int id;
     String name;
     DepartmentDto departmentDto;

}
