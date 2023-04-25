package com.udemy.demodaojdbc.model.dao.impl;

import com.udemy.demodaojdbc.db.DB;
import com.udemy.demodaojdbc.db.DbException;
import com.udemy.demodaojdbc.model.dao.SellerDao;
import com.udemy.demodaojdbc.model.entities.Department;
import com.udemy.demodaojdbc.model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");

            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                Department dep = new Department();
                dep.setId(resultSet.getInt("DepartmentId"));
                dep.setName(resultSet.getString("DepName"));

                Seller obj = new Seller();
                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));
                obj.setEmail(resultSet.getString("Email"));
                obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
                obj.setBirthDate(resultSet.getDate("BirthDate"));
                obj.setDepartment(dep);
                return obj;
            }
            return null;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }


    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
