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
import org.junit.jupiter.api.Test;

class CaptionGeneratorTest {

  @Test
  void generateCaption() {
    CaptionGenerator cg = new CaptionGenerator();
    String caption  = cg.generateCaption(
        new BufferedImage(400, 600, BufferedImage.TYPE_INT_RGB)
    );

    assertNotNull(caption); // verify that it runs
  }

  @Test
  void generateCaption__nullImage() {
    CaptionGenerator cg = new CaptionGenerator();
    assertThrows(IllegalArgumentException.class, () -> cg.generateCaption(null));
  }
}