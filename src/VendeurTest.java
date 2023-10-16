import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendeurTest {

    @Test
    public void testCalculCommissions() {
        TypePiece chambre = new TypePiece(TypePiece.CHAMBRE, true, true);
        TypePiece salon = new TypePiece(TypePiece.SALON, true, true);
        TypePiece cuisine = new TypePiece(TypePiece.CUISINE, true, false);
        TypePiece salleDeBain = new TypePiece(TypePiece.SALLE_DE_BAIN, true, false);
        TypePiece Wc = new TypePiece(TypePiece.WC, true, false);
        TypePiece Cave = new TypePiece(TypePiece.CAVE, false, false);

        // Création d'un vendeur
        Vendeur vendeur = new Vendeur("Dupuis", "Jean-Claude", "dp.jc@gmail.com", "06.74.74.00.00");

        // Création d'un nouveau bien immobilier : Appartement
        Appartement appartement1 = new Appartement("16 rue du lac", "ANNECY", "74000", vendeur, "1er étage");
        appartement1.ajouterPiece(new PieceQuadrilatere(chambre, "0", 4, 3));
        appartement1.ajouterPiece(new PieceTriangulaire(cuisine, "0", 6, 2.3));
        appartement1.ajouterPiece(new PieceCirculaire(salon, "0", 2.1));
        appartement1.ajouterPiece(new PieceQuadrilatere(Wc, "0", 1.5, 2));
        appartement1.ajouterPiece(new PieceQuadrilatere(salleDeBain, "0", 2, 2));
        appartement1.ajouterPiece(new PieceQuadrilatere(Cave, "-1", 3, 3));
        // Création de l'annonce associée au bien immobilier : Appartement
        Annonce annonceAppartement = new Annonce("AA0922001", "27/09/2023", "Appartement avec vue sur le Lac d'Annecy", 6380, appartement1);

        vendeur.addGestion(appartement1);
        

      
        Maison maison1 = new Maison("rue de la Gare", "Annecy", "74000", vendeur, 500);
        maison1.ajouterPiece(new PieceQuadrilatere(chambre, "0", 5, 2));
        maison1.ajouterPiece(new PieceQuadrilatere(salleDeBain, "0", 2, 2));
        maison1.ajouterPiece(new PieceQuadrilatere(salon, "0", 5, 4));
        // Création de l'annonce associée au bien immobilier : Appartement
        Annonce annonceMaison = new Annonce("AA093366", "04/10/2023", "Villa de luxe",
                4800, maison1);
       vendeur.addGestion(maison1);
        Appartement appartement2 = new Appartement("rue des moulins", "Annecy", "74000", vendeur, "1");
        appartement2.ajouterPiece(new PieceQuadrilatere(chambre, "0", 10, 3));
        vendeur.addGestion(appartement2);
        // Création de l'annonce associée au bien immobilier : Appartement
        Annonce annonceMonAppartement = new Annonce("AB0622001", "30/09/2023", "Studio spacieux",
                3600, appartement2);
      
        double resultat = vendeur.calculCommissions();
        assertEquals(26959.41, resultat, 1e-2);
    }
}
