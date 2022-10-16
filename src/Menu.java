import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    public boolean readObj(Library data){
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

    public Library writeObj(){
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

    public void userPubList(ArrayList<Customer> ourCus, int i) {
        ArrayList<Published> books = ourCus.get(i).getArrOfBooks();
        for (int j = 0; j < books.size(); j++) {
            System.out.println("\t"+(j+1)+") "+books.get(j).getName()+ " - " + books.get(j).getAuthor()+ " - " + books.get(j).getYearOfRealise());
        }
    }

    public void addPublishedMenu(Scanner numb, Scanner text, Library lib){

        String name, author;
        int year;
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
                lib.addPublished(new Published(name, author, year, TypeOfPublished.Book));
                System.out.println("Додано!");
                break;
            case 2:
                System.out.print("\tНазва журналу: ");
                name = text.nextLine().trim();
                System.out.print("\tАвтор: ");
                author = text.nextLine().trim();
                System.out.print("\tРік релізу: ");
                year = numb.nextInt();
                lib.addPublished(new Published(name, author, year, TypeOfPublished.Magazine));
                System.out.println("Додано!");
                break;
            case 3:
                System.out.print("\tНазва автореферат: ");
                name = text.nextLine().trim();
                System.out.print("\tАвтор: ");
                author = text.nextLine().trim();
                System.out.print("\tРік релізу: ");
                year = numb.nextInt();
                lib.addPublished(new Published(name, author, year, TypeOfPublished.Abstract));
                System.out.println("Додано!");
                break;
            case 4:
                System.out.print("\tНазва газету: ");
                name = text.nextLine().trim();
                System.out.print("\tАвтор: ");
                author = text.nextLine().trim();
                System.out.print("\tРік релізу: ");
                year = numb.nextInt();
                lib.addPublished(new Published(name, author, year, TypeOfPublished.Newspaper));
                System.out.println("Додано!");
                break;
            case 5:
                break;
            default:
                System.out.println("Нажаль, такого варіанту відповіді немає");
                break;
        }
    }

    public void lendPubMenu(Scanner text, ArrayList<Customer> ourCus, Library lib){
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
    }

    public void returnPubMenu(Scanner text, ArrayList<Customer> ourCus, Library lib){
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
            return;
        }
        System.out.print("Введіть назву книги яку ви хочете повернути: ");
        if(lib.returnBook(isOldUser, text.nextLine().trim())){
            System.out.println("Операція успішна:)");
        }else {
            System.out.println("Щось пішло не так:(");
        }
    }

    public void deletePubMenu(Scanner text, Library lib){
        System.out.print("Введіть назву книги яку ви хочете видалити: ");
        if(lib.removePublished(text.nextLine().trim())){
            System.out.println("Операція пройшла успішно!");
        }else {
            System.out.println("Нажаль, такого видання в нас немає або воно ще зайняте :(");
        }
    }

    public void showPubMenu(Scanner numb, ArrayList<Published> ourPub){
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
    }

    public void showTakenPubMenu(ArrayList<Customer> ourCus){
        for (int i = 0; i < ourCus.size(); i++) {
            System.out.println("Користувач: "+ourCus.get(i).getName()+"\nЗаборогованність: ");
            userPubList(ourCus, i);
        }
    }

    public void searchPubMenu(Scanner numb, Scanner text, ArrayList<Published> ourPub){
        System.out.println("\t1. Шукати за назвою");
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
                    if(ourPub.get(i).getName().toLowerCase().contains(template.toLowerCase()) && !ourPub.get(i).isTaken){
                        System.out.println("\t"+(j)+") "+ourPub.get(i).getName()+ " - " + ourPub.get(i).getAuthor()+ " - " + ourPub.get(i).getYearOfRealise());
                        j+=1;
                    }
                }
                break;
            case 2:
                System.out.print("Введіть автора шуканого видання: ");
                String authorName = text.nextLine().trim();
                for (int i = 0, j = 1; i < ourPub.size(); i++) {
                    if(ourPub.get(i).getAuthor().toLowerCase().contains(authorName.toLowerCase()) && !ourPub.get(i).isTaken){
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
    }

    public void saveDataMenu(Library lib){
        if (readObj(lib)){
            System.out.println("Дані було успішно збережено!");
        } else {
            System.out.println("Дані не вдалось зберегти");
        }
    }
}
