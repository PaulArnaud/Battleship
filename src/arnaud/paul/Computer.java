package arnaud.paul;

import java.util.ArrayList;

public class Computer extends Player {
	// attaque
	public ArrayList<String> currentboat = new ArrayList<String>();
	public String state = "chasse";// chasse ou tir
	public String dirstate = "top"; // haut,droite,bas,gauche
	public String choice = "before";
	public int lvl;

	public Computer(int a) {
		super();
		this.lvl = a;
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

	public void doingthings() {
		setState("chasse");
		setDirstate("top");
		setCurrentboat(new ArrayList<String>());
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
		if (this.lvl == 3) {
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
					} else if (dirstate.equals("left")) {
						pos = leftcase(this.currentboat.get(0));
						this.dirstate = "top";
						if (!Config.isCorrect(pos) || (super.myShoots.contains(pos))) {
							pos = recovery();
						}
					}
				} else {
					pos = recovery();
				}
			}
		}else if ( this.lvl == 2) {
			pos = hasard();
		}
		else {
			pos = shootlvlmin(); 
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
		int direction = 0;
		String firstletter = Config.getLetter(this.currentboat.get(0));
		String secondletter = Config.getLetter(this.currentboat.get(1));
		if (firstletter.equals(secondletter)) {
			direction = 1;
		}
		return direction;
	}

	public void tricurrentboat() {
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
		this.currentboat = new ArrayList<String>();
		this.state = "chasse";
		this.dirstate = "top";
		this.choice = "before";
		return hasard();
	}

	public String hasard() {
		String pos = "";
		int i = (int) (Math.random() * (Config.limrigthtoint - Config.limlefttoint + 1)) + Config.limlefttoint;
		int j = (int) (Math.random() * (Config.limbottomtoint - Config.limtoptoint + 1)) + Config.limtoptoint;
		String letter = Config.convinttostring(i);
		String number = String.valueOf(j);
		pos = letter + number;
		while (super.myShoots.contains(pos)) {
			pos = hasard();
		}
		return pos;
	}

	public void implementboat(String nombateau) {
		boolean verif = false;
		while (!verif) {
			ArrayList<String> test = hasardcontruc(nombateau);
			int resu = 0;
			for (String a : test) {
				int i = 0;
				while ((resu < 1) && (i < (this.battlecrew.size()))) {
					Ship ship = this.battlecrew.get(i);
					if (ship.localisation.contains(a)) {
						resu = +1;
					}
					i++;
				}
			}
			if (resu == 0) {
				verif = super.buildboat(nombateau, test.get(0), test.get(test.size() - 1), test);
			}
		}
	}

	public void reset() {
		super.reset();
		this.currentboat = new ArrayList<String>();
		this.state = "chasse";
		this.dirstate = "top";
		this.choice = "before";
	}

	public void updateshoot(String shoot) {
		super.updateshoot(shoot);
		setState("tir");
		getCurrentboat().add(shoot);
	}

	public String shootlvlmin() {
		String pos = "";
		int i = (int) (Math.random() * (Config.limrigthtoint - Config.limlefttoint + 1)) + Config.limlefttoint;
		int j = (int) (Math.random() * (Config.limbottomtoint - Config.limtoptoint + 1)) + Config.limtoptoint;
		String letter = Config.convinttostring(i);
		String number = String.valueOf(j);
		pos = letter + number;
		return pos;
	}
}
