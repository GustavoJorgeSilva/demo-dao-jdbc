package com.udemy.demodaojdbc.model.dao;

import com.udemy.demodaojdbc.db.DB;
import com.udemy.demodaojdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }


}
