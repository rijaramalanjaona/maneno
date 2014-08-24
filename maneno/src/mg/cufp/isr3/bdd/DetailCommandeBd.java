package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mg.cufp.isr3.entite.Commande;
import mg.cufp.isr3.entite.DetailCommande;
import mg.cufp.isr3.entite.Produit;

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
    
    public List<DetailCommande> getByCommande(Commande commande) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	List<DetailCommande> liste = new ArrayList<DetailCommande>();
	ProduitBd produitBd = new ProduitBd();
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, id_produit, prix_jour, quantite FROM tp.detail_commande WHERE id_commande = '" + commande.getId() + "'";
	    rs = st.executeQuery(sql);
	    while (rs.next()) {
		DetailCommande detailCommande = new DetailCommande();
		detailCommande.setId(rs.getInt("id"));
		detailCommande.setCommande(commande);
		detailCommande.setPrixJour(rs.getFloat("prix_jour"));
		detailCommande.setQuantite(rs.getInt("quantite"));
		
		Produit produit = produitBd.getById(rs.getInt("id_produit"));
		detailCommande.setProduit(produit);
		
		liste.add(detailCommande);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (connex != null && rs != null) {
		try {
		    rs.close();
		    connex.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return liste;
    }
}
