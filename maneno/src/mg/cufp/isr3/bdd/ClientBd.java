package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mg.cufp.isr3.entite.Client;

public class ClientBd {
    public int insert(Client client) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	
	Statement st = null;
	int nb = 0;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "INSERT INTO tp.client(nom, prenom, login, password, mail, adresse) "
		    + "VALUES ('"
		    + client.getNom()
		    + "', '"
		    + client.getPrenom()
		    + "', '"
		    + client.getLogin()
		    + "', '"
		    + client.getPassword()
		    + "', '"
		    + client.getMail()
		    + "', '"
		    + client.getAdresse()
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
