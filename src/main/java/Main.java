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
import javafx.embed.swing.SwingFXUtils;
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

/**
 * Entry point to the What Does It Mean? captioning application.
 */

public class Main extends Application {
  
  /**
   * Launches the application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    launch(args);
    System.out.println("Program Finished");
  }
  
  /**
   * Entry point of the JavaFX application.
   *
   * @param primaryStage
   */
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("What Does It Mean?");
    BorderPane bp = new BorderPane();
    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    HBox hb3 = new HBox();
    Label lb1 = new Label("Picture Caption!");
    ImageView myImageView = new ImageView();
    Button btn1 = new Button("Browse");
    Button btn2 = new Button("Caption");
    btn2.setDisable(true);

    //Controls
    hb1.setSpacing(20);
    hb1.setAlignment(Pos.TOP_LEFT);
    hb1.setStyle("-fx-background-color: #336699;");
    hb1.getChildren().addAll(btn1, btn2);
    
    //Image
    hb2.setAlignment(Pos.CENTER);
    hb2.getChildren().addAll(myImageView);
    
    //Caption
    hb3.setAlignment(Pos.BOTTOM_CENTER);
    hb3.getChildren().addAll(lb1);
    
    //layout management
    bp.setTop(hb1);
    bp.setCenter(hb2);
    bp.setBottom(hb3);
  
    //Scene & Stage
    Scene scene = new Scene(bp,500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();

    //Caption Button User Event
    btn2.setOnAction(event -> {
      //Instantiate CaptionGenerator
      CaptionGenerator cg = new CaptionGenerator();
      //Convert JavaFX Image to BufferedImage
      BufferedImage img = SwingFXUtils.fromFXImage(myImageView.getImage(), null);
      //Run captioning method and set label text
      lb1.setText(cg.generateCaption(img));
    });
    
    //Browse Button User Event
    btn1.setOnAction(event -> {
      FileChooser fc = new FileChooser();

      fc.setTitle("Open File");
      File file = fc.showOpenDialog(primaryStage);
      
      try {
        Image image = new Image(String.valueOf(file.toURI().toURL()));
        myImageView.setImage(image);
        myImageView.setPreserveRatio(true);
        myImageView.setFitHeight(300);
        myImageView.setFitWidth(300);
        myImageView.setSmooth(true);
        btn2.setDisable(false);

      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      
      //enable caption button for captioning
      if (file != null) {
        btn2.setDisable(false);
      }
      
    });
  }
}
