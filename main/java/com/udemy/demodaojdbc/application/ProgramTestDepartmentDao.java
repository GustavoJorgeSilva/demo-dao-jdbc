package com.udemy.demodaojdbc.application;

import com.udemy.demodaojdbc.model.dao.DaoFactory;
import com.udemy.demodaojdbc.model.dao.DepartmentDao;
import com.udemy.demodaojdbc.model.entities.Department;

import java.util.List;

public class ProgramTestDepartmentDao {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== Test 1: Deparment findById ===");
        Department department = departmentDao.findById(2);
        System.out.println(department);

        System.out.println("=== Test 2: Deparment Insert ===");
        Department departmentnew = new Department(null,"Stationery");
        departmentDao.insert(departmentnew);
        System.out.println("Insert! new department id: " + departmentnew.getId());


        System.out.println("=== Test 3: Deparment findAll ===");
        List<Department> list = departmentDao.findAll();
        for (Department dlist : list){
            System.out.println(dlist);
        }

        System.out.println("=== Test 4: Deparment delete ===");
        departmentDao.deleteById(13);
        System.out.println("Done! department deleted");

        List<Department> list2 = departmentDao.findAll();
        for (Department dlist : list2){
            System.out.println(dlist);
        }








    }
}
