package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mg.cufp.isr3.entite.Commande;

public class CommandeBd {
    public int insert(Commande commande) {
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
