package com.udemy.demodaojdbc.application;

import com.udemy.demodaojdbc.model.entities.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDaoJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDaoJdbcApplication.class, args);


        Department department = new Department(1,"Books");
        System.out.println(department);




    }
}
