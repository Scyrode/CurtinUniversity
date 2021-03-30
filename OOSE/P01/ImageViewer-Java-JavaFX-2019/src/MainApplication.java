import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }
    
    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage)
    {
        // Construct an album object.
        Album album = new Album();
        
        // Make a new window.
        MainWindow window = new MainWindow(album, stage);        
        
        // Choose which album to load.
        File albumFile = window.chooseAlbumFile();
        
        if(albumFile == null)
        {
            Platform.exit(); // Otherwise JavaFX keeps the program open, doing nothing.
        }
        else
        {
            try
            {
                // Attempt to read an album file.
                readAlbumFile(albumFile, album);
                
                // Display the window.
                window.show();
            }
            catch(IOException e)
            {
                System.err.println("Error while reading " + albumFile);
                Platform.exit();
            }
        }
    }
    
    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFile The file storing the list of image filenames and their captions.
     * @param album An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(File albumFile, Album album) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(albumFile));
        String line = reader.readLine();
        while(line != null)
        {
            if(line.trim().length() > 0) // Ignore blank lines
            {
                String[] parts = line.split(":", 2);
                
                String imageFilename = albumFile.getParent() + File.separatorChar + parts[0];
                String imageCaption = "";
                if(parts.length == 2)
                {
                    imageCaption = parts[1];
                }
                
                album.getNames().add(imageFilename);
                album.getCaptions().add(imageCaption);
            }
                        
            line = reader.readLine();
        }
        reader.close();
    }

}
