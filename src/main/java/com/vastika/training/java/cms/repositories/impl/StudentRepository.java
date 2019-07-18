package com.vastika.training.java.cms.repositories.impl;

import com.vastika.training.java.cms.model.Student;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.util.DBConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements CrudRepository<Student> {

    @Override
    public void insert(Student student) {
        try (Connection connection = DBConnector.getConnection()){
            String sql = "INSERT INTO STUDENT (id, firstName, lastName, gpa) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setDouble(4, student.getGpa());
            preparedStatement.execute();
            System.out.println("Data inserted into Students.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        try (Connection connection = DBConnector.getConnection()) {
            List<Student> studentList = new ArrayList<>();
            String sql = "SELECT * FROM student";
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
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "SELECT * FROM student WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "UPDATE student SET firstName = ?, lastName = ?, gpa = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(4, student.getId());
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDouble(3, student.getGpa());
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
            String sql = "DELETE FROM student WHERE id = ?";
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
