import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName, entryName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
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
    private static AddressBook readAddressBook(String fileName) throws IOException
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

                System.out.println(email);
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
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        Entry entrySearch;
        while(!done)
        {
            int option;
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:

                        System.out.print("Enter name: ");
                        String name = input.nextLine();

                        entrySearch = addressBook.getNameMap().get(name);

                        if (entrySearch != null)
                        {
                            System.out.println(entrySearch.toString());
                        } else
                        {
                            System.out.println("Entry does not exist");
                        }
                        
                        break;
                        
                    case 2:

                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        
                        entrySearch = addressBook.getEmailMap().get(email);

                        if (entrySearch != null)
                        {
                            System.out.println(entrySearch.toString());
                        } else
                        {
                            System.out.println("Entry does not exist");
                        }
                        
                        break;
                        
                    case 3:
                        done = true;
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }
}
