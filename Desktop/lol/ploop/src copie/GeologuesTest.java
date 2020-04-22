import java.util.ArrayList;

public class GeologuesTest {

    ArrayList<Activite> activites = new ArrayList<>();
    Activite activite1 = new Activite("Cours sur la chimieee", "cours", "14", "2019-04-20");
    Activite activite2 = new Activite("Cours sur l'informatique", "cours", "20",
            "2019-04-20");
    Declaration declaration1 = new Declaration("TG0007", "Géologues", "2016-2019", "Potter", "Harry", "0", activites);

    @Test
    void testValiderNumeroPermis(){
        assertTrue(Geologues.validerNumeroPermis(declaration1.getNumeroDePermis));
    }

}
