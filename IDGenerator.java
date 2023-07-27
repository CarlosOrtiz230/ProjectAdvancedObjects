/**
 * The IDGenerator interface defines methods to generate unique IDs
 */
public interface IDGenerator {

    /**
     * Generates a unique 3-digit ID
     *
     * @return A String representing the generated 3-digit ID.
     */
    String generateID();

    /**
     * Generates a unique account 4-digit ID
     *
     * @return A String representing the generated 4-digit account ID.
     */
    String generateAccountID();
}
