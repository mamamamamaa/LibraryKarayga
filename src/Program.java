import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Program {

    public static boolean readObj(Library data){
        File f = new File("library.bin");
        f.delete();
        try(FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Library writeObj(){
        Library data;
        File f = new File("library.bin");
        if(f.length() == 0){
            return new Library();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {

            data = (Library) ois.readObject();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Library();
    }

    public static void main(String[] args) {
        Scanner numb = new Scanner(System.in);
        Scanner text = new Scanner(System.in);
        Library lib = writeObj();
        String name, author;
        int year;
        lib.startSettings();
        ArrayList<Published> ourPub = lib.getRegistry();
        ArrayList<Customer> ourCus = lib.getCustomers();
        System.out.println("\n Меню");
        while(true) {
            System.out.println("\n1. Додати видання до реєстру");
            System.out.println("2. Видати абоненту");
            System.out.println("3. Повернення видання");
            System.out.println("4. Видалити з реєстру");
            System.out.println("5. Переглянути реєстр видань");
            System.out.println("6. Переглянути взяті книги");
            System.out.println("7. Пошук видання");
            System.out.println("8. Зберегти введені дані");
            System.out.println("9. Вийти");
            System.out.print("Вибір: ");

            int choice = numb.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\t1. Книга");
                    System.out.println("\t2. Журнал");
                    System.out.println("\t3. Автореферат");
                    System.out.println("\t4. Газета");
                    System.out.println("\t5. Назад");
                    System.out.print("\tЯкий тип видання ви хочете додати?\nВибір: ");
                    int choice2 = numb.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.print("\tНазва книги: ");
                            name = text.nextLine().trim();
                            System.out.print("\tАвтор: ");
                            author = text.nextLine().trim();
                            System.out.print("\tРік релізу: ");
                            year = numb.nextInt();
                            lib.addPublished(new Book(name, author, year));
                            System.out.println("Додано!");
                            break;
                        case 2:
                            System.out.print("\tНазва журналу: ");
                            name = text.nextLine().trim();
                            System.out.print("\tАвтор: ");
                            author = text.nextLine().trim();
                            System.out.print("\tРік релізу: ");
                            year = numb.nextInt();
                            lib.addPublished(new Magazine(name, author, year));
                            System.out.println("Додано!");
                            break;
                        case 3:
                            System.out.print("\tНазва автореферат: ");
                            name = text.nextLine().trim();
                            System.out.print("\tАвтор: ");
                            author = text.nextLine().trim();
                            System.out.print("\tРік релізу: ");
                            year = numb.nextInt();
                            lib.addPublished(new Abstract(name, author, year));
                            System.out.println("Додано!");
                            break;
                        case 4:
                            System.out.print("\tНазва газету: ");
                            name = text.nextLine().trim();
                            System.out.print("\tАвтор: ");
                            author = text.nextLine().trim();
                            System.out.print("\tРік релізу: ");
                            year = numb.nextInt();
                            lib.addPublished(new Newspaper(name, author, year));
                            System.out.println("Додано!");
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Нажаль, такого варіанту відповіді немає");
                            break;
                    }
                    break;
                case 2:
                    System.out.print("Введіть ім'я читача: ");
                    String newUserName = text.nextLine().trim();
                    Customer isNewUser = null;
                    for (int i = 0; i < ourCus.size(); i++) {
                        if(Objects.equals(ourCus.get(i).getName(), newUserName)){
                            isNewUser = ourCus.get(i);
                        }
                    }
                    System.out.print("Введіть назву книги яку ви хочете отримати: ");
                    if(lib.lendBook(isNewUser == null ? new Customer(newUserName) : isNewUser, text.nextLine().trim())){
                        System.out.println("Ось ваше видання :)");
                    }else {
                        System.out.println("Нажаль, такого видання в нас немає або воно вже зайняте :(");
                    }
                    break;
                case 3:
                    System.out.print("Введіть ім'я читача: ");
                    String userName = text.nextLine().trim();
                    Customer isOldUser = null;
                    int k = 0;
                    for (int i = 0; i < ourCus.size(); i++) {
                        if(Objects.equals(ourCus.get(i).getName(), userName)){
                            k+=1;
                            isOldUser = ourCus.get(i);
                            if(ourCus.get(i).getArrOfBooks().size() == 0){
                                System.out.println("У вас немає заборгованності");
                                break;
                            } else {
                                System.out.println("Заборгованість "+userName+": ");
                                userPubList(ourCus, i);
                            }
                        }
                    }
                    if(k==0){
                        System.out.println("Користувача на ім'я "+userName+" не знайдено");
                        break;
                    }
                    System.out.print("Введіть назву книги яку ви хочете повернути: ");
                    if(lib.returnBook(isOldUser, text.nextLine().trim())){
                        System.out.println("Операція успішна:)");
                    }else {
                        System.out.println("Щось пішло не так:(");
                    }
                    break;
                case 4:
                    System.out.print("Введіть назву книги яку ви хочете видалити: ");
                    if(lib.removePublished(text.nextLine().trim())){
                        System.out.println("Операція пройшла успішно!");
                    }else {
                        System.out.println("Нажаль, такого видання в нас немає або воно ще зайняте :(");
                    }
                    break;
                case 5:
                    System.out.println("\t1. Переглянути всі видання");
                    System.out.println("\t2. Показати книги");
                    System.out.println("\t3. Показати журнали");
                    System.out.println("\t4. Показати автореферати");
                    System.out.println("\t5. Показати газети");
                    System.out.println("\t6. Назад");

                    System.out.print("\tЯкий тип видання ви хочете побачити?\nВибір: ");
                    int choice3 = numb.nextInt();
                    switch (choice3) {
                        case 1:
                            for (int i = 0, j = 1; i < ourPub.size(); i++) {
                                if(!ourPub.get(i).isTaken){
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 2:
                            for (int i = 0, j=1; i < ourPub.size(); i++) {
                                if (ourPub.get(i).type == TypeOfPublished.Book && !ourPub.get(i).isTaken) {
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 3:
                            for (int i = 0, j=1; i < ourPub.size(); i++) {
                                if (ourPub.get(i).type == TypeOfPublished.Magazine && !ourPub.get(i).isTaken) {
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 4:
                            for (int i = 0, j=1; i < ourPub.size(); i++) {
                                if (ourPub.get(i).type == TypeOfPublished.Abstract && !ourPub.get(i).isTaken) {
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 5:
                            for (int i = 0, j=1; i < ourPub.size(); i++) {
                                if (ourPub.get(i).type == TypeOfPublished.Newspaper && !ourPub.get(i).isTaken) {
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Нажаль, такого варіанту немає");
                            break;
                    }
                    break;
                case 6:
                    for (int i = 0; i < ourCus.size(); i++) {
                        System.out.println("Користувач: "+ourCus.get(i).getName()+"\nЗаборогованність: ");
                        userPubList(ourCus, i);
                    }
                    break;
                case 7:
                    System.out.println("\t1. Шукаи за назвою");
                    System.out.println("\t2. Шукати за автором");
                    System.out.println("\t3. Шукати за роком видання");
                    System.out.println("\t4. Назад");

                    System.out.print("\tЯкий тип видання ви хочете побачити?\nВибір: ");
                    int choice4 = numb.nextInt();
                    switch (choice4) {
                        case 1:
                            System.out.print("Введіть назву шуканого видання: ");
                            String template = text.nextLine().trim();
                            for (int i = 0, j = 1; i < ourPub.size(); i++) {
                                if(ourPub.get(i).getName().contains(template) && !ourPub.get(i).isTaken){
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 2:
                            System.out.print("Введіть автора шуканого видання: ");
                            String authorName = text.nextLine().trim();
                            for (int i = 0, j = 1; i < ourPub.size(); i++) {
                                if(ourPub.get(i).getAuthor().contains(authorName) && !ourPub.get(i).isTaken){
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 3:
                            System.out.print("Введіть рік релізу шуканого видання: ");
                            int pubYear = numb.nextInt();
                            for (int i = 0, j = 1; i < ourPub.size(); i++) {
                                if((ourPub.get(i).getYearOfRealise() == pubYear) && (!ourPub.get(i).isTaken)){
                                    System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                                    j+=1;
                                }
                            }
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Нажаль, такого варіанту немає");
                            break;
                    }
                    break;
                case 8:
                    if (readObj(lib)){
                        System.out.println("Дані було успішно збережено!");
                    } else {
                        System.out.println("Дані не вдалось зберегти");
                    }
                    break;
                case 9:
                    System.out.println("Папа!");
                    return;

                default:
                    break;
            }
        }
    }

    private static void userPubList(ArrayList<Customer> ourCus, int i) {
        ArrayList<Published> books = ourCus.get(i).getArrOfBooks();
        for (int j = 0; j < books.size(); j++) {
            System.out.println("\t"+(j+1)+") "+books.get(j).getName()+ " - " + books.get(j).getAuthor()+ " - " + books.get(j).getYearOfRealise());
        }
    }
}
