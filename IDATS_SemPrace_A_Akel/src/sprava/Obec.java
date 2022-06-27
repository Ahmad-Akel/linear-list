package sprava;

import java.util.Objects;

/**
 *
 * @author Obadah Al Hariri
 */
public class Obec {

    private int PSC;
    private String nazevObce;
    private int pocetMuzu;
    private int pocetZen;
    private int celkem;

    public Obec() {
    }

    public Obec(int PSC, String nazevObce, int pocetMuzu, int pocetZen, int celkem) {
        this.PSC = PSC;
        this.nazevObce = nazevObce;
        this.pocetMuzu = pocetMuzu;
        this.pocetZen = pocetZen;
        this.celkem = celkem;
    }

    public int getPSC() {
        return PSC;
    }

    public void setPSC(int PSC) {
        this.PSC = PSC;
    }

    public String getNazevObce() {
        return nazevObce;
    }

    public void setNazevObce(String nazevObce) {
        this.nazevObce = nazevObce;
    }

    public int getPocetMuzu() {
        return pocetMuzu;
    }

    public void setPocetMuzu(int pocetMuzu) {
        this.pocetMuzu = pocetMuzu;
    }

    public int getPocetZen() {
        return pocetZen;
    }

    public void setPocetZen(int pocetZen) {
        this.pocetZen = pocetZen;
    }

    public int getCelkem() {
        return celkem;
    }

    public void setCelkem(int celkem) {
        this.celkem = celkem;
    }

    @Override
    public String toString() {
        return "PSC: " + PSC + ", nazevObce: " + nazevObce + ", pocetMuzu: " + pocetMuzu + ", pocetZen: " + pocetZen + ", celkem: " + celkem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.PSC;
        hash = 53 * hash + Objects.hashCode(this.nazevObce);
        hash = 53 * hash + this.pocetMuzu;
        hash = 53 * hash + this.pocetZen;
        hash = 53 * hash + this.celkem;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Obec other = (Obec) obj;
        if (this.PSC != other.PSC) {
            return false;
        }
        if (this.pocetMuzu != other.pocetMuzu) {
            return false;
        }
        if (this.pocetZen != other.pocetZen) {
            return false;
        }
        if (this.celkem != other.celkem) {
            return false;
        }
        if (!Objects.equals(this.nazevObce, other.nazevObce)) {
            return false;
        }
        return true;
    }

}
