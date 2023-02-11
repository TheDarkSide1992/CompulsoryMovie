package dk.easv.GUI.Controller;

import dk.easv.BE.Movie;
import dk.easv.BE.TopMovie;
import dk.easv.BE.User;
import dk.easv.GUI.Model.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieWindowController implements Initializable {
    @FXML private Label lblWatchAgain;
    @FXML private Label lblCheckOut;
    @FXML private Label lblRecommend;
    @FXML private VBox vBox1, vBox2, vBox3;
    private AppModel model = new AppModel();

    private ArrayList<Movie> movies = new ArrayList<>();

    private ArrayList<Movie> topAVGRatedMoviesNotSeen;
    private ArrayList<Movie> topAVGRatedMoviesSeen;
    private ArrayList<TopMovie> topMoviesFromSimilarUsers;


    private ObservableList<User> users = FXCollections.observableArrayList();
    private ArrayList<VBox> vBoxes;
    private int numberOfMoviesPrVBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfMoviesPrVBox = 20;
        getUserAndLoadData();
        createListOfVBox();
        loadImages();
    }
    private void getUserAndLoadData() {
        model.loadUsers();
        model.loginUserFromUsername("Georgi Facello");
        model.loadData(model.getUser("Georgi Facello"));

        getTopAvdRatedMoviesSeen();
        getTopAvdRatedMoviesNotSeen();
        getArrTopMoviesSimilarUsers();
    }

    private void getTopAvdRatedMoviesSeen() {
        movies.clear();
        movies = model.getArrTopMovieSeen();
        topAVGRatedMoviesSeen = new ArrayList<>();
        if (movies != null) {
            for (int i = 0; i < numberOfMoviesPrVBox; i++) {
                Movie movie = movies.get(i);
                topAVGRatedMoviesSeen.add(movie);
            }
        }
    }
    private void getTopAvdRatedMoviesNotSeen() {
        movies.clear();
        movies = model.getArrTopMovieNotSeen();
        topAVGRatedMoviesNotSeen = new ArrayList<>();
        if (movies != null) {
            for (int i = 0; i < numberOfMoviesPrVBox; i++) {
                Movie movie = movies.get(i);
                topAVGRatedMoviesNotSeen.add(movie);
            }
        }
    }

    private void getArrTopMoviesSimilarUsers() {
        ArrayList<TopMovie> movies = model.getArrTopMoviesSimilarUsers();
        topMoviesFromSimilarUsers = new ArrayList<TopMovie>();
        if (movies != null) {
            for (int i = 0; i < numberOfMoviesPrVBox; i++) {
                TopMovie topMovie = movies.get(i);
                topMoviesFromSimilarUsers.add(topMovie);
            }
        }
    }

    private void createListOfVBox() {
        vBoxes = new ArrayList<>();
        vBoxes.add(vBox1);
        vBoxes.add(vBox2);
        vBoxes.add(vBox3);
    }

    private void loadImages() {
        try {
            ArrayList<ArrayList> arrayListOfMovieLists = new ArrayList<>();
            arrayListOfMovieLists.add(topAVGRatedMoviesNotSeen);
            arrayListOfMovieLists.add(topAVGRatedMoviesSeen);
            arrayListOfMovieLists.add(topMoviesFromSimilarUsers);
            //creating the movieRoll object width movie roll
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image movieRoll = new Image(stream);

            for (int i = 0; i < 2; i++) {
                VBox vBox = vBoxes.get(i);
                ArrayList<Movie> movieList = arrayListOfMovieLists.get(i);
                for (int b = 0; b < numberOfMoviesPrVBox; b++) {
                    //OMDB does not handle series, It is an experiment with the "movieTitleTrimmed()" method that removes everything after a special character
                    String movieTitleTrimmed = movieTitleTrimmed(movieList.get(b).getTitle());
                    String posterURL = model.searchMovieGetPoster(movieTitleTrimmed, movieList.get(b).getYear());
                    Image poster = null;
                    //GetMoviePoster
                    if (posterURL.equals("N/A")) {
                        poster = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Orange_question_mark.svg/2048px-Orange_question_mark.svg.png");
                    }else{
                        poster = new Image(posterURL);
                    }
                    Group blend = makeThePhotoPoster(poster, movieRoll);

                    //Set a function to the blended movieRoll Group
                    int finalB = b;
                    blend.setOnMouseClicked(e ->{
                        System.out.println("movie: " + movieList.get(finalB).getTitle() + "\t Year: " + movieList.get(finalB).getYear());
                });
                    vBox.getChildren().add(blend);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Group makeThePhotoPoster(Image poster, Image movieRoll) {
        //the racio of a movie poster is 27" x 40"
        //forholdet 1 i bredde og 1,1 i længde i den der er lavet her
        int width = 180;
        int height = 198;

        //Define bottom and top and set height and width
        ImageView bottom = new ImageView(poster);
        bottom.setFitHeight(height);
        bottom.setFitWidth(width);
        ImageView top = new ImageView(movieRoll);
        top.setFitHeight(height);
        top.setFitWidth(width);

        Group blend = new Group(
                bottom,
                top
        );
        return blend;
    }

    private String movieTitleTrimmed(String title) {
        String trimmedTitle = title;
        String regExSpecialChars = ":<([{\\^=$!|]})?*+.>"; //excluded the sign: -

        for (char c: regExSpecialChars.toCharArray()) {
            if(trimmedTitle.contains(c + "")){
                trimmedTitle = trimmedTitle.substring(0,trimmedTitle.indexOf(c)).trim();
                return trimmedTitle;
            }
        }
        return trimmedTitle;
    }


}
