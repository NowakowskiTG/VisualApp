import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Font font = Font.font("Calibre", FontWeight.BOLD, 30);
        Font fontText = Font.font("Times New Roman", FontPosture.ITALIC, 17);
        Color color = Color.web("#E41E0E");

        Button nameButton = new Button("Confirm name");
        nameButton.setLayoutX(200);
        nameButton.setLayoutY(100);

        Button ageButton = new Button("Confirm age");
        ageButton.setLayoutX(200);
        ageButton.setLayoutY(160);

        Button passwordButton = new Button("Confirm password");
        passwordButton.setLayoutX(200);
        passwordButton.setLayoutY(220);

        Button logInButton = new Button("LOG IN!");
        logInButton.setLayoutX(10);
        logInButton.setLayoutY(800);
        logInButton.setPrefWidth(200);
        logInButton.setTextFill(color.GREEN);

        Label label = new Label();
        label.setText("LOGIN TO MY APP!");
        label.setFont(font);
        label.setLayoutX(30);
        label.setLayoutY(20);
        label.setTextFill(Paint.valueOf("#E12111"));

        Text textName = new Text();
        textName.setFont(fontText);
        textName.setText("Name: ");
        textName.setX(10);
        textName.setY(90);

        TextField fieldName = new TextField();
        fieldName.setPromptText("name");
        fieldName.setLayoutX(10);
        fieldName.setLayoutY(100);
        nameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textName.getText() == null) {
                    nameButton.setText("DONE!");
                    nameButton.setDisable(true);
                    fieldName.setDisable(true);

                } else {
                    fieldName.setPromptText("no name");
                }

            }
        });

        Text textAge = new Text();
        textAge.setFont(fontText);
        textAge.setText("Age: ");
        textAge.setX(10);
        textAge.setY(150);

        TextField fieldAge = new TextField();
        fieldAge.setPromptText("age");
        fieldAge.setLayoutX(10);
        fieldAge.setLayoutY(160);
        ageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int age = Integer.parseInt(fieldAge.getText());
                if(age > 0){
                    ageButton.setText("DONE!");
                    ageButton.setDisable(true);
                    fieldAge.setDisable(true);
                } else {
                    ageButton.setText("wrong age!");
                }

            }
        });

        Text textPassword = new Text();
        textPassword.setFont(fontText);
        textPassword.setText("Create password: ");
        textPassword.setX(10);
        textPassword.setY(210);

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(10);
        passwordField.setLayoutY(220);
        passwordField.setPromptText("password");
        passwordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passwordButton.setText("DONE!");
                passwordButton.setDisable(true);
                passwordField.setDisable(true);
            }
        });

        Text genderText = new Text();
        genderText.setFont(fontText);
        genderText.setText("Pick gender: ");
        genderText.setX(10);
        genderText.setY(270);

        String[] genderPick = {"Male", "Female", "NONE"};

        ChoiceBox<String> genderBox = new ChoiceBox<>();
        genderBox.setLayoutX(10);
        genderBox.setLayoutY(280);
        genderBox.setPrefWidth(100);
        genderBox.getItems().addAll(genderPick);
        genderBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                genderBox.setDisable(true);
            }
        });

        Button reset = new Button("Edit Fields");
        reset.setLayoutX(200);
        reset.setLayoutY(280);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fieldName.setDisable(false);
                nameButton.setDisable(false);
                nameButton.setText("Confirm name");
                ageButton.setDisable(false);
                ageButton.setText("Confirm age");
                fieldAge.setDisable(false);
                passwordButton.setDisable(false);
                passwordButton.setText("Confirm password");
                passwordField.setDisable(false);
                genderBox.setDisable(false);
            }
        });

        Image personImage = new Image("wzrost.jpg");
        ImageView personImageView = new ImageView(personImage);
        personImageView.setPreserveRatio(true);
        personImageView.setX(10);
        personImageView.setY(415);
        personImageView.setFitWidth(250);

        ProgressBar heightProgressBar = new ProgressBar();
        heightProgressBar.setRotate(270);
        heightProgressBar.setLayoutX(100);
        heightProgressBar.setLayoutY(577);
        heightProgressBar.setPrefWidth(354);
        heightProgressBar.setPrefHeight(30);
        heightProgressBar.setProgress(0);

        Text tallText = new Text();
        tallText.setFont(fontText);
        tallText.setText("How tall are You? (in 'cm')");
        tallText.setX(10);
        tallText.setY(330);

        Slider tallSlider = new Slider(0, 200, 0);
        tallSlider.setLayoutX(5);
        tallSlider.setLayoutY(340);
        tallSlider.setPrefWidth(380);
        tallSlider.setShowTickMarks(true);
        tallSlider.setShowTickLabels(true);
        tallSlider.setMajorTickUnit(5);
        tallSlider.setMinorTickCount(5);
        tallSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double valuePercent = newValue.doubleValue() / 100 / 2;
                heightProgressBar.setProgress(valuePercent);
            }
        });

        ProgressIndicator loginProgress = new ProgressIndicator();
        loginProgress.setLayoutX(230);
        loginProgress.setLayoutY(800);
        loginProgress.setVisible(false);

        Thread progressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    double progressValue = 0.01 * i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loginProgress.setProgress(progressValue);
                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        logInButton.setText("LOGIN SUCCESSFULLY!");
                        logInButton.setDisable(true);
                    }
                });
            }
        });

        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("Name: " + fieldName.getText());
                System.out.println("Age: " + fieldAge.getText());
                System.out.println("Created password: " + passwordField.getText());
                System.out.println("Gender: " + genderBox.getValue());
                System.out.println("Height: " + (int) tallSlider.getValue() + " cm");
                reset.setDisable(true);
                tallSlider.setDisable(true);
                loginProgress.setVisible(true);
                progressThread.start();
            }
        });


        Group group = new Group();
        group.getChildren().add(label);
        group.getChildren().add(textName);
        group.getChildren().add(fieldName);
        group.getChildren().add(textAge);
        group.getChildren().add(fieldAge);
        group.getChildren().add(textPassword);
        group.getChildren().add(passwordField);
        group.getChildren().add(nameButton);
        group.getChildren().add(ageButton);
        group.getChildren().add(passwordButton);
        group.getChildren().add(reset);
        group.getChildren().add(genderText);
        group.getChildren().add(genderBox);
        group.getChildren().add(tallText);
        group.getChildren().add(tallSlider);
        group.getChildren().add(personImageView);
        group.getChildren().add(heightProgressBar);
        group.getChildren().add(logInButton);
        group.getChildren().add(loginProgress);


        Scene scene = new Scene(group, 400, 900, Color.CADETBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MyApp");
        primaryStage.show();
    }
}
