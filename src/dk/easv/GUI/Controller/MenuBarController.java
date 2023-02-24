package dk.easv.GUI.Controller;

import dk.easv.BE.User;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {
    @FXML private Label lblHome;
    @FXML private Label lblMovies;
    @FXML private Label lblTvSeries;
    @FXML private Label lblCat;
    @FXML private Label lblSet;
    @FXML private TextField fieldSearch;
    @FXML private Label lblUerNameId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //No Init
        lblUerNameId.setText("Georgi Facello");
    }

    public void lblHomeClicked(MouseEvent mouseEvent) {
        System.out.println("Going to HOME");
    }

    public void lblMoviesClicked(MouseEvent mouseEvent) {
        System.out.println("going to movies");
    }

    public void lblTvClicked(MouseEvent mouseEvent) {
        System.out.println("Going to tv-series");
    }

    public void lblCatClicked(MouseEvent mouseEvent) {
        System.out.println("going to categories");
    }

    public void lblSettingsClicked(MouseEvent mouseEvent) {
        System.out.println("Going to settings");
        System.exit(0);
    }
}
