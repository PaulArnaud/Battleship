import java.util.ArrayList;

public class Ship {
	public String startCoordonate; // d�but du bateau
	public String endCoordonate; // fin du bateau
	public ArrayList<String> localisation; // listes de toutes les cases sur lesquelles le bateau se situe
	public String etat;

	// Fonction de cr�ation d'un bateau
	public Ship(String start, String end, String name) {
		super();
		this.startCoordonate = start;
		this.endCoordonate = end;
		this.setLocalisation(start, end);
		if ((name.equals("carrier")) && (this.localisation.size() == 5)) {
			this.etat = "valide";
		} else if ((name.equals("battleship")) && (this.localisation.size() == 4)) {
			this.etat = "valide";
		}

		else if ((name.equals("cruiser")) && (this.localisation.size() == 3)) {
			this.etat = "valide";
		}

		else if ((name.equals("submarine")) && (this.localisation.size() == 3)) {
			this.etat = "valide";
		}

		else if ((name.equals("destroyer")) && (this.localisation.size() == 2)) {
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
	public void setLocalisation(String start, String end) {
		ArrayList<String> loca = new ArrayList<String>();
		String firstletter = (String) start.substring(0, 1);
		String firstnumber = (String) start.substring(1, 2);
		String secondletter = (String) end.substring(0, 1);
		String secondnumber = (String) end.substring(1, 2);
		if (firstletter.equals(secondletter)) {
			int fn = Battleship.convstringtoint(firstnumber);
			int sn = Battleship.convstringtoint(secondnumber);
			for (int i = fn; i <= sn; i++) {
				loca.add(firstletter + i);
			}
		} else if (firstnumber.equals(secondnumber)) {
			int fl = Battleship.convstringtoint(firstletter);
			int sl = Battleship.convstringtoint(secondletter);
			for (int i = fl; i <= sl; i++) {
				loca.add(Battleship.convinttostring(i) + firstnumber);
			}
		} else {
			System.out.println("ErrorsetLocalisation");
		}
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