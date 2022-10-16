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

class Book extends Published implements Serializable{
    private int numbOfPages;
    public Book(String name,String author, int yearOfRealise, TypeOfPublished type, int numbOfPages){
        super(name, author, yearOfRealise, type);
        this.numbOfPages = numbOfPages;
    }

    public int getNumbOfPages() {
        return numbOfPages;
    }
}

class Magazine extends Published implements Serializable{
    private int editionNumber;
    public Magazine(String name,String author, int yearOfRealise, TypeOfPublished type, int numbOfPages){
        super(name, author, yearOfRealise, type);
        this.editionNumber = numbOfPages;
    }

    public int getEditionNumber() {
        return editionNumber;
    }
}

