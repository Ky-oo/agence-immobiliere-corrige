public class Maison extends BienImmobilier{
    
    private double surfaceTerrain;
    private boolean piscine;

    public Maison(String rue, String ville, String codePostal, Vendeur vendeur, double surfaceTerrain,
            boolean piscine) {
        super(rue, ville, codePostal, vendeur);
        this.surfaceTerrain = surfaceTerrain;
        this.piscine = piscine;
    }

    public Maison(String rue, String ville, String codePostal, Vendeur vendeur, double surfaceTerrain) {
        super(rue, ville, codePostal, vendeur);
        this.surfaceTerrain = surfaceTerrain;
        this.piscine = false;
    }
    
    public double dpe() {
        double resultat = 0;
        resultat = (consommationKWhAn() * this.mapOrientation.get(this.Orientation) * this.mapMenuiserie.get(this.Menuiserie) * this.mapChauffage.get(this.Chauffage) * this.mapIsolation.get(this.Isolation)) /100;
        return resultat;
    }

    @Override
    public String toString() {
        String resultat ="";
        resultat = "Maison individuelle ";
        resultat += "avec son terrain de " + surfaceTerrain + "m2";
        if(piscine){
            resultat += " et sa piscine";
        }
        resultat += ". " + super.toString();
        return resultat;
    }

    

    
}
