package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mg.cufp.isr3.entite.Produit;

public class ProduitBd {
    public int insert(Produit produit) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "INSERT INTO tp.produit(libelle, description, prix, stock, id_categorie) "
		    + "VALUES ('"
		    + produit.getLibelle()
		    + "', '"
		    + produit.getDescription()
		    + "', '"
		    + produit.getPrix()
		    + "', '"
		    + produit.getStock()
		    + "', '"
		    + produit.getCategorie().getId()
		   + "')";
	    nb = st.executeUpdate(sql);
	} catch (SQLException e) {
	    System.out.println(e);
	} finally {
	    if (connex != null) {
		try {
		    connex.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return nb;
    }
}
