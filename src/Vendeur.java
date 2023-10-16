import java.util.ArrayList;

public class Vendeur extends Personne {
    private double tauxCommission;
    private ArrayList<BienImmobilier> biensImmobiliersEnGestion;

    public Vendeur(String nom, String prenom, String email, String numeroTelephone) {
        super(nom, prenom, email, numeroTelephone);
    }

    public double getTauxCommission() {
        return tauxCommission;
    }

    public double calculCommissions() {
        double resultat = 0;
        for (BienImmobilier bienImmobilier : biensImmobiliersEnGestion) {
            resultat += bienImmobilier.getAnnonce().commission();
        }

        return resultat;
    }

    public void addGestion(BienImmobilier appart){
        biensImmobiliersEnGestion.add(0, appart);
    }

}
