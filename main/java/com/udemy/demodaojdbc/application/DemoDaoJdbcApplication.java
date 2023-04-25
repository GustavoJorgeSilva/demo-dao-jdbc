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


        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println(seller);






    }
}
