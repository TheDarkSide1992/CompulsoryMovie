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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public ImageView BottomRoll, TopRoll;
    public Text TextYear;
    @FXML
    private ImageView imageIMDB;
    @FXML
    private ImageView imageRTomato;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placeRolls();
    }

    public void setInfoForMovie(String movieTitle, int year) {
        title = movieTitle;
        txtTitle.setText(movieTitle);
        TextYear.setText(String.valueOf(year));
        String movieInfoString = movieInfoModel.getMovieInfo(title, year);
        String director, writer, actor, genre, runtime, ageRestriction,description,RTScore,ImdbScore;
        if(movieInfoString.contains("Response\":\"False\",\"Error\":\"Movie not found!")){
            director = "N/A";
            writer = "N/A";
            actor = "N/A";
            genre = "N/A";
            runtime = "N/A";
            ageRestriction = "N/A";
            description = "N/A";
            RTScore = "N/A";
            ImdbScore = "N/A";

        }
        else {
            director = movieInfoString.substring(movieInfoString.indexOf("Director") + 11, movieInfoString.indexOf("Writer") - 3);
            writer = movieInfoString.substring(movieInfoString.indexOf("Writer") + 9, movieInfoString.indexOf("Actors") - 3);
            actor = movieInfoString.substring(movieInfoString.indexOf("Actors") + 9, movieInfoString.indexOf("Plot") - 3);
            genre = movieInfoString.substring(movieInfoString.indexOf("Genre") + 8, movieInfoString.indexOf("Director") - 3);
            runtime = movieInfoString.substring(movieInfoString.indexOf("Runtime") + 10, movieInfoString.indexOf("Genre") - 3);
            ageRestriction = movieInfoString.substring(movieInfoString.indexOf("Rated") + 8, movieInfoString.indexOf("Released") - 3);
            description = movieInfoString.substring(movieInfoString.indexOf("Plot") + 7, movieInfoString.indexOf("Language") - 3);

            if (movieInfoString.contains("Rotten Tomatoes")) {
                RTScore = movieInfoString.substring(movieInfoString.indexOf("Rotten Tomatoes") + 26, movieInfoString.indexOf("%") + 1);
            } else {
                RTScore = "N/A";
            }
            ImdbScore = movieInfoString.substring(movieInfoString.indexOf("imdbRating") + 13, movieInfoString.indexOf("imdbVotes") - 3);

        }
        txtRTomato.setText(RTScore);
        txtDescription.setText(description);
        txtIMDB.setText(ImdbScore);
        txtInfo.setText("Director: " + director + "\n" + "Writers: " + writer + "\n" + "Actors: " + actor + "\n" + "Genre: " + genre
                + "\n" + "Runtime: " + runtime + "\n" + "Age restriction: " + ageRestriction);
    }


    public void HandleClose(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }

    private void placeRolls() {
        try {
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image bottom = new Image(stream);
            BottomRoll.setImage(bottom);
            TopRoll.setImage(bottom);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
