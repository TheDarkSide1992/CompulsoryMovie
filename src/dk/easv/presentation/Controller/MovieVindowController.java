package dk.easv.presentation.Controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.Model.AppModel;
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

public class MovieVindowController implements Initializable {
    @FXML private VBox vBox1, vBox2, vBox3;
    private AppModel model = new AppModel();
    ArrayList<Movie> movieArrayList;
    private ArrayList<VBox> vBoxes;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTopAvdRatedMovies();
        createListOfVBox();
        loadImages();
    }

    private void getTopAvdRatedMovies() {
        /**
        ObservableList<Movie> movies = model.getObsTopMovieSeen();
        movieArrayList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Movie movie = movies.get(i);
            movieArrayList.add(movie);
        }
         */
    }

    private void createListOfVBox() {
        vBoxes = new ArrayList<>();
        vBoxes.add(vBox1);
        vBoxes.add(vBox2);
        vBoxes.add(vBox3);
    }

    private void loadImages() {
        try {
            //creating the image object with movie roll
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image image = new Image(stream);

            for (VBox vbox: vBoxes) {
                //Setting image to the image view
                for (int i = 0; i < 10; i++) {
                    //GetMoviePoster
                    InputStream posterInputStream = new FileInputStream("data/Img/JokerPoster.png");
                    //Image poster = new Image(posterInputStream);
                    Image poster = new Image("https://www.vintagemovieposters.co.uk/wp-content/uploads/2020/05/IMG_3693-482x715.jpeg");

                    //Define bottom and top
                    ImageView bottom = new ImageView(poster);
                    bottom.setFitHeight(160);
                    bottom.setFitWidth(147);
                    ImageView top = new ImageView(image);
                    top.setFitHeight(160);
                    top.setFitWidth(147);

                    Group blend = new Group(
                            bottom,
                            top
                    );

                    //Set a function to the blended image Group
                    blend.setOnMouseClicked(e ->{
                        System.out.println("yes");
                        System.out.println("sucess");
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
