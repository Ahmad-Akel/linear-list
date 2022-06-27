package bdats_semprace_a_akel;

import sprava.SpravaObyvatele;

/**
 *
 * @author Ahmad Akel
 */
public class BDATS_SemPrace_A_Akel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpravaObyvatele spravaObyvatele = new SpravaObyvatele();
        spravaObyvatele.importData("kraje.csv");
        
    }

}
