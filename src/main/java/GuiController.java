
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;


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

  @FXML
  public Label DragAndDrop;

  public void imageDragOver (DragEvent de){
    Dragboard board = de.getDragboard();
    if (board.hasFiles()) {
      de.acceptTransferModes(TransferMode.ANY);
    }
  }

    @FXML
    public void imageDropped (DragEvent de){
    try {
      Dragboard board = de.getDragboard();
      List<File> phil = board.getFiles();
      FileInputStream fis;
      fis = new FileInputStream(phil.get(0));
      Image image = new Image(fis);
      imageView.setImage(image);
      imageView.setPreserveRatio(true);
      imageView.setFitHeight(300);
      imageView.setFitWidth(300);
      imageView.setSmooth(true);
      btnCaption.setDisable(false);
    } catch (FileNotFoundException e) {
      e.printStackTrace();

    }
  }

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
