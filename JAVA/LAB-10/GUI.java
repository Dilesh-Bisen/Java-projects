import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isValidCredentials(username, password)) {
                    showCollegeManagementPage();
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

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Grade");
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));

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
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        String grade = gradeField.getText();
        model.addRow(new Object[] { id, name, grade });

        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("ADD," + id + "," + name + "," + grade);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nStudent(added) - \nID    : " + id + " \nName  : " + name + "\nGrade : " + grade);
    }

    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) model.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String grade = gradeField.getText();
            model.setValueAt(name, selectedRow, 1);
            model.setValueAt(grade, selectedRow, 2);

            updateStudent(id, name, grade);

            System.out.println("\nStudent(edited) - \nID    : " + id + " \nName  : " + name + " \nGrade : " + grade);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) model.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String grade = gradeField.getText();
            model.removeRow(selectedRow);

            deleteStudent(id);

            System.out.println("\nStudent(deleted) - \nID    : " + id + " \nName  : " + name + " \nGrade : " + grade);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void updateStudent(String id, String name, String grade) {
        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("UPDATE," + id + "," + name + "," + grade);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(String id) {
        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("DELETE," + id);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
