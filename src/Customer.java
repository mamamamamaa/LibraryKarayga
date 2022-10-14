import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String name;
    public ArrayList<Published> arrOfBooks;

    public Customer(String name){
        this.name = name;
        arrOfBooks = new ArrayList<Published>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Published> getArrOfBooks() {
        return arrOfBooks;
    }
}
