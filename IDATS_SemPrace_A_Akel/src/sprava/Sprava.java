package sprava;

/**
 *
 * @author Obadah Al Hariri
 */
public interface Sprava {

    int importData(String soubor);

    void vlozObec(Obec obec, Pozice pozice, Kraj kraj);

    Obec zpristupniObec(Pozice pozice, Kraj kraj);

    Obec odeberObec(Pozice pozice, Kraj kraj);

    float zjistiPrumer(Kraj kraj);

    void zobrazObce(Kraj kraj);

    void zobrazObceNadPrumer(Kraj kraj);

    void zrus(Kraj kraj);

}
