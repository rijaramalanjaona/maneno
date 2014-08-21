package mg.cufp.isr3.test;

import java.text.ParseException;
import java.util.List;

import mg.cufp.isr3.bdd.CategorieBd;
import mg.cufp.isr3.entite.Categorie;

public class CategorieTest {
    public static void main(String[] args) throws ParseException {
	System.out.println("test categorie");
	
	// creation d'une entite Categorie
//	Categorie categorie = new Categorie();
//	categorie.setLibelle("categorie 02");
//	
//	// appel de CategorieBd
	CategorieBd categorieBd = new CategorieBd();
//	categorieBd.insert(categorie);
	
	List<Categorie> liste = categorieBd.getAll();
	System.out.println(liste.size());
	
	System.out.println("fin test insert categorie");
    }
}
