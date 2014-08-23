package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mg.cufp.isr3.entite.Categorie;
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
    
    public List<Produit> getAll() {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	List<Produit> liste = new ArrayList<Produit>();
	CategorieBd categorieBd = new CategorieBd();
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, libelle, description, prix, stock, id_categorie FROM tp.produit ORDER BY id";
	    rs = st.executeQuery(sql);
	    while (rs.next()) {
		Produit produit = new Produit();
		produit.setId(rs.getInt("id"));
		produit.setLibelle(rs.getString("libelle"));
		produit.setDescription(rs.getString("description"));
		produit.setPrix(rs.getFloat("prix"));
		produit.setStock(rs.getInt("stock"));
		
		Categorie categorie = categorieBd.getById(rs.getInt("id_categorie"));
		produit.setCategorie(categorie);
		liste.add(produit);
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
    
    public Produit getById(Integer id) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	Produit produit = null;
	CategorieBd categorieBd = new CategorieBd();
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, libelle, description, prix, stock, id_categorie FROM tp.produit WHERE id = '" + id + "'";
	    rs = st.executeQuery(sql);
	    if (rs.next()) {
		produit = new Produit();
		produit.setId(rs.getInt("id"));
		produit.setLibelle(rs.getString("libelle"));
		produit.setDescription(rs.getString("description"));
		produit.setPrix(rs.getFloat("prix"));
		produit.setStock(rs.getInt("stock"));
		
		Categorie categorie = categorieBd.getById(rs.getInt("id_categorie"));
		produit.setCategorie(categorie);
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
	return produit;
    }
    
    public int update(Produit produit) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "UPDATE tp.produit SET " 
	    		+ "libelle = '" + produit.getLibelle() + "',"
	    		+ "description = '" + produit.getDescription() + "',"
	    		+ "prix = '" + produit.getPrix() + "',"
	    		+ "stock= '" + produit.getStock() + "',"
	    		+ "id_categorie = '" + produit.getCategorie().getId() + "'" 
	    		+ "WHERE id = '" + produit.getId() + "'";
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
