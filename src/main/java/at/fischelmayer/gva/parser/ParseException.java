package at.fischelmayer.gva.parser;

public class ParseException extends Exception {
    public ParseException( String message ) {
        super( message );
    }

    public ParseException( String message, Throwable cause ) {
        super( message, cause );
    }
}
