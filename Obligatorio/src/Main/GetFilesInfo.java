import entities.Hashtag;
import entities.Tweets;
import entities.User;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;
import exception.FileNotValidException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class GetFilesInfo {
    private static final String csvFile = "src/main/resources/f1_dataset_test.csv";
    private static final String driversFile = "src/main/resources/drivers.txt";
    public MyLinkedList<String> driversLinkedList = new MyLinkedList<>();
    public  MyLinkedList<User> Users = new MyLinkedList<>();
    public  MyLinkedList<Tweets> Tweets = new MyLinkedList<>();

    public void GetDriversInfo() {
        Main.calcularEficiencia(() -> {
            System.out.println("Leyendo archivo de conductores...");
            try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    driversLinkedList.add(line.toLowerCase());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static String formatDate(String dateString) {
        String[] dateTimeParts = dateString.split(" "); // Dividir la cadena en fecha y hora
        String datePart = dateTimeParts[0]; // Obtener la parte de la fecha
        String[] dateComponents = datePart.split("-"); // Dividir la fecha en componentes (año, mes, día)
        String year = dateComponents[0];
        String month = dateComponents[1];
        String day = dateComponents[2];
        // Reorganizar los componentes de fecha para obtener el formato deseado (YYYY-MM-DD)
        String formattedDate = year + "-" + month + "-" + day;
        //System.out.println(formattedDate);
        return formattedDate;
    }

    public void GetUsersInfo() throws FileNotValidException {
        Main.calcularEficiencia(() -> {
            System.out.println("Leyendo archivo de usuarios...");
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
                 CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
                int count = 0;
                boolean skipFirstLine = true;
                for (CSVRecord csvRecord : csvParser) {
                    if (skipFirstLine) {
                        skipFirstLine = false;  // Cambiar el valor después de leer la primera línea
                        continue;  // Saltar el procesamiento de la primera línea
                    }
                    count = ++count;
                    MyLinkedList<Hashtag> hashtags = new MyLinkedList<>();
                    String[] values = csvRecord.values();
                    try {
                        Tweets tweet = new Tweets();
                        String dateString = values[9];
                        tweet.setDate(formatDate(dateString));
                        tweet.setId(Long.valueOf(values[0]));
                        tweet.setContent(values[10]);
                        String hashtagsString = values[11];
                        if (!hashtagsString.isEmpty()) {
                            String[] hashtagArray = hashtagsString.replace("[", "").replace("]", "").replace("'", "").split(", ");
                            for (String hashtagText : hashtagArray) {
                                Hashtag hashtag = new Hashtag();
                                hashtag.setText(hashtagText);
                                hashtags.add(hashtag);
                            }
                        }
                        tweet.setHashtags(hashtags);
                        tweet.setSource(values[12]);
                        tweet.setRetweet(Boolean.parseBoolean(values[13]));
                        Tweets.add(tweet);
                        User user = new User();
                        user.setName(values[1]);
                        user.setFavourites(Integer.parseInt(values[7]));
                        user.setVerified(Boolean.parseBoolean(values[8]));
                        if (Users.contains(user)){
                            User existingUser = Users.getObject(user).getValue();
                            existingUser.getTweets().add(tweet);
                        }else {
                            user.getTweets().add(tweet);
                            Users.add(user);
                        }
                    } catch (Exception ignored) {}
                }
            } catch (IOException e) {
                try {
                    throw new FileNotValidException("FILE_ERROR_FORMAT", e);
                } catch (FileNotValidException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public MyLinkedList<String> getDriversLinkedList() {
        return driversLinkedList;
    }

    public MyLinkedList<User> getUsers() {
        return Users;
    }

    public MyLinkedList<entities.Tweets> getTweets() {
        return Tweets;
    }

}
