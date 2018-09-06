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
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    System.out.println("Program Finished");
  }

  /**
   * Entry point of the JavaFX application.
   *
   * @param primaryStage JavaFX stage
   */
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("What Does It Mean?");

    //Controls
    HBox hb1 = new HBox();
    hb1.setSpacing(20);
    hb1.setAlignment(Pos.TOP_LEFT);
    hb1.setStyle("-fx-background-color: #336699;");
    Button btn1 = new Button("Browse");
    Button btn2 = new Button("Caption");
    btn2.setDisable(true);
    hb1.getChildren().addAll(btn1, btn2);

    //Image
    HBox hb2 = new HBox();
    hb2.setAlignment(Pos.CENTER);
    ImageView myImageView = new ImageView();
    hb2.getChildren().addAll(myImageView);

    //Caption
    HBox hb3 = new HBox();
    hb3.setAlignment(Pos.BOTTOM_CENTER);
    Label lb1 = new Label("Picture Caption!");
    hb3.getChildren().addAll(lb1);

    //layout management
    BorderPane bp = new BorderPane();
    bp.setTop(hb1);
    bp.setCenter(hb2);
    bp.setBottom(hb3);

    //Scene & Stage
    Scene scene = new Scene(bp, 500, 500);
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

      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }

      //enable caption button for captioning
      if (file != null) {
        btn2.setDisable(false);
      }

    });
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
