package classObject;

import android.app.Application;
import classObject.*;
/**
 * Created by machengzhang on 3/4/17.
 */

public class Global extends Application{
    public Spending sp;

    public Global(){
        sp = new Spending();
    }
}
