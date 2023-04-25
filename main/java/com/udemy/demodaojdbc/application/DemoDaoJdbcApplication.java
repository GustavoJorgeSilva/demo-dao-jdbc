package com.udemy.demodaojdbc.application;

import com.udemy.demodaojdbc.model.dao.DaoFactory;
import com.udemy.demodaojdbc.model.dao.SellerDao;
import com.udemy.demodaojdbc.model.entities.Department;
import com.udemy.demodaojdbc.model.entities.Seller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class DemoDaoJdbcApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(DemoDaoJdbcApplication.class, args);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        Department department = new Department(1,"Books");
        System.out.println(department);

        Date aniversario = sdf.parse("11/11/1994");
        Seller sl = new Seller(1,"Gustavo","gustavo@gmail.com",aniversario,2000.0,department);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(sl);




    }
}
