import adt.hashtable.HashNode;
import adt.hashtable.MyHashTable;
import adt.heap.MyHeap;
import adt.linkedlist.MyLinkedList;
import adt.linkedlist.Node;
import entities.User;
import entities.Hashtag;
import entities.Tweets;
import exception.FileNotValidException;
import exception.InvalidDateException;

import java.util.Scanner;

public class Main {
    private static GetFilesInfo Csv;
    public static void main(String[] args) throws FileNotValidException, InvalidDateException {
        Csv = new GetFilesInfo();
        Csv.GetDriversInfo();
        Csv.GetUsersInfo();
        start();
    }

    private static boolean isValidDate(String date) {
        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";

        if (date.matches(dateFormatRegex)) {
            String[] dateArray = date.split("-");
            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);

            if ((year == 2021 && month >= 7) || (year == 2022 && month <= 8)) {
                return true;
            }
        }

        return false;
    }

    private static void start() throws  InvalidDateException {
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

    private static void cantidadHashtagsDistintos(Scanner scanner) throws InvalidDateException {
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        scanner.nextLine();
        String date = scanner.nextLine();
        int hashtagQty = 0;
        MyLinkedList<String> hashtagMyLinkedList = new MyLinkedList<>();
        if (isValidDate(date)) {
            Node<Tweets> current = Csv.getTweets().getFirst();
            while (current != null) {
                String tweetDate = current.getValue().getDate();
                if (tweetDate.equals(date)) {
                    Node<Hashtag> currentHashtag = current.getValue().getHashtags().getFirst();
                    while (currentHashtag != null) {
                        String hashtagText = currentHashtag.getValue().getText().toLowerCase();
                        if (!(hashtagMyLinkedList.contains(hashtagText))) {
                            hashtagMyLinkedList.add(hashtagText);
                            hashtagQty++;
                        }
                        currentHashtag = currentHashtag.getNext();
                    }
                }
                current = current.getNext();
            }
            System.out.println("La cantidad de hashtags distintos para el día " + date + " es: " + hashtagQty);
        }else throw new InvalidDateException("Fecha fuera del rango permitido o formato inválido.");
    }



    private static void hashtagMasUsado(Scanner scanner) throws  InvalidDateException {
        MyHashTable<String,Integer> hashtagHash = new MyHashTable<>();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        scanner.nextLine();
        String date = scanner.nextLine();
        String maxHashtag = null;
        int maxCount = 0;
        if (isValidDate(date)) {
            Node<Tweets> current = Csv.getTweets().getFirst();
            while (current != null) {
                String tweetDate = current.getValue().getDate();
                if (tweetDate.equals(date)) {
                    Node<Hashtag> currentHashtag = current.getValue().getHashtags().getFirst();
                    while (currentHashtag != null) {
                        String hashtagText = currentHashtag.getValue().getText();
                        if (!(hashtagHash.contains(hashtagText))) {
                            if (!currentHashtag.getValue().getText().equalsIgnoreCase("f1")) {
                                hashtagHash.put(currentHashtag.getValue().getText(), 1);
                            }
                        } else {
                            HashNode<String, Integer> changeNode = hashtagHash.get(currentHashtag.getValue().getText());
                            int newCount = changeNode.getValue() + 1;
                            changeNode.setValue(newCount);

                            if (newCount > maxCount) {
                                maxHashtag = hashtagText;
                                maxCount = newCount;
                            }
                        }
                        currentHashtag = currentHashtag.getNext();
                    }
                }
                current = current.getNext();
            }


        } else throw new InvalidDateException("Fecha fuera del rango permitido o formato inválido.");
        // Verificar si se encontró un hashtag más utilizado
        if (maxHashtag != null) {
            System.out.println("El hashtag más utilizado es: " + maxHashtag);
            System.out.println("Aparece " + maxCount + " veces.");
        } else {
            System.out.println("No se encontró ningún hashtag en la tabla.");
        }

    }

    private static void topCuentasConMasFavoritos(Scanner scanner) {
        MyLinkedList<User> listaUsers = Csv.getUsers();
        MyHeap<User> heapUsers = new MyHeap<>(true, false);
        for (int i = 0; i < listaUsers.size(); i++) {
            heapUsers.insert(listaUsers.get(i));
        }
        User[] top7 = new User[7];
        for (int i = 0; i < 7 ; i++){
            top7[i] = heapUsers.deleteAndReturn();
        }
        System.out.println("Top 7 cuentas con más favoritos: ");
        for (int i = 0; i < 7; i++) {
            System.out.println(top7[i].getName() + " con " + top7[i].getFavourites() + " favoritos.");
        }
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