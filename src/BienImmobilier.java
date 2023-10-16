import java.net.CacheRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class  BienImmobilier {
    private String rue;
    private String ville;
    private String codePostal;
    private Vendeur  vendeur;
    private Proprietaire proprietaire;
    protected ArrayList<Piece> pieces;
    private Annonce annonce;
    protected String Orientation;
    protected String Menuiserie;
    protected String Chauffage;
    protected String Isolation;
    protected Map<String, Double> mapOrientation;
    protected Map<String, Double> mapMenuiserie;
    protected Map<String, Double> mapChauffage;
    protected Map<String, Double> mapIsolation;


    public BienImmobilier(String rue, String ville, String codePostal, Vendeur vendeur) {
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.vendeur = vendeur;
        vendeur.ajouterBienImmobilier(this);
        pieces = new ArrayList<Piece>();
        this.mapOrientation = new HashMap<String, Double>(); mapOrientation.put("Nord", 1.8); mapOrientation.put("Sud", 1.0); mapOrientation.put("Est", 1.2); mapOrientation.put("Ouest", 1.4);
        this.mapMenuiserie = new HashMap<String, Double>(); mapMenuiserie.put("Excellente", 1.0); mapMenuiserie.put("Bonne", 1.1); mapMenuiserie.put("Moyenne", 1.3); mapMenuiserie.put("Mauvaise", 1.6);
        this.mapChauffage = new HashMap<String, Double>(); mapChauffage.put("Bois", 0.8); mapChauffage.put("Géothermie", 0.6); mapChauffage.put("Electrique", 1.0); mapChauffage.put("Gaz", 0.9);
        this.mapIsolation = new HashMap<String, Double>(); mapIsolation.put("Excellente", 0.8); mapIsolation.put("Bonne", 1.0); mapIsolation.put("Moyenne", 1.4); mapIsolation.put("Aucune", 2.0);
    }
    public double surfaceHabitable(){
        double resultat = 0;
        for(Piece unePiece : pieces){
            if(unePiece.isSurfaceHabitable() == true)
            resultat += unePiece.surface();
        }
        return resultat;
    }
    public double surfaceNonHabitable(){
        double resultat = 0;
        for(Piece unePiece : pieces){
            if(!unePiece.getTypePiece().isSurfaceHabitable())
            resultat += unePiece.surface();
        }
        return resultat;
    }
    public void ajouterPiece(Piece piece){
        pieces.add(piece);
    }

    public Vendeur getVendeur() {
        return vendeur;
    }   

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public void diagnosticDPE(String Orientation, String Menuiserie, String Chauffage, String Isolation){
        this.Orientation = Orientation;
        this.Menuiserie = Menuiserie;
        this.Chauffage = Chauffage;
        this.Isolation = Isolation;
    }

    public double consommationKWhAn(){
        double resultat = 0;
        resultat = surfaceHabitable() * 110;
        return resultat;
        }

    public abstract double dpe();

    public String lettreDpe(double orientation, double menuiserie, double chauffage, double isolation) {
        String lettre = "";
        Double dpe = dpe();
        if (dpe <= 70) {
            lettre = "A";
        } else if (dpe > 70 && dpe <= 110) {
            lettre = "B";
        } else if (dpe > 110 && dpe <= 180) {
            lettre = "C";
        } else if (dpe > 180 && dpe <= 250) {
            lettre = "D";
        } else if (dpe > 250 && dpe <= 330) {
            lettre = "E";
        } else if (dpe > 330 && dpe <= 420) {
            lettre = "F";
        } else if (dpe > 420) {
            lettre = "G";
        }
        return lettre;
    }

    public Map<String, Double> diagnosticDpeDictionnaire(double Orientation, double Menuiserie, double Chauffage, double Isolation) {
        Map<String, Double> mapDiagnostic = new HashMap<String, Double>();
        mapDiagnostic.put("Orientation", Orientation);
        mapDiagnostic.put("Menuiserie", Menuiserie);
        mapDiagnostic.put("Chauffage", Chauffage);
        mapDiagnostic.put("Isolation", Isolation);

        return mapDiagnostic;
    }
    
    @Override
    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return "\nLocalisation : " + rue + " " + codePostal + " " + ville +
                "\n \n Description du bien : \n" +
                toStringPieces() +
                "\nPour une surface habitable de : " + numberFormat.format(surfaceHabitable())
                + " m2 et une surface non habitable de : " + numberFormat.format(surfaceNonHabitable()) + " m2.";
    }

    public String toStringPieces() {
        String resultat = "";
        for (Piece unePiece : pieces) {
            resultat += unePiece.toString();
        }
        return resultat;
    }



}
