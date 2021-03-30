import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;

/**
 * Represents the main user interface for the Image Viewer application.
 */
public class MainWindow
{
    private Album album;
    
    private BorderPane mainBox = new BorderPane();
    private ImageView imageWidget = new ImageView();
    private Label captionWidget = new Label();
    
    /**
     * Constructs the UI object, based on an Album and a JavaFX 'Stage' (which is basically a 
     * pre-existing window).
     */
    public MainWindow(Album newAlbum, Stage stage)
    {
        album = newAlbum;
        
        stage.setTitle("JavaFX Image Viewer");
        
        // *** Fix this code so that it loads the initial (first) image. ***
        String url = new File(album.getNames().get(0)).toURI().toString();
        imageWidget.setImage(new Image(url));
        
        // Add 'mainBox' to the window. This is a container for holding the other bits: the toolbar, 
        // scroller (containing the image), and caption.
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        
        Button prevBtn = new Button("Previous");
        Button nextBtn = new Button("Next");
        ToolBar toolBar = new ToolBar(prevBtn, nextBtn);
        mainBox.setTop(toolBar);
        
        // Set up nextBtnHandler() to be called when nextBtn is clicked, and similarly for prevBtn.
        // (This uses Java 8's 'method reference' feature.)
        prevBtn.setOnAction(this::prevBtnHandler);
        nextBtn.setOnAction(this::nextBtnHandler);
        
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(imageWidget);
        mainBox.setCenter(scroller);
        
        // *** Fix this code so that it displays the caption for the first image. ***
        captionWidget.setText(album.getCaptions().get(0));
        mainBox.setBottom(captionWidget);
        
        stage.sizeToScene();
    }
    
    /**
    * Retrieves the album.
    */
    public Album getAlbum()
    {
        return album;
    }

    /**
     * Called to handle "previous" button clicks.
     */
    private void prevBtnHandler(ActionEvent event)
    {
        // *** Fix this code so that it actually displays the previous image & caption. ***
        album.decrementIdx();
        String url = new File(album.getNames().get(album.getCurrentImageIdx())).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(album.getCaptions().get(album.getCurrentImageIdx()));
    }

    /**
     * Called to handle "next" button clicks.
     */
    private void nextBtnHandler(ActionEvent event)
    {
        // *** Fix this code so that it actually displays the next image & caption. ***
        album.incrementIdx();
        String url = new File(album.getNames().get(album.getCurrentImageIdx())).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(album.getCaptions().get(album.getCurrentImageIdx()));
    }
}
