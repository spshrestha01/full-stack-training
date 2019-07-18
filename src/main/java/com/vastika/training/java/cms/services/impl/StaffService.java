package com.vastika.training.java.cms.services.impl;

import com.vastika.training.java.cms.model.Staff;
import com.vastika.training.java.cms.repositories.CrudRepository;
import com.vastika.training.java.cms.repositories.impl.StaffRepository;
import com.vastika.training.java.cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class StaffService implements BaseService {

    private CrudRepository<Staff> staffRepository;

    public StaffService(){
        this.staffRepository = new StaffRepository();
    }

    @Override
    public void displayMenu() {
        System.out.println("Please Input a choice for the options below:");
        System.out.println("1. Insert Staff data");
        System.out.println("2. Retrieve all Staff data");
        System.out.println("3. Retrieve Staff data by ID");
        System.out.println("4. Update Staff data");
        System.out.println("5. Delete Staff by ID");
        System.out.println("-----------------------------------");

        Scanner scanner = new Scanner(System.in);
        int staffChoice = scanner.nextInt();

        switch (staffChoice){
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
                System.out.println("Invalid Input please try again !!");
        }
    }

    @Override
    public void insertInfo() {
        System.out.println("Insert the Staff details");
        Staff staff = enterStaffDetails();
        if(staff == null){
            while (staff == null){
                System.out.println("Enter a Different ID:");
                staff = enterStaffDetails();
            }
        }
        staffRepository.insert(staff);
    }

    @Override
    public void viewInfo() {
        List<Staff> staffList = staffRepository.findAll();
        System.out.println(staffList);
    }

    @Override
    public void viewInfoById() {
        System.out.println("Enter the Id of the student to View");
        Staff staff = staffRepository.findById(getStaffId());
        System.out.println(staff);
    }

    @Override
    public void updateInfo() {
        System.out.println("Insert the Student details to update the staff (id cannot be changed)");
        Staff staff = enterStaffDetails();
        boolean update = staffRepository.update(staff);
        if (update){
            System.out.println("Success");
        }else{
            System.out.println("Failed");
        }
    }

    @Override
    public void deleteInfo() {
        System.out.println("Enter the Id to delete the student");
        boolean update = staffRepository.deleteById(getStaffId());
        if (update){
            System.out.println("Record Deleted");
        }else{
            System.out.println("Deletion Failed");
        }
    }

    private Staff enterStaffDetails() {
        System.out.println("Staff Id: ");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.valueOf(scanner.next());
        StaffRepository staffRepository = new StaffRepository();
        if (staffRepository.findById(id) == null){
            System.out.print("Staff First Name :");
            String firstName = scanner.next();
            System.out.println();
            System.out.print("Last Name:");
            String lastName = scanner.next();
            System.out.println();
            System.out.println("Gpa: ");
            String department = scanner.next();
            Staff staff = new Staff(id, firstName, lastName, department);
            return staff;
        }
        System.out.println("ID already exists");
        return null;
    }

    private int getStaffId() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }

}
