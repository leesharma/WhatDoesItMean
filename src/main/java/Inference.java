/*
 * -----------------------------------------------------------------------------
 * Inference.java
 * -----------------------------------------------------------------------------
 * Created October 4, 2018 06:33
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import org.tensorflow.Graph;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.TensorFlow;

public class Inference {

  /**
   * Test method to make sure the model is loading correctly.
   *
   * @param args command-line args
   */
  public static void main(String[] args) {
    TensorFlow.version();  // crash the app if tensorflow isn't loaded
    try (SavedModelBundle model = SavedModelBundle.load("src/main/resources/model", "serve")) {
      Session sess = model.session();
      Graph graph = model.graph();
      System.out.println("Success!");
    }
  }
}
