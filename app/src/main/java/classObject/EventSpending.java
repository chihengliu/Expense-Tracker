package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */

public class EventSpending extends Spending {
    String personId;

    public void setPerson(String personId){
        this.personId = personId;
    }

    public String getPerson(){
        return this.personId;
    }
}
