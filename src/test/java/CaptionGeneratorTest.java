/*
 * -----------------------------------------------------------------------------
 * CaptionGeneratorTest.java
 * -----------------------------------------------------------------------------
 * Created September 10, 2018 12:25
 * Course: CMSC 495 Capstone
 * Group: F(antastic)
 * Project: What Does It Mean?: Automatic Caption Generator
 */

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaptionGeneratorTest {
  // provides convenience functions for accessing test images
  private TestImageLoader fixtures = new TestImageLoader();

  @BeforeEach
  void resetImages() {
    fixtures.reset(); // resets the image fixtures to a clean state
  }

  @Test
  void generateCaption() {
    CaptionGenerator cg = new CaptionGenerator();
    String caption  = cg.generateCaption(fixtures.getBlankImage());

    assertNotNull(caption); // verify that it runs
  }

  @Test
  void generateCaption__failsWithNullInput() {
    CaptionGenerator cg = new CaptionGenerator();
    assertThrows(IllegalArgumentException.class, () -> cg.generateCaption(null));
  }

  @Test
  void generateCaption__isDeterministic__sameObject() throws IOException {
    CaptionGenerator cg = new CaptionGenerator();
    BufferedImage img = fixtures.getNextImage();

    // exact same object
    assertEquals(
        cg.generateCaption(img),
        cg.generateCaption(img)
    );
  }

  @Test
  void generateCaption__isDeterministic__sameImage() throws IOException {
    CaptionGenerator cg = new CaptionGenerator();
    BufferedImage img = fixtures.getNextImage();
    BufferedImage dupImg = fixtures.getCurrentImage();

    // same image, different objects
    assertEquals(
        cg.generateCaption(img),
        cg.generateCaption(dupImg)
    );
  }

  @Test
  void generateCaption__multipleCaptions__differentImage() throws IOException {
    CaptionGenerator cg = new CaptionGenerator();
    BufferedImage img1 = fixtures.getNextImage();
    BufferedImage img2 = fixtures.getNextImage();

    // different image = different caption
    assertNotEquals(
        cg.generateCaption(img1),
        cg.generateCaption(img2)
    );
  }
}