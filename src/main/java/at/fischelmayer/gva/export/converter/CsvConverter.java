package at.fischelmayer.gva.export.converter;

import at.fischelmayer.gva.DateHelper;
import at.fischelmayer.gva.model.Abholtermin;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CSV Converter fuer google calendar kompatibles Format
 */
public class CsvConverter implements Converter {

    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";
    private static final String STR_IDF = "\"";

    @Override
    public String convert( List<Abholtermin> termine ) {
        if ( termine == null || termine.isEmpty() ) {
            throw new IllegalArgumentException( "termine must not be null or empty" );
        }

        StringBuilder csvStringBuilder = new StringBuilder( 1000 );

        csvStringBuilder.append( csvHeaderRow() ).append( NEW_LINE );

        for ( Abholtermin abholtermin : termine ) {
            csvStringBuilder.append( csvRowOf( abholtermin ) ).append( NEW_LINE );
        }

        return csvStringBuilder.toString();
    }

    private String csvHeaderRow() {
        List<String> headerValues = List.of( "Subject", "Start Date", "End Date", "All Day Event", "Description",
                "Start Time", "End Time", "Location", "Private" );
        return headerValues.stream().collect( Collectors.joining( DELIMITER ) );
    }

    private String csvRowOf( Abholtermin abholtermin ) {
        StringBuilder rowSb = new StringBuilder( 100 );
        rowSb.append( STR_IDF ).append( abholtermin.getType() ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( DateHelper.format( abholtermin.getDate() ) ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( DateHelper.format( abholtermin.getDate() ) ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( "TRUE" ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( abholtermin.getBeschreibung() ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( "05:00 AM" ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( "06:00 AM" ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( "" ).append( STR_IDF ).append( DELIMITER )
                .append( STR_IDF ).append( "FALSE" ).append( STR_IDF );
        return rowSb.toString();
    }

}
