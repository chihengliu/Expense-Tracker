package classObject;

/**
 * Created by machengzhang on 2/27/17.
 */

public class Spending {
    String label;
    double amount;
    String date;
    int category;
    String description;

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

    public void setCategory(int category){
        this.category = category;
    }

    public int getCategory(){
        return this.category;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
