package at.fischelmayer.gva.model;

import at.fischelmayer.gva.DateHelper;

import java.time.LocalDate;
import java.util.Objects;

public class Abholtermin {

    public enum Type {
        RESTMUELL, WINDEL, PAPIER, BIO, GELBE_TONNE
    }

    private Type type;
    private LocalDate date;
    private String beschreibung;

    public Abholtermin( Type type, LocalDate date, String beschreibung ) {
        this.type = type;
        this.date = date;
        this.beschreibung = beschreibung;
    }

    public Abholtermin( String dateString, String beschreibung ) {
        this( DateHelper.parse( dateString ), beschreibung );
    }

    public Abholtermin( LocalDate date, String beschreibung ) {

        if ( beschreibung.contains( "Windeltonne" ) ) {
            this.type = Type.WINDEL;
        }
        if ( beschreibung.contains( "Gelber" ) ) {
            this.type = Type.GELBE_TONNE;
        }
        if ( beschreibung.contains( "Altpapier" ) ) {
            this.type = Type.PAPIER;
        }
        if ( beschreibung.contains( "Rest" ) ) {
            this.type = Type.RESTMUELL;
        }
        if ( beschreibung.contains( "Biotonne" ) ) {
            this.type = Type.BIO;
        }

        this.date = date;
        this.beschreibung = beschreibung;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Abholtermin that = (Abholtermin) o;
        return type == that.type && Objects.equals( date, that.date ) && Objects.equals( beschreibung, that.beschreibung );
    }

    @Override
    public int hashCode() {
        return Objects.hash( type, date, beschreibung );
    }

    @Override
    public String toString() {
        return "Abholtermin{" +
                "type=" + type +
                ", date=" + date +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}
