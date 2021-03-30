import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Ahmad Allahham
 */
public class AddressBook
{

    private Map<String, Entry> nameMap;
    private Map<String, Entry> emailMap;

    public AddressBook()
    {

        nameMap = new HashMap<String, Entry>();
        emailMap = new HashMap<String, Entry>();
        
    }

    public Map<String, Entry> getNameMap()
    {
        return nameMap;
    }

    public Map<String, Entry> getEmailMap()
    {
        return emailMap;
    }
    
}
