package polytech;


public class Etudiant {
	private int identifiant;
	private String nom;
	private String prenom;
	
	public Etudiant() {
		super();
	}

	public Etudiant(int identifiant, String nom, String prenom) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	
}
