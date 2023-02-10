package dk.easv.GUI.Controller;

import dk.easv.BE.Movie;
import dk.easv.BE.User;
import dk.easv.GUI.Model.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
    @FXML private VBox vBox1, vBox2, vBox3;
    private AppModel model = new AppModel();

    private ArrayList<Movie> movies;

    private ArrayList<Movie> movieArrayList;
    private ObservableList<User> users = FXCollections.observableArrayList();
    private ArrayList<VBox> vBoxes;
    private int numberOfMoviesPrVBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfMoviesPrVBox = 10;
        getUserAndLoadData();
        getTopAvdRatedMovies();
        createListOfVBox();
        loadImages();
    }

    private void getUserAndLoadData() {
        model.loadUsers();
        model.loginUserFromUsername("Georgi Facello");
    }

    private void getTopAvdRatedMovies() {

        model.loadData(model.getUser("Georgi Facello"));

        movies = model.getArrTopMovieNotSeen();
        movieArrayList = new ArrayList<>();
        if (movies != null) {
            for (int i = 0; i < numberOfMoviesPrVBox; i++) {
                Movie movie = movies.get(i);
                movieArrayList.add(movie);
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
            //creating the MovieRoll object width movie roll
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image MovieRoll = new Image(stream);

            //the racio of a movie poster is 27" x 40"
            //forholdet 1 i bredde og 1,1 i længde i den der er lavet her
            int width = 180;
            int height = 198;
            for (VBox vbox: vBoxes) {
                //Setting MovieRoll to the MovieRoll view
                for (Movie movie : movieArrayList) {
                    //first we get the IMDB-ID on the movie by searching on its name on OMDB
                    //OMDB does not handle series, It is an experiment with the substring
                    String imdbID = model.searchAddMovieGetimdbID(movie.getTitle().substring(0, 8));
                    Image poster = null;
                    //GetMoviePoster
                    if (imdbID == null) {
                        poster = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Orange_question_mark.svg/2048px-Orange_question_mark.svg.png");
                    }else{
                        String posterURL = model.searchSelectedMovieGetPosterURL(imdbID);
                        poster = new Image(posterURL);
                    }
                    //Define bottom and top and set height and width
                    ImageView bottom = new ImageView(poster);
                    bottom.setFitHeight(height);
                    bottom.setFitWidth(width);
                    ImageView top = new ImageView(MovieRoll);
                    top.setFitHeight(height);
                    top.setFitWidth(width);

                    Group blend = new Group(
                            bottom,
                            top
                    );

                    //Set a function to the blended MovieRoll Group
                    blend.setOnMouseClicked(e ->{
                        System.out.println("movie: " + movie.getTitle() + "\t Year: " + movie.getYear());
                });
                    vbox.getChildren().add(blend);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //imageView1.setImage(new Image("data/MovieRollRight.png"));
        //Image imProfile = new Image("data/MovieRollRight.png");
        //imageView1 = new ImageView(imProfile);
    }


}
