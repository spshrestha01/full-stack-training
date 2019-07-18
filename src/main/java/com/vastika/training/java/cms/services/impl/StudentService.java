package com.vastika.training.java.cms.services.impl;

import com.vastika.training.java.cms.model.Student;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.repositories.impl.StudentRepository;
import com.vastika.training.java.cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class StudentService implements BaseService {
    @Override
    public void displayMenu() {
        System.out.println("Please Input a choice for the options below:");
        System.out.println("1. Insert student data");
        System.out.println("2. Retrieve all student data");
        System.out.println("3. Retrieve student data by ID");
        System.out.println("4. Update student data");
        System.out.println("5. Delete Student by ID");
        System.out.println("---------------------------------");

        CrudRepository<Student> studentRepository = new StudentRepository();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Insert the student details");
            Student student = enterStudentDetails();
            studentRepository.insert(student);
        } else if (choice == 2) {
            List<Student> studentList = studentRepository.findAll();
            System.out.println(studentList);
        } else if (choice == 3) {
            System.out.println("Enter the Id of the student to View");
            Student student = studentRepository.findById(getStudentId());
            System.out.println(student);
        } else if (choice == 4) {
            System.out.println("Insert the Student details to update the student (id cannot be changed)");
            Student student = enterStudentDetails();
            boolean update = studentRepository.update(student);
            if (update){
                System.out.println("Success");
            }else{
                System.out.println("Failed");
            }
        } else if (choice == 5) {
            System.out.println("Enter the Id to delete the student");
            boolean update = studentRepository.deleteById(getStudentId());
            if (update){
                System.out.println("Record Deleted");
            }else{
                System.out.println("Deletion Failed");
            }
        } else {
            System.out.println("Wrong input please try again !!");
        }
    }

    private static Student enterStudentDetails() {
        System.out.println("Student Id: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.valueOf(scanner.next());
        System.out.print("Student First Name :");
        String firstName = scanner.next();
        System.out.println();
        System.out.print("Last Name:");
        String lastName = scanner.next();
        System.out.println();
        System.out.println("Gpa: ");
        Double gpa = Double.valueOf(scanner.next());
        Student student = new Student(id, firstName, lastName, gpa);
        return student;
    }

    private static int getStudentId() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }
}
