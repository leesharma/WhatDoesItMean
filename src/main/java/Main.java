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

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import java.nio.charset.StandardCharsets;


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
    // FIXME: delete me once everyone verifies their setup
    helloTensorflow();  // verifies tensorflow installation
    System.out.println("If you a tensorflow console message, you're all set!");

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

  /**
   * Verifies basic Tensorflow installation.
   *
   * <p>Reprinted from: https://www.tensorflow.org/install/install_java
   *
   * <p>FIXME: Delete me once everyone verifies their setup.
   */
  private static void helloTensorflow() {
    try (Graph g = new Graph()) {
      final String value = "Hello Tensorflow " + TensorFlow.version();

      // Construct the computation graph with a single operation, a constant
      // named "MyConst" with a value "value".
      try (Tensor t = Tensor.create(value.getBytes(StandardCharsets.UTF_8))) {
        // The Java API doesn't yet include convenience functions for adding operations.
        g.opBuilder("Const", "MyConst")
            .setAttr("dtype", t.dataType())
            .setAttr("value", t)
            .build();
      }

      // Execute the "MyConst" operation in a Session.
      try (Session s = new Session(g);
          // Generally, there may be multiple output tensors, all of them must
          // be closed to prevent resource leaks.
          Tensor output = s.runner().fetch("MyConst").run().get(0)) {
        System.out.println(new String(output.bytesValue(), StandardCharsets.UTF_8));
      }
    }
  }
}
