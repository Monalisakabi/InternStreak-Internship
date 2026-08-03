package org.example;

import org.example.model.Student;
import org.example.service.StudentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService();

        while (true) {

            System.out.println("\n===== STUDENT PORTAL =====");

            System.out.println("1.Add Student");
            System.out.println("2.View Students");
            System.out.println("3.Search By ID");
            System.out.println("4.Search By Name");
            System.out.println("5.Update Student");
            System.out.println("6.Delete Student");
            System.out.println("7.Search By Branch");
            System.out.println("8.Search By Grade");
            System.out.println("9.Count Students");
            System.out.println("10.Sort By Marks");
            System.out.println("11.Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("ID : ");
                    int id=sc.nextInt();

                    sc.nextLine();

                    System.out.print("Name : ");
                    String name=sc.nextLine();

                    System.out.print("Age : ");
                    int age=sc.nextInt();

                    sc.nextLine();

                    System.out.print("Branch : ");
                    String branch=sc.nextLine();

                    System.out.print("Marks : ");
                    double marks=sc.nextDouble();

                    System.out.print("Grade : ");
                    char grade=sc.next().charAt(0);

                    service.addStudent(new Student(id,name,age,branch,marks,grade));

                    break;

                case 2:

                    service.viewStudents();
                    break;

                case 3:

                    System.out.print("Enter ID : ");
                    service.searchById(sc.nextInt());
                    break;

                case 4:

                    sc.nextLine();

                    System.out.print("Enter Name : ");

                    service.searchByName(sc.nextLine());

                    break;

                case 5:

                    System.out.print("ID : ");
                    id=sc.nextInt();

                    sc.nextLine();

                    System.out.print("Name : ");
                    name=sc.nextLine();

                    System.out.print("Age : ");
                    age=sc.nextInt();

                    sc.nextLine();

                    System.out.print("Branch : ");
                    branch=sc.nextLine();

                    System.out.print("Marks : ");
                    marks=sc.nextDouble();

                    System.out.print("Grade : ");
                    grade=sc.next().charAt(0);

                    service.updateStudent(id,name,age,branch,marks,grade);

                    break;

                case 6:

                    System.out.print("Enter ID : ");

                    service.deleteStudent(sc.nextInt());

                    break;

                case 7:

                    sc.nextLine();

                    System.out.print("Branch : ");

                    service.searchByBranch(sc.nextLine());

                    break;

                case 8:

                    System.out.print("Grade : ");

                    service.searchByGrade(sc.next().charAt(0));

                    break;

                case 9:

                    service.countStudents();

                    break;

                case 10:

                    service.sortByMarks();

                    break;

                case 11:

                    System.out.println("Thank You!");

                    System.exit(0);

                default:

                    System.out.println("Invalid Choice");
            }
        }
    }
}