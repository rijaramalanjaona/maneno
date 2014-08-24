package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
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
    
    public Client isIdentificationOk(String login, String password) {
	ConnexionBd connexionBd = new ConnexionBd();
	Connection connex = null;
	ResultSet rs = null;
	Statement st;
	Client client = null;
	try {
	    connex = connexionBd.getConnexionManeno();
	    st = connex.createStatement();
	    String sql = "SELECT id, nom, prenom, login, password, mail, adresse FROM tp.client " +
	    		" WHERE login = '" + login + "' AND password = '" + password + "'";
	    rs = st.executeQuery(sql);
	    if (rs.next()) {
		client = new Client();
		client.setId(rs.getInt("id"));
		client.setNom(rs.getString("nom"));
		client.setPrenom(rs.getString("prenom"));
		client.setLogin(rs.getString("login"));
		client.setPassword(rs.getString("password"));
		client.setMail(rs.getString("mail"));
		client.setAdresse(rs.getString("adresse"));
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
	return client;
    }
}
