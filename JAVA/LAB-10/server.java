// javac -cp "d:\Other Downloads\mysql-connector-j-9.0.0\mysql-connector-j-9.0.0\mysql-connector-j-9.0.0.jar;C:\Users\1dile\OneDrive\Desktop\code\Lab Assignment\JAVA\LAB-10" server.java
// java -cp "d:\Other Downloads\mysql-connector-j-9.0.0\mysql-connector-j-9.0.0\mysql-connector-j-9.0.0.jar;C:\Users\1dile\OneDrive\Desktop\code\Lab Assignment\JAVA\LAB-10" server.java

import java.io.*;
import java.net.*;
import java.sql.*;

public class server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Connection connection = null;
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("\nServer started. Waiting for a client response...");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_management", "root","MySQL@123");
            connection.setAutoCommit(false);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("\nClient connected.");

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("\nClient: " + inputLine);
                        String[] data = inputLine.split(",");
                        if (data.length == 4) {
                            // Add student
                            if (data[0].equals("ADD")) {
                                addStudent(connection, data[1], data[2], data[3]);
                                out.println("\nServer received: " + inputLine);
                            } else {
                                out.println("\nInvalid request.");
                            }
                        } else if (data.length == 4) {
                            // Update student
                            if (data[0].equals("UPDATE")) {
                                updateStudent(connection, data[1], data[2], data[3]);
                                out.println("\nServer received: " + inputLine);
                            } else {
                                out.println("\nInvalid request.");
                            }
                        } else if (data.length == 2) {
                            // Delete student
                            if (data[0].equals("DELETE")) {
                                deleteStudent(connection, data[1]);
                                out.println("\nServer received: " + inputLine);
                            } else {
                                out.println("\nInvalid request.");
                            }
                        } else {
                            out.println("\nInvalid input format.");
                        }
                    }
                } catch (SocketException e) {
                    System.out.println("Client connection closed.");
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void addStudent(Connection connection, String id, String name, String grade) throws SQLException {
        String query = "INSERT INTO students (id, name, grade) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, grade);
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }

    private static void updateStudent(Connection connection, String id, String name, String grade) throws SQLException {
        String query = "UPDATE students SET name=?, grade=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, grade);
            preparedStatement.setString(3, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                connection.commit();
            } else {
                System.out.println("No student found with ID: " + id);
            }
        }
    }

    private static void deleteStudent(Connection connection, String id) {
        String query = "DELETE FROM students WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student with ID " + id + " deleted successfully.");
                connection.commit();
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                System.out.println("Rollback failed: " + rollbackException.getMessage());
            }
        }
    }
}
