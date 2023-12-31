import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class generates unique ID numbers for customers and accounts.
 */
class IDGeneratorReal implements IDGenerator {

    private static int idCounter = 0;
    private static final String CSVFile = "BankUser.CSV"; // could not inherent, so I did another reader

    /**
     * Generates a unique ID for a customer.
     *
     * @return A unique customer ID.
     */
    @Override
    public String generateID() { //overrides generateID from interface.
        String newId;
        do {
            newId = String.format("%03d", ++idCounter % 1000); // Limit to 3 digits
        } while (idExists(newId, 0));
        return newId;
    }

    /**
     * Generates a unique ID for an account.
     *
     * @return A unique account ID.
     */
    @Override
    public String generateAccountID() {
        String newId;
        do {
            newId = String.format("%04d", ++idCounter % 10000); // Limit to 4 digits  and max number 9999
        } while (idExists(newId, 8) || idExists(newId, 10) || idExists(newId, 12)); // check that ID does not exist in indexes for accounts.
        return newId; // once it reaches a false, it returns a new ID using counter. 
    }

    /**
     * Checks if an ID already exists in the CSV file.
     *
     * @param id The ID to check.
     * @param index The index of the ID in the CSV file.
     * @return True if the ID exists, else false.
     */
    private boolean idExists(String id, int index) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSVFile))) { //read CSV file BankUser
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[index].equals(id)) { // if the value in CSV file equals to ID generated, return true/ else false
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
