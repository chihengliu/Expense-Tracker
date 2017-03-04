package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */

import java.util.ArrayList;

public abstract class TrackUnit {
    ArrayList<Spending> spendingList;

    public TrackUnit(){
        spendingList = new ArrayList<Spending>();
    }

    public void addSpending(Spending s){
        spendingList.add(s);
    }

}
