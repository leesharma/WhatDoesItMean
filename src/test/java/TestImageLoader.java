/*
 * -----------------------------------------------------------------------------
 * TestImageLoader.java
 * -----------------------------------------------------------------------------
 * Created September 18, 2018 12:29
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Helper class for loading test images.
 */
class TestImageLoader {
  private String[] filepaths;
  private int currImg = -1;

  /**
   * Constructs a new TestImageLoader with the hard-coded test images.
   */
  TestImageLoader() {
    // assumes this is run from the project root
    this.filepaths = new String[]{
        "./src/test/resources/images/giraffe.jpg",
        "./src/test/resources/images/waves.jpg",
        "./src/test/resources/images/windows.jpg",
    };
  }

  /**
   * Resets the TestImageLoader to the beginning of the list.
   */
  void reset() {
    currImg = -1;
  }

  /**
   * Returns a blank BufferedImage.
   *
   * <p>This is a faster operation for when the content is not important.
   *
   * @return blank image
   */
  BufferedImage getBlankImage() {
    return new BufferedImage(400, 600, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Loads and returns the next test image.
   *
   * @return next test image as a BufferedImage
   * @throws IOException if image cannot be opened
   * @throws ArrayIndexOutOfBoundsException when you run out of images
   */
  BufferedImage getNextImage() throws IOException, ArrayIndexOutOfBoundsException {
    incImage();
    return getCurrentImage();
  }

  /**
   * Loads and returns the current test image.
   *
   * <p>If the current image index is invalid (for example, if "getNextImage()"
   * has not been called yet or the class is out of test images), this method
   * will throw an error. If this happens try calling getNextImage() or reset().
   *
   * @return current test image as a BufferedImage
   * @throws IOException if image cannot be opened
   * @throws ArrayIndexOutOfBoundsException when you run out of images
   */
  BufferedImage getCurrentImage() throws IOException, ArrayIndexOutOfBoundsException {
    if (currImg < 0) {
      throw new ArrayIndexOutOfBoundsException("Out of bounds: try starting with getNextImage().");
    } else if (currImg >= filepaths.length) {
      throw new ArrayIndexOutOfBoundsException("Out of test images!");
    }

    return ImageIO.read(new File(filepaths[currImg]));
  }

  /**
   * Increments the current image.
   *
   * @throws ArrayIndexOutOfBoundsException when you run out of images
   */
  private void incImage() {
    if (currImg > filepaths.length-1) {
      throw new ArrayIndexOutOfBoundsException("Out of test images!");
    }
    currImg++;
  }
}
