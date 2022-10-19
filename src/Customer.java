import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String name;
    private String phoneNumber;
    public ArrayList<Published> arrOfBooks;

    public Customer(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        arrOfBooks = new ArrayList<Published>();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Published> getArrOfBooks() {
        return arrOfBooks;
    }
}
