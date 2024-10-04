import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;
import java.net.URI;

public class GUI extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JTextField idField;
    private JTextField nameField;
    private JTextField gradeField;

    public GUI() {
        setTitle("College Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel loginPanel = createLoginPanel();

        add(loginPanel);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("College Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check credentials
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isValidCredentials(username, password)) {
                    showCollegeManagementPage();
                    // Connect to the remote server
                    // connectToServer();
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Invalid username or password. Please try again.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        return panel;
    }

    private boolean isValidCredentials(String username, String password) {
        return username.equals("Dilesh") && password.equals("Dilesh@123");
    }

    private void showCollegeManagementPage() {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();

        // College management page setup
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Grade");
        table = new JTable(model);

        JButton addButton = new JButton("Add Student");
        JButton editButton = new JButton("Edit Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton visitWebsiteButton = new JButton("Visit Website");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(visitWebsiteButton);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        idField = new JTextField(10);
        nameField = new JTextField(10);
        gradeField = new JTextField(10);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        visitWebsiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWebsite("https://ieeexplore.ieee.org/document/10380594");
            }
        });

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.SOUTH);
    }

    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        String grade = gradeField.getText();
        model.addRow(new Object[] { id, name, grade });
    }

    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = idField.getText();
            String name = nameField.getText();
            String grade = gradeField.getText();
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(name, selectedRow, 1);
            model.setValueAt(grade, selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void openWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // private void connectToServer() {
    //     new Thread(new Runnable() {
    //         @Override
    //         public void run() {
    //             try {
    //                 @SuppressWarnings("deprecation")
    //                 URL url = new URL("http://localhost");
    //                 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //                 connection.setRequestMethod("GET");

    //                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //                 StringBuilder response = new StringBuilder();
    //                 String line;
    //                 while ((line = reader.readLine()) != null) {
    //                     response.append(line);
    //                 }
    //                 reader.close();

    //                 // Do something with the response
    //                 System.out.println("Response from server: " + response.toString());
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }).start();
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
