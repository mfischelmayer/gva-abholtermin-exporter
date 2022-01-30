package at.fischelmayer.gva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "dd.MM.yyyy" );
    private static DateTimeFormatter googleDateTimeFormatter = DateTimeFormatter.ofPattern( "MM/dd/yyyy" );

    /**
     * @param date in format dd.MM.yyyy (e.g. 31.01.2022)
     * @return
     */
    public static LocalDate parse( String date ) {
        return LocalDate.parse( date, dateTimeFormatter );
    }

    /**
     * @param date
     * @return date in format MM/dd/yyyy (eg. 05/30/2020)
     */
    public static String format( LocalDate date ) {
        return googleDateTimeFormatter.format( date );
    }

}
