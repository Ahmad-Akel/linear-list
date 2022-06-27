package sprava;

/**
 *
 * @author Ahmad Akel
 */
public enum Kraj {
    PHA("Hlavni mesto Praha"),
    JHC("Jihocesky"),
    JHM("Jihomoravsky"),
    KVR("Karlovarsky"),
    KVC("Kraj Vysocina"),
    KRH("Kralovehradecky"),
    LBR("Liberecky"),
    MVS("Moravskoslezsky"),
    OMO("Olomoucky"),
    PRD("Pardubicky"),
    PLN("Plzensky"),
    SDC("Stredocesky"),
    UTC("Ustecky"),
    ZLN("Zlinsky");

    private final String nazenv;

    private Kraj(String nazenv) {
        this.nazenv = nazenv;
    }

    @Override
    public String toString() {
        return nazenv;
    }
}
