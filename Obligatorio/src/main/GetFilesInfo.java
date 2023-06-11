import entities.Hashtag;
import entities.Tweets;
import entities.User;
import adt.linkedlist.MyLinkedList;
import exception.FileNotValidException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetFilesInfo {
    private static final String csvFile = "src/main/resources/f1_dataset_test.csv";
    private static final String driversFile = "src/main/resources/drivers.txt";
    public MyLinkedList<String> driversLinkedList = new MyLinkedList<>();
    public void GetDriversInfo() {


        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void GetUsersInfo() throws FileNotValidException {
        MyLinkedList<User> Users = new MyLinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            int count = 0;
            for (CSVRecord csvRecord : csvParser) {
                String[] values = csvRecord.values();
                String dateString = values[9]; // Obtén la cadena de texto que representa la fecha del tweet
                String[] inputFormats = {"M/d/yyyy h:mm:ss a", "yyyy-MM-dd HH:mm:ss"};
                String outputFormat = "yyyy-MM-dd"; // Formato de salida deseado
                Hashtag hashTag = new Hashtag();
                try {

                    User user = new User();
                    user.setName(values[1]);
                    ;
                    user.setFavourites(Integer.parseInt(values[7]));
                    user.setVerified(Boolean.parseBoolean(values[8]));

                    SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);

                    Date date = null;
                    for (String inputFormat : inputFormats) {
                        try {
                            SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
                            date = inputFormatter.parse(dateString); // Intenta analizar la fecha utilizando el formato actual
                            break; // Sal del bucle si el análisis es exitoso
                        } catch (ParseException ignored) {
                        }
                    }

                    String formattedDate = null;

                    if (date != null) {
                        formattedDate = outputFormatter.format(date);
                        System.out.println(formattedDate); // Imprime la fecha en formato "YYYY-MM-DD"
                        count = ++count;
                        System.out.println("Cuenta : " + count);
                        System.out.println(csvRecord.getRecordNumber());
                    } else {
                        System.out.println("No se pudo analizar la fecha");
                    }

                    Tweets tweet = new Tweets();
                    tweet.setDate(formattedDate);

                } catch (Exception ignored) {

                }
            }
        } catch (IOException e) {
            throw new FileNotValidException("FILE_ERROR_FORMAT", e);
        }
    }

}
