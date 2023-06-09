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
import java.util.Scanner;

@SpringBootApplication
public class DemoDaoJdbcApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(DemoDaoJdbcApplication.class, args);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner leitor = new Scanner(System.in);


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

        System.out.println("\n=== Test 3: Seller findAll");

        list = sellerDao.findAll();
        for (Seller seller1 : list){
            System.out.println(seller1);
        }

//        System.out.println("\n=== Test 4: Seller Insert");
//        Seller func = new Seller(null,"Gustavo Jorge","gustavo@gmail.com",new Date(),3000.0,department);
//        sellerDao.insert(func);
//        System.out.println("Insert! New id: " + func.getId());
//
//
//        list = sellerDao.findAll();
//        for (Seller seller1 : list){
//            System.out.println(seller1);
//        }


        System.out.println("\n=== Test 5: Seller Update");
        seller = sellerDao.findById(2);
        seller.setName("Jao gomes");
        sellerDao.update(seller);
        System.out.println("Update completed! ");

        System.out.println("\n=== Test 6: Seller Delete");
        System.out.println("Enter the id of the employee you want to delete");
        int id = leitor.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Done!");

        leitor.close();







    }
}
