package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author Obadah Al Hariri
 */
public class AbstrDoubleList<E> implements IAbstrDoubleList<E> {

    // modul is meaning class
    // abstract data structure is meaning generics
    private static class Prvek<E> {

        Prvek dalsi;
        Prvek predchozi;
        final E data;

        public Prvek(Prvek dalsi, Prvek predchozi, E data) {
            this.dalsi = dalsi;
            this.predchozi = predchozi;
            this.data = data;
        }

        public Prvek(E data) {
            this.data = data;
        }
    }

    private Prvek prvni;
    private Prvek posledni;
    private Prvek aktualni;
    private int pocet;

    public AbstrDoubleList() {
        zrus();
    }

    @Override
    public void zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
        pocet = 0;
    }

    @Override
    public boolean jePrazdny() {
        return prvni == null; // "linear" list
    }

    @Override
    public void vlozPrvni(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Data jsou prazdne!");
        }
        Prvek prvek = new Prvek(data);
        if (jePrazdny()) {
            posledni = prvek;
            prvek.dalsi = prvek;
            prvek.predchozi = prvek;
            aktualni = prvek;
            pocet++;
        } else {
            prvni.predchozi = prvek;
            prvek.dalsi = prvni;
            prvek.predchozi = posledni;
            pocet++;
        }
        prvni = prvek;
    }

    @Override
    public void vlozPosledni(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Data jsou prazdna!");
        }
        if (jePrazdny()) {
            vlozPrvni(data);
        } else {
            Prvek prvek = new Prvek(prvni, posledni, data);
            posledni.dalsi = prvek;
            prvni.predchozi = prvek;
            posledni = prvek;
            pocet++;
        }
    }

    @Override
    public void vlozNaslednika(E data) throws NullPointerException, NoSuchElementException {
        if (data == null) {
            throw new NullPointerException("Data jsou prazdna!");
        }
        if (aktualni == null) {
            throw new NoSuchElementException("Aktualni prvek neexistuje!");
        }
        Prvek prvek = new Prvek(data);
        aktualni.dalsi.predchozi = prvek;
        prvek.dalsi = aktualni.dalsi;
        aktualni.dalsi = prvek;
        prvek.predchozi = aktualni;
        if (prvek.dalsi == prvni) {
            posledni = prvek;
        }
        pocet++;
    }

    @Override
    public void vlozPredchudce(E data) throws NullPointerException, NoSuchElementException {
        if (data == null) {
            throw new NullPointerException("Data jsou prazdna!");
        }
        if (aktualni == null) {
            throw new NoSuchElementException("Aktualni prvek neexistuje!");
        }
        Prvek prvek = new Prvek(data);
        aktualni.predchozi.dalsi = prvek;
        prvek.dalsi = aktualni;
        prvek.predchozi = aktualni.predchozi;
        aktualni.predchozi = prvek;
        if (prvek.predchozi == posledni) {
            prvni = prvek;
        }
        pocet++;
    }

    @Override
    public E zpristupniAktualni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny seznam!");
        }
        Objects.requireNonNull(aktualni);
        return (E) aktualni.data;
    }

    @Override
    public E zpristupniPrvni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prvni prvek neexistuje! sezanm je prazdny!");
        }
        aktualni = prvni;
        return (E) prvni.data;
    }

    @Override
    public E zpristupniPosledni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Posledni prvek neexistuje! sezanm je prazdny!");
        }
        aktualni = posledni;
        return (E) posledni.data;
    }

    @Override
    public E zpristupniNaslednika() throws NoSuchElementException, NullPointerException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny seznam!");
        }
        if (aktualni == null) {
            throw new NoSuchElementException("Aktualni prvek neexituje!");
        }
        aktualni = aktualni.dalsi;
        return (E) aktualni.data;
    }

    @Override
    public E zpristupniPredchudce() throws NoSuchElementException, NullPointerException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny seznam!");
        }
        if (aktualni == null) {
            throw new NoSuchElementException("Aktualni prvek neexituje!");
        }
        aktualni = aktualni.predchozi;
        return (E) aktualni.data;
    }

    @Override
    public E odeberAktualni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny seznam!");
        }
        Objects.requireNonNull(aktualni);
        E data;
        if (aktualni == prvni) {
            data = odeberPrvni();
        } else if (aktualni == posledni) {
            data = odeberPosledni();
        } else {
            data = (E) aktualni.data;
            aktualni.predchozi.dalsi = aktualni.dalsi;
            aktualni.dalsi.predchozi = aktualni.predchozi;
            aktualni = prvni;
        }
        return data;
    }

    @Override
    public E odeberPrvni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny seznam!");
        }
        E data;
        if (prvni == posledni) {
            data = (E) prvni.data;
            zrus();
        } else {
            data = (E) prvni.data;
            posledni.dalsi = prvni.dalsi;
            prvni.dalsi.predchozi = posledni;
            if (aktualni == prvni) {
                aktualni = prvni.dalsi;
            }
            prvni = prvni.dalsi;
        }
        return data;
    }

    @Override
    public E odeberPosledni() throws NoSuchElementException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny seznam!");
        }
        E data;
        if (posledni == prvni) {
            data = (E) posledni.data;
            zrus();
        } else {
            data = (E) posledni.data;
            posledni.predchozi.dalsi = prvni;
            prvni.predchozi = posledni.predchozi;
            if (aktualni == posledni) {
                aktualni = prvni;
            }
            posledni = posledni.predchozi;
        }
        return data;
    }

    @Override
    public E odeberNaslednika() throws NoSuchElementException, NullPointerException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny sezanm!");
        }
        if (aktualni == null) {
            throw new NullPointerException("Aktualni prvek neexistuje!");
        }
        E data;
        if (aktualni == posledni) {
            data = odeberPrvni();
        } else {
            data = (E) aktualni.dalsi.data;
            aktualni.dalsi = aktualni.dalsi.dalsi;
            aktualni.dalsi.predchozi = aktualni;
            aktualni = prvni;
        }
        return data;
    }

    @Override
    public E odeberPredchudce() throws NoSuchElementException, NullPointerException {
        if (jePrazdny()) {
            throw new NoSuchElementException("Prazdny sezanm!");
        }
        if (aktualni == null) {
            throw new NullPointerException("Aktualni prvek neexistuje!");
        }
        E data;
        if (aktualni == prvni) {
            data = odeberPosledni();
        } else {
            data = (E) aktualni.predchozi.data;
            aktualni.predchozi = aktualni.predchozi.predchozi;
            aktualni.predchozi.dalsi = aktualni;
            aktualni = prvni;
        }
        return data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Prvek prvek = prvni;
            boolean temp = true;

            @Override
            public boolean hasNext() {
                return temp || (prvek != prvni);
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = (E) prvek.data;
                    prvek = prvek.dalsi;
                    temp = false;
                    return data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
