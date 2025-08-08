import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Hotel {
    private String name;
    private Room[] rooms;

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel_reservation_db";
    static final String JDBC_USER = "NAME";  
    static final String JDBC_PASS = "PASSWORD"; 

    public Hotel(String name, Room[] rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public void checkIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number to check in: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();  // Consume newline

        // JDBC connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            String query = "SELECT occupied FROM rooms WHERE room_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                boolean occupied = rs.getBoolean("occupied");

                if (occupied) {
                    System.out.println("Room is already occupied.");
                } else {
                    System.out.println("Enter guest name: ");
                    String guestName = sc.nextLine();

                    // Update room status in the database
                    String updateSQL = "UPDATE rooms SET guest_name = ?, occupied = ? WHERE room_number = ?";
                    pstmt = conn.prepareStatement(updateSQL);
                    pstmt.setString(1, guestName);
                    pstmt.setBoolean(2, true);
                    pstmt.setInt(3, roomNumber);
                    pstmt.executeUpdate();

                    System.out.println("Guest " + guestName + " checked into room " + roomNumber);
                }
            } else {
                System.out.println("Invalid room number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkOut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number to check out: ");
        int roomNumber = sc.nextInt();

        // JDBC connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            String query = "SELECT occupied, guest_name FROM rooms WHERE room_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                boolean occupied = rs.getBoolean("occupied");
                String guestName = rs.getString("guest_name");

                if (!occupied) {
                    System.out.println("Room is already available.");
                } else {
                    // Update room status in the database
                    String updateSQL = "UPDATE rooms SET guest_name = '', occupied = ? WHERE room_number = ?";
                    pstmt = conn.prepareStatement(updateSQL);
                    pstmt.setBoolean(1, false);
                    pstmt.setInt(2, roomNumber);
                    pstmt.executeUpdate();

                    System.out.println("Guest " + guestName + " checked out of room " + roomNumber);
                }
            } else {
                System.out.println("Invalid room number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAvailableRooms() {
        // JDBC connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            String query = "SELECT room_number FROM rooms WHERE occupied = FALSE";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Available Rooms:");
            while (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                System.out.println("Room " + roomNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
