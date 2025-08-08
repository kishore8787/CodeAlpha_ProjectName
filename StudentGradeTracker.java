import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentGradeTracker {

    // Database credentials
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_grades_db";
    static final String JDBC_USER = "USER";  
    static final String JDBC_PASS = "PASSWORD";

    public static void main(String[] args) {

        // Create Scanner object to get input from the user
        Scanner sc = new Scanner(System.in);

        // Get the total number of students input from the user
        System.out.println("Enter the total number of Students: ");
        int totalStudent = sc.nextInt();
        sc.nextLine();  // Consume newline

        // Create arrays to store students' names and marks
        String[] studentNames = new String[totalStudent];
        int[] studentMarks = new int[totalStudent];

        // Initialize variables to calculate totals
        int totalMarks = 0;
        int maxMarks = Integer.MIN_VALUE;
        int minMarks = Integer.MAX_VALUE;

        // JDBC connection
        Connection conn = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Prepare SQL query to insert student marks
            String insertSQL = "INSERT INTO student_grades (student_name, marks) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // Get student names and marks, and store them in the database
            for (int i = 0; i < totalStudent; i++) {
                System.out.println("Enter the name of Student " + (i + 1) + ": ");
                studentNames[i] = sc.nextLine();

                System.out.println("Enter marks obtained by " + studentNames[i] + " (Out of 100):");
                studentMarks[i] = sc.nextInt();
                sc.nextLine();  // Consume newline

                // Validate marks are within the range
                while (studentMarks[i] < 0 || studentMarks[i] > 100) {
                    System.out.println("Invalid Marks. Please enter valid marks for " + studentNames[i] + " (out of 100):");
                    studentMarks[i] = sc.nextInt();
                    sc.nextLine();  // Consume newline
                }

                // Insert into the database
                pstmt.setString(1, studentNames[i]);
                pstmt.setInt(2, studentMarks[i]);
                pstmt.executeUpdate();

                // Calculate total marks, and track max and min marks
                totalMarks += studentMarks[i];
                if (studentMarks[i] > maxMarks) maxMarks = studentMarks[i];
                if (studentMarks[i] < minMarks) minMarks = studentMarks[i];
            }

            // Calculate and display average score
            double averageScore = (double) totalMarks / totalStudent;
            System.out.println("Student's Average Score: " + averageScore);
            System.out.println("Maximum Marks obtained by student: " + maxMarks);
            System.out.println("Minimum Marks obtained by student: " + minMarks);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close scanner and database connection
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            sc.close();
        }
    }
}
