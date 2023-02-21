package dk.easv.GUI.Controller;

import dk.easv.GUI.Model.MovieInfoModel;
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
    MovieInfoModel movieInfoModel = new MovieInfoModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInfoForMovie(String movieTitle, int year) {
        title = movieTitle;
        txtTitle.setText(movieTitle + "(" + year + ")");
        String movieInfoString = movieInfoModel.getMovieInfo(title, year);


        String director = movieInfoString.substring(movieInfoString.indexOf("Director") + 11, movieInfoString.indexOf("Writer") - 3);
        String writer = movieInfoString.substring(movieInfoString.indexOf("Writer") + 9, movieInfoString.indexOf("Actors") - 3);
        String actor = movieInfoString.substring(movieInfoString.indexOf("Actors") + 9, movieInfoString.indexOf("Plot") - 3);
        String genre = movieInfoString.substring(movieInfoString.indexOf("Genre") + 8, movieInfoString.indexOf("Director") - 3);
        String runtime = movieInfoString.substring(movieInfoString.indexOf("Runtime") + 10, movieInfoString.indexOf("Genre") - 3);
        String ageRestriction = movieInfoString.substring(movieInfoString.indexOf("Rated") + 8, movieInfoString.indexOf("Released") - 3);

        txtInfo.setText("Director: " + director + "\n" + "Writers: " + writer + "\n" + "Actors: " + actor + "\n" + "Genre: " + genre
                + "\n" + "Runtime: " + runtime + "\n" + "Age restriction: " + ageRestriction);
        String description = movieInfoString.substring(movieInfoString.indexOf("Plot") + 7, movieInfoString.indexOf("Language") - 3);
        txtDescription.setText(description);
        String RTScore = null;
        if (movieInfoString.contains("Rotten Tomatoes")) {
            RTScore = movieInfoString.substring(movieInfoString.indexOf("Rotten Tomatoes")+26, movieInfoString.indexOf("%")+1);
        }
        else {
            RTScore = "N/A";
        }
        txtRTomato.setText(RTScore);
        String ImdbScore = movieInfoString.substring(movieInfoString.indexOf("imdbRating") + 13, movieInfoString.indexOf("imdbVotes") - 3);
        txtIMDB.setText(ImdbScore);
    }


    public void HandleClose(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }
}
