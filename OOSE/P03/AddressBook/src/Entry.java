import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Ahmad Allahham
 */
public class Entry 
{

    // CLASS FIELDS
    private String name;
    private Set<String> emails;

    // ALTERNATE CONSTRUCTOR
    public Entry()
    {
        emails = new HashSet<String>();
    }

    // GETTERS
    public String getName()
    {
        return name;
    }

    public Set<String> getEmails()
    {
        return emails;
    }

    // SETTERS
    public void setName(String inName)
    {
        name = inName;
    }

    public String toString()
    {
        return name + " : " + emails.toString();
    }
    
}
