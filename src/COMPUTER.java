import java.util.ArrayList;

public class COMPUTER extends PLAYER {
	// attaque
	public ArrayList<String> currentboat;
	public String state = "chasse";// chasse ou tir
	public String dirstate = "haut"; // haut,droite,bas,gauche
	public String choix ="avant";
	public COMPUTER() {
		this.currentboat = new ArrayList<String>();
	}
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
		if (this.state.equals("chasse") || this.currentboat.size()  == 0) {
			pos = hasardtir(game);
		} else {
			if (this.currentboat.size() > 1 && this.currentboat.size() < 6) {
				pos = calcul(game);
				while ((game.OppositePlayer.hasAlreadyShot(pos)) || (!game.Grille.contains(pos))) {
					pos = calcul(game);
				}
				System.out.println(pos + " = position");
				System.out.println("tir intelligent");
			} else if (this.currentboat.size() < 2) {
				if (dirstate.equals("haut")) {
					pos = casehaut(this.currentboat.get(0));
					this.dirstate = "droite";
					if (!game.Grille.contains(pos) || (super.myShoots.contains(pos))) {
						pos = tir(game);
					}
				} else if (dirstate.equals("droite")) {
					pos = casedroite(this.currentboat.get(0));
					this.dirstate = "bas";
					if (!game.Grille.contains(pos)|| (super.myShoots.contains(pos))) {
						pos = tir(game);
					}
				} else if (dirstate.equals("bas")) {
					pos = casebas(this.currentboat.get(0));
					this.dirstate = "gauche";
					if (!game.Grille.contains(pos)|| (super.myShoots.contains(pos))) {
						pos = tir(game);
					}
				} else {
					pos = casegauche(this.currentboat.get(0));
					this.dirstate = "haut";
					if (!game.Grille.contains(pos)||(super.myShoots.contains(pos))) {
						pos = tir(game);
					}
				}
			} else {
				pos = hasardtir(game);
			}
		}
		return pos;
	}

	public String calcul(GAME game) {
		String pos = "";
		String firstpos = this.currentboat.get(0);
		String secondpos = this.currentboat.get(this.currentboat.size() - 1);
		int dir = direction();
		if (dir == 0) {
			if (choix.equals("avant")) {
				System.out.println("étape1");
				pos = casebas(firstpos);
				this.choix = "apres";
			} else {
				System.out.println("étape2");
				pos = casehaut(secondpos);
				this.choix = "avant";
			}
		} else {
			if (choix.equals("avant")) {
				System.out.println("étape3");
				pos = casegauche(firstpos);
				this.choix = "apres";
			} else {
				System.out.println("étape4");
				pos = casedroite(secondpos);
				this.choix = "avant";
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
			pos = BATTLESHIP.convinttostring(BATTLESHIP.convstringtoint(letter) - 1) + number;
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
			pos = letter + String.valueOf(BATTLESHIP.convstringtoint(number) + 1);
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
			pos = BATTLESHIP.convinttostring(BATTLESHIP.convstringtoint(letter) + 1) + number;
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
			pos = letter + String.valueOf(BATTLESHIP.convstringtoint(number) - 1);
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
