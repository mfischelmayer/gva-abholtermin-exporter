package at.fischelmayer.gva.parser;

import at.fischelmayer.gva.model.Abholtermin;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class GVAParser {

    /**
     * @param url    e.g. "https://tulln.umweltverbaende.at/?gem_nr=32120&jahr=2022&portal=verband&vb=tu&kat=32"
     * @param filter e.g. "Haushalte 2", "Wohnhausanlagen"
     * @return
     * @throws ParseException
     */
    public List<Abholtermin> parse( String url, List<String> filter ) throws ParseException {
        Objects.requireNonNull( url );

        Connection connect = Jsoup.connect( url );

        try {
            Document document = connect.get();
            Element body = document.body();
            Elements rowElements = body.getElementsByClass( "tunterlegt" );

            Stream<Element> elementStream = rowElements.stream();
            if ( filter != null && !filter.isEmpty() ) {
                for ( String f : filter ) {
                    elementStream = elementStream.filter( element -> !element.text().contains( f ) );
                }
            }
            return elementStream.map( Element::text )
                    .map( this::from )
                    .toList();

        } catch ( IOException e ) {
            throw new ParseException( "error parse document", e );
        }
    }

    /**
     * @param textRow
     * @return
     */
    private Abholtermin from( String textRow ) {
        String dateString = textRow.substring( 3, 13 );
        String description = textRow.substring( 13 ).trim();
        return new Abholtermin( dateString, description );
    }
}
