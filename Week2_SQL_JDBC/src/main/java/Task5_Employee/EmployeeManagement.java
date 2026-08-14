package Task5_Employee;

import java.util.Scanner;

public class EmployeeManagement {

    public static void main(String[] args) {

        Scanner sc =
                new Scanner(System.in);

        EmployeeDAO dao =
                new EmployeeDAO();


        while (true) {

            System.out.println(
                    "\n================================"
            );

            System.out.println(
                    "       EMPLOYEE MANAGEMENT"
            );

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "1. Add Employee"
            );

            System.out.println(
                    "2. View Employees"
            );

            System.out.println(
                    "3. Search Employee"
            );

            System.out.println(
                    "4. Update Employee"
            );

            System.out.println(
                    "5. Delete Employee"
            );

            System.out.println(
                    "6. Exit"
            );


            System.out.print(
                    "Enter choice: "
            );

            int choice =
                    sc.nextInt();

            sc.nextLine();


            switch (choice) {


                case 1:

                    System.out.print(
                            "Enter name: "
                    );

                    String name =
                            sc.nextLine();


                    System.out.print(
                            "Enter email: "
                    );

                    String email =
                            sc.nextLine();


                    System.out.print(
                            "Enter department: "
                    );

                    String department =
                            sc.nextLine();


                    System.out.print(
                            "Enter salary: "
                    );

                    double salary =
                            sc.nextDouble();

                    sc.nextLine();


                    Employee employee =
                            new Employee(
                                    name,
                                    email,
                                    department,
                                    salary
                            );


                    dao.addEmployee(
                            employee
                    );

                    break;


                case 2:

                    dao.viewEmployees();

                    break;


                case 3:

                    System.out.print(
                            "Enter employee ID: "
                    );

                    int searchId =
                            sc.nextInt();

                    sc.nextLine();


                    dao.searchEmployee(
                            searchId
                    );

                    break;


                case 4:

                    System.out.print(
                            "Enter employee ID: "
                    );

                    int updateId =
                            sc.nextInt();

                    sc.nextLine();


                    System.out.print(
                            "Enter new name: "
                    );

                    String newName =
                            sc.nextLine();


                    System.out.print(
                            "Enter new email: "
                    );

                    String newEmail =
                            sc.nextLine();


                    System.out.print(
                            "Enter new department: "
                    );

                    String newDepartment =
                            sc.nextLine();


                    System.out.print(
                            "Enter new salary: "
                    );

                    double newSalary =
                            sc.nextDouble();

                    sc.nextLine();


                    Employee updatedEmployee =
                            new Employee(
                                    updateId,
                                    newName,
                                    newEmail,
                                    newDepartment,
                                    newSalary
                            );


                    dao.updateEmployee(
                            updatedEmployee
                    );

                    break;


                case 5:

                    System.out.print(
                            "Enter employee ID: "
                    );

                    int deleteId =
                            sc.nextInt();

                    sc.nextLine();


                    dao.deleteEmployee(
                            deleteId
                    );

                    break;


                case 6:

                    System.out.println(
                            "Thank you!"
                    );

                    sc.close();

                    return;


                default:

                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }
}