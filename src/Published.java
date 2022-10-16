import java.io.Serializable;

public class Published implements Serializable {
    public TypeOfPublished type;
    private String name;
    private String author;
    private int yearOfRealise;
    public boolean isTaken;

    public Published(String name, String author, int yearOfRealise, TypeOfPublished type){
        this.name = name;
        this.author = author;
        this.yearOfRealise = yearOfRealise;
        this.type = type;
        isTaken = false;
    }

    public String getName() {
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public int getYearOfRealise(){
        return yearOfRealise;
    }
}

//class Book extends Published{
//
//    public Book(String name,String author, int yearOfRealise){
//        super(name, author, yearOfRealise);
//        type = TypeOfPublished.Book;
//    }
//
//
//}
//
//class Magazine extends Published implements Serializable{
//    public Magazine(String name,String author, int yearOfRealise){
//        super(name, author, yearOfRealise);
//        type = TypeOfPublished.Magazine;
//    }
//}
//
//class Abstract extends Published implements Serializable{
//    public Abstract(String name,String author, int yearOfRealise){
//        super(name, author, yearOfRealise);
//        type = TypeOfPublished.Abstract;
//    }
//}
//
//class Newspaper extends Published implements Serializable{
//
//    public Newspaper(String name,String author, int yearOfRealise){
//        super(name, author, yearOfRealise);
//        type = TypeOfPublished.Newspaper;
//    }
//}
