package polytech;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();

	}

	// si qqun met /controler dans l'url la methode get est call
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("res", Etudiants.afficherTousLesEtudiants());// donne un attribut a la jsp

		this.getServletContext().getRequestDispatcher("/WEB-INF/dao.jsp").forward(request, response);

	}

	// si qqun met un form avec la methode post alors doPost est call
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// On definit un objet de la classe metier, on fait appel a la methode
		// ajouterUnEtudiant(etudiant, etudiant)
		Etudiant etudiant = new Etudiant();
		etudiant.setIdentifiant(Integer.parseInt(request.getParameter("id")));
		etudiant.setNom(request.getParameter("nom"));
		etudiant.setPrenom(request.getParameter("prenom"));

		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equals("Send")) {
				Etudiants.ajouterUnEtudiant(etudiant);
			}
			if (action.equals("Edit")) {
				Etudiants.modifierEtudiant(etudiant);
			}
			if (action.equals("Delete")) {
				Etudiants.supprimerUnEtudiant(etudiant);
			}
		}

		doGet(request, response);

	}

}
