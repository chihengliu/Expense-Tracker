package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */
import java.util.*;
import android.os.Parcelable;
import android.os.Parcel;

public class Spending implements Parcelable {
    private  String label;
    private double amount;
    private String date;
    private String category;
    private String description;

    //Use global variable to assign unique spending ID
    private static int idCounter = 0;
    private final int spendingId;

    public Spending(){
        label = "Unknown";
        amount = 0;
        date = "Unknown";
        category = "Unknown";
        description = "Unknown";
        this.spendingId = ++idCounter;
    }

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(label);
        out.writeDouble(amount);
        out.writeString(date);
        out.writeString(category);
        out.writeString(description);
        out.writeInt(spendingId);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Spending> CREATOR = new Parcelable.Creator<Spending>() {
        public Spending createFromParcel(Parcel in) {
            return new Spending(in);
        }

        public Spending[] newArray(int size) {
            return new Spending[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Spending(Parcel in) {
         label = in.readString();
        amount = in.readDouble();
        date = in.readString();
        category = in.readString();
        description = in.readString();
        spendingId = in.readInt();
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public int getId(){
        return this.spendingId;
    }
}
