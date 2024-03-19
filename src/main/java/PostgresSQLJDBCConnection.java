import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class PostgresSQLJDBCConnection {
    private static Connection conn; // allows other functions to access conn
    public static void getAllStudents(){
        try {
                Statement stmt = conn.createStatement();
                String SQL = "SELECT * FROM students";
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String emailAddress = rs.getString("email");
                    Date enrollDate = rs.getDate("enrollment_date");
                    System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + emailAddress + ", Enrollment Date: " + enrollDate);
                }
        } catch (SQLException e) {
            System.out.println("Database fully written.");
        }
    }

    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, enrollment_date);
            pstmt.executeUpdate();
            System.out.println("Data inserted using PreparedStatement.");
            getAllStudents();
        } catch (SQLException e) {
            System.out.println("Student: " + first_name + " " + last_name + " already exists within database.");
        }
    }

    public static void deleteStudent(int student_id) {
        String SQL = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, student_id);
            int rowDeleted = pstmt.executeUpdate();
            if (rowDeleted > 0){ // executeUpdate returns 1 if a row was changed
                System.out.println("Student: " + student_id + " successfully deleted using PreparedStatement.");
                getAllStudents();
            } else {
                System.out.println("No student with id: " + student_id);
            }
        } catch (SQLException e) {
            System.out.println("Database is empty, no students can be deleted.");
        }
    }

    public static void updateStudentEmail(int student_id, String new_email){
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            int emailUpdated = pstmt.executeUpdate();
            if (emailUpdated > 0){ // executeUpdate returns 1 if a row was changed
                System.out.println("Student: " + student_id + " successfully updated email using PreparedStatement.");
                getAllStudents();
            } else {
                System.out.println("No student with id: " + student_id);
            }
        } catch (SQLException e) {
            System.out.println("Database is empty, no students can be deleted.");
        }
    }

    public static void main(String[] args) {
        // JDBC & Database credentials
//        String url = "jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>";
//        String user = "<USERNAME>";
//        String password = "<PASSWORD>";
        // personal credentials for testing purposes
        String url = "jdbc:postgresql://localhost:5432/a3q1";
        String user = "warlord";
        String password = "comp3005";
        try { // Load PostgresSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to PostgresSQL successfully!\n");
                Date enrollmentDate = Date.valueOf("2021-09-08");
//                getAllStudents(); // Read operation
//                addStudent("Mahad", "Ahmed", "mahadahmed3@cmail.carleton.ca", enrollmentDate); // Create operation
//                updateStudentEmail(3, "newmahad@cmail.carleton.ca"); // Update operation
                deleteStudent(2); // Delete operation
            } else {
                System.out.println("Failed to establish connection.");
            }
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
