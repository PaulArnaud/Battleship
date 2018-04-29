import java.util.ArrayList;

public class Computer extends Player {
	// attaque
	public ArrayList<String> currentboat;
	public String state = "chasse";// chasse ou tir
	public String dirstate = "top"; // haut,droite,bas,gauche
	public String choice = "before";

	public Computer() {
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
	public static ArrayList<String> hasardcontruc(Game n, String name) {
		String finalpos = null;
		ArrayList<String> loca = new ArrayList<String>();
		if (name.equals("carrier")) {
			int i = (int) (Math.random() * 36);
			String a = n.Grille.get(i);
			finalpos = Config.compfonc(a, 5);
			loca = Config.locacalcul(a, finalpos);
			return loca;
		} else if (name.equals("battleship")) {
			int i = (int) (Math.random() * 49);
			String a = n.Grille.get(i);
			finalpos = Config.compfonc(a, 4);
			loca = Config.locacalcul(a, finalpos);
			return loca;
		} else if (name.equals("cruiser")) {
			int i = (int) (Math.random() * 64);
			String a = n.Grille.get(i);
			finalpos = Config.compfonc(a, 3);
			loca = Config.locacalcul(a, finalpos);
			return loca;
		} else if (name.equals("submarine")) {
			int i = (int) (Math.random() * 64);
			String a = n.Grille.get(i);
			finalpos = Config.compfonc(a, 3);
			loca = Config.locacalcul(a, finalpos);
			return loca;
		} else {
			int i = (int) (Math.random() * 81);
			String a = n.Grille.get(i);
			finalpos = Config.compfonc(a, 2);
			loca = Config.locacalcul(a, finalpos);
			return loca;
		}
	}
	// fonction principal du tir de l'ordinateur chaque cas expliquer en détails à l'intérieur
	public String shoot(Game game) {
		String pos = "";
		if (this.state.equals("chasse") || this.currentboat.size()  == 0) {
			// si on est dans le mode chasse ou que l'on a pas encore trouvé de bateau on tire au hasard
			pos = hasardshoot(game);
		} else {
			if (this.currentboat.size() > 1 && this.currentboat.size() < 5) {
				//partie principal, ici c'est le tir intelligent, expliquer dans la fonction calcul !
				pos = calcul(game);
			} else if (this.currentboat.size() < 2) {
				//ici on a trouvé un bateau mais on sait pas encore sa direction donc on cherche en haut,puis à droite,puis en bas, puis à gauche
				if (dirstate.equals("top")) {
					pos = topcase(this.currentboat.get(0));
					this.dirstate = "right";
					if (!game.Grille.contains(pos) || (super.myShoots.contains(pos))) {
						pos = shoot(game);
					}
				} else if (dirstate.equals("right")) {
					pos = rigthcase(this.currentboat.get(0));
					this.dirstate = "bottom";
					if (!game.Grille.contains(pos)|| (super.myShoots.contains(pos))) {
						pos = shoot(game);
					}
				} else if (dirstate.equals("bottom")) {
					pos = bottomcase(this.currentboat.get(0));
					this.dirstate = "left";
					if (!game.Grille.contains(pos)|| (super.myShoots.contains(pos))) {
						pos = shoot(game);
					}
				} else {
					pos = leftcase(this.currentboat.get(0));
					this.dirstate = "top";
					if (!game.Grille.contains(pos)||(super.myShoots.contains(pos))) {
						pos = shoot(game);
					}
				}
			} else {
				pos = recovery(game);
			}
		}return pos;
	}

	public String calcul(Game game) {
		// fonction trés trés importante : quand on a au moins deux positions dans currentboat, on a la direction, on tire donc soit avant soit aprés
		String pos = "";
		tricurrentboat();
		String firstpos = this.currentboat.get(0);
		String secondpos = this.currentboat.get(this.currentboat.size() - 1);
		int dir = direction();
		if (dir == 0) {
			if (choice.equals("before")) {
				pos = topcase(firstpos);
				if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)) {
					pos = bottomcase(secondpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = recovery(game);
					}
				}
				this.choice = "after";
			} else {
				pos = bottomcase(secondpos);
				if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)) {
					pos = topcase(firstpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = recovery(game);
					}
				}
				this.choice = "before";
			}
		} else {
			if (choice.equals("before")) {
				pos = leftcase(firstpos);
				if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)) {
					pos = rigthcase(secondpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = recovery(game);
					}
				}
				this.choice = "after";
			} else {
				pos = rigthcase(secondpos);
				if (super.myShoots.contains(pos) || !game.Grille.contains(pos)) {
					pos = leftcase(firstpos);
					if (super.myShoots.contains(pos)|| !game.Grille.contains(pos)){
						pos = recovery(game);
					}
				}
				this.choice = "before";
			}
		}
		return pos;
	}

	public String topcase(String a) {
		// retourne la position au dessus de la position entrée en paramétre
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (letter.equals(Config.limittop)) {
			return ("hors limite");
		} else {
			pos = Config.convinttostring(Config.convstringtoint(letter) - 1) + number;
			return pos;
		}

	}

	public String rigthcase(String a) {
		// retourne la position é droite de la position entrée en paramétre
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (number.equals(Config.limitright)) {
			return ("hors limite");
		} else {
			pos = letter + String.valueOf(Config.convstringtoint(number) + 1);
			return pos;
		}
	}

	public String bottomcase(String a) {
		// retourne la position en dessous de la position entrée en paramétre
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (letter.equals(Config.limitbottom)) {
			return ("hors limite");
		} else {
			pos = Config.convinttostring(Config.convstringtoint(letter) + 1) + number;
			return pos;
		}
	}

	public String leftcase(String a) {
		// retourne la position é gauche de la position entrée en paramétre
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (number.equals(Config.limitleft)) {
			return ("hors limite");
		} else {
			pos = letter + String.valueOf(Config.convstringtoint(number) - 1);
			return pos;
		}
	}

	public int direction() {
		// renvoit la direction du bateau surlequel on est en train de tirer : 1 si horizontal, 0 si vertical.
		int direction = 0;
		String firstletter = Config.getLetter(this.currentboat.get(0));
		String secondletter = Config.getLetter(this.currentboat.get(1));
		if (firstletter.equals(secondletter)) {
			direction = 1;
		}
		return direction;
	}

	public void tricurrentboat() {
		// fonction de tri de currentboat pour pouvoir tirer avant ou aprés ( avant c'est par rapport é la premiére position aprés ...etc)
		int dir = direction();
		ArrayList<String> battri = new ArrayList<String>();
		if (dir == 1) {// Méme ligne
			while (!this.currentboat.isEmpty()) {
				String min = this.currentboat.get(0);
				for (int i = 0; i < this.currentboat.size(); i = i + 1) {
					if (Config.getNumber(min) > Config.getNumber(this.currentboat.get(i))) {
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
					if (Config.convstringtoint(Config.getLetter(min)) > Config.convstringtoint(Config.getLetter(this.currentboat.get(i)))) {
						min = this.currentboat.get(i);
					}
				}
				battri.add(min);
				this.currentboat.remove(min);
			}
			this.currentboat = battri;
		}
	}
	
	public String recovery(Game game) {
		// lorsqu'on ne peut plus tirer ni avant ni aprés on se remet en position initiale soit mode chasse,etc)
		this.currentboat = new ArrayList<String>();
		this.state = "chasse";
		this.dirstate ="top";
		this.choice ="before";
		return hasardshoot(game);
	}
	
	public String hasardshoot(Game game) {
		// renvoit une position au hasard qui n'a pas déjé été essayée
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
