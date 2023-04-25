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
import java.util.List;

@SpringBootApplication
public class DemoDaoJdbcApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(DemoDaoJdbcApplication.class, args);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n=== Test 2: Seller findByDepartment");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller seller1 : list){
            System.out.println(seller1);
        }






    }
}
