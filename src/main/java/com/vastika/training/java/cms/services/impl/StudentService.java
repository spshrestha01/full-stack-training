package com.vastika.training.java.cms.services.impl;

import com.vastika.training.java.cms.model.Student;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.repositories.impl.StudentRepository;
import com.vastika.training.java.cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class StudentService implements BaseService {
    private CrudRepository<Student> studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    @Override
    public void displayMenu() {
        System.out.println("Please Input a choice for the options below:");
        System.out.println("1. Insert student data");
        System.out.println("2. Retrieve all student data");
        System.out.println("3. Retrieve student data by ID");
        System.out.println("4. Update student data");
        System.out.println("5. Delete Student by ID");
        System.out.println("---------------------------------");

        Scanner scanner = new Scanner(System.in);
        int studentChoice = scanner.nextInt();

        switch (studentChoice){
            case 1:
                insertInfo();
                break;
            case 2:
                viewInfo();
                break;
            case 3:
                viewInfoById();
                break;
            case 4:
                updateInfo();
                break;
            case 5:
                deleteInfo();
                break;
            default:
                System.out.println("Wrong input please try again !!");
                break;
        }

    }

    @Override
    public void insertInfo() {
        System.out.println("Insert the student details");
        Student student = enterStudentDetails();
        if(student == null){
            while (student == null){
                System.out.println("Enter a Different ID:");
                student = enterStudentDetails();
            }
        }
        studentRepository.insert(student);
    }

    @Override
    public void viewInfo() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

    @Override
    public void viewInfoById() {
        System.out.println("Enter the Id of the student to View");
        Student student1 = studentRepository.findById(getStudentId());
        System.out.println(student1);
    }

    @Override
    public void updateInfo() {
        System.out.println("Insert the Student details to update the student (id cannot be changed)");
        Student student = enterStudentDetails();
        boolean update = studentRepository.update(student);
        if (update){
            System.out.println("Success");
        }else{
            System.out.println("Failed");
        }

    }

    @Override
    public void deleteInfo() {
        System.out.println("Enter the Id to delete the student");
        boolean update = studentRepository.deleteById(getStudentId());
        if (update){
            System.out.println("Record Deleted");
        }else{
            System.out.println("Deletion Failed");
        }

    }


    private static Student enterStudentDetails() {
        System.out.println("Student Id: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.valueOf(scanner.next());
        StudentRepository studentRepository = new StudentRepository();
        if (studentRepository.findById(id) == null){
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
        System.out.println("ID already exists");
        return null;
    }

    private static int getStudentId() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }
}
