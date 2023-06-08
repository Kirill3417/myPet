package com.kirillvasilev.spring.springboot.my_pet.dto;



public class EmployeeDto {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public EmployeeDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
