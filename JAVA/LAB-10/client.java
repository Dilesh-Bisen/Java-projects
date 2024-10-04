import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class client {
    public static void main(String[] args) {
        try {

            System.out.println("\nAttempting to connect to the server...");
           
            Socket socket = new Socket("localhost", 12345);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("\nHello Dilesh server is running properly !!!!!");

            String response = in.readLine();
            System.out.println("\nServer response: " + response);

             String statusMessage = "Connection to the server has been established successfully.";
            System.out.println(statusMessage);
            JOptionPane.showMessageDialog(null, statusMessage, "Connection Status", JOptionPane.INFORMATION_MESSAGE); 

            out.close();
            in.close();
            socket.close();

            SwingUtilities.invokeLater(() -> {
                GUI gui = new GUI(); 
                gui.setVisible(true);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
