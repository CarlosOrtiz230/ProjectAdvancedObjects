/**
 * The abstract class representing a person with a name and address.
 */
public abstract class Person {
    
    private String name;  
    private String address; 

    /**
     * Constructs a new instance of the Person class with the specified name and address.
     *
     * @param name    the name of the person
     * @param address the address of the person
     */
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the address of the person.
     *
     * @return the address of the person
     */
    public String getAddress() {
        return address;
    }
}
