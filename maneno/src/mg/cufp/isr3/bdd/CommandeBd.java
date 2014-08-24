package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mg.cufp.isr3.entite.Client;
import mg.cufp.isr3.entite.Commande;

public class CommandeBd {
    public Commande insert(Commande commande) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "INSERT INTO tp.commande(date, total, id_client) "
		    + "VALUES ('"
		    + commande.getDate()
		    + "', '"
		    + commande.getTotal()
		    + "', '"
		    + commande.getClient().getId()
		   + "')";
	    nb = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
	    if (nb == 0) {
	        throw new SQLException("Creating commande failed, no rows affected.");
	    }
	    ResultSet generatedKeys = st.getGeneratedKeys();
	    if (generatedKeys.next()) {
		commande.setId(generatedKeys.getInt(1));
	    }
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
	return commande;
    }
    
    public List<Commande> getByClient(Client client) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	List<Commande> liste = new ArrayList<Commande>();
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, date, total FROM tp.commande WHERE id_client = '" + client.getId() + "'";
	    rs = st.executeQuery(sql);
	    while (rs.next()) {
		Commande commande = new Commande();
		commande.setId(rs.getInt("id"));
		commande.setDate(rs.getDate("date"));
		commande.setTotal(rs.getFloat("total"));
		commande.setClient(client);
		liste.add(commande);
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
