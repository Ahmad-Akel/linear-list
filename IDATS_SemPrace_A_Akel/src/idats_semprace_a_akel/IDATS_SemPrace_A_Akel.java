package idats_semprace_a_akel;

import sprava.SpravaObyvatele;

/**
 *
 * @author Obadah Al Hariri
 */
public class IDATS_SemPrace_A_Akel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpravaObyvatele spravaObyvatele = new SpravaObyvatele();
        spravaObyvatele.importData("kraje.csv");
        
    }

}
