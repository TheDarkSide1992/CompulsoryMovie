package dk.easv.GUI.Controller;

import dk.easv.BLL.LogicManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML BorderPane borderPane;
    private LogicManager logicManager;

    private String userName = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logicManager = new LogicManager();

        try {
            loadLogIn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLogIn() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/GUI/View/LogIn.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        LogInController logInController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Log-In");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.showAndWait();

        //Gets data form login controller
        userName = logInController.getUserString();

        if(logInController.isLoginSuccessful()){
            loadLeft("MenuBar.fxml");
            loadCenter("MovieWindow.fxml");

        } else {
            System.exit(0);
        }

    }

    private void loadCenter(String file) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("presentation/View/" + file));
        //borderPane.setCenter(root);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/GUI/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setCenter(newScene);
    }

    private void loadTop(String file) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/GUI/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setTop(newScene);
    }

    private void loadBottom(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/GUI/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setBottom(newScene);
    }

    private void loadLeft(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/GUI/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setLeft(newScene);
    }

    private void loadRight(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/GUI/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setRight(newScene);
    }

    public void handleMenu(ActionEvent event) {

    }

}
