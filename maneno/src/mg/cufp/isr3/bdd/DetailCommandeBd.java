package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mg.cufp.isr3.entite.DetailCommande;

public class DetailCommandeBd {
    public int insert(DetailCommande detailCommande) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "INSERT INTO tp.detail_commande(id_commande, id_produit, prix_jour, quantite) "
		    + "VALUES ('"
		    + detailCommande.getCommande().getId()
		    + "', '"
		    + detailCommande.getProduit().getId()
		    + "', '"
		    + detailCommande.getPrixJour()
		    + "', '"
		    + detailCommande.getQuantite()
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
