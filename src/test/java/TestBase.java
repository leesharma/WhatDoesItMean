import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public abstract class TestBase extends ApplicationTest {
  
  /**
   * Configure for headless testing of the Application which is run once before all
   * tests.
   */
  @BeforeAll
  public static void setupHeadlessMode() {
    System.setProperty("testfx.robot", "glass");
    System.setProperty("testfx.headless", "true");
    System.setProperty("prism.order", "sw");
    System.setProperty("prism.text", "t2k");
    System.setProperty("java.awt.headless", "true");
  }
  
  /**
   * The following FxToolkit lines allow for indirectly performing
   * ApplicationTest.launch(Main.class); and registering the primary stage
   * in order to allow running multiple @Test in a single file.
   *
   * @throws Exception
   */
  @BeforeEach
  public void beforeEachTest() throws Exception {
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(Main.class);
  }
  
  /**
   * The release() lines are used to clear all the possible key or mouse events
   * that are still in progress at the end of each unit test.  The hideStage operation
   * closes the GUI after each test.
   *
   * @throws Exception
   */
  @AfterEach
  public void afterEachTest() throws Exception {
    FxToolkit.hideStage();
    release(new KeyCode[] {});
    release(new MouseButton[]{});
  }
  
  /**
   * Overrides the ApplicationTest start method.
   *
   * @param stage
   * @throws Exception
   */
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
    root.setId("root");
    Scene scene = new Scene(root, 500, 500);
    stage.setTitle("What Does It Mean?");
    stage.setScene(scene);
    stage.show();
  }
}
