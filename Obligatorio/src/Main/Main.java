import adt.linkedlist.MyLinkedList;
import adt.linkedlist.Node;
import entities.Tweets;
import entities.User;
import exception.FileNotValidException;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class main {
    private static GetFilesInfo Csv;
    public static void main(String[] args) throws FileNotValidException {
        Csv = new GetFilesInfo();
        //File.GetDriversInfo();
        Csv.GetUsersInfo();
        start();
    }


    private static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int option = readOption(scanner);

            switch (option) {
                case 1:
                    listarPilotosActivos(scanner);
                    break;
                case 2:
                    topUsuariosConMasTweets(scanner);
                    break;
                case 3:
                    cantidadHashtagsDistintos(scanner);
                    break;
                case 4:
                    hashtagMasUsado(scanner);
                    break;
                case 5:
                    topCuentasConMasFavoritos(scanner);
                    break;
                case 6:
                    contarTweetsConPalabraFrase(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("========== Menú ==========");
        System.out.println("1. Listar los 10 pilotos activos más mencionados en los tweets");
        System.out.println("2. Top 15 usuarios con más tweets");
        System.out.println("3. Cantidad de hashtags distintos para un día dado");
        System.out.println("4. Hashtag más usado para un día dado");
        System.out.println("5. Top 7 cuentas con más favoritos");
        System.out.println("6. Cantidad de tweets con una palabra o frase específicos");
        System.out.println("0. Salir");
        System.out.print("Ingresa el número de la opción deseada: ");
    }

    private static int readOption(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void listarPilotosActivos(Scanner scanner) {
        // Implementa la lógica para listar los pilotos activos más mencionados en los tweets
    }

    private static void topUsuariosConMasTweets(Scanner scanner) {
        // Implementa la lógica para mostrar el top de usuarios con más tweets
    }

    private static void cantidadHashtagsDistintos(Scanner scanner) {
        // Implementa la lógica para contar la cantidad de hashtags distintos para un día dado
    }

    private static void hashtagMasUsado(Scanner scanner) {
        // Implementa la lógica para encontrar el hashtag más usado para un día dado
    }

    private static void topCuentasConMasFavoritos(Scanner scanner) {

    }


    private static void contarTweetsConPalabraFrase(Scanner scanner) {

        System.out.print("Ingrese la palabra clave: ");
        scanner.nextLine();
        String keyword = scanner.nextLine();
        int count = 0;
        Node<Tweets> current = Csv.getTweets().getFirst(); // Accede a la lista enlazada de tweets

        while (current != null) {
            Tweets tweet = current.getValue();
            if (tweet.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
            }
            current = current.getNext();
        }

        System.out.println("Número de tweets con la palabra o frase '" + keyword + "': " + count);

    }
}