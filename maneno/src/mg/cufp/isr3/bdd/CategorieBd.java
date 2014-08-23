package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mg.cufp.isr3.entite.Categorie;

public class CategorieBd {

    public int insert(Categorie categorie) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "INSERT INTO tp.categorie(libelle)"
		    	+ "VALUES ('" + categorie.getLibelle() +  "')";
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
    
    public int update(Categorie categorie) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "UPDATE tp.categorie SET libelle = '" + categorie.getLibelle() + "' WHERE id = '" + categorie.getId() + "'";
	    nb = st.executeUpdate(sql);
	} catch (SQLException e) {		
	    e.printStackTrace();
	}
	finally {
	    if(connex != null){
		try {
		    connex.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return nb;
    }
    
    public int delete(Categorie categorie) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "DELETE FROM tp.categorie WHERE id = '" + categorie.getId() + "'";
	    nb = st.executeUpdate(sql);
	} catch (SQLException e) {		
	    e.printStackTrace();
	}
	finally {
	    if(connex != null){
		try {
		    connex.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return nb;
    }
    
    public List<Categorie> getAll() {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	List<Categorie> liste = new ArrayList<Categorie>();
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, libelle FROM tp.categorie ORDER BY id";
	    rs = st.executeQuery(sql);
	    while (rs.next()) {
		Categorie categorie = new Categorie();
		categorie.setId(rs.getInt("id"));
		categorie.setLibelle(rs.getString("libelle"));
		liste.add(categorie);
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
    
    public Categorie getById(Integer id) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	Categorie categorie = null;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, libelle FROM tp.categorie WHERE id = '" + id + "'";
	    rs = st.executeQuery(sql);
	    if (rs.next()) {
		categorie = new Categorie();
		categorie.setId(rs.getInt("id"));
		categorie.setLibelle(rs.getString("libelle"));
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
	return categorie;
    }
}
