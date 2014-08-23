package mg.cufp.isr3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.cufp.isr3.bdd.CategorieBd;
import mg.cufp.isr3.bdd.ProduitBd;
import mg.cufp.isr3.entite.Categorie;
import mg.cufp.isr3.entite.Produit;

public class Application extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategorieBd categorieBd;
    private ProduitBd produitBd;
    
    private String urlCategorieList, urlCategorieEdit;
    private String urlProduitList, urlProduitEdit;
    
    private String id, libelle, description, prix, stock, idCategorie;
    
    public Application() {
	super();
    }

    public void init() throws ServletException {
	categorieBd = new CategorieBd();
	produitBd = new ProduitBd();
	
	urlCategorieList = getServletConfig().getInitParameter("urlCategorieList");
	urlProduitEdit = getServletConfig().getInitParameter("urlProduitEdit");
	urlProduitList = getServletConfig().getInitParameter("urlProduitList");
	urlProduitEdit = getServletConfig().getInitParameter("urlProduitEdit");
    }

    public void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	System.out.println("Action demandee : " + action);
	if (action == null) {
	    action = "liste";
	} else {
	    if (action.equals("insererCategorie")) {
		libelle = request.getParameter("libelle");
		Categorie categorie = new Categorie();
		categorie.setLibelle(libelle);
		categorieBd.insert(categorie);
	    } else if (action.equals("formModifierCategorie")) {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("categorie", categorieBd.getById(id));
		getServletContext().getRequestDispatcher(urlCategorieEdit).forward(request, response);
	    } else if (action.equals("modifierCategorie")) {
		id = request.getParameter("id");
		libelle = request.getParameter("libelle");
		Categorie categorie = new Categorie();
		categorie.setId(Integer.parseInt(id));
		categorie.setLibelle(libelle);
		categorieBd.update(categorie);
	    } else if (action.equals("supprimerCategorie")) {
		int id = Integer.parseInt(request.getParameter("id"));
		Categorie categorieToDel = categorieBd.getById(id);
		categorieBd.delete(categorieToDel);
	    } else if (action.equals("listProduit")) {
		request.setAttribute("listCategorie", categorieBd.getAll());
		request.setAttribute("listProduit", produitBd.getAll());
		getServletContext().getRequestDispatcher(urlProduitList).forward(request, response);
	    } else if (action.equals("insererProduit")) {
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
	    } else if (action.equals("formModifierProduit")) {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("produit", produitBd.getById(id));
		request.setAttribute("listCategorie", categorieBd.getAll());
		getServletContext().getRequestDispatcher(urlProduitEdit).forward(request, response);
	    } else if (action.equals("modifierProduit")) {
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
	    }
	}
	
	request.setAttribute("listCategorie", categorieBd.getAll());
	getServletContext().getRequestDispatcher(urlCategorieList).forward(request, response);
    }

    public void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }

}
