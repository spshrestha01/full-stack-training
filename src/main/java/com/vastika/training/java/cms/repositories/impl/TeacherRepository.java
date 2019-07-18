package com.vastika.training.java.cms.repositories.impl;


import com.vastika.training.java.cms.model.Teacher;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements CrudRepository<Teacher> {

    private Connection connection = DBConnector.getConnection();


    @Override
    public void insert(Teacher teacher) {
        String sql = "INSERT INTO teacher (id, firstName, lastName, subject) VALUES (" + teacher.getId() + ", '"
                + teacher.getFirstName() + "', '" + teacher.getLastName() + "', '" + teacher.getSubject() + "')";
        try {
            Statement preparedStatement = connection.createStatement();
            preparedStatement.executeUpdate(sql);
            System.out.println("Data inserted into Teacher.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "SELECT * FROM teacher";
        try {
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
        String sql = "SELECT * FROM teacher WHERE id = " + id;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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

        }
        return null;
    }

    @Override
    public boolean update(Teacher teacher) {
        String sql = "UPDATE teacher SET firstName = '" + teacher.getFirstName() +
                "', lastName = '" + teacher.getLastName() + "', subject = '" + teacher.getSubject() + "' WHERE id = " +
                teacher.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM teacher WHERE id = " + id;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
