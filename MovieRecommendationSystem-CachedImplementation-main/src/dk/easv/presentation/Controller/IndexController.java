package dk.easv.presentation.Controller;

import dk.easv.logic.LogicManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML BorderPane borderPane;
    @FXML private Button btnMenu;
    private LogicManager logicManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logicManager = new LogicManager();

        try {
            loadCenter("LogIn.fxml");
            loadTop("Menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadCenter(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/presentation/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setCenter(newScene);
    }

    private void loadTop(String file) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/presentation/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setTop(newScene);
    }

    private void loadBottom(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/presentation/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setBottom(newScene);
    }

    private void loadLeft(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/presentation/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setLeft(newScene);
    }

    private void loadRight(String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/presentation/View/" + file));
        BorderPane newScene = loader.load();

        borderPane.setRight(newScene);
    }

    public void handleMenu(ActionEvent event) {

    }

}
