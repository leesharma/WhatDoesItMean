/*
 * -----------------------------------------------------------------------------
 * Main.java
 * -----------------------------------------------------------------------------
 * Created: August 30, 2018 22:54
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point to the What Does It Mean? captioning application.
 */

public class Main extends Application {
    /**
     * Launches the application.
     *
     * @param args command-line arguments
     */
    ImageView myImageView;

    public static void main(String[] args) {
        launch(args);
        System.out.println("Program Finished");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("What Does It Mean?");
        BorderPane bp = new BorderPane();
        //Label label = new Label("Name:");


        //Layout
        Button btn1 = new Button("Browse");
        Button btn2 = new Button("Caption");
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setAlignment(Pos.TOP_LEFT);
        hb.setStyle("-fx-background-color: #336699;");
        bp.setTop(hb);

        myImageView = new ImageView();

        //Scene & Stage
        hb.getChildren().addAll(btn1, btn2, myImageView);

        Scene scene = new Scene(bp,500, 500, Color.DODGERBLUE);


        primaryStage.setScene(scene);
        primaryStage.show();

        //Browse Button User Event
        btn1.setOnAction(event -> {
            FileChooser fc = new FileChooser();

            //File Format Filters
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            FileChooser.ExtensionFilter extFilterBMP = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.BMP");
            FileChooser.ExtensionFilter extFilterALL = new FileChooser.ExtensionFilter("All Image Files (JPG,PNG,BMP)", "*.JPG", "*.BMP", "*.PNG");
            fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterBMP, extFilterALL);

            fc.setTitle("Open File");
            File file = fc.showOpenDialog(primaryStage);

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                myImageView.setImage(image);
                myImageView.setFitHeight(400);
                myImageView.setPreserveRatio(true);
                myImageView.setSmooth(true);
                myImageView.setCache(true);
            } catch (IOException ex) {
            }


            if (file != null) {
                //set the label to the selected file's path

                //label.setText(file.getName());

                //Do other things..
                //Image image1 = new Image(file.toURI().toString());
            }

        });



    }
}
