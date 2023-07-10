public class Person{
    private String name;

    public Person(String name){
        this.name = name;
    }
    /**
     * Getting persons name
     * @return the persons name 
     */
    public String getName(){
        return name;
    }
    /**
     * sets the persons name
     * @param name the persons name
     */
    public void setName(String name){
        this.name = name;
    }
}
