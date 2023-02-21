package dk.easv.GUI.Controller;

import dk.easv.BE.Movie;
import dk.easv.BE.TopMovie;
import dk.easv.BE.User;
import dk.easv.GUI.Model.AppModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.FileInputStream;
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
        String user = LogInController.getUserString();
        boolean validUser = model.loginUserFromUsername(user);
        if (validUser == true){
            model.loadData(model.getUser(user));
        }else {
            JOptionPane.showMessageDialog(null, "This is not a valid username: " + user + "\nTo test the program you will be registered as: Georgi Facello");
            model.loadData(model.getUser("Georgi Facello"));
        }

        getTopAvdRatedMoviesSeen();
        getTopAvdRatedMoviesNotSeen();
        new Thread(() ->getArrTopMoviesSimilarUsers()).start(); //Starts new Thread
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
        try {
            InputStream stream = null;
            stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image movieRoll = new Image(stream);
            for (TopMovie topMovie: topMoviesFromSimilarUsers) {
                //OMDB does not handle series, It is an experiment with the "movieTitleTrimmed()" method that removes everything after a special character
                String movieTitleTrimmed = movieTitleTrimmed(topMovie.getTitle());
                String posterURL = model.searchMovieGetPoster(movieTitleTrimmed, topMovie.getYear());
                Image poster = null;
                Label label = null;
                //GetMoviePoster
                if (posterURL.equals("N/A")) {
                    //poster = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Orange_question_mark.svg/2048px-Orange_question_mark.svg.png");
                }else{
                    poster = new Image(posterURL);
                }
                Group blend = makeThePhotoPoster(poster, movieRoll, topMovie.getTitle(), topMovie.getYear());
                //Set a function to the blended movieRoll Group
                blend.setOnMouseClicked(e ->{
                    System.out.println("movie: " + topMovie.getTitle() + "\t Year: " + topMovie.getYear());
                    loadMovieInfo(topMovie.getTitle(), topMovie.getYear());
                });
                Platform.runLater(() -> vBox3.getChildren().add(blend)); //set the FXML elements after Thread
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void createListOfVBox() {
        vBoxes = new ArrayList<>();
        vBoxes.add(vBox1);
        vBoxes.add(vBox2);
    }

    private void loadImages() {
        try {
            ArrayList<ArrayList> arrayListOfMovieLists = new ArrayList<>();
            arrayListOfMovieLists.add(topAVGRatedMoviesNotSeen);
            arrayListOfMovieLists.add(topAVGRatedMoviesSeen);
            //creating the movieRoll object width movie roll
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image movieRoll = new Image(stream);

            for (int i = 0; i < vBoxes.size(); i++) {
                VBox vBox = vBoxes.get(i);
                ArrayList<Movie> movieList = arrayListOfMovieLists.get(i);

                new Thread(() -> {
                    try {
                        loadThreads(movieList, vBox, movieRoll);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadThreads(ArrayList<Movie> movieList,VBox vBox, Image movieRoll) throws Exception{
        for (int b = 0; b < numberOfMoviesPrVBox; b++) {
            //OMDB does not handle series, It is an experiment with the "movieTitleTrimmed()" method that removes everything after a special character
            String movieTitleTrimmed = movieTitleTrimmed(movieList.get(b).getTitle());
            String posterURL = model.searchMovieGetPoster(movieTitleTrimmed, movieList.get(b).getYear());
            Image poster = null;
            //GetMoviePoster
            if (posterURL.equals("N/A")) {
                //poster = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Orange_question_mark.svg/2048px-Orange_question_mark.svg.png");
            } else {
                poster = new Image(posterURL);
            }
            Group blend = makeThePhotoPoster(poster, movieRoll, movieList.get(b).getTitle(), movieList.get(b).getYear());

            Platform.runLater(() -> vBox.getChildren().add(blend));
        }
    }

    private Group makeThePhotoPoster(Image poster, Image movieRoll, String title, int year) {
        //the racio of a movie poster is 27" x 40"
        //forholdet 1 i bredde og 1,1 i længde i den der er lavet her
        int width = 192;
        int height = 210;

        //Define bottom and top and set height and width
        ImageView bottom = new ImageView(poster);
        bottom.setFitHeight(195);
        bottom.setFitWidth(132);
        bottom.setX(30);
        bottom.setY(10);
        ImageView top = new ImageView(movieRoll);
        top.setFitHeight(height);
        top.setFitWidth(width);

        Group blend = null;

        if (poster == null) {
            Label label = new Label("  Title:\n  " + title +"\n\n\n\n\n");
            label.setStyle("-fx-font-scale: 10");
            label.setStyle("-fx-background-color: grey");
            label.setPrefHeight(195);
            label.setPrefWidth(132);
            label.setLayoutX(30);
            label.setLayoutY(10);

            blend = new Group(
                    label,
                    top
            );
        } else {
            blend = new Group(
                    bottom,
                    top

            );
        }
        //Set a function to the blended movieRoll Group

        blend.setOnMouseClicked(e -> {
            System.out.println("movie: " + title + "\t Year: " + year);
            loadMovieInfo(title, year);
        });

        Label L = new Label("");
        Group finalBlend = blend;
        blend.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                L.setText(title + " \n(" + year + ")");
                L.setLayoutX(width * 0.15);
                L.setLayoutY(height * 0.04);
                finalBlend.getChildren().add(L);
            } else if (oldValue) {
                L.setText("");
                L.setLayoutX(width * 0.15);
                L.setLayoutY(height * 0.04);
                finalBlend.getChildren().remove(L);
            }

        });

        return blend;
    }

    private void loadMovieInfo(String movieTittle, int year){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/GUI/View/MovieInfoScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            MovieInfoController showMovieController = fxmlLoader.getController(); // might not be needed  //Uncomment to get the controller
            Stage stage = new Stage();
            stage.setTitle("Show_Movie_Info");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            showMovieController.setInfoForMovie(movieTittle, year);
            stage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String movieTitleTrimmed(String title) {
        title.trim();
        String regExSpecialChars = ":<([{\\^=$!|]})?*+.>"; //excluded the sign: -

        for (char c: regExSpecialChars.toCharArray()) {
            if(title.contains(c + "")){
                title = title.substring(0,title.indexOf(c)).trim();
                return title;
            }
        }
        return title;
    }
}
