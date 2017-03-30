package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */

import java.lang.reflect.Array;
import java.util.*;
import android.os.Parcelable;
import android.os.Parcel;

public class Event implements Parcelable {
    private  String name;
    private String date;
    private String description;
    private ArrayList<String> members;


    //Use global variable to assign unique spending ID
    //private static int idCounter = -1;
    private int eventId;

    public Event(){
        name = "Unknown";
        date = "Unknown";
        description = "Unknown";
        eventId = 0;
        members = new ArrayList<>();
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
        out.writeString(date);
        out.writeString(description);
        out.writeInt(eventId);
        out.writeSerializable(members);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Event(Parcel in) {
        name = in.readString();
        date = in.readString();
        description = in.readString();
        eventId = in.readInt();
        members = (ArrayList<String>) in.readSerializable();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setId(int id) {this.eventId = id;  }

    public int getId(){
        return this.eventId;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public void addMember(String newMember) {
        this.members.add(newMember);
    }

    public ArrayList<String> getMembers() {return this.members;}
}