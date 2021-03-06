package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */
import java.text.ParseException;
import java.util.*;

import android.icu.text.SimpleDateFormat;
import android.os.Parcelable;
import android.os.Parcel;

public class Spending implements Parcelable {
    private  String name;
    private String category;
    private String description;
    private int eventId;
    private int spendingId;
    private double amount;
    private Date date;
    private String s_date;

    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");

    public Spending(){
        name = "Unknown";
        category = "Unknown";
        description = "Unknown";
        spendingId = 0;
        eventId = 0;
        amount = 0;
        s_date = "Unknown";
        date = new Date();
    }

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(category);
        out.writeString(description);
        out.writeInt(spendingId);
        out.writeInt(eventId);
        out.writeDouble(amount);
        out.writeString(s_date);
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
        name = in.readString();
        category = in.readString();
        description = in.readString();
        spendingId = in.readInt();
        eventId = in.readInt();
        amount = in.readDouble();
        s_date = in.readString();

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void set_s_date(String date){
        this.s_date = date;
        try {
            this.date = ft.parse(this.s_date);
        }catch (ParseException e) {
            System.out.println("Unparseable");
        }
    }

    public String get_s_date(){
        return this.s_date;
    }

    public void setDate(){
        try {
            this.date = ft.parse(this.s_date);
        }catch (ParseException e) {
            System.out.println("Unparseable");
        }
    }

    public Date getDate(){
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

    public void setId(int id) {this.spendingId = id;  }

    public int getId(){
        return this.spendingId;
    }

    public void setEventId(int id){
        this.eventId = id;
    }

    public int getEventId(){
        return this.eventId;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }


}
