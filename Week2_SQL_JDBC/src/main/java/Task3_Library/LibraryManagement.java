package Task3_Library;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagement {

    private static final String URL =
            "jdbc:mysql://localhost:3306/library_db";

    private static final String USER = "root";

    private static final String PASSWORD =
            "root";

    private static final Scanner sc =
            new Scanner(System.in);


    // Database connection
    private static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }


    // Add Book
    private static void addBook() {

        String sql =
                "INSERT INTO books " +
                        "(title, author, category, price, quantity) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            System.out.print("Enter title: ");
            String title = sc.nextLine();

            System.out.print("Enter author: ");
            String author = sc.nextLine();

            System.out.print("Enter category: ");
            String category = sc.nextLine();

            System.out.print("Enter price: ");
            double price = sc.nextDouble();

            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            sc.nextLine();

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setDouble(4, price);
            ps.setInt(5, quantity);

            ps.executeUpdate();

            System.out.println(
                    "Book added successfully!"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // View Books
    private static void viewBooks() {

        String sql = "SELECT * FROM books";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println(
                    "\n===== ALL BOOKS ====="
            );

            while (rs.next()) {

                System.out.println(
                        "ID: " +
                                rs.getInt("book_id") +

                                " | Title: " +
                                rs.getString("title") +

                                " | Author: " +
                                rs.getString("author") +

                                " | Category: " +
                                rs.getString("category") +

                                " | Price: " +
                                rs.getDouble("price") +

                                " | Quantity: " +
                                rs.getInt("quantity")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Search Book
    private static void searchBook() {

        System.out.print("Enter Book ID: ");

        int id = sc.nextInt();
        sc.nextLine();

        String sql =
                "SELECT * FROM books WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "ID: " +
                                rs.getInt("book_id")
                );

                System.out.println(
                        "Title: " +
                                rs.getString("title")
                );

                System.out.println(
                        "Author: " +
                                rs.getString("author")
                );

                System.out.println(
                        "Category: " +
                                rs.getString("category")
                );

                System.out.println(
                        "Price: " +
                                rs.getDouble("price")
                );

                System.out.println(
                        "Quantity: " +
                                rs.getInt("quantity")
                );

            } else {

                System.out.println(
                        "Book not found!"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Update Book
    private static void updateBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        System.out.print("Enter new price: ");
        double price = sc.nextDouble();

        System.out.print("Enter new quantity: ");
        int quantity = sc.nextInt();

        sc.nextLine();

        String sql =
                "UPDATE books " +
                        "SET price = ?, quantity = ? " +
                        "WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setDouble(1, price);
            ps.setInt(2, quantity);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Book updated successfully!"
                );
            } else {
                System.out.println(
                        "Book not found!"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete Book
    private static void deleteBook() {

        System.out.print("Enter Book ID: ");

        int id = sc.nextInt();
        sc.nextLine();

        String sql =
                "DELETE FROM books WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Book deleted successfully!"
                );
            } else {
                System.out.println(
                        "Book not found!"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Issue Book using Transaction
    private static void issueBook() {

        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String studentName = sc.nextLine();

        String checkSql =
                "SELECT quantity FROM books " +
                        "WHERE book_id = ?";

        String updateBookSql =
                "UPDATE books " +
                        "SET quantity = quantity - 1 " +
                        "WHERE book_id = ? AND quantity > 0";

        String issueSql =
                "INSERT INTO issue_book " +
                        "(book_id, student_name, issue_date) " +
                        "VALUES (?, ?, CURDATE())";

        try (Connection con = getConnection()) {

            con.setAutoCommit(false);

            try {

                int quantity;

                try (PreparedStatement ps =
                             con.prepareStatement(checkSql)) {

                    ps.setInt(1, bookId);

                    ResultSet rs =
                            ps.executeQuery();

                    if (!rs.next()) {

                        System.out.println(
                                "Book not found!"
                        );

                        con.rollback();
                        return;
                    }

                    quantity =
                            rs.getInt("quantity");
                }

                if (quantity <= 0) {

                    System.out.println(
                            "Book is not available!"
                    );

                    con.rollback();
                    return;
                }


                try (PreparedStatement ps =
                             con.prepareStatement(
                                     updateBookSql)) {

                    ps.setInt(1, bookId);

                    ps.executeUpdate();
                }


                try (PreparedStatement ps =
                             con.prepareStatement(issueSql)) {

                    ps.setInt(1, bookId);
                    ps.setString(2, studentName);

                    ps.executeUpdate();
                }


                con.commit();

                System.out.println(
                        "Book issued successfully!"
                );

            } catch (SQLException e) {

                con.rollback();

                System.out.println(
                        "Transaction failed. Rollback performed."
                );

                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Return Book
    private static void returnBook() {

        System.out.print("Enter Issue ID: ");

        int issueId = sc.nextInt();
        sc.nextLine();

        String findSql =
                "SELECT book_id FROM issue_book " +
                        "WHERE issue_id = ? " +
                        "AND status = 'ISSUED'";

        String updateIssueSql =
                "UPDATE issue_book " +
                        "SET return_date = CURDATE(), " +
                        "status = 'RETURNED' " +
                        "WHERE issue_id = ?";

        String updateBookSql =
                "UPDATE books " +
                        "SET quantity = quantity + 1 " +
                        "WHERE book_id = ?";

        try (Connection con = getConnection()) {

            con.setAutoCommit(false);

            try {

                int bookId;

                try (PreparedStatement ps =
                             con.prepareStatement(findSql)) {

                    ps.setInt(1, issueId);

                    ResultSet rs =
                            ps.executeQuery();

                    if (!rs.next()) {

                        System.out.println(
                                "Issue record not found!"
                        );

                        con.rollback();
                        return;
                    }

                    bookId =
                            rs.getInt("book_id");
                }


                try (PreparedStatement ps =
                             con.prepareStatement(
                                     updateIssueSql)) {

                    ps.setInt(1, issueId);
                    ps.executeUpdate();
                }


                try (PreparedStatement ps =
                             con.prepareStatement(
                                     updateBookSql)) {

                    ps.setInt(1, bookId);
                    ps.executeUpdate();
                }


                con.commit();

                System.out.println(
                        "Book returned successfully!"
                );

            } catch (SQLException e) {

                con.rollback();

                System.out.println(
                        "Transaction failed. Rollback performed."
                );

                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Search by Category
    private static void searchByCategory() {

        System.out.print("Enter category: ");

        String category = sc.nextLine();

        String sql =
                "SELECT * FROM books " +
                        "WHERE category = ?";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, category);

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        rs.getInt("book_id") +
                                " | " +
                                rs.getString("title") +
                                " | " +
                                rs.getString("author") +
                                " | Quantity: " +
                                rs.getInt("quantity")
                );
            }

            if (!found) {
                System.out.println(
                        "No books found!"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // GROUP BY
    private static void groupByCategory() {

        String sql =
                "SELECT category, COUNT(*) AS total " +
                        "FROM books " +
                        "GROUP BY category";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs =
                     st.executeQuery(sql)) {

            System.out.println(
                    "\n===== BOOKS BY CATEGORY ====="
            );

            while (rs.next()) {

                System.out.println(
                        rs.getString("category") +
                                " -> " +
                                rs.getInt("total") +
                                " books"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // HAVING
    private static void havingCategory() {

        String sql =
                "SELECT category, COUNT(*) AS total " +
                        "FROM books " +
                        "GROUP BY category " +
                        "HAVING COUNT(*) >= 2";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs =
                     st.executeQuery(sql)) {

            System.out.println(
                    "\n===== HAVING RESULT ====="
            );

            while (rs.next()) {

                System.out.println(
                        rs.getString("category") +
                                " -> " +
                                rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Batch Processing
    private static void batchAddBooks() {

        String sql =
                "INSERT INTO books " +
                        "(title, author, category, price, quantity) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    "Spring Boot Basics"
            );
            ps.setString(
                    2,
                    "Craig Walls"
            );
            ps.setString(
                    3,
                    "Programming"
            );
            ps.setDouble(4, 600);
            ps.setInt(5, 4);

            ps.addBatch();


            ps.setString(
                    1,
                    "SQL Complete"
            );
            ps.setString(
                    2,
                    "John Viescas"
            );
            ps.setString(
                    3,
                    "Database"
            );
            ps.setDouble(4, 550);
            ps.setInt(5, 3);

            ps.addBatch();


            ps.setString(
                    1,
                    "Java JDBC"
            );
            ps.setString(
                    2,
                    "David Heffelfinger"
            );
            ps.setString(
                    3,
                    "Programming"
            );
            ps.setDouble(4, 700);
            ps.setInt(5, 2);

            ps.addBatch();


            int[] results =
                    ps.executeBatch();

            System.out.println(
                    results.length +
                            " books added using batch processing."
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Main Menu
    public static void main(String[] args) {

        while (true) {

            System.out.println(
                    "\n================================"
            );

            System.out.println(
                    "      LIBRARY MANAGEMENT"
            );

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "1. Add Book"
            );

            System.out.println(
                    "2. View All Books"
            );

            System.out.println(
                    "3. Search Book by ID"
            );

            System.out.println(
                    "4. Update Book"
            );

            System.out.println(
                    "5. Delete Book"
            );

            System.out.println(
                    "6. Issue Book"
            );

            System.out.println(
                    "7. Return Book"
            );

            System.out.println(
                    "8. Search by Category"
            );

            System.out.println(
                    "9. Group By Category"
            );

            System.out.println(
                    "10. Having Clause"
            );

            System.out.println(
                    "11. Batch Add Books"
            );

            System.out.println(
                    "12. Exit"
            );

            System.out.print(
                    "Enter choice: "
            );

            int choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    updateBook();
                    break;

                case 5:
                    deleteBook();
                    break;

                case 6:
                    issueBook();
                    break;

                case 7:
                    returnBook();
                    break;

                case 8:
                    searchByCategory();
                    break;

                case 9:
                    groupByCategory();
                    break;

                case 10:
                    havingCategory();
                    break;

                case 11:
                    batchAddBooks();
                    break;

                case 12:

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