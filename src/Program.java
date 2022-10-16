import java.util.ArrayList;
import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();

        Scanner numb = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        Library lib = menu.writeObj();
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
            System.out.println("8. Вийти");
            System.out.print("Вибір: ");

            int choice = numb.nextInt();
            switch (choice) {
                case 1:
                    menu.addPublishedMenu(numb, text, lib);
                    menu.saveDataMenu(lib);
                    break;
                case 2:
                    menu.lendPubMenu(text, ourCus, lib);
                    menu.saveDataMenu(lib);
                    break;
                case 3:
                    menu.returnPubMenu(text, ourCus, lib);
                    menu.saveDataMenu(lib);
                    break;
                case 4:
                    menu.deletePubMenu(text, lib);
                    menu.saveDataMenu(lib);
                    break;
                case 5:
                    menu.showPubMenu(numb, ourPub);
                    break;
                case 6:
                    menu.showTakenPubMenu(ourCus);
                    break;
                case 7:
                    menu.searchPubMenu(numb, text, ourPub);
                    break;
                case 8:
                    System.out.println("Папа!");
                    return;
                default:
                    break;
            }
        }
    }
}
