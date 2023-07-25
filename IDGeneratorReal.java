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
    public String generateID() {
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
    public String generateAccountID() {
        String newId;
        do {
            newId = String.format("%04d", ++idCounter % 10000); // Limit to 4 digits 
        } while (idExists(newId, 8) || idExists(newId, 10) || idExists(newId, 12));
        return newId;
    }

    /**
     * Checks if an ID already exists in the CSV file.
     *
     * @param id The ID to check.
     * @param index The index of the ID in the CSV file.
     * @return True if the ID exists, false otherwise.
     */
    private boolean idExists(String id, int index) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSVFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[index].equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
