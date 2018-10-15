/*
 * -----------------------------------------------------------------------------
 * CaptionGenerator.java
 * -----------------------------------------------------------------------------
 * Created September 10, 2018 12:11
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Generates a caption from a stored image.
 */
class CaptionGenerator {

  /**
   * Constructs a new CaptionGenerator with canned captions.
   */
  CaptionGenerator() {
  }

  /**
   * Returns a relevant, readable caption describing a given image.
   *
   * @return image caption
   */
  String generateCaption(String filename) throws IOException {
    //    if (image == null) {
    //      throw new IllegalArgumentException("Input must be a valid image.");
    //    }

    String checkpointPath = "src/main/resources/full_model/ckpt5";
    String vocabFile = "src/main/resources/word_counts.txt";
    String imageFile = filename
        .replace("\\", "\\\\")
        .replace(" ", "\\ ");

    String str;
    String cmd = String.format("src/main/python/bazel-bin/im2txt/run_inference \\\n"
            + "  --checkpoint_path=%s \\\n"
            + "  --vocab_file=%s \\\n"
            + "  --input_files=%s",
        checkpointPath, vocabFile, imageFile);
    StringBuilder caption = new StringBuilder();
    System.out.println("Captioning image with the following command:");
    System.out.println(cmd);

    try {
      Process proc = Runtime.getRuntime().exec(cmd);

      // read the output from the command
      BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      System.out.println("Here is the standard output of the command:\n");
      while ((str = stdInput.readLine()) != null) {
        caption.append(str);
        System.out.println(str);
      }

      BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
      System.out.println("Here is the error output of the command:\n");
      while ((str = stdError.readLine()) != null) {
        System.out.println(str);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return caption.toString();
  }
}
