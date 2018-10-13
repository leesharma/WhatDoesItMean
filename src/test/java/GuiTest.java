import javafx.scene.Node;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;


public class GuiTest extends TestBase {
  
  @Test
  public void captionButtonIsDisabled() {
    FxAssert.verifyThat("#btnCaption", Node::isDisabled);
  }
  
  @Test
  public void browseButtonIsExists() {
    FxAssert.verifyThat("#btnBrowse", LabeledMatchers.hasText("Browse"));
  }
  
  @Test
  public void captionLabelExists() {
    FxAssert.verifyThat("#caption", LabeledMatchers.hasText("Browse for an image, click caption, and enjoy :)"));
  }
  
}
