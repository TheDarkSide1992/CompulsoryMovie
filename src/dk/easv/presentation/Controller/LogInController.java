package dk.easv.presentation.Controller;

import dk.easv.logic.LogicManager;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML private MFXTextField userId;
    @FXML private MFXButton btnLogIn;
    @FXML private MFXButton btnSignUp;
    @FXML private MFXPasswordField passwordField;

    private LogicManager logicManager;
    private boolean loginSauces = false;
    private String userString = "";
    private String passwordString = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogIn.setDisable(true);
        btnSignUp.setDisable(false);
    }

    public void logIn(ActionEvent actionEvent) {
        if(!userId.getText().trim().equals(passwordField.getText().trim())) return;

        loginSauces = true;
        userString = userId.getText();
        passwordString = passwordField.getText();
        Stage stage = (Stage) btnLogIn.getScene().getWindow();
        stage.close();
    }

    public void signUp(ActionEvent actionEvent) {

    }

    public void checkIfAnyInput(KeyEvent keyEvent) {
        if(!userId.getText().isEmpty() && !passwordField.getText().isEmpty()){
            btnLogIn.setDisable(false);
        } else {
            btnLogIn.setDisable(true);
        }
    }

    public boolean isLoginSuccessful() {
        return loginSauces;
    }

    public String getUserString() {return  userString;}

}
