import java.util.ArrayList;

public class Ship implements Cloneable {
	public String startCoordonate; // d�but du bateau
	public String endCoordonate; // fin du bateau
	public ArrayList<String> localisation; // listes de toutes les cases sur lesquelles le bateau se situe
	public String etat;

	public Ship clone() {
		Ship newship = new Ship(this.startCoordonate, this.endCoordonate,this.localisation, "copy");
		return newship;
	}

	// Fonction de cr�ation d'un bateau
	public Ship(String start, String end, ArrayList<String> loca, String name) {
		super();
		this.startCoordonate = start;
		this.endCoordonate = end;
		this.localisation = loca;
		if ((name.equals("carrier")) && (this.localisation.size() == 5)) {
			this.etat = "valide";
		} else if ((name.equals("battleship")) && (this.localisation.size() == 4)) {
			this.etat = "valide";
		} else if (name.equals("copy")) {
			this.etat = "valide";
		} else if ((name.equals("cruiser")) && (this.localisation.size() == 3)) {
			this.etat = "valide";
		} else if ((name.equals("submarine")) && (this.localisation.size() == 3)) {
			this.etat = "valide";
		} else if ((name.equals("destroyer")) && (this.localisation.size() == 2)) {
			this.etat = "valide";
		} else {
			this.etat = "invalide";
			localisation = null;
		}
	}

	public String getStart() {
		return startCoordonate;
	}

	public void setStart(String start) {
		this.startCoordonate = start;
	}

	public String getEnd() {
		return endCoordonate;
	}

	public void setEnd(String end) {
		this.endCoordonate = end;
	}

	public ArrayList<String> getLocalisation() {
		return localisation;
	}

	// Fonction pour remplir la liste des coordonn�es du bateau
	public void setLocalisation(ArrayList<String> loca) {
		this.localisation = loca;
	}

	// fonction pour savoir si le bateau est touch�
	public boolean isHit(String pos) {
		if (this.localisation.contains(pos)) {
			return true;
		} else {
			return false;
		}
	}

	// fonction pour savoir si le bateau est d�truit
	public boolean isDestroyed() {
		if (this.localisation.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void removepos(String pos) {
		this.localisation.remove(pos);
	}

}