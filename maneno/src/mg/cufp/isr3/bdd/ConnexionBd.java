package mg.cufp.isr3.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBd {
    private Connection connexionManeno;

    public Connection getConnexionManeno() {
	return connexionManeno;
    }

    public ConnexionBd() {
	this.initialize();
    }

    public void initialize() {
	try {
	    Class.forName("org.postgresql.Driver");
	    connexionManeno = DriverManager.getConnection(
		    "jdbc:postgresql://localhost:5432/maneno", "postgres", "root");

	} catch (ClassNotFoundException e) {
	    System.out.println("ClassNotFoundException : " + e);
	} catch (SQLException e) {
	    System.out.println("SQLException : " + e);
	}
    }
}
