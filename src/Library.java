import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Library implements Serializable {
    private ArrayList<Published> registry;
    private ArrayList<Customer> customers;

    public Library(){
        registry = new ArrayList<Published>();
        customers = new ArrayList<Customer>();
    }

    public boolean addPublished(Published item){
        for (int i = 0; i < registry.size(); i++) {
            if(Objects.equals(registry.get(i).getName(), item.getName())
                    && Objects.equals(registry.get(i).getAuthor(), item.getAuthor())
                    && registry.get(i).getYearOfRealise() == item.getYearOfRealise()){

                return false;

            }
        }
        registry.add(item);
        return true;
    }

    public void startSettings(){
        String []Books = {"Путешествие Пилигрима в Небесную Страну", "Робинзон Крузо", "Путешествия Гулливера", "Кларисса", "История Тома Джонса, найденыша" };
        String []Newspaper = {"Жизнь и мнения Тристрама Шенди, джентльмена", "Эмма", "Франкенштейн, или Современный Прометей", "Аббатство кошмаров"};
        String []Abstract = {"Повесть о приключениях Артура Гордона Пима", "Сибилла", "Джейн Эйр" };

        for (String item: Books) {
            addPublished(new Published(item, "Maxix", 2004, TypeOfPublished.Book));
        }
        for (String item: Newspaper) {
            addPublished(new Published(item, "Valera", 1999, TypeOfPublished.Newspaper));
        }
        for (String item: Abstract) {
            addPublished(new Published(item, "Sabir", 2022, TypeOfPublished.Abstract));
        }
    }

    public boolean removePublished(Published item){
        if(registry.contains(item) && !item.isTaken){
            registry.remove(item);
            return true;
        }
        return false;
    }

    public boolean removePublished(String name){
        return removePublished(getPublishedByName(name));
    }

    public boolean lendBook(Customer user, Published item){
        if(registry.contains(item)){
            if (!customers.contains(user)){
                customers.add(user);
            }
            item.isTaken = true;
            user.arrOfBooks.add(item);
            return true;
        }

        return false;
    }

    public boolean lendBook(Customer user, String name){
        return lendBook(user, getPublishedByName(name));
    }

    public ArrayList<Published> getRegistry() {return registry;}

    public ArrayList<Customer> getCustomers() {return customers;}

    public boolean returnBook(Customer user, Published item){
        if(customers.contains(user) && user.arrOfBooks.contains(item)){
            user.arrOfBooks.remove(item);
            item.isTaken = false;
            if(user.arrOfBooks.isEmpty()){
                customers.remove(user);
            }
            return true;
        }
        return false;
    }

    public boolean returnBook(Customer user, String name){
        return returnBook(user, getPublishedByName(name));
    }

    public Published getPublishedByName(String name) {
        for(Published item : registry){
            if(Objects.equals(item.getName(), name)){
                return item;
            }
        }
        return null;
    }
}
