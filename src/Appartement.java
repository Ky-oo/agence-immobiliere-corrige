public class Appartement extends BienImmobilier{
    private String etage;
    private boolean ascenseur;
    private int nbFaceExterieur;
    private boolean dernierEtage;

    public Appartement(String rue, String ville, String codePostal, Vendeur vendeur, String etage, boolean ascenseur, int nbFaceExterieur, boolean dernierEtage) {
        super(rue, ville, codePostal, vendeur);
        this.etage = etage;
        this.ascenseur = ascenseur;
        this.nbFaceExterieur = nbFaceExterieur;
        this.dernierEtage = dernierEtage;
    }

    public Appartement(String rue, String ville, String codePostal, Vendeur vendeur, String etage) {
        super(rue, ville, codePostal, vendeur);
        this.etage = etage;
        this.ascenseur = false;
    }

    public String typeBien(){
        String resultat ="";
        int nombrePieces = 0;
        for(Piece unePiece : super.pieces){
            if(unePiece.getTypePiece().isPiece()){
                nombrePieces++;
            }
        }
        resultat = "T" + nombrePieces;
        if(nombrePieces >7){
            resultat = "T7+";
        }
        return resultat;
    }

    public double dpe() {
        double resultat = 0;
        double valueEtage = 1.0;
        double valueFace = 1.0;
        
        if( Integer.valueOf(etage) == 0 || dernierEtage){
            valueEtage = 1.2;
        } else {
            valueEtage = 0.9;
        }

        if(nbFaceExterieur == 2){
            valueFace = 1.1;
        } else if (nbFaceExterieur == 3){
            valueFace = 1.15;
        } else {
            valueFace = 1.25;
        }

        resultat = (consommationKWhAn() * this.mapOrientation.get(this.Orientation) * this.mapMenuiserie.get(this.Menuiserie) * this.mapChauffage.get(this.Chauffage) * this.mapIsolation.get(this.Isolation) * valueEtage * valueFace) /100;
        return resultat;
    }

    @Override
    public String toString() {
        String resultat ="";
        resultat = "Appartement de type "+ typeBien();
        if(ascenseur){
            resultat += " avec ascenseur ";
        }
        resultat += " situé au " + etage + " ";
        resultat += super.toString();
        return resultat;
    }

    
    
    
}
