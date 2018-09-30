
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuiController {
  
  @FXML
  public Button btnBrowse;
  
  @FXML
  public Button btnCaption;
  
  @FXML
  public Label caption;
  
  @FXML
  public ImageView imageView;
  
  @FXML
  public Image image;
  
  
  /**
   * Handles the Browse button action.
   */
  public void handleBtnBrowse() {
    FileChooser fc = new FileChooser();
    Stage stage = (Stage) btnBrowse.getScene().getWindow();
  
    fc.setTitle("Open File");
    File file = fc.showOpenDialog(stage);
  
    try {
      Image image = new Image(String.valueOf(file.toURI().toURL()));
      imageView.setImage(image);
      imageView.setPreserveRatio(true);
      imageView.setFitHeight(300);
      imageView.setFitWidth(300);
      imageView.setSmooth(true);
    
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  
    //enable caption button for captioning
    if (file != null) {
      btnCaption.setDisable(false);
    }
  }
  
  /**
   * Handles the Caption button action.
   */
  public void handleBtnCaption() {
    //Instantiate CaptionGenerator
    CaptionGenerator cg = new CaptionGenerator();
    //Convert JavaFX Image to BufferedImage
    BufferedImage img = SwingFXUtils.fromFXImage(imageView.getImage(), null);
    //Run captioning method and set label text
    caption.setText(cg.generateCaption(img));
  }
}
