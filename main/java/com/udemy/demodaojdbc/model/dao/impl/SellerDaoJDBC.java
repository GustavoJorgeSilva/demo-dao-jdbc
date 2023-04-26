package com.udemy.demodaojdbc.model.dao.impl;

import com.udemy.demodaojdbc.db.DB;
import com.udemy.demodaojdbc.db.DbException;
import com.udemy.demodaojdbc.model.dao.SellerDao;
import com.udemy.demodaojdbc.model.entities.Department;
import com.udemy.demodaojdbc.model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("insert into seller " +
                            "(Name,Email,BirthDate,BaseSalary,DepartmentId) " +
                            "values (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            statement.setDouble(4, obj.getBaseSalary());
            statement.setInt(5, obj.getDepartment().getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet1 = statement.getGeneratedKeys();
                if (resultSet1.next()) {
                    int id = resultSet1.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet1);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);

        }

    }

    @Override
    public void update(Seller obj) {

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("Update seller " +
                    "set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                    "where id = ?");

            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            statement.setDouble(4, obj.getBaseSalary());
            statement.setInt(5, obj.getDepartment().getId());
            statement.setInt(6, obj.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);

        }


    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("DELETE from seller " +
                    "where Id = ?");

            statement.setInt(1,id);

           int rows = statement.executeUpdate();

           if (rows == 0){
               throw new DbException("id non-existent in the database");
           }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }

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

            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                Department dep = instantiateDepartment(resultSet);

                Seller obj = instantiateSeller(resultSet, dep);
                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

    }


    @Override
    public List<Seller> findAll() {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.prepareStatement("SELECT seller.*,department.Name as depName " +
                    "from seller inner join department " +
                    "on seller.DepartmentId = department.Id " +
                    "order by Name");

            resultSet = statement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()) {

                Department dep = map.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller obj = instantiateSeller(resultSet, dep);
                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }


    }


    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.prepareStatement("SELECT seller.*,department.Name as depName " +
                    "from seller inner join department " +
                    "on seller.DepartmentId = department.Id " +
                    "where DepartmentId = ? " +
                    "order by Name");

            statement.setInt(1, department.getId());

            resultSet = statement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()) {

                // METODO PARA REAPROVEITAR O OBJ DEPARTMENT ASSIM FAZENDO COM QUE CASO TENHA MAIS DE 1 OBJ SELLER
                // APONTEM PARA O MESMO OBJ DEPARTMENT
                Department dep = map.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller obj = instantiateSeller(resultSet, dep);
                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    // METODOS AUXILIARES PARA INSTANCIAÇÃO DO SELLER E DEPARTMENT

    private Seller instantiateSeller(ResultSet resultSet, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(resultSet.getInt("Id"));
        obj.setName(resultSet.getString("Name"));
        obj.setEmail(resultSet.getString("Email"));
        obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
        obj.setBirthDate(resultSet.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department dep = new Department();
        dep.setId(resultSet.getInt("DepartmentId"));
        dep.setName(resultSet.getString("DepName"));
        return dep;
    }


}
