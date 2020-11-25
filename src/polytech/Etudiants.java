package polytech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.sun.java.util.jar.pack.Package.Class;

// Ici on definit la couche de notre app
public class Etudiants {

	private static Connection connection = null;

	public Connection getConnection() {
		return connection;
	}

	/*
	 * public void setConnection(Connection connection) { this.connection =
	 * connection; }
	 */

	public static void seConnecter() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // exception surveill�e
		} catch (ClassNotFoundException e) {
			System.out.println("Le Driver n'est pas charge");
		}

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/cm13nov?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			System.out.println("Connecte a la BDD");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Etudiant> afficherTousLesEtudiants() {
		List<Etudiant> res = new ArrayList<Etudiant>();

		seConnecter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // exception surveill�e
		} catch (ClassNotFoundException e) {
			System.out.println("Le Driver n'est pas charge");
		}

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			statement = connection.createStatement();
			// execute une requete et recuperer le contenu dans l'objet resultSet
			resultSet = statement.executeQuery("SELECT * FROM `etudiants`");

			// recuperation des donnees...
			while (resultSet.next()) {// tant qu'il y a une nextline
				int identifiant = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				res.add(new Etudiant(identifiant, nom, prenom));
			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Probleme de connexion a la base de donnee");
		} finally {

			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}

	public static void ajouterUnEtudiant(Etudiant etudiant) {
		seConnecter();

		// failles d'injection SQL
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO `etudiants`(`id`, `nom`, `prenom`) VALUES (?,?,?);");
			preparedStatement.setInt(1, etudiant.getIdentifiant());
			preparedStatement.setString(2, etudiant.getNom());
			preparedStatement.setString(3, etudiant.getPrenom());

			// executer la requete
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Probleme dans l'ajout d'un etudiant");
		}
	}

	public static void modifierEtudiant(Etudiant etudiant) {
		seConnecter();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE `etudiants` SET `nom`=?,`prenom`=? WHERE `id`=?;");
			preparedStatement.setInt(3, etudiant.getIdentifiant());
			preparedStatement.setString(1, etudiant.getNom());
			preparedStatement.setString(2, etudiant.getPrenom());

			// executer la requete
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Probleme dans la modification d'un etudiant");
		}
	}

	public static void supprimerUnEtudiant(Etudiant etudiant) {
		seConnecter();

		// failles d'injection SQL
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `etudiants`WHERE id=?;");
			preparedStatement.setInt(1, etudiant.getIdentifiant());

			// executer la requete
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Probleme dans l'ajout d'un etudiant");
		}
	}

	public static List<Etudiant> afficherResultatRecherche(String value) {
		List<Etudiant> res = new ArrayList<Etudiant>();

		seConnecter();

		try {
			Statement statement = connection.createStatement();
			// execute une requete et recuperer le contenu dans l'objet resultSet
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM `etudiants`WHERE nom='" + value + "'OR prenom='" + value + "';");
			int c = 0;
			// recuperation des donnees...
			while (resultSet.next()) {// tant qu'il y a une nextline
				c++;
				int identifiant = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				res.add(new Etudiant(identifiant, nom, prenom));
			}
			if (c == 0) {
				res.add(new Etudiant(Integer.MIN_VALUE, "Personne avec cette", "recherche"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
