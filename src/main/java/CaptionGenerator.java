/*
 * -----------------------------------------------------------------------------
 * CaptionGenerator.java
 * -----------------------------------------------------------------------------
 * Created September 10, 2018 12:11
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import java.awt.Image;

/**
 * Generates a caption from a stored image.
 */
class CaptionGenerator {
  /**
   * Returns a relevant, readable caption describing a given image.
   *
   * @return image caption
   */
  String generateCaption(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Input must be a valid image.");
    }

    return "This is definitely an image of something.";
  }
}
