import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{

    // CLASSFIELDS
    private List<String> names;
    private List<String> captions;
    private int currentImageIdx;

    public Album()
    {

        names = new LinkedList<>();
        captions = new LinkedList<>();
        currentImageIdx = 0;
        
    }

    public List<String> getNames()
    {
        return names;
    }

    public List<String> getCaptions()
    {
        return captions;
    }

    public int getCurrentImageIdx()
    {
        return currentImageIdx;
    }

    public void incrementIdx()
    {
        currentImageIdx = (currentImageIdx + 1) % names.size();
    }

    public void decrementIdx()
    {
        if (currentImageIdx == 0)
        {
            currentImageIdx = names.size();
        } else
        {
            currentImageIdx--;
        }
    }
    
}
