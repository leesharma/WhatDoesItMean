/*
 * -----------------------------------------------------------------------------
 * Main.java
 * -----------------------------------------------------------------------------
 * Created: August 30, 2018 22:54
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    HBox hb = new HBox();
    HBox hb2 = new HBox();
    ImageView myImageView = new ImageView();
    Button btn1 = new Button("Browse");
    Button btn2 = new Button("Caption");
    
    //Controls
    hb.setSpacing(20);
    hb.setAlignment(Pos.TOP_LEFT);
    hb.setStyle("-fx-background-color: #336699;");
    hb.getChildren().addAll(btn1, btn2);
    
    //Image
    hb2.setAlignment(Pos.CENTER);
    hb2.getChildren().addAll(myImageView);
    
    //layout management
    bp.setTop(hb);
    bp.setCenter(hb2);
  
    //Scene & Stage
    Scene scene = new Scene(bp,500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();

    //Browse Button User Event
    btn1.setOnAction(event -> {
      FileChooser fc = new FileChooser();
      
      //File Format Filters
//      FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//      FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//      FileChooser.ExtensionFilter extFilterBMP = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.BMP");
//      FileChooser.ExtensionFilter extFilterALL = new FileChooser.ExtensionFilter("All Image Files (JPG,PNG,BMP)", "*.JPG", "*.BMP", "*.PNG");
//      fc.getExtensionFilters().addAll(extFilterJPG, extFilterBMP, extFilterALL);

      fc.setTitle("Open File");
      File file = fc.showOpenDialog(primaryStage);
      
      try {
        Image image = new Image(String.valueOf(file.toURI().toURL()));
        myImageView.setImage(image);
        myImageView.setPreserveRatio(true);
        myImageView.setFitHeight(200);
        myImageView.setFitWidth(200);
        myImageView.setSmooth(true);
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      
    });
  }
}
