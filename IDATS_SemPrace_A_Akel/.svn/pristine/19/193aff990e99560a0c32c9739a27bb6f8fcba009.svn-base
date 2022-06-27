package kolekce;

import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Rozhraní IAbstrDoubleList je určeno k implementaci dvousměrného spojového
 * seznamu. Chování implementace tohoto rozhraní musí plně vyhovět učitelskému
 * testu.
 *
 * <p>
 * <b>Poznámka:</b>
 * Význam použití výjimek není konzistentní v jednotlivých metodách. Proto je
 * nutné u každé metody použít výjimky tak, jak specifikováno.
 *
 * @author kasi
 * @param <E>
 */
public interface IAbstrDoubleList<E> extends Iterable<E> {

    /**
     * Metoda zruší obsah celého seznamu.
     *
     */
    void zrus();

    /**
     * Test naplněnosti seznamu.
     *
     * @return Vrací true, když seznam je prázdný.
     */
    boolean jePrazdny();

    /**
     * Metoda vloží datový element do seznamu na první místo. Pokud byl seznam
     * prázdný, nastaví se tento vkládaný element zaroveň jako poslední.
     *
     * @param data Reference na datový element, která se vloží do seznamu.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     *
     */
    void vlozPrvni(E data) throws NullPointerException;

    /**
     * Metoda vloží referenci na datový element do seznamu na poslední místo.
     * Když je seznam prázdný, tak se element zároveň nastaví jako první prvek v
     * seznamu.
     *
     * @param data Reference na datový element, která se má vložit do seznamu.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     */
    void vlozPosledni(E data) throws NullPointerException;

    /**
     * Metoda vloží referenci na datový element do seznamu jako následníka
     * aktuálního prvku. Pokud by následník byl posledním prvkem seznamu,
     * nastaví se jako poslední.
     *
     * <p>
     * <b>Poznámka:</b> Je nutné si dát pozor na zpřístupnění po odebrání prvků
     * ze seznamu. Totiž může docházet k přesunu aktuálního prvku na první prvek
     * seznamu. To znamená při zavolání této metody vložení datového elementu
     * jako druhého prvku seznamu.
     *
     * @param data Reference na datový element, která se má vložit do seznamu
     * jako následník.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     *
     * @throws NoSuchElementException Vystavení výjimky při nenastaveném
     * aktuálním prvku.
     *
     */
    void vlozNaslednika(E data)
            throws NullPointerException, NoSuchElementException;

    /**
     * Metoda vloží referenci na datový element do seznamu jako předchůdce
     * aktuálního prvku. Pokud by předchůdce byl prvním prvkem seznamu, nastaví
     * se prvek jako první.
     * <p>
     * <b>Poznámka:</b> Je nutné si dát pozor na zpřístupnění po odebrání prvků
     * ze seznamu. Totiž může docházet k přesunu aktuálního prvku na první prvek
     * seznamu a tím pádem může dojít u této metody k vystavení výjimky.
     *
     * @param data Reference na datový element, která se má vložit do seznamu
     * jako předchůdce.
     *
     * @throws NullPointerException Vystavení výjimky při žádné referenci v
     * parametru data.
     *
     * @throws NoSuchElementException Vystavení výjimky při nenastaveném
     * aktuálním prvku.
     *
     */
    void vlozPredchudce(E data)
            throws NullPointerException, NoSuchElementException;

    /**
     * Metoda zpřístupní aktuální datový element seznamu.
     *
     * <b>Poznámka:</b> Je nutné si dát pozor na zpřístupnění po odebrání prvků
     * ze seznamu. Totiž může docházet k přesunu aktuálního prvku na první prvek
     * seznamu.
     *
     * @return Reference na zpřistupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu. *
     *
     */
    E zpristupniAktualni() throws NoSuchElementException;

    /**
     * Metoda vrací referenci na zpřístupněný první prvek seznamu.
     *
     * @return Reference na zpřistupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu.
     *
     */
    E zpristupniPrvni() throws NoSuchElementException;

    /**
     * Metoda vrací referenci na zpřístupněný poslední prvek seznamu. Zároveň
     * nastaví vnitřní aktuální referenci na poslední prvek seznamu.
     *
     * @return Reference na zpřistupněný datový element v seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu.
     *
     */
    E zpristupniPosledni() throws NoSuchElementException;

    /**
     *
     * Metoda zpřístupní následníka aktuálního prvku.
     *
     * @return reference na zpřístupněný objekt v seznamu
     *
     * @throws NoSuchElementException Výjimka se vystaví, když není následník
     * aktuálního prvku.
     *
     * @throws NullPointerException Výjimka se vystaví v případě, když není
     * zpřistupněn aktuální prvek.
     *
     */
    E zpristupniNaslednika()
            throws NoSuchElementException, NullPointerException;

    /**
     * Metoda zpřistupní předchůdce aktuálního prvku a přenastaví
     *
     * @return reference na zpřístupněný objekt v seznamu
     *
     * @throws NoSuchElementException Výjimka se vystaví když není předchůdce
     * akutálního prvku.
     *
     * @throws NullPointerException Výjimka se vystaví v případě, když není
     * zpřistupněn aktuální prvek.
     */
    E zpristupniPredchudce()
            throws NoSuchElementException, NullPointerException;

    /**
     * Metoda odebere (vyjme) aktuální prvek ze seznamu, poté je reference na
     * aktuální prvek nastavena na první prvek, pokud ovšem je v seznamu.
     *
     * @return Reference na odebrany objekt ze seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu.
     *
     */
    E odeberAktualni() throws NoSuchElementException;

    /**
     * Metoda odebere první prvek ze seznamu. Pokud by první prvek nastaven
     * současně jako aktuální, bude reference na aktuální prvek nastavena na
     * nový první prvek, pokud ovšem prvek bude v seznamu.
     *
     * @return reference na odebrany objekt ze seznamu
     *
     * @throws NoSuchElementException výjimka se vystaví při prázdném seznamu
     */
    E odeberPrvni() throws NoSuchElementException;

    /**
     * Metoda odebere poslední prvek ze seznamu. Pokud by poslední prvek
     * nastaven současně jako aktuální, bude reference na aktuální prvek
     * nastavena na první prvek, pokud ovšem prvek bude v seznamu.
     *
     * @return Reference na odebrany objekt ze seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu.
     */
    E odeberPosledni() throws NoSuchElementException;

    /**
     * Metoda odebere následníka aktuálního prvku ze seznamu. Pokud by byl
     * aktuální prvek poslední, odebere se první prvek seznamu.
     *
     * @return Reference na odebrany objekt ze seznamu.
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu
     *
     * @throws NullPointerException Výjimka se vystaví v případě, když není
     * zpřistupněn aktuální prvek.
     */
    E odeberNaslednika() throws NoSuchElementException, NullPointerException;

    /**
     * Metoda odebere předchůdce aktuálního prvku. Pokud by byl aktuálně
     * nastaven první prvek, odebere se poslední prvek seznamu.
     *
     * @return reference na odebrany objekt ze seznamu
     *
     * @throws NoSuchElementException Výjimka se vystaví při prázdném seznamu
     * nebo v takovém případě, kdy není predchudce, tj. když aktuální prvek je
     * nastaven jako první.
     *
     * @throws NullPointerException Výjimka se vystaví v případě, když není
     * zpřistupněn aktuální prvek.
     */
    E odeberPredchudce() throws NoSuchElementException, NullPointerException;

    /**
     * Vrací sekvenci v {@code Stream} datové elementy stromu.
     * 
     * Metoda se nemusí implementovat!
     *
     * @return Stream dat uložených v seznamu jehož pořadí je dáno iterátorem.
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
