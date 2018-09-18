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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates a caption from a stored image.
 */
class CaptionGenerator {
  private String[] captions;

  /**
   * Constructs a new CaptionGenerator with canned captions.
   */
  CaptionGenerator() {
    String captionsPath = "./src/main/resources/canned_captions.txt";
    this.captions = readLines(captionsPath);
  }

  /**
   * Reads a file and returns the lines, one line per array entry.
   *
   * @param filename input file path
   * @return lines in file
   */
  private String[] readLines(String filename) {
    List<String> lines = new ArrayList<>();
    String line;

    // open the file and read in lines
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException err) {
      err.printStackTrace();
    }

    return lines.toArray(new String[0]);
  }

  /**
   * Returns a relevant, readable caption describing a given image.
   *
   * @return image caption
   */
  String generateCaption(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Input must be a valid image.");
    }

    // choose a caption (pre-Tensorflow)
    int digest = hash(image);
    int idx = Math.abs(digest % captions.length);

    return captions[idx];
  }

  /**
   * Naive hash to identify the image content.
   *
   * <p>This is done by simply summing up the pixel values in the image. This
   * is definitely not a secure method, but it will return the same value if two
   * images are identical and (likely) return different values for different
   * images.
   *
   * <p>All this code will be deleted once we have Tensorflow integration.
   *
   * @param image image input
   * @return integer digest
   */
  private int hash(BufferedImage image) {
    int sum = 0;
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        sum += image.getRGB(i,j);
      }
    }
    return Math.abs(sum);
  }
}
