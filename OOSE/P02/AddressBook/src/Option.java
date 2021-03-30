public interface Option {

    String doOption(String s, AddressBook addressBook);
    boolean requiresText();
    
}

class SearchByName implements Option
{

    @Override
    public String doOption(String s, AddressBook addressBook) {
        
        Entry entrySearch = addressBook.getNameMap().get(s);
        String result;

        if (entrySearch != null)
        {
            result = entrySearch.toString();
        } else
        {
            result = "Entry does not exist";
        }

        return result;
        
    }

    @Override
    public boolean requiresText() {
        return true;
    }

}

class SearchByEmail implements Option
{

    @Override
    public String doOption(String s, AddressBook addressBook) {

        Entry entrySearch = addressBook.getEmailMap().get(s);
        String result;

        if (entrySearch != null)
        {
            result = entrySearch.toString();
        } else
        {
            result = "Entry does not exist";
        }

        return result;

    }

    @Override
    public boolean requiresText() {
        return true;
    }
    
}

class PrintAllEntries implements Option
{

    @Override
    public String doOption(String s, AddressBook addressBook) {

        StringBuilder result = new StringBuilder();

        addressBook.getNameMap().forEach((key, entry) -> result.append(entry.toString() + "\n"));

        return result.toString();

    }

    @Override
    public boolean requiresText() {
        return false;
    }
    
}