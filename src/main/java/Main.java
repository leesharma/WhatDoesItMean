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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
  }

  /**
   * Entry point of the JavaFX application.
   *
   * @param stage JavaFX stage
   */
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
    Scene scene = new Scene(root, 500, 500);
    stage.setTitle("What Does It Mean?");
    stage.setScene(scene);
    stage.show();
  }
}
