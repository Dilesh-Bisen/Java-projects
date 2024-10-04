import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.beans.property.SimpleStringProperty;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    private TableView<Object[]> table;
    private TextField idField;
    private TextField nameField;
    private TextField gradeField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("College Management System");
        primaryStage.setResizable(false);

        BorderPane root = new BorderPane();

        GridPane loginPane = createLoginPanel(primaryStage);
        root.setCenter(loginPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createLoginPanel(Stage primaryStage) {
        GridPane panel = new GridPane();
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));
        panel.setHgap(5);
        panel.setVgap(5);
        panel.setStyle("-fx-background-color: linear-gradient(to bottom, #7F7FD5, #86A8E7, #91EAE4)");
        Label titleLabel = new Label("College Management System");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: purple;");
        panel.add(titleLabel, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: black;");
        panel.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        panel.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: black;");
        panel.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        panel.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: black; -fx-font-weight: bold;");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (isValidCredentials(username, password)) {
                primaryStage.setScene(new Scene(showCollegeManagementPage(primaryStage), 800, 600));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }
        });

        panel.add(loginButton, 0, 3, 2, 1);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        panel.getColumnConstraints().add(columnConstraints);

        return panel;
    }

    private boolean isValidCredentials(String username, String password) {
        return username.equals("Dilesh") && password.equals("Dilesh@123");
    }

    @SuppressWarnings("unchecked")
    private BorderPane showCollegeManagementPage(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        table = new TableView<>();
        table.setStyle("-fx-background-color: white;");

        TableColumn<Object[], String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> {
            Object[] rowData = cellData.getValue();
            return new SimpleStringProperty((String) rowData[0]);
        });

        TableColumn<Object[], String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> {
            Object[] rowData = cellData.getValue();
            return new SimpleStringProperty((String) rowData[1]);
        });

        TableColumn<Object[], String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(cellData -> {
            Object[] rowData = cellData.getValue();
            return new SimpleStringProperty((String) rowData[2]);
        });

        table.getColumns().addAll(idColumn, nameColumn, gradeColumn);

        idColumn.prefWidthProperty().bind(table.widthProperty().divide(3));
        nameColumn.prefWidthProperty().bind(table.widthProperty().divide(3));
        gradeColumn.prefWidthProperty().bind(table.widthProperty().divide(3));

        idField = new TextField();
        nameField = new TextField();
        gradeField = new TextField();

        Button addButton = new Button("Add Student");
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: black; -fx-font-weight: bold;");
        addButton.setOnAction(e -> addStudent());
        Button editButton = new Button("Edit Student");
        editButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black; -fx-font-weight: bold;");
        editButton.setOnAction(e -> editStudent());
        Button deleteButton = new Button("Delete Student");
        deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: black; -fx-font-weight: bold;");
        deleteButton.setOnAction(e -> deleteStudent());
        Button visitWebsiteButton = new Button("Visit Website");
        visitWebsiteButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: black; -fx-font-weight: bold;");
        visitWebsiteButton.setOnAction(e -> openWebsite("https://ieeexplore.ieee.org/document/10380594"));

        HBox buttonsBox = new HBox(10, addButton, editButton, deleteButton, visitWebsiteButton);
        buttonsBox.setAlignment(Pos.CENTER);
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(5);
        inputGrid.addRow(0, new Label("ID:"), idField);
        inputGrid.addRow(1, new Label("Name:"), nameField);
        inputGrid.addRow(2, new Label("Grade:"), gradeField);
        inputGrid.setAlignment(Pos.CENTER);

        root.setTop(buttonsBox);
        root.setCenter(inputGrid);
        root.setBottom(table);

        return root;
    }

    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        String grade = gradeField.getText();
        Object[] data = { id, name, grade };
        table.getItems().add(data);
    }

    private void editStudent() {
        Object[] selectedRow = table.getSelectionModel().getSelectedItem();
        if (selectedRow != null) {
            selectedRow[0] = idField.getText();
            selectedRow[1] = nameField.getText();
            selectedRow[2] = gradeField.getText();
            table.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Edit Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to edit.");
            alert.showAndWait();
        }
    }

    private void deleteStudent() {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            table.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        }
    }

    private void openWebsite(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
