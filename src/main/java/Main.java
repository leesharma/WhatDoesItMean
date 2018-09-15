/*
 * -----------------------------------------------------------------------------
 * Main.java
 * -----------------------------------------------------------------------------
 * Created: August 30, 2018 22:54
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

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
  
  public void start(Stage primaryStage) {
    BorderPane bp = new BorderPane();
    Label label = new Label("File/Path/");
    Button btn = new Button("Browse");
    
    btn.setOnAction(event -> {
      FileChooser fc = new FileChooser();
      fc.setTitle("Open File");
      File file = fc.showOpenDialog(primaryStage);
      if (file != null) {
        //set the label to the selected file's path
        label.setText(file.getPath());
        
        //Do other things..
      }
    });
    
    //Layout
    HBox hb = new HBox(btn, label);
    hb.setSpacing(20);
    hb.setAlignment(Pos.CENTER);
    bp.setCenter(hb);
  
    //Scene & Stage
    Scene scene = new Scene(bp,600,500);
    primaryStage.setScene(scene);
    primaryStage.setTitle("WhatDoesItMean?");
    primaryStage.show();
  }
}
