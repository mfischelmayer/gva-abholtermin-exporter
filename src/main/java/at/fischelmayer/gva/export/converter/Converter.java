package at.fischelmayer.gva.export.converter;

import at.fischelmayer.gva.model.Abholtermin;

import java.util.List;

public interface Converter {
    String convert( List<Abholtermin> termine );
}
