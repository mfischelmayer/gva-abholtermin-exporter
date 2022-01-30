package at.fischelmayer.gva.export;

import at.fischelmayer.gva.export.converter.Converter;
import at.fischelmayer.gva.model.Abholtermin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileExporter {

    private Converter converter;

    public FileExporter( Converter converter ) {
        this.converter = converter;
    }

    /**
     * @param exportFile das target file
     * @param termine    die abholetermine welche exportiert werden sollen
     * @throws ExportException
     */
    public void export( File exportFile, List<Abholtermin> termine ) throws ExportException {

        String textToExport = converter.convert( termine );
        try ( FileOutputStream fos = new FileOutputStream( exportFile ) ) {
            fos.write( textToExport.getBytes( StandardCharsets.UTF_8 ) );
        } catch ( FileNotFoundException e ) {
            throw new ExportException( String.format( "file %s not found", exportFile.getName() ), e );
        } catch ( IOException e ) {
            throw new ExportException( "IO error export file", e );
        }
    }
}
