package dk.easv.presentation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.print.attribute.standard.Media;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieVindowController implements Initializable {
    @FXML private ImageView imageView2;
    @FXML private ImageView imageView1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadImages();
    }

    private void loadImages() {
        try {
            //creating the image object
            InputStream stream = new FileInputStream("data/Img/MovieRollRight.png");

            Image image = new Image(stream);
            //Setting image to the image view
            imageView2.setImage(image);
            imageView2.setPreserveRatio(true);
            //imageView1.setImage(new Image("/data/MovieRollRight.png"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //imageView1.setImage(new Image("data/MovieRollRight.png"));
        //Image imProfile = new Image("data/MovieRollRight.png");
        //imageView1 = new ImageView(imProfile);
    }


}
