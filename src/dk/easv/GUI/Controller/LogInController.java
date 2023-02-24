package dk.easv.GUI.Controller;

import dk.easv.BLL.LogicManager;
import dk.easv.GUI.Model.AppModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML private Label lblErrorMessage;
    @FXML private MFXTextField userId;
    @FXML private MFXButton btnLogIn;
    @FXML private MFXButton btnSignUp;
    @FXML private MFXPasswordField passwordField;

    private AppModel model = new AppModel();

    private LogicManager logicManager;
    private boolean loginSauces = false;
    private static String userString = "";
    private String passwordString = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogIn.setDisable(true);
        btnSignUp.setDisable(false);
    }


    public void logIn(ActionEvent actionEvent) {



        if(!userId.getText().trim().equals(passwordField.getText().trim())) {
            lblErrorMessage.setText("Current password does not match this user");
            return;
        }

        model.loadUsers();
        userString = userId.getText();
        model.loadData(model.getUser(userString));
        boolean validUser = model.loginUserFromUsername(userString);
        if (validUser == true){
            loginSauces = true;
            passwordString = passwordField.getText();
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            stage.close();
        }else {
            lblErrorMessage.setText("User ID or Password is Incorrect");
        }

    }

    public void signUp(ActionEvent actionEvent) {
        model.loadUsers();
        model.loadData(model.getUser("Georgi Facello"));
        boolean validUser = model.loginUserFromUsername("Georgi Facello");
        userString = "Georgi Facello";
        if (validUser == true){
            loginSauces = true;
            passwordString = "Georgi Facello";
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            stage.close();
        }
    }

    public void checkIfAnyInput(KeyEvent keyEvent) {
        if(!userId.getText().isEmpty() && !passwordField.getText().isEmpty()){
            btnLogIn.setDisable(false);
            lblErrorMessage.setText("");
        } else if (userId.getText().isEmpty()){
            btnLogIn.setDisable(true);
            lblErrorMessage.setText("Missing User ID");
        } else if (passwordField.getText().isEmpty()){
            btnLogIn.setDisable(true);
            lblErrorMessage.setText("Missing Password");
        } else {
            lblErrorMessage.setText("Missing both User ID & Password");
        }
    }

    public boolean isLoginSuccessful() {
        return loginSauces;
    }

    public static String getUserString() {return userString;}

}
