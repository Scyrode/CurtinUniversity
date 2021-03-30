import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...Ahmad Allahham
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        AddressBookApp addressBookApp = new AddressBookApp();
        Map<String, Option> options = new HashMap<>();
        String fileName, entryName;

        addressBookApp.addOption(options, 1);
        addressBookApp.addOption(options, 2);
        addressBookApp.addOption(options, 3);
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = addressBookApp.readAddressBook(fileName);
            addressBookApp.showMenu(addressBook, options);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while(line != null)
        {

            String[] parts = line.split(":");

            Entry newEntry = new Entry();

            newEntry.setName(parts[0]);

            for (int i = 1; i < parts.length; i++)
            {
                newEntry.getEmails().add(parts[i]);
            }

            addressBook.getNameMap().put(newEntry.getName(), newEntry);

            for (String email : newEntry.getEmails()) {
                addressBook.getEmailMap().put(email, newEntry);
            }
            
            line = reader.readLine();
            
        }

        reader.close();
        
        return addressBook;
        
    }
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private void showMenu(AddressBook addressBook, Map<String, Option> options)
    {
        boolean done = false;
        String searchTerm = null;
        while(!done)
        {
            int option;
            System.out.println("(1) Search by name, (2) Search by email, (3) Print All Entries, (4) Quit");

            try {

                option = Integer.parseInt(input.nextLine());

                if (option == 4)
                {
                    done = true;
                } else {

                    if (options.get(Integer.toString(option)).requiresText())
                    {

                        System.out.print("Enter Search Term: ");
                        searchTerm = input.nextLine();
                        
                    }

                    System.out.println(options.get(Integer.toString(option)).doOption(searchTerm, addressBook));
                    
                }
                   
            }catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
            
        }
    }

    private void addOption(Map<String, Option> options, Integer option)
    {

        switch (option) {
            case 1:

                options.put(Integer.toString(option), new SearchByName());
                break;

            case 2:
            
                options.put(Integer.toString(option), new SearchByEmail());
                break;

            case 3:
            
                options.put(Integer.toString(option), new PrintAllEntries());
                break;
        
            default:
                System.out.println("no such option exists");
                break;
        }
        
    }

}
