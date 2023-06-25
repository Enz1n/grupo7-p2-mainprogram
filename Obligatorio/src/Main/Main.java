import uy.edu.um.prog2.adt.hashtable.HashNode;
import uy.edu.um.prog2.adt.hashtable.MyHashTable;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;
import uy.edu.um.prog2.adt.linkedlist.Node;
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

    public static void Calculareficiencia(Runnable code) {
        // Inicio de la medición del tiempo de ejecución
        long startTime = System.currentTimeMillis();

        // Ejecución del código
        code.run();

        // Finalización de la medición del tiempo de ejecución
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime; // Duración en milisegundos

        // Cálculo de la cantidad de memoria consumida
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        long usedMemory = totalMemory - rt.freeMemory();

        // Imprimir los resultados
        System.out.println("Cantidad de memoria RAM consumida: " + usedMemory + " bytes");
        System.out.println("Tiempo de ejecución promedio: " + (duration / 1000.0) + " segundos");
    }

    private static boolean isValidDate(String date) throws InvalidDateException {
        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";

        if (date.matches(dateFormatRegex)) {
            String[] dateArray = date.split("-");
            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);

            if ((year == 2021 && month >= 7) || (year == 2022 && month <= 8)) {
                return true;
            }
        }else{
            throw new InvalidDateException("Fecha fuera del rango permitido o formato inválido.");
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
        MyHashTable<String,Integer> hashTable = new MyHashTable<>();
        System.out.print("Ingrese el año: ");
        int year = scanner.nextInt();
        System.out.print("Ingrese el mes: ");
        int month = scanner.nextInt();
        Calculareficiencia(() -> {
            Node<Tweets> currentTweet = Csv.getTweets().getFirst();
            while (currentTweet != null){
                Node<String> currentDriver = Csv.getDriversLinkedList().getFirst();
                String fechaTweet = currentTweet.getValue().getDate();
                String[] fechaTweetArray = fechaTweet.split("-");
                int yearTweet = Integer.parseInt(fechaTweetArray[0]);
                int monthTweet = Integer.parseInt(fechaTweetArray[1]);
                if ((yearTweet == year && monthTweet == month)){
                    while (currentDriver != null) {
                        String tweetContent = currentTweet.getValue().getContent().toLowerCase();
                        String driverName = currentDriver.getValue().toLowerCase();
                        if (!hashTable.contains(currentDriver.getValue().toLowerCase())) {
                            hashTable.put(currentDriver.getValue().toLowerCase(), 0);
                        }
                        if ((tweetContent.toLowerCase().contains(driverName))) {
                            HashNode<String, Integer> driverChange = hashTable.get(currentDriver.getValue());
                            driverChange.setValue(driverChange.getValue() + 1);
                        }
                        currentDriver = currentDriver.getNext();
                    }
                }
                currentTweet = currentTweet.getNext();
            }
            MyLinkedList<HashNode<String,Integer>> allEntries = hashTable.getAllEntries();
            MyHeap<HashNode<String,Integer>> heapDrivers = new MyHeap<>(true, false);
            Node<HashNode<String,Integer>> currentNode = allEntries.getFirst();
            while (currentNode != null){
                heapDrivers.insert(currentNode.getValue());
                currentNode = currentNode.getNext();
            }
            HashNode<String,Integer>[] top10 = new HashNode[10];
            for (int i = 0; i < 10 ; i++){
                top10[i] = heapDrivers.deleteAndReturn();
                System.out.println(top10[i].getKey() + " con " + top10[i].getValue() + " ocurrencias.");
            }
        });
    }

    private static void topUsuariosConMasTweets(Scanner scanner) {
        MyLinkedList<User> users = Csv.getUsers();
        MyLinkedList<User> top15 = new MyLinkedList<>();

        createTop15UsersList(users, top15);
        usersByTweetsQty(top15);

    }

    public static void createTop15UsersList(MyLinkedList<User> users, MyLinkedList<User> top15) {
        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);
            if (top15.contains(currentUser)) {
                continue;
            }
            if (top15.size() < 15) {
                top15.add(currentUser);
            } else {

                int minIndex = 0;
                for (int j = 1; j < top15.size(); j++) {
                    if (top15.get(j).getTweets().size() < top15.get(minIndex).getTweets().size()) {
                        minIndex = j;
                    }
                }
                if (currentUser.getTweets().size() > top15.get(minIndex).getTweets().size()) {
                    top15.remove(top15.get(minIndex));
                    top15.add(currentUser);
                }
            }
        }
    }

    public static void usersByTweetsQty(MyLinkedList<User> users) {
        int n = users.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                User user1 = users.get(j);
                User user2 = users.get(j + 1);

                if (user1.getTweets().size() < user2.getTweets().size()) {
                    users.swap(j, j + 1);
                }
            }
        }
        System.out.println("Top 15 usuarios con más tweets:");
        for (int i = 0; i < 15; i++) {
            System.out.println(users.get(i).getName() + " con " + users.get(i).getTweets().size() + " tweets.");
        }
    }

    private static void cantidadHashtagsDistintos(Scanner scanner) throws InvalidDateException {
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        scanner.nextLine();
        String date = scanner.nextLine();
        Calculareficiencia(() -> {
            int hashtagQty = 0;
            MyLinkedList<String> hashtagMyLinkedList = new MyLinkedList<>();
            try {
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
                }
            } catch (InvalidDateException e) {
                throw new RuntimeException(e);
            }
        });
    }



    private static void hashtagMasUsado(Scanner scanner) throws  InvalidDateException {
        MyHashTable<String,Integer> hashtagHash = new MyHashTable<>();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        scanner.nextLine();
        String date = scanner.nextLine();
        Calculareficiencia(() -> {
            String maxHashtag = null;
            int maxCount = 0;
            try {
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
                }
            } catch (InvalidDateException e) {
                throw new RuntimeException(e);
            }

            // Verificar si se encontró un hashtag más utilizado
            if (maxHashtag != null) {
                System.out.println("El hashtag más utilizado es: " + maxHashtag);
                System.out.println("Aparece " + maxCount + " veces.");
            } else {
                System.out.println("No se encontró ningún hashtag en la tabla.");
            }
        });
    }

    private static void topCuentasConMasFavoritos(Scanner scanner) {
        Calculareficiencia(() -> {
            MyLinkedList<User> listaUsers = Csv.getUsers();
            MyHeap<User> heapUsers = new MyHeap<>(true, false, listaUsers);
            System.out.println("Top 7 cuentas con más favoritos: ");
            for (int i = 0; i < 7; i++) {
                User user = heapUsers.deleteAndReturn();
                System.out.println(user.getName() + " con " + user.getFavourites() + " favoritos.");
            }
        });
    }


    private static void contarTweetsConPalabraFrase(Scanner scanner) {
            System.out.print("Ingrese la palabra clave: ");
            scanner.nextLine();
            String keyword = scanner.nextLine();
        Calculareficiencia(() -> {
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
        });
    }
}