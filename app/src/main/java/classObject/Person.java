package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */

public class Person extends TrackUnit {
    String name;

    public Person(){
        super();
        name = "Unknown";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
