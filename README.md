# GVA (Gemeindeverband für Abfallbeseitigung) Abholtermin-Exporter

Lädt die Seite des "Gemeindeverband für Abfallbeseitigung" und exportiert die Abholtermin Daten in ein Google Kalender kompatibles Format.

### usage

#### parameter

`-- url` Die URL der GVA Zielseite (z.b. https://tulln.umweltverbaende.at/?gem_nr=32120&jahr=2022&portal=verband&vb=tu&kat=32)

`-- file` Der Pfad für die Export Datei

`-- filter` Ein Wortfilter für Inhalte welche nicht benötigt werden (Mehrfachnennung möglich)


````
java -jar gva.jar --url https://tulln.umweltverbaende.at/?gem_nr=32120&jahr=2022&portal=verband&vb=tu&kat=32 --file C:\tmp\exportgoogle.csv --filter "Haushalte 2" --filter "Wohnhausanlagen"
````
