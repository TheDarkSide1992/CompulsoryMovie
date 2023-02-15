package dk.easv.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieInfoController implements Initializable {

    @FXML private Circle circleClose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void HandleClose(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }
}
