package dk.easv.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
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
    @FXML private Text txtInfo;
    @FXML private Text txtDescription;
    @FXML private Text txtIMDB;
    @FXML private Text txtRTomato;
    @FXML private Circle circleClose;
    private String title = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInfoForMovie(String movieTitle, int year){
        title = movieTitle;

        txtTitle.setText(movieTitle+"("+year+")");

        String director= "";
        String writer="";
        String actor="";
        String genre="";
        String runtime="";
        String ageRestriction="";

        txtInfo.setText("Director: " + director + "\n" +"Writers: " + writer +"\n" +"Actors: " + actor +"\n"+"Genre: " + genre
                +"\n"+"Runtime: " + runtime + "\n"+ "Age restriction: "+ ageRestriction);
        String description="";
        txtDescription.setText(description);
        String RTScore="";
        txtRTomato.setText(RTScore);
        String ImdbScore = "";
        txtIMDB.setText(ImdbScore);
        Image poster = null;
        imagePoster.setImage(poster);
    }


    public void HandleClose(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }
}
