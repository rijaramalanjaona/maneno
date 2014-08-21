package mg.cufp.isr3.test;

import java.text.ParseException;
import java.util.List;

import mg.cufp.isr3.bdd.ProduitBd;
import mg.cufp.isr3.entite.Categorie;
import mg.cufp.isr3.entite.Produit;

public class ProduitTest {
    public static void main(String[] args) throws ParseException {
	System.out.println("test produit");
	
	// creation d'une entite Categorie
	Categorie categorie = new Categorie();
	categorie.setId(1);
	
	Produit produit = new Produit();
	produit.setCategorie(categorie);
	produit.setLibelle("produit 01");
	produit.setDescription("desc 01");
	produit.setPrix(new Float(150));
	produit.setStock(new Integer(10));
//	
//	// appel de CategorieBd
	ProduitBd produitBd = new ProduitBd();
	produitBd.insert(produit);
	
//	List<Categorie> liste = categorieBd.getAll();
//	System.out.println(liste.size());
	
	System.out.println("fin test produit");
    }
}
