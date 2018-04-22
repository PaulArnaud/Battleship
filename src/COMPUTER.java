import java.util.ArrayList;

public class COMPUTER extends PLAYER {
	// attaque
	public ArrayList<String> currentboat;
	public String state = "chasse";// chasse ou tir
	public String dirstate = "haut"; // haut,droite,bas,gauche
	public String choix = "avant";

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
	//fonction qui renvoit la construction hasardeuse d'un bateau 
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
	// fonction principal du tir de l'ordinateur chaque cas expliquer en d�tails � l'int�rieur
	public String tir(GAME game) {
		String pos = "";
		if (this.state.equals("chasse") || this.currentboat.size()  == 0) {
			// si on est dans le mode chasse ou que l'on a pas encore trouv� de bateau on tire au hasard
			pos = hasardtir(game);
		} else {
			if (this.currentboat.size() > 1 && this.currentboat.size() < 5) {
				//partie principal, ici c'est le tir intelligent, expliquer dans la fonction calcul !
				pos = calcul(game);
			} else if (this.currentboat.size() < 2) {
				//ici on a trouv� un bateau mais on sait pas encore sa direction donc on cherche en haut,puis � droite,puis en bas, puis � gauche
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
				pos = r��tablissement(game);
			}
		}return pos;
	}

	public String calcul(GAME game) {
		// fonction tr�s tr�s importante : quand on a au moins deux positions dans currentboat, on a la direction, on tire donc soit avant soit apr�s
		String pos = "";
		tricurrentboat();
		String firstpos = this.currentboat.get(0);
		String secondpos = this.currentboat.get(this.currentboat.size() - 1);
		int dir = direction();
		if (dir == 0) {
			if (choix.equals("avant")) {
				pos = casehaut(firstpos);
				if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)) {
					pos = casebas(secondpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = r��tablissement(game);
					}
				}
				this.choix = "apres";
			} else {
				pos = casebas(secondpos);
				if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)) {
					pos = casehaut(firstpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = r��tablissement(game);
					}
				}
				this.choix = "avant";
			}
		} else {
			if (choix.equals("avant")) {
				pos = casegauche(firstpos);
				if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)) {
					pos = casedroite(secondpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = r��tablissement(game);
					}
				}
				this.choix = "apres";
			} else {
				pos = casedroite(secondpos);
				if (super.myShoots.contains(pos) || !game.Grille.contains(pos)) {
					pos = casegauche(firstpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = r��tablissement(game);
					}
				}
				this.choix = "avant";
			}
		}
		return pos;
	}

	public String casehaut(String a) {
		// retourne la position au dessus de la position entr�e en param�tre
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
		// retourne la position � droite de la position entr�e en param�tre
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
		// retourne la position en dessous de la position entr�e en param�tre
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
		// retourne la position � gauche de la position entr�e en param�tre
		String pos = "";
		String start = a;
		String letter = (String) start.substring(0, 1);
		String number = (String) start.substring(1, 2);
		if (number.equals("0")) {
			return ("Z0");
		} else {
			pos = letter + String.valueOf(BATTLESHIP.convstringtoint(number) - 1);
			return pos;
		}
	}

	public int direction() {
		// renvoit la direction du bateau surlequel on est en train de tirer : 1 si horizontal, 0 si vertical.
		int direction = 0;
		String firstletter = (String) this.currentboat.get(0).substring(0, 1);
		String secondletter = (String) this.currentboat.get(1).substring(0, 1);
		if (firstletter.equals(secondletter)) {
			direction = 1;
		}
		return direction;
	}

	public void tricurrentboat() {
		// fonction de tri de currentboat pour pouvoir tirer avant ou apr�s ( avant c'est par rapport � la premi�re position apr�s ...etc)
		int dir = direction();
		ArrayList<String> battri = new ArrayList<String>();
		if (dir == 1) {// M�me ligne
			while (!this.currentboat.isEmpty()) {
				String min = this.currentboat.get(0);
				for (int i = 0; i < this.currentboat.size(); i = i + 1) {
					if (BATTLESHIP.convstringtoint(min.substring(1, 2)) > BATTLESHIP
							.convstringtoint(this.currentboat.get(i).substring(1, 2))) {
						min = this.currentboat.get(i);
					}
				}
				battri.add(min);
				this.currentboat.remove(min);
			}
			this.currentboat = battri;
		} else {
			while (!this.currentboat.isEmpty()) {
				String min = this.currentboat.get(0);
				for (int i = 0; i < this.currentboat.size(); i = i + 1) {
					if (BATTLESHIP.convstringtoint(min.substring(0, 1)) > BATTLESHIP
							.convstringtoint(this.currentboat.get(i).substring(0, 1))) {
						min = this.currentboat.get(i);
					}
				}
				battri.add(min);
				this.currentboat.remove(min);
			}
			this.currentboat = battri;
		}
	}
	
	public String r��tablissement(GAME game) {
		// lorsqu'on ne peut plus tirer ni avant ni apr�s on se remet en position initiale soit mode chasse,etc)
		this.currentboat = new ArrayList<String>();
		this.state = "chasse";
		this.dirstate ="haut";
		this.choix ="avant";
		return hasardtir(game);
	}
	
	public String hasardtir(GAME game) {
		// renvoit une position au hasard qui n'a pas d�j� �t� essay�e
		String pos = "";
		int i = (int) (Math.random() * 100);
		pos = game.Grille.get(i);
		while (super.myShoots.contains(pos)) {
			i = (int) (Math.random() * 100);
			pos = game.Grille.get(i);
		}
		return pos;
	}
}
