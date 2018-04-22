import java.util.ArrayList;

public class SHIP {
	public String start; // début du bateau
	public String end; // fin du bateau
	public ArrayList<String> localisation; // listes de toutes les cases sur lesquelles le bateau se situe
	public String etat;

	// Fonction de création d'un bateau
	public SHIP(String start, String end, String name) {
		super();
		this.start = start;
		this.end = end;
		this.setLocalisation(start, end);
		if ((name == "carier") && (this.localisation.size() == 5)) {
			this.etat = "valide";
		} else if ((name == "battleship") && (this.localisation.size() == 4)) {
			this.etat = "valide";
		}

		else if ((name == "cruiser") && (this.localisation.size() == 3)) {
			this.etat = "valide";
		}

		else if ((name == "submarine") && (this.localisation.size() == 3)) {
			this.etat = "valide";
		}

		else if ((name == "destroyer") && (this.localisation.size() == 2)) {
			this.etat = "valide";
		} else {
			this.etat = "invalide";
			localisation = null;
		}

	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public ArrayList<String> getLocalisation() {
		return localisation;
	}

	// Fonction pour remplir la liste des coordonnées du bateau
	public void setLocalisation(String start, String end) {
		ArrayList<String> loca = new ArrayList<String>();
		String firstletter = (String) start.substring(0, 1);
		String firstnumber = (String) start.substring(1, 2);
		String secondletter = (String) end.substring(0, 1);
		String secondnumber = (String) end.substring(1, 2);
		if (firstletter.equals(secondletter)) {
			int fn = BATTLESHIP.convstringtoint(firstnumber);
			int sn = BATTLESHIP.convstringtoint(secondnumber);
			for (int i = fn; i <= sn; i++) {
				loca.add(firstletter + i);
			}
		} else if (firstnumber.equals(secondnumber)) {
			int fl = BATTLESHIP.convstringtoint(firstletter);
			int sl = BATTLESHIP.convstringtoint(secondletter);
			for (int i = fl; i <= sl; i++) {
				loca.add(BATTLESHIP.convinttostring(i) + firstnumber);
			}
		} else {
			System.out.println("ErrorsetLocalisation");
		}

		this.localisation = loca;
	}
	//fonction pour savoir si le bateau est touché
	public boolean isHit(String pos) {
		if (this.localisation.contains(pos)) {
			localisation.remove(pos);
			return true;
		} else {
			return false;
		}
	}
	// fonction pour savoir si le bateau est détruit
	public boolean isDestroyed() {
		if (this.localisation.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}