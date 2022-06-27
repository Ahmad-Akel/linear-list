package sprava;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.*;

/**
 *
 * @author Obadah Al Hariri
 */
public class SpravaObyvatele implements Sprava {

    public static ArrayList<IAbstrDoubleList<Obec>> kraje = new ArrayList<>();
    private int pocetZaznamu;
    private Kraj kraj;

    public SpravaObyvatele() {
        this.pocetZaznamu = 0;
    }

    public static SpravaObyvatele vytvorSpravce(Supplier<IAbstrDoubleList<Obec>> supplier) {
        Objects.requireNonNull(supplier);
        SpravaObyvatele spravce = new SpravaObyvatele();
        for (Kraj kraj : Kraj.values()) {
            kraje.add(supplier.get());
        }
        return spravce;
    }

    @Override
    public int importData(String soubor) {
        try {
            Files.lines(Paths.get(soubor), StandardCharsets.UTF_8)
                    .filter(line -> line != null)
                    .map(line -> {
                        Obec obec = null;
                        if (line.length() == 0) {
                            return obec;
                        }
                        String[] items = line.split(";");

                        int idKraje = Integer.valueOf(items[0]);
                        String nazevKraje = items[1];
                        int PSC = Integer.valueOf(items[2].trim());
                        String nazevObce = items[3];
                        int pocetM = Integer.valueOf(items[4].trim());
                        int pocetZ = Integer.valueOf(items[5].trim());
                        int celkem = Integer.valueOf(items[6].trim());

                        kraj = Kraj.values()[idKraje - 1];

                        pocetZaznamu++;

                        obec = new Obec(PSC, nazevObce, pocetM, pocetZ, celkem);
                        return obec;
                    })
                    .forEach(obec -> vlozObec((Obec) obec, Pozice.POSLEDNI, kraj));
        } catch (IOException ex) {
            Logger.getLogger(SpravaObyvatele.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pocetZaznamu;
    }

    @Override
    public void vlozObec(Obec obec, Pozice pozice, Kraj kraj) {
        switch (pozice) {
            case PRVNI:
                kraje.get(kraj.ordinal()).vlozPrvni(obec);
                break;
            case POSLEDNI:
                kraje.get(kraj.ordinal()).vlozPosledni(obec);
                break;
            case PREDCHUDCE:
                kraje.get(kraj.ordinal()).vlozPredchudce(obec);
                break;
            case NASLEDNIK:
                kraje.get(kraj.ordinal()).vlozNaslednika(obec);
                break;
        }
    }

    @Override
    public Obec zpristupniObec(Pozice pozice, Kraj kraj) {
        Obec obec = null;
        switch (pozice) {
            case PRVNI:
                obec = this.kraje.get(kraj.ordinal()).zpristupniPrvni();
                break;
            case POSLEDNI:
                obec = this.kraje.get(kraj.ordinal()).zpristupniPosledni();
                break;
            case PREDCHUDCE:
                obec = this.kraje.get(kraj.ordinal()).zpristupniPredchudce();
                break;
            case NASLEDNIK:
                obec = this.kraje.get(kraj.ordinal()).zpristupniNaslednika();
                break;
            case AKTUALNI:
                obec = this.kraje.get(kraj.ordinal()).zpristupniAktualni();
                break;
        }
        return obec;
    }

    @Override
    public Obec odeberObec(Pozice pozice, Kraj kraj) {
        Obec obec = null;
        switch (pozice) {
            case PRVNI:
                obec = kraje.get(kraj.ordinal()).odeberPrvni();
                break;
            case POSLEDNI:
                obec = kraje.get(kraj.ordinal()).odeberPosledni();
                break;
            case PREDCHUDCE:
                obec = kraje.get(kraj.ordinal()).odeberPredchudce();
                break;
            case NASLEDNIK:
                obec = kraje.get(kraj.ordinal()).odeberNaslednika();
                break;
            case AKTUALNI:
                obec = kraje.get(kraj.ordinal()).odeberAktualni();
                break;
        }
        return obec;
    }

    @Override
    public float zjistiPrumer(Kraj kraj) {
        int hodnotaKraje = 1;
        float prumer = 0;
        int sum = 0;

        if (hodnotaKraje == 0) {
            Iterator<Obec> it = null;
            for (IAbstrDoubleList<Obec> k : kraje) {
                it = k.iterator();
                while (it.hasNext()) {
                    sum = sum + 1;
                    prumer = prumer + it.next().getCelkem();
                }
            }
        } else {
            Iterator<Obec> it = kraje.get(kraj.ordinal()).iterator();
            while (it.hasNext()) {
                sum = sum + 1;
                prumer = prumer + it.next().getCelkem();
            }
        }

        prumer = prumer / sum;
        return prumer;
    }

    @Override
    public void zobrazObce(Kraj kraj) {
        int hodnotaKraje = 1;
        if (hodnotaKraje == 0) {
            Iterator<Obec> itObce = null;
            Iterator<IAbstrDoubleList<Obec>> itKraje = kraje.iterator();
            while (itKraje.hasNext()) {
                itObce = itKraje.next().iterator();
                while (itObce.hasNext()) {
                    System.out.println(itObce.next());
                }
            }
        } else {
            Iterator<Obec> it = kraje.get(kraj.ordinal()).iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }

    @Override
    public void zobrazObceNadPrumer(Kraj kraj) {
        int hodnotaKraje = 1;
        if (hodnotaKraje == 0) {
            Iterator<Obec> itObce = null;
            Iterator<IAbstrDoubleList<Obec>> itKraje = kraje.iterator();
            while (itKraje.hasNext()) {
                itObce = itKraje.next().iterator();
                while (itObce.hasNext()) {
                    if (itObce.next().getCelkem() > zjistiPrumer(kraj)) {
                        System.out.println(itObce.next());
                    }
                }
            }
        } else {
            Iterator<Obec> it = kraje.get(kraj.ordinal()).iterator();
            while (it.hasNext()) {
                if (it.next().getCelkem() > zjistiPrumer(kraj)) {
                    System.out.println(it.next());
                }
            }
        }
    }

    @Override
    public void zrus(Kraj kraj) {
        int hodnotaKraje = 1;
        if (hodnotaKraje == 0) {
            Iterator<IAbstrDoubleList<Obec>> it = kraje.iterator();
            while (it.hasNext()) {
                it.next().zrus();
            }
        } else {
            kraje.get(kraj.ordinal()).zrus();
        }
    }
}
