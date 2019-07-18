package com.vastika.training.java.cms.services.impl;



import com.vastika.training.java.cms.model.Teacher;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.repositories.impl.TeacherRepository;
import com.vastika.training.java.cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class TeacherService implements BaseService {

    private CrudRepository<Teacher> teacherRepository;

    public TeacherService() {
        this.teacherRepository = new TeacherRepository();
    }

    @Override
    public void displayMenu() {
        System.out.println("Please Input a choice for the options below:");
        System.out.println("1. Insert teacher data");
        System.out.println("2. Retrieve all teacher data");
        System.out.println("3. Retrieve teacher data by ID");
        System.out.println("4. Update teacher data");
        System.out.println("5. Delete teacher by ID");
        System.out.println("----------------------------");

        Scanner scanner = new Scanner(System.in);
        int teacherChoice = scanner.nextInt();

        switch (teacherChoice){
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
                System.out.println("Invalid input please try again !!");
                break;
        }
    }

    @Override
    public void insertInfo() {
        System.out.println("Insert the teacher details");
        Teacher teacher = enterTeacherDetail();
        if(teacher == null){
            while(teacher == null){
                System.out.println("Id Already Exists. Enter a different Id:");
                teacher = enterTeacherDetail();
            }
        }
        teacherRepository.insert(teacher);
    }

    @Override
    public void viewInfo() {
        List<Teacher> teacherList = teacherRepository.findAll();
        System.out.println(teacherList);
    }

    @Override
    public void viewInfoById() {
        System.out.println("Enter the Id of the teacher to View");
        Teacher teacher = teacherRepository.findById(getTeacherId());
        System.out.println(teacher);
    }

    @Override
    public void updateInfo() {
        System.out.println("Insert the teacher details to update the teacher (id cannot be changed)");
        Teacher teacher = enterTeacherDetail();
        boolean update = teacherRepository.update(teacher);
        if (update){
            System.out.println("Success");
        }else{
            System.out.println("Failed");
        }
    }

    @Override
    public void deleteInfo() {
        System.out.println("Enter the Id to delete the student");
        boolean update = teacherRepository.deleteById(getTeacherId());
        if (update){
            System.out.println("Deleted");
        }else{
            System.out.println("Deletion Failed");
        }
    }

    private static Teacher enterTeacherDetail() {
        System.out.println("Teacher Id: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.valueOf(scanner.next());
        TeacherRepository teacherRepository = new TeacherRepository();
        if (teacherRepository.findById(id) == null){
            System.out.println("Teacher First Name : ");
            String firstName = scanner.next();
            System.out.println("Last Name:");
            String lastName = scanner.next();
            System.out.println("Subject: ");
            String subject = scanner.next();
            Teacher teacher = new Teacher(id, firstName, lastName, subject);
            return teacher;
        }
        return null;

    }

    private static int getTeacherId() {
        Scanner scanner = new Scanner(System.in);
        int teacherId = scanner.nextInt();
        return teacherId;
    }
}
