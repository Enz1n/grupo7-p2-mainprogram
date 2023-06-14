package exception;

import java.io.IOException;

public class FileNotValidException extends Throwable {


    public FileNotValidException(String fileGenerationError, Exception ex) {
        super(fileGenerationError, ex); }

}
