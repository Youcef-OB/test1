package polytech;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller2")
public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("resultat", Etudiants.afficherTousLesEtudiants());// donne un attribut a la jsp

		this.getServletContext().getRequestDispatcher("/WEB-INF/dao2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value = request.getParameter("recherche");
		request.setAttribute("resultat", Etudiants.afficherResultatRecherche(value));
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao2.jsp").forward(request, response);

		// doGet(request, response);
	}

}
