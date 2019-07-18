package com.vastika.training.java.cms.repositories.impl;


import com.vastika.training.java.cms.model.Teacher;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements CrudRepository<Teacher> {

    @Override
    public void insert(Teacher teacher) {
        try (Connection connection = DBConnector.getConnection()){
            String sql = "INSERT INTO teacher (id, firstName, lastName, subject) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getFirstName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setString(4, teacher.getSubject());
            preparedStatement.execute();
            System.out.println("Data inserted into Teacher.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teacher> findAll() {
        try (Connection connection = DBConnector.getConnection()){
            List<Teacher> teacherList = new ArrayList<>();
            String sql = "SELECT * FROM teacher";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String subject = resultSet.getString("subject");
                Teacher tea = new Teacher(id, firstName, lastName, subject);
                teacherList.add(tea);
            }
            return teacherList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher findById(int id) {
        try (Connection connection = DBConnector.getConnection()){
            String sql = "SELECT * FROM teacher WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int teacherId = resultset.getInt("id");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String subject = resultset.getString("subject");
                Teacher teacher = new Teacher(teacherId, firstName, lastName, subject);
                return teacher;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Teacher teacher) {
        try (Connection connection = DBConnector.getConnection()){
            String sql = "UPDATE teacher SET firstName = ?, lastName = ? , subject = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setInt(4, teacher.getId());
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = DBConnector.getConnection()){
            String sql = "DELETE FROM teacher WHERE id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
