package at.fischelmayer.gva;

import at.fischelmayer.gva.export.ExportException;
import at.fischelmayer.gva.export.FileExporter;
import at.fischelmayer.gva.export.converter.CsvConverter;
import at.fischelmayer.gva.model.Abholtermin;
import at.fischelmayer.gva.parser.GVAParser;
import at.fischelmayer.gva.parser.ParseException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger( Main.class.getName() );

    @Parameter( names = { "--url", "-u" }, description = "Die URL der GVA Seite" )
    String url;

    @Parameter( names = { "--file", "-f" }, description = "Das Pfad fuer die Output Datei" )
    String filename;

    @Parameter( names = "--filter", description = "Zeilenfilter" )
    private List<String> filterList = new ArrayList<>();


    public static void main( String[] args ) {
        Main main = new Main();
        JCommander.newBuilder().addObject( main ).build().parse( args );
        main.run();
    }

    private void run() {
        GVAParser gvaParser = new GVAParser();

        List<Abholtermin> termine = null;
        try {
            termine = gvaParser.parse( url, filterList );

        } catch ( ParseException e ) {
            LOG.log( Level.SEVERE, e, () -> "Fehler beim auslesen der Daten von GVA" );
        }

        FileExporter fileExporter = new FileExporter( new CsvConverter() );
        try {
            fileExporter.export( new File( filename ), termine );
        } catch ( ExportException e ) {
            LOG.log( Level.SEVERE, e, () -> "Fehler beim exportieren der Datei" );
        }
    }

}
