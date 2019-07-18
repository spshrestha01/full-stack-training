package com.vastika.training.java.cms.services.impl;



import com.vastika.training.java.cms.model.Teacher;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.repositories.impl.TeacherRepository;
import com.vastika.training.java.cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class TeacherService implements BaseService {

    @Override
    public void displayMenu() {
        System.out.println("Please Input a choice for the options below:");
        System.out.println("1. Insert teacher data");
        System.out.println("2. Retrieve all teacher data");
        System.out.println("3. Retrieve teacher data by ID");
        System.out.println("4. Update teacher data");
        System.out.println("5. Delete teacher by ID");
        System.out.println("----------------------------");

        CrudRepository<Teacher> teacherRepository = new TeacherRepository();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Insert the teacher details");
            Teacher teacher = enterTeacherDetail();
            teacherRepository.insert(teacher);
        } else if (choice == 2) {
            List<Teacher> teacherList = teacherRepository.findAll();
            System.out.println(teacherList);
        } else if (choice == 3) {
            System.out.println("Enter the Id of the teacher to View");
            Teacher teacher = teacherRepository.findById(getTeacherId());
            System.out.println(teacher);
        } else if (choice == 4) {
            System.out.println("Insert the teacher details to update the teacher (id cannot be changed)");
            Teacher teacher = enterTeacherDetail();
            boolean update = teacherRepository.update(teacher);
            if (update){
                System.out.println("Success");
            }else{
                System.out.println("Failed");
            }
        } else if (choice == 5) {
            System.out.println("Enter the Id to delete the student");
            boolean update = teacherRepository.deleteById(getTeacherId());
            if (update){
                System.out.println("Deleted");
            }else{
                System.out.println("Deletion Failed");
            }
        } else {
            System.out.println("Wrong input please try again !!");
        }
    }

    private static Teacher enterTeacherDetail() {
        System.out.println("Teacher Id: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.valueOf(scanner.next());
        System.out.println("Teacher First Name : ");
        String firstName = scanner.next();
        System.out.println("Last Name:");
        String lastName = scanner.next();
        System.out.println("Subject: ");
        String subject = scanner.next();
        Teacher teacher = new Teacher(id, firstName, lastName, subject);
        return teacher;

    }

    private static int getTeacherId() {
        Scanner scanner = new Scanner(System.in);
        int teacherId = scanner.nextInt();
        return teacherId;
    }
}
