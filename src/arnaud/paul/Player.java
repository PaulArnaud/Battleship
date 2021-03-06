package arnaud.paul;

import java.util.ArrayList;

public class Player {
	public ArrayList<Ship> battlecrew = new ArrayList<Ship>();
	public ArrayList<Ship> losses = new ArrayList<Ship>();
	public ArrayList<String> myshoot = new ArrayList<String>();
	public String playername;
	public int score;

	public String shoot() {
		System.out.println(getPlayername() + " ,choisissez une position à attaquer(exemple:" + Config.limleft
				+ Config.limbottom + "," + Config.limright + Config.limbottom + ")");
		System.out.println("Coordonnée du tir :");
		String shoot = Config.readmsg();
		while (hasAlreadyShot(shoot) || (!Config.isCorrect(shoot))) {
			System.out.println(getPlayername()
					+ " ,choisissez une nouvelle position, vous avez d�j� attaqu� ici ou la position n'est pas valide ! ");
			System.out.println("Coordonnée du tir :");
			shoot = Config.readmsg();

		}
		return shoot;
	}

	public void setPlayername(String a) {
		this.playername = a;
	}

	public String getPlayername() {
		return playername;
	}

	public ArrayList<Ship> getBattlecrew() {
		return battlecrew;
	}

	public ArrayList<Ship> getLosses() {
		return losses;
	}

	public ArrayList<String> getMyShoots() {
		return myshoot;
	}

	public void setMyShoots(ArrayList<String> myshoots) {
		myshoot = myshoots;
	}

	public boolean isDown() {
		return battlecrew.isEmpty();
	}

	public boolean hasAlreadyShot(String shoot) {
		return this.myshoot.contains(shoot);
	}

	// fonction qui renvoie le nb de bateau restant du joueur
	public int length() {
		return battlecrew.size();
	}

	// fonction qui supprime un bateau de la liste des bateaux du joueur
	public void removeShip(Ship sp) {
		losses.add(sp);
		battlecrew.remove(sp);
	}

	public boolean isInCrew(String a) {
		return isInLosses(a) || isInBattlecrew(a);
	}

	public boolean isInBattlecrew(String a) {
		boolean res = false;
		for (int j = 0; j < this.battlecrew.size(); j++) {
			if (this.battlecrew.get(j).isHit(a)) {
				res = true;
			}
		}
		return res;
	}

	public boolean isInLosses(String a) {
		boolean res = false;
		for (int j = 0; j < this.losses.size(); j++) {
			if (this.losses.get(j).isHit(a)) {
				res = true;
			}
		}
		return res;
	}

	public void reset() {
		this.battlecrew = new ArrayList<Ship>();
		this.losses = new ArrayList<Ship>();
		this.myshoot = new ArrayList<String>();
	}

	public void implementboat(String nombateau) {
		// mise en place des bateaux pour le joueur, elle vérifie si les choix faits par
		// le joueur sont possibles
		System.out.println(this.getPlayername() + " , choississez des coordonnées pour votre " + nombateau
				+ Config.boatsize(nombateau));
		boolean verif = false;
		while (!verif) {
			String coordA = askcoord("d�but");
			String coordB = askcoord("fin");
			ArrayList<String> test = Config.locacalcul(coordA, coordB);
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
				verif = buildboat(nombateau, coordA, coordB, test);
			}
		}
	}

	public boolean buildboat(String nombateau, String coordA, String coordB, ArrayList<String> test) {
		boolean res = false;
		if (nombateau.equals("carrier")) {
			Ship carrier = new Ship(coordA, coordB, test, "carrier");
			if (carrier.etat.equals("valide")) {
				res = true;
				this.battlecrew.add(carrier);
			}
		} else if (nombateau.equals("battleship")) {
			Ship battleship = new Ship(coordA, coordB, test, "battleship");
			if (battleship.etat.equals("valide")) {
				res = true;
				this.battlecrew.add(battleship);
			}
		} else if (nombateau.equals("cruiser")) {
			Ship cruiser = new Ship(coordA, coordB, test, "cruiser");
			if (cruiser.etat.equals("valide")) {
				res = true;
				this.battlecrew.add(cruiser);
			}
		} else if (nombateau.equals("destroyer")) {
			Ship destroyer = new Ship(coordA, coordB, test, "destroyer");
			if (destroyer.etat.equals("valide")) {
				res = true;
				this.battlecrew.add(destroyer);
			}
		} else if (nombateau.equals("submarine")) {
			Ship submarine = new Ship(coordA, coordB, test, "submarine");
			if (submarine.etat.equals("valide")) {
				res = true;
				this.battlecrew.add(submarine);
			}
		}
		return res;
	}

	public void updateshoot(String shoot) {
		myshoot.add(shoot);
	}

	public void doingthings() {

	}

	public void showMyCrew() {
		String res = "";
		String coord = "";
		System.out.println("  A B C D E F G H I J");
		for (int i = Config.limtoptoint; i <= Config.limbottomtoint; i++) {
			res = String.valueOf(i);
			for (int j = Config.convstringtoint(Config.limleft); j <= Config.convstringtoint(Config.limright); j++) {
				coord = Config.convinttostring(j) + String.valueOf(i);
				if (isInBattlecrew(coord)) {
					res = res + " " + "b";

				} else {
					res = res + " " + "~";
				}
			}
			System.out.println(res);
		}

	}

	public String askcoord(String var) {
		System.out.println("La grille est composée de " + Config.limleft + Config.limtop + ", .... ," + Config.limright
				+ Config.limbottom);
		System.out.println("Coordonnée de " + var);
		String coordA = Config.readmsg();
		if (Config.isCorrect(coordA)) {
			return coordA;
		} else {
			return askcoord(var);
		}
	}
}