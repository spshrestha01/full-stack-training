package com.vastika.training.java.cms.controller;

import com.vastika.training.java.cms.services.BaseService;
import com.vastika.training.java.cms.services.impl.StudentService;
import com.vastika.training.java.cms.services.impl.TeacherService;

import java.util.Scanner;

public class DeriverProgram {
    public static void main(String[] args) {

        BaseService studentService = new StudentService();
        BaseService teacherService = new TeacherService();

        String continueChoice = "yes";
        while (!continueChoice.equals("no")) {
            System.out.println("Please select ");
            System.out.println("1. To view and update students");
            System.out.println("2. For Teachers:");
            System.out.println("***************************************");

            Scanner studentTeacherScanner = new Scanner(System.in);
            int studentTeacherChoice = studentTeacherScanner.nextInt();

            if (studentTeacherChoice == 1) {
                studentService.displayMenu();
            } else if (studentTeacherChoice == 2) {
                teacherService.displayMenu();
            } else {
                System.out.println("Wrong Input Try again !!!");
            }
            System.out.println("-------------------------------------");
            System.out.println("Do you want to continue ? (yes / no):");
            Scanner continueScanner = new Scanner(System.in);
            continueChoice = continueScanner.next();
        }
    }
}
