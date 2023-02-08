package dk.easv.presentation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javax.print.attribute.standard.Media;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieVindowController implements Initializable {
    @FXML private VBox vBox1, vBox2, vBox3;

    private ArrayList<VBox> vBoxes;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createListOfVBox();
        loadImages();

    }

    private void createListOfVBox() {
        vBoxes = new ArrayList<>();
        vBoxes.add(vBox1);
        vBoxes.add(vBox2);
        vBoxes.add(vBox3);
    }

    private void loadImages() {
        try {
            //creating the image object
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");
            Image image = new Image(stream);

            for (VBox vbox: vBoxes) {
                //Setting image to the image view
                for (int i = 0; i < 10; i++) {
                    ImageView imageView = new ImageView();
                    //Define the size
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(147);
                    /*
                    //Blend Together
                    InputStream stream2 = new FileInputStream("data/Img/JokerPoster.png");
                    Image image2 = new Image(stream2);
                    ImageView bottom = new ImageView(image2);
                    ImageView top    = new ImageView(image);
                    top.setBlendMode(BlendMode.DIFFERENCE);

                    Group blend = new Group(
                            bottom,
                            top
                    );
                    */

                    imageView.setImage(image);
                    imageView.setPreserveRatio(true);
                    vbox.getChildren().add(imageView);
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
