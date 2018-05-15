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

	// fonction qui renvoit la construction hasardeuse d'un bateau
	public ArrayList<String> hasardcontruc(String name) {
		String finalpos = null;
		ArrayList<String> loca = new ArrayList<String>();
		String a = hasard();
		if (name.equals("carrier")) {
			finalpos = Config.compfonc(a, 5);
			if (!Config.isCorrect(finalpos)) {
				return hasardcontruc(name);
			} else {
				loca = Config.locacalcul(a, finalpos);
				return loca;
			}
		} else if (name.equals("battleship")) {
			finalpos = Config.compfonc(a, 4);
			if (!Config.isCorrect(finalpos)) {
				return hasardcontruc(name);
			} else {
				loca = Config.locacalcul(a, finalpos);
				return loca;
			}
		} else if (name.equals("cruiser")) {
			finalpos = Config.compfonc(a, 3);
			if (!Config.isCorrect(finalpos)) {
				return hasardcontruc(name);
			} else {
				loca = Config.locacalcul(a, finalpos);
				return loca;
			}
		} else if (name.equals("submarine")) {
			finalpos = Config.compfonc(a, 3);
			if (!Config.isCorrect(finalpos)) {
				return hasardcontruc(name);
			} else {
				loca = Config.locacalcul(a, finalpos);
				return loca;
			}
		} else {
			finalpos = Config.compfonc(a, 2);
			if (!Config.isCorrect(finalpos)) {
				return hasardcontruc(name);
			} else {
				loca = Config.locacalcul(a, finalpos);
				return loca;
			}
		}
	}

	// fonction principal du tir de l'ordinateur chaque cas expliquer en détails à
	// l'intérieur
	public String shoot() {
		String pos = "";
		if (this.state.equals("chasse") || this.currentboat.size() == 0) {
			// si on est dans le mode chasse ou que l'on a pas encore trouvé de bateau on
			// tire au hasard
			pos = hasard();
		} else {
			if (this.currentboat.size() > 1 && this.currentboat.size() < 5) {
				// partie principal, ici c'est le tir intelligent, expliquer dans la fonction
				// calcul !
				pos = calcul();
			} else if (this.currentboat.size() < 2) {
				// ici on a trouvé un bateau mais on sait pas encore sa direction donc on
				// cherche en haut,puis à droite,puis en bas, puis à gauche
				if (dirstate.equals("top")) {
					pos = topcase(this.currentboat.get(0));
					this.dirstate = "right";
					if (!Config.isCorrect(pos) || (super.myShoots.contains(pos))) {
						pos = shoot();
					}
				} else if (dirstate.equals("right")) {
					pos = rigthcase(this.currentboat.get(0));
					this.dirstate = "bottom";
					if (!Config.isCorrect(pos) || (super.myShoots.contains(pos))) {
						pos = shoot();
					}
				} else if (dirstate.equals("bottom")) {
					pos = bottomcase(this.currentboat.get(0));
					this.dirstate = "left";
					if (!Config.isCorrect(pos) || (super.myShoots.contains(pos))) {
						pos = shoot();
					}
				} else {
					pos = leftcase(this.currentboat.get(0));
					this.dirstate = "top";
					if (!Config.isCorrect(pos) || (super.myShoots.contains(pos))) {
						pos = shoot();
					}
				}
			} else {
				pos = recovery();
			}
		}
		return pos;
	}

	public String calcul() {
		// fonction trés trés importante : quand on a au moins deux positions dans
		// currentboat, on a la direction, on tire donc soit avant soit aprés
		String pos = "";
		tricurrentboat();
		String firstpos = this.currentboat.get(0);
		String secondpos = this.currentboat.get(this.currentboat.size() - 1);
		int dir = direction();
		if (dir == 1) {
			if (choice.equals("before")) {
				pos = topcase(firstpos);
				if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
					pos = bottomcase(secondpos);
					if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
						pos = recovery();
					}
				}
				this.choice = "after";
			} else {
				pos = bottomcase(secondpos);
				if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
					pos = topcase(firstpos);
					if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
						pos = recovery();
					}
				}
				this.choice = "before";
			}
		} else {
			if (choice.equals("before")) {
				pos = leftcase(firstpos);
				if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
					pos = rigthcase(secondpos);
					if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
						pos = recovery();
					}
				}
				this.choice = "after";
			} else {
				pos = rigthcase(secondpos);
				if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
					pos = leftcase(firstpos);
					if (super.myShoots.contains(pos) || !Config.isCorrect(pos)) {
						pos = recovery();
					}
				}
				this.choice = "before";
			}
		}
		return pos;
	}

	public String leftcase(String a) {
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (letter.equals(Config.limleft)) {
			return ("hors limite");
		} else {
			pos = Config.convinttostring(Config.convstringtoint(letter) - 1) + number;
			return pos;
		}

	}

	public String bottomcase(String a) {
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (number.equals(Config.limbottom)) {
			return ("hors limite");
		} else {
			pos = letter + String.valueOf(Config.convstringtoint(number) + 1);
			return pos;
		}
	}

	public String rigthcase(String a) {
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (letter.equals(Config.limright)) {
			return ("hors limite");
		} else {
			pos = Config.convinttostring(Config.convstringtoint(letter) + 1) + number;
			return pos;
		}
	}

	public String topcase(String a) {
		String pos = "";
		String letter = Config.getLetter(a);
		String number = String.valueOf(Config.getNumber(a));
		if (number.equals(Config.limtop)) {
			return ("hors limite");
		} else {
			pos = letter + String.valueOf(Config.convstringtoint(number) - 1);
			return pos;
		}
	}

	public int direction() {
		// renvoit la direction du bateau surlequel on est en train de tirer : 1 si
		// horizontal, 0 si vertical.
		int direction = 0;
		String firstletter = Config.getLetter(this.currentboat.get(0));
		String secondletter = Config.getLetter(this.currentboat.get(1));
		if (firstletter.equals(secondletter)) {
			direction = 1;
		}
		return direction;
	}

	public void tricurrentboat() {
		// fonction de tri de currentboat pour pouvoir tirer avant ou aprés ( avant
		// c'est par rapport é la premiére position aprés ...etc)
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
					if (Config.convstringtoint(Config.getLetter(min)) > Config
							.convstringtoint(Config.getLetter(this.currentboat.get(i)))) {
						min = this.currentboat.get(i);
					}
				}
				battri.add(min);
				this.currentboat.remove(min);
			}
			this.currentboat = battri;
		}
	}

	public String recovery() {
		// lorsqu'on ne peut plus tirer ni avant ni aprés on se remet en position
		// initiale soit mode chasse,etc)
		this.currentboat = new ArrayList<String>();
		this.state = "chasse";
		this.dirstate = "top";
		this.choice = "before";
		return hasard();
	}

	public String hasard() {
		// renvoit une position au hasard qui n'a pas déjé été essayée
		String pos = "";
		int i = (int) (Math.random() * (Config.limrigthtoint - Config.limlefttoint + 1)) + Config.limlefttoint;
		int j = (int) (Math.random() * (Config.limlbottomtoint - Config.limtoptoint + 1)) + Config.limtoptoint;
		String letter = Config.convinttostring(i);
		String number = String.valueOf(j);
		pos = letter + number;
		while (super.myShoots.contains(pos)) {
			pos = hasard();
		}
		return pos;
	}

	public String shootlvlmin() {
		String pos = "";
		int i = (int) (Math.random() * (Config.limrigthtoint - Config.limlefttoint + 1)) + Config.limlefttoint;
		int j = (int) (Math.random() * (Config.limlbottomtoint - Config.limtoptoint + 1)) + Config.limtoptoint;
		String letter = Config.convinttostring(i);
		String number = String.valueOf(j);
		pos = letter + number;
		return pos;
	}

}
