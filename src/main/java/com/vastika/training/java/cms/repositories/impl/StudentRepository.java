package com.vastika.training.java.cms.repositories.impl;


import com.vastika.training.java.cms.model.Student;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements CrudRepository<Student> {

    private Connection connection = DBConnector.getConnection();


    @Override
    public void insert(Student student) {
        String sql = "INSERT INTO STUDENT (id, firstName, lastName, gpa) VALUES (" + student.getId() + ", '"
                + student.getFirstName() + "', '" + student.getLastName() + "', " + student.getGpa() + ")";
        try {
            Statement preparedStatement = connection.createStatement();
            preparedStatement.executeUpdate(sql);
            System.out.println("Data inserted into Students.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Double gpa = resultSet.getDouble("gpa");
                Student student = new Student(id, firstName, lastName, gpa);
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM student WHERE id = " + id;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();

            while(resultset.next()){
                int studentId = resultset.getInt("id");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                Double gpa = resultset.getDouble("gpa");
                Student student = new Student(studentId, firstName, lastName, gpa);
               return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE student SET firstName = '" + student.getFirstName() +
                "', lastName = '" + student.getLastName() + "', gpa = " + student.getGpa() + "WHERE id = " +
                student.getId();
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
        String sql = "DELETE FROM student WHERE id = " + id;

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
