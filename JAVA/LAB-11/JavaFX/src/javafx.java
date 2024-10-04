import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class javafx extends Application {

    private TextField studentNameField;
    private TextField courseIdField;
    private Label outputLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("College Management System");

        studentNameField = new TextField();
        studentNameField.setPromptText("Enter student name");

        courseIdField = new TextField();
        courseIdField.setPromptText("Enter course ID");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registerStudent();
            }
        });

        Button enrollButton = new Button("Enroll");
        enrollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enrollCourse();
            }
        });

        outputLabel = new Label();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(studentNameField, courseIdField, registerButton, enrollButton, outputLabel);
        layout.setPrefWidth(300);
        layout.setPrefHeight(200);
        layout.setStyle("-fx-background-color: lightblue; -fx-padding: 10px;");

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void registerStudent() {
        String studentName = studentNameField.getText();
        outputLabel.setText("Student '" + studentName + "' registered successfully!");
    }

    private void enrollCourse() {
        String studentName = studentNameField.getText();
        String courseId = courseIdField.getText();
        outputLabel.setText("Student '" + studentName + "' enrolled in course '" + courseId + "'");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
