package com.vastika.training.java.cms.repositories.impl;

import com.vastika.training.java.cms.model.Staff;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffRepository implements CrudRepository<Staff> {
    @Override
    public void insert(Staff staff) {
        try (Connection connection = DBConnector.getConnection()){
            String sql = "INSERT INTO staff (id, firstName, lastName, department) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getLastName());
            preparedStatement.setString(4, staff.getDepartment());
            preparedStatement.execute();
            System.out.println("Data inserted into Staff.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Staff> findAll() {
        try (Connection connection = DBConnector.getConnection()) {
            List<Staff> staffList = new ArrayList<>();
            String sql = "SELECT * FROM staff";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String department = resultSet.getString("department");
                Staff staff = new Staff(id, firstName, lastName, department);
                staffList.add(staff);
            }
            return staffList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Staff findById(int id) {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "SELECT * FROM staff WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultset = preparedStatement.executeQuery();

            while(resultset.next()){
                int staffId = resultset.getInt("id");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String department = resultset.getString("department");
                Staff staff = new Staff(staffId, firstName, lastName, department);
                return staff;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Staff staff) {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "UPDATE staff SET firstName = ?, lastName = ?, department = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setString(3, staff.getDepartment());
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setInt(4, staff.getId());
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "DELETE FROM staff WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
