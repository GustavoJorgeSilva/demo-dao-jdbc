package com.udemy.demodaojdbc.model.dao.impl;

import com.udemy.demodaojdbc.db.DB;
import com.udemy.demodaojdbc.db.DbException;
import com.udemy.demodaojdbc.db.DbIntegrityException;
import com.udemy.demodaojdbc.model.dao.DepartmentDao;
import com.udemy.demodaojdbc.model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("insert into department " +
                    "(Name)" + "values (?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, obj.getName());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet);
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
    public void update(Department obj) {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(" UPDATE department " +
                    "set Name = ? " +
                    "where department.Id = ?");
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getId());

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
            statement = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ?");

            statement.setInt(1, id);

           int rowsAffected = statement.executeUpdate();
           if (rowsAffected == 0){
               throw new DbException("id department non-existent in the database");
           }
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
        }

    }


    @Override
    public Department findById(Integer id) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                Department dep = instantiateDepartment(resultSet);
                return dep;
            } else {
                throw new DbException("Department not found in database");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }


    }

    @Override
    public List<Department> findAll() {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Name");
            resultSet = statement.executeQuery();

            List<Department> list = new ArrayList<>();

            while (resultSet.next()) {
                Department obj = new Department();
                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }




    }


    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department dep = new Department();
        dep.setId(resultSet.getInt("Id"));
        dep.setName(resultSet.getString("Name"));
        return dep;
    }

}
