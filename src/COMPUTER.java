import java.util.ArrayList;

public class COMPUTER extends PLAYER {
	// attaque
	public ArrayList<String> currentboat;
	public String state = "chasse";// chasse ou tir
	public String dirstate = "haut"; // haut,droite,bas,gauche
	// "défense"

	public ArrayList<String> getCurrentboat() {
		return currentboat;
	}

	public void setCurrentboat(ArrayList<String> currentboat) {
		this.currentboat = currentboat;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDirstate() {
		return dirstate;
	}

	public void setDirstate(String dirstate) {
		this.dirstate = dirstate;
	}

	public static ArrayList<String> hasardcontruc(GAME n, String name) {
		String posfinal = null;
		ArrayList<String> loca = new ArrayList<String>();
		if (name.equals("carier")) {
			int i = (int) (Math.random() * 36);
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a, 5);
			loca = n.test(a, posfinal);
			return loca;
		} else if (name.equals("battleship")) {
			int i = (int) (Math.random() * 49);
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a, 4);
			loca = n.test(a, posfinal);
			return loca;
		} else if (name.equals("cruiser")) {
			int i = (int) (Math.random() * 64);
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a, 3);
			loca = n.test(a, posfinal);
			return loca;
		} else if (name.equals("submarine")) {
			int i = (int) (Math.random() * 64);
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a, 3);
			loca = n.test(a, posfinal);
			return loca;
		} else {
			int i = (int) (Math.random() * 81);
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a, 2);
			loca = n.test(a, posfinal);
			return loca;
		}
	}

	public String tir(GAME game) {
		String pos = "";
		if (this.state.equals("chasse")) {
			pos = hasardtir(game);
		} else {
			if (this.currentboat.size() > 1 && this.currentboat.size() < 6) {
				pos = calcul();
			} else if (this.currentboat.size() < 2) {
				if (dirstate.equals("haut")) {
					pos = casehaut(this.currentboat.get(0));
					if (!game.Grille.contains(pos)) {
						this.dirstate = "droite";
						pos = tir(game);
					}
				} else if (dirstate.equals("droite")) {
					pos = casedroite(this.currentboat.get(0));
					if (!game.Grille.contains(pos)) {
						this.dirstate = "bas";
						pos = tir(game);
					}
				} else if (dirstate.equals("bas")) {
					pos = casebas(this.currentboat.get(0));
					if (!game.Grille.contains(pos)) {
						this.dirstate = "gauche";
						pos = tir(game);
					}
				} else {
					pos = casegauche(this.currentboat.get(0));
					if (!game.Grille.contains(pos)) {
						this.dirstate = "haut";
						pos = tir(game);
					}
				}
			} else {
				pos = hasardtir(game);
			}
		}
		return pos;
	}

	public String calcul() {
		String pos = "";
		String firstpos = this.currentboat.get(0);
		String secondpos = this.currentboat.get(this.currentboat.size() - 1);
		int dir = direction();
		int choix = (int) (Math.random());
		if (dir == 0) {
			if (choix == 0) {
				pos = casebas(firstpos);
			} else {
				pos = casehaut(secondpos);
			}
		} else {
			if (choix == 0) {
				pos = casegauche(firstpos);
			} else {
				pos = casedroite(secondpos);
			}
		}
		return pos;
	}



	public String casehaut(String a) {
		String pos = "";
		String start = a;
		String letter = (String) start.substring(0, 1);
		String number = (String) start.substring(1, 2);
		if (letter.equals("A")) {
			return ("Z0");
		} else {
			pos = BATTLESHIP.convinttostring(BATTLESHIP.convstringtoint(letter) + 1) + number;
			return pos;
		}

	}

	public String casedroite(String a) {
		String pos = "";
		String start = a;
		String letter = (String) start.substring(0, 1);
		String number = (String) start.substring(1, 2);
		if (number.equals("9")) {
			return ("Z0");
		} else {
			pos = letter + BATTLESHIP.convinttostring(BATTLESHIP.convstringtoint(number) + 1);
			return pos;
		}
	}

	public String casebas(String a) {
		String pos = "";
		String start = a;
		String letter = (String) start.substring(0, 1);
		String number = (String) start.substring(1, 2);
		if (letter.equals("J")) {
			return ("Z0");
		} else {
			pos = BATTLESHIP.convinttostring(BATTLESHIP.convstringtoint(letter) - 1) + number;
			return pos;
		}
	}

	public String casegauche(String a) {
		String pos = "";
		String start = a;
		String letter = (String) start.substring(0, 1);
		String number = (String) start.substring(1, 2);
		if (number.equals("1")) {
			return ("Z0");
		} else {
			pos = letter + BATTLESHIP.convinttostring(BATTLESHIP.convstringtoint(number) - 1);
			return pos;
		}
	}

	public int direction() {
		int direction = 0;
		String firstletter = (String) this.currentboat.get(0).substring(0, 1);
		String secondletter = (String) this.currentboat.get(1).substring(0, 1);
		if (firstletter.equals(secondletter)) {
			direction = 1;
		}
		return direction;
	}

}
