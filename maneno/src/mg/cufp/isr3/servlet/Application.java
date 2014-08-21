package mg.cufp.isr3.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.cufp.isr3.bdd.CategorieBd;

public class Application extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategorieBd categorieBd;
    private String urlCategorieList;
    
    public Application() {
	super();
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
	categorieBd = new CategorieBd();
	
	urlCategorieList = getServletConfig().getInitParameter("urlCategorieList");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("listCategorie", categorieBd.getAll());
	getServletContext().getRequestDispatcher(urlCategorieList)
	    .forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }

}
