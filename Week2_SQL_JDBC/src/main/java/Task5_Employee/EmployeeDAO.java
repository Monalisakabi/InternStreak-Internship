package Task5_Employee;

import java.sql.*;

public class EmployeeDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/employee_db";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "root";


    // Database connection
    private Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }


    // Add employee
    public void addEmployee(
            Employee employee) {

        String sql =
                "INSERT INTO employee " +
                        "(name, email, department, salary) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection con =
                     getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    employee.getName()
            );

            ps.setString(
                    2,
                    employee.getEmail()
            );

            ps.setString(
                    3,
                    employee.getDepartment()
            );

            ps.setDouble(
                    4,
                    employee.getSalary()
            );

            ps.executeUpdate();

            System.out.println(
                    "Employee added successfully!"
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // View employees
    public void viewEmployees() {

        String sql =
                "SELECT * FROM employee";

        try (Connection con =
                     getConnection();

             Statement st =
                     con.createStatement();

             ResultSet rs =
                     st.executeQuery(sql)) {

            System.out.println(
                    "\n===== EMPLOYEE LIST ====="
            );

            while (rs.next()) {

                System.out.println(
                        "ID: " +
                                rs.getInt("id") +

                                " | Name: " +
                                rs.getString("name") +

                                " | Email: " +
                                rs.getString("email") +

                                " | Department: " +
                                rs.getString("department") +

                                " | Salary: " +
                                rs.getDouble("salary")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // Search employee
    public void searchEmployee(int id) {

        String sql =
                "SELECT * " +
                        "FROM employee " +
                        "WHERE id = ?";

        try (Connection con =
                     getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "ID: " +
                                rs.getInt("id")
                );

                System.out.println(
                        "Name: " +
                                rs.getString("name")
                );

                System.out.println(
                        "Email: " +
                                rs.getString("email")
                );

                System.out.println(
                        "Department: " +
                                rs.getString("department")
                );

                System.out.println(
                        "Salary: " +
                                rs.getDouble("salary")
                );

            } else {

                System.out.println(
                        "Employee not found!"
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // Update employee
    public void updateEmployee(
            Employee employee) {

        String sql =
                "UPDATE employee SET " +
                        "name = ?, " +
                        "email = ?, " +
                        "department = ?, " +
                        "salary = ? " +
                        "WHERE id = ?";

        try (Connection con =
                     getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    employee.getName()
            );

            ps.setString(
                    2,
                    employee.getEmail()
            );

            ps.setString(
                    3,
                    employee.getDepartment()
            );

            ps.setDouble(
                    4,
                    employee.getSalary()
            );

            ps.setInt(
                    5,
                    employee.getId()
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee updated successfully!"
                );

            } else {

                System.out.println(
                        "Employee not found!"
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // Delete employee
    public void deleteEmployee(int id) {

        String sql =
                "DELETE FROM employee " +
                        "WHERE id = ?";

        try (Connection con =
                     getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee deleted successfully!"
                );

            } else {

                System.out.println(
                        "Employee not found!"
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}