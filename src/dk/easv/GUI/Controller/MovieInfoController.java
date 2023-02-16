package dk.easv.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieInfoController implements Initializable {

    @FXML private Text txtTitle;
    @FXML private ImageView imagePoster;
    @FXML private ImageView imageIMDB;
    @FXML private ImageView imageRTomato;
    @FXML private Text txtInfo;
    @FXML private Text txtDescription;
    @FXML private Text txtIMDB;
    @FXML private Text txtRTomato;
    @FXML private Circle circleClose;
    private String tittle = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInfoForMovie(String movieTittle, int year){
        tittle = movieTittle;

        txtTitle.setText(movieTittle);

        //TODO INIT INFO HERE
    }

    public void HandleClose(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }
}
