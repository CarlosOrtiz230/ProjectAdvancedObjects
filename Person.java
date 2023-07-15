/**
 * The abstract class representing a person with a name and address.
 */
public abstract class Person {
    //attributes----------------------------------------------------------------
    private String name;  
    private String address; 
    private String dob;
    private String phoneNumber;

    //constructor----------------------------------------------------------------
    public Person() {} //default 

    /**
     * Constructs a new instance of the Person class with the specified name and address.
     *
     * @param name    the name of the person
     * @param address the address of the person
     */
    public Person(String name, String address,String dob,String phoneNumber) {
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    //getters and setters----------------------------------------------------------------

    /**
     * Retrieves the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
         * Retrieves the date of birth of the customer.
         *
         * @return The date of birth of the customer.
    */
    public String getDob(){
        return this.dob;
    }

    /**
         * Retrieves the phone number of the customer.
         *
         * @return The phone number of the customer.
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
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
