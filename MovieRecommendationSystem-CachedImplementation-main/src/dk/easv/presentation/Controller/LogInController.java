package dk.easv.presentation.Controller;

import dk.easv.logic.LogicManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML private Button btnLogIn, btnSignUp;
    @FXML private PasswordField passwordField;
    @FXML private TextField userId;

    private LogicManager logicManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logicManager = new LogicManager();
        btnLogIn.setDisable(true);
        btnSignUp.setDisable(false);
    }

    public void logIn(ActionEvent actionEvent) {
        logicManager = new LogicManager();
        System.out.println("Username is: " + userId.getText() + "\nPassword is: " + passwordField.getText());

        Stage stage = (Stage) btnLogIn.getScene().getWindow();
        stage.close();
    }

    public void signUp(ActionEvent actionEvent) {
       logicManager = new LogicManager();
       System.out.println("Username is: " + userId.getText() + "\nPassword is: " + passwordField.getText());
       logicManager.openNewView("Index.fxml", "Movie recommendation system");
    }

    public void checkIfAnyInput(KeyEvent keyEvent) {
        if(!userId.getText().equals("")){
            if(!passwordField.getText().equals("")){
                btnSignUp.setDisable(false);
                btnLogIn.setDisable(false);
            }
            else {
                btnSignUp.setDisable(true);
                btnLogIn.setDisable(true);
            }
        }
        else {
            btnSignUp.setDisable(true);
            btnLogIn.setDisable(true);
        }
    }
}
