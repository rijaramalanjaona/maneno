package mg.cufp.isr3.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mg.cufp.isr3.bdd.CategorieBd;
import mg.cufp.isr3.bdd.ClientBd;
import mg.cufp.isr3.bdd.CommandeBd;
import mg.cufp.isr3.bdd.DetailCommandeBd;
import mg.cufp.isr3.bdd.ProduitBd;
import mg.cufp.isr3.entite.Categorie;
import mg.cufp.isr3.entite.Client;
import mg.cufp.isr3.entite.Commande;
import mg.cufp.isr3.entite.DetailCommande;
import mg.cufp.isr3.entite.Produit;

public class Application extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // constantes pour les identifiants ADMIN
    private static String ADMIN_LOGIN = "admin";
    private static String ADMIN_PASSWORD = "admin";

    // accès à la base de données
    private CategorieBd categorieBd;
    private ProduitBd produitBd;
    private ClientBd clientBd;
    private CommandeBd commandeBd;
    private DetailCommandeBd detailCommandeBd;
    
    // urls prédéfinis dans web.xml
    private String urlCategorieList, urlCategorieEdit;
    private String urlProduitList, urlProduitEdit;
    private String urlInscription;
    private String urlIdentification;
    private String urlCommande;
    private String urlHistoriqueAchat;
    
    // pour récupérer les données des formulaires
    private String id, libelle, description, prix, stock, idCategorie;
    private String nom, prenom, login, password, mail, adresse;
    private String produitId[], prixProduitCommande[], quantite[];
    private String total;
    
    // message d'erreur
    private String errorMessage;
    
    public Application() {
	super();
    }

    public void init() throws ServletException {
	categorieBd = new CategorieBd();
	produitBd = new ProduitBd();
	clientBd = new ClientBd();
	commandeBd = new CommandeBd();
	detailCommandeBd = new DetailCommandeBd();
	
	urlCategorieList = getServletConfig().getInitParameter("urlCategorieList");
	urlCategorieEdit = getServletConfig().getInitParameter("urlCategorieEdit");
	urlProduitList = getServletConfig().getInitParameter("urlProduitList");
	urlProduitEdit = getServletConfig().getInitParameter("urlProduitEdit");
	urlInscription = getServletConfig().getInitParameter("urlInscription");
	urlIdentification = getServletConfig().getInitParameter("urlIdentification");
	urlCommande = getServletConfig().getInitParameter("urlCommande");
	urlHistoriqueAchat = getServletConfig().getInitParameter("urlHistoriqueAchat");
	
	errorMessage = "";
    }

    public void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	String action = request.getParameter("action");
	System.out.println("Action demandee : " + action);
	// action va determiner quelle page afficher ?
	
	if (action == null) {
	    action = "identification";
	    session.setAttribute("clientLogOn", null);
	    session.setAttribute("isAdmin", "no");
	    getServletContext().getRequestDispatcher(urlIdentification).forward(request, response);
	    
	} else {
	    if (action.equals("identification")) {
		// formulaire d'identification
		getServletContext().getRequestDispatcher(urlIdentification).forward(request, response);
		
	    } else if (action.equals("identifier")) {
		login = request.getParameter("login");
		password = request.getParameter("password");
		
		// TODO gerer admin autrement
		if (login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD)) {
		    session.setAttribute("isAdmin", "yes");
		    
		    request.setAttribute("listCategorie", categorieBd.getAll());
		    getServletContext().getRequestDispatcher(urlCategorieList).forward(request, response);
		} else {
		    // connexion d'un client
		    Client clientLogOn = clientBd.isIdentificationOk(login, password);
		    if (clientLogOn != null) {
			request.setAttribute("client", clientLogOn);
			request.setAttribute("listProduit", produitBd.getAll());
			session.setAttribute("clientLogOn", clientLogOn);
			session.setAttribute("isAdmin", "no");
			
			getServletContext().getRequestDispatcher(urlCommande).forward(request, response);
			
		    } else {
			errorMessage = "Login ou password incorrect!";
			
			request.setAttribute("errorMessage", errorMessage);
			getServletContext().getRequestDispatcher(urlIdentification).forward(request, response);
		    }
		}
		
		
	    } else if (action.equals("deconnexion")) {
		// deconnexion
		session.setAttribute("clientLogOn", null);
		session.setAttribute("isAdmin", "no");
		getServletContext().getRequestDispatcher(urlIdentification).forward(request, response);
		
	    }  else if (action.equals("listCategorie") && session.getAttribute("isAdmin").equals("yes")) {
		// liste des categories, accessible uniquement par l'admin
		request.setAttribute("listCategorie", categorieBd.getAll());
		getServletContext().getRequestDispatcher(urlCategorieList).forward(request, response);
		
	    } else if (action.equals("insererCategorie") && session.getAttribute("isAdmin").equals("yes")) {
		// insertion d'une categorie, accessible uniquement par l'admin
		libelle = request.getParameter("libelle");
		Categorie categorie = new Categorie();
		categorie.setLibelle(libelle);
		categorieBd.insert(categorie);
		
		request.setAttribute("listCategorie", categorieBd.getAll());
		getServletContext().getRequestDispatcher(urlCategorieList).forward(request, response);
		
	    } else if (action.equals("formModifierCategorie") && session.getAttribute("isAdmin").equals("yes")) {
		// formulaire de modification d'une categorie, accessible uniquement par l'admin
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("categorie", categorieBd.getById(id));
		getServletContext().getRequestDispatcher(urlCategorieEdit).forward(request, response);
		
	    } else if (action.equals("modifierCategorie") && session.getAttribute("isAdmin").equals("yes")) {
		// modification d'une categorie, accessible uniquement par l'admin
		id = request.getParameter("id");
		libelle = request.getParameter("libelle");
		Categorie categorie = new Categorie();
		categorie.setId(Integer.parseInt(id));
		categorie.setLibelle(libelle);
		categorieBd.update(categorie);
		
		request.setAttribute("listCategorie", categorieBd.getAll());
		getServletContext().getRequestDispatcher(urlCategorieList).forward(request, response);
		
	    } else if (action.equals("supprimerCategorie") && session.getAttribute("isAdmin").equals("yes")) {
		// suppression d'une categorie, accessible uniquement par l'admin
		// fonctionnalité non activée par souci d'intégrité de données
		int id = Integer.parseInt(request.getParameter("id"));
		Categorie categorieToDel = categorieBd.getById(id);
		categorieBd.delete(categorieToDel);
		
		request.setAttribute("listCategorie", categorieBd.getAll());
		getServletContext().getRequestDispatcher(urlCategorieList).forward(request, response);
		
	    } else if (action.equals("listProduit") && session.getAttribute("isAdmin").equals("yes")) {
		// liste des produits, accessibles uniquement par l'admin
		request.setAttribute("listCategorie", categorieBd.getAll());
		request.setAttribute("listProduit", produitBd.getAll());
		getServletContext().getRequestDispatcher(urlProduitList).forward(request, response);
		
	    } else if (action.equals("insererProduit") && session.getAttribute("isAdmin").equals("yes")) {
		// insertion d'un produit, accessible uniquement par l'admin
		libelle = request.getParameter("libelle");
		description = request.getParameter("description");
		prix = request.getParameter("prix");
		stock = request.getParameter("stock");
		idCategorie = request.getParameter("id_categorie");
		
		Categorie categorie = categorieBd.getById(Integer.parseInt(idCategorie));
		Produit produit = new Produit();
		produit.setLibelle(libelle);
		produit.setDescription(description);
		produit.setPrix(Float.parseFloat(prix.replace(",", ".")));
		produit.setStock(Integer.parseInt(stock));
		produit.setCategorie(categorie);
		produitBd.insert(produit);
		
		request.setAttribute("listCategorie", categorieBd.getAll());
		request.setAttribute("listProduit", produitBd.getAll());
		getServletContext().getRequestDispatcher(urlProduitList).forward(request, response);
		
	    } else if (action.equals("formModifierProduit") && session.getAttribute("isAdmin").equals("yes")) {
		// formulaire de modification d'un produit, accessible uniquement par l'admin
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("produit", produitBd.getById(id));
		request.setAttribute("listCategorie", categorieBd.getAll());
		getServletContext().getRequestDispatcher(urlProduitEdit).forward(request, response);
		
	    } else if (action.equals("modifierProduit") && session.getAttribute("isAdmin").equals("yes")) {
		// modification d'un produit, accessible uniquement par l'admin
		id = request.getParameter("id");
		libelle = request.getParameter("libelle");
		description = request.getParameter("description");
		prix = request.getParameter("prix");
		stock = request.getParameter("stock");
		idCategorie = request.getParameter("id_categorie");
		
		Categorie categorie = new Categorie();
		categorie.setId(Integer.parseInt(idCategorie));
		
		Produit produit = new Produit();
		produit.setId(Integer.parseInt(id));
		produit.setLibelle(libelle);
		produit.setDescription(description);
		produit.setPrix(Float.parseFloat(prix.replace(",", ".")));
		produit.setStock(Integer.parseInt(stock));
		produit.setCategorie(categorie);
		produitBd.update(produit);
		
		request.setAttribute("listCategorie", categorieBd.getAll());
		request.setAttribute("listProduit", produitBd.getAll());
		getServletContext().getRequestDispatcher(urlProduitList).forward(request, response);
		
	    } else if (action.equals("inscription")) {
		// formulaire d'inscription d'un nouveau client
		getServletContext().getRequestDispatcher(urlInscription).forward(request, response);
		
	    } else if (action.equals("insererClient")) {
		// insertion d'un nouveau client
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		login = request.getParameter("login");
		password = request.getParameter("password");
		mail = request.getParameter("mail");
		adresse = request.getParameter("adresse");

		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setLogin(login);
		client.setPassword(password);
		client.setMail(mail);
		client.setAdresse(adresse);
		clientBd.insert(client);
		getServletContext().getRequestDispatcher(urlIdentification).forward(request, response);
		
	    } else if (action.equals("achat")) {
		// formulaire de commande
		getServletContext().getRequestDispatcher(urlCommande).forward(request, response);
		
	    } else if (action.equals("commander")) {
		// passer une commande
		if (session.getAttribute("clientLogOn") != null) {
		    produitId = request.getParameterValues("produitId");
		    prixProduitCommande = request.getParameterValues("prixProduitCommande");
		    quantite = request.getParameterValues("quantite");
		    total = request.getParameter("total");

		    if (Float.parseFloat(total) > 0) {
			Client clientLogOn = (Client) session.getAttribute("clientLogOn");
			Commande commande = new Commande();
			commande.setClient(clientLogOn);
			commande.setDate(new Date());
			commande.setTotal(Float.parseFloat(total));
			commande = commandeBd.insert(commande);
			
			Integer resteStock;

			// gestion des détails de la commande
			for (int i = 0; i < produitId.length; i++) {
			    if (Integer.parseInt(quantite[i]) > 0) {
				Produit produit = produitBd.getById(Integer.parseInt(produitId[i]));
				DetailCommande detailCommande = new DetailCommande();
				detailCommande.setCommande(commande);
				detailCommande.setPrixJour(Float.parseFloat(prixProduitCommande[i]));
				detailCommande.setProduit(produit);
				detailCommande.setQuantite(Integer.parseInt(quantite[i]));
				detailCommandeBd.insert(detailCommande);
			
				// gestion du stock
				resteStock = produit.getStock() - detailCommande.getQuantite();
				produit.setStock(resteStock);
				produitBd.update(produit);
			    }
			}

			// affichage historique commandes client
			List<Commande> listeCommande = commandeBd.getByClient(clientLogOn);
			if (listeCommande != null && !listeCommande.isEmpty()) {
			    for (Commande commandeClient : listeCommande) {
				commandeClient.setDetailCommandes(detailCommandeBd.getByCommande(commandeClient));
			    }
			    clientLogOn.setCommandes(listeCommande);
			}

			request.setAttribute("client", clientLogOn);
			request.setAttribute("listeCommandeClientLogOn", listeCommande);
			getServletContext().getRequestDispatcher(urlHistoriqueAchat).forward(request, response);
			
		    }
		} else {
		    // client non connecté
		   getServletContext().getRequestDispatcher(urlIdentification).forward(request, response);
		   
		}
	    } else if (action.equals("historiqueAchat")) {
		// affichage historique commandes client
		Client clientLogOn = (Client) session.getAttribute("clientLogOn");
		List<Commande> listeCommande = commandeBd.getByClient(clientLogOn);
		if (listeCommande != null && !listeCommande.isEmpty()) {
		    for (Commande commandeClient : listeCommande) {
			commandeClient.setDetailCommandes(detailCommandeBd.getByCommande(commandeClient));
		    }
		    clientLogOn.setCommandes(listeCommande);
		}

		request.setAttribute("client", clientLogOn);
		request.setAttribute("listeCommandeClientLogOn", listeCommande);
		getServletContext().getRequestDispatcher(urlHistoriqueAchat).forward(request, response);
		
	    }
	}
    }

    public void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }

}
