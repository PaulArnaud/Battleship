import java.util.ArrayList;
import java.util.Scanner;

public class BATTLESHIP {

	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		// Création d'une nouvelle partie
		GAME newgame = new GAME();
		newgame.setGrille();

		// Création des 2 joueurs
		PLAYER player1 = new PLAYER();
		COMPUTER player2 = new COMPUTER();

		// Mise en place de joueur courant et joueur opposé
		newgame.setActivePlayer(player1);
		newgame.setOppositePlayer(player2);

		// Création de la variable de tir
		String shoot;

		// Création de la variable battlecrew
		ArrayList<SHIP> battlecrew;
		
		System.out.println("Mise en place des bateaux de l'ordinateur");
		misenplaceordi(newgame, "carier");
		System.out.println("Carier Done");
		misenplaceordi(newgame, "battleship");
		System.out.println("Battleship Done");
		misenplaceordi(newgame, "cruiser");
		System.out.println("Cruiser Done");
		misenplaceordi(newgame, "submarine");
		System.out.println("Submarine Done");
		misenplaceordi(newgame, "destroyer");
		System.out.println("Destroyer Done");
		System.out.println("Fin de la mise en place des bateaux de l'ordinateur");

		System.out.println("Mise en place des bateaux du joueur");
		System.out.println("la grille est composée de A0 , .... , A9 jusque J0,....,J9");
		misenplace(newgame, "carier");
		misenplace(newgame, "battleship");
		misenplace(newgame, "cruiser");
		misenplace(newgame, "submarine");
		misenplace(newgame, "destroyer");

		// Début de la partie
		while (!newgame.IsOver()) {
			// demande des coordonnées du tir
			System.out.println(newgame.ActivetoString() + " Choisissez une position à attaquer(exemple: A1, J10)");
			System.out.println("Coordonnée du tir :");
			shoot = reader.next();
			// si le joueur tir sur une case déjà essayée on lui redemande des coordonnées
			while (newgame.ActivePlayer.hasAlreadyShot(shoot) || (!newgame.Grille.contains(shoot))) {
				System.out.println(newgame.ActivetoString()
						+ " Choisissez une nouvelle position, vous avez déjà attaqué ici(exemple: A0, J9) ");
				System.out.println("Coordonnée du tir :");
				shoot = reader.next();
			}
			newgame.ActivePlayer.myShoots.add(shoot);
			System.out.println(newgame.ActivePlayer.myShoots);
			// on instancie le crew du joueur adverse pour savoir si on touche
			battlecrew = newgame.OppositePlayer.getBattlecrew();
			int i = 0;
			String res = "A l’eau";
			SHIP ship;
			// on boucle tant qu'il y a des bateaux dans la liste et que l'on a pas tout
			// parcouru
			// et qu'on ne touche pas
			while ((i < (newgame.OppositePlayer.length())) && (res.equals("A l’eau"))) {
				ship = battlecrew.get(i);
				// System.out.println(ship);
				// si c'est touché
				if (ship.isHit(shoot)) {
					// on regarde si c'est coulé
					if (ship.isDestroyed()) {
						res = "Touché Coulé";
						newgame.OppositePlayer.removeShip(ship);
					} else {
						res = "Touché";
					}
				}
				i = i + 1;
			}
			System.out.println(res);
			if (!newgame.IsOver()) {
				shoot = player2.tir(newgame);
				newgame.OppositePlayer.myShoots.add(shoot);
				System.out.println("l'ennemi a frappé en " + shoot);
				System.out.println(player2.currentboat);
				battlecrew = newgame.ActivePlayer.getBattlecrew();
				i = 0;
				res = "A l’eau";
				while ((i < (newgame.ActivePlayer.length())) && (res.equals("A l’eau"))) {
					ship = battlecrew.get(i);
					// System.out.println(ship);
					// si c'est touché
					if (ship.isHit(shoot)) {
						player2.setState("tir");
						// on regarde si c'est coulé
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							player2.setCurrentboat(new ArrayList<String>());
							newgame.ActivePlayer.removeShip(ship);
							player2.setState("chasse");
							player2.setDirstate("haut");
						} else {
							res = "Touché";
							player2.getCurrentboat().add(shoot);
						}
					}
					i = i + 1;
				}
				System.out.println(res);
				// newgame.changePlayer();
			}
		}

		// on regarde qui a gagné et qui a perdu
		if (newgame.ActivePlayer.length() == 0) {
			System.out.println(newgame.OppositetoString() + "a gagné");
		} else {
			System.out.println(newgame.ActivetoString() + "a gagné");
		}
	}

	// Conversion d'un int vers un string
	public static String convinttostring(int a) {
		if (a == 0) {
			return "A";
		} else if (a == 1) {
			return "B";
		} else if (a == 2) {
			return "C";
		} else if (a == 3) {
			return "D";
		} else if (a == 4) {
			return "E";
		} else if (a == 5) {
			return "F";
		} else if (a == 6) {
			return "G";
		} else if (a == 7) {
			return "H";
		} else if (a == 8) {
			return "I";
		} else {
			return "J";
		}
	}

	// Fonction de conversion d'un string vers un int
	public static int convstringtoint(String a) {
		if (a.equals("1")) {
			return 1;
		} else if (a.equals("2")) {
			return 2;
		} else if (a.equals("3")) {
			return 3;
		} else if (a.equals("4")) {
			return 4;
		} else if (a.equals("5")) {
			return 5;
		} else if (a.equals("6")) {
			return 6;
		} else if (a.equals("7")) {
			return 7;
		} else if (a.equals("8")) {
			return 8;
		} else if (a.equals("9")) {
			return 9;
		} else if (a.equals("0")) {
			return 0;
		} else if (a.equals("A")) {
			return 0;
		} else if (a.equals("B")) {
			return 1;
		} else if (a.equals("C")) {
			return 2;
		} else if (a.equals("D")) {
			return 3;
		} else if (a.equals("E")) {
			return 4;
		} else if (a.equals("F")) {
			return 5;
		} else if (a.equals("G")) {
			return 6;
		} else if (a.equals("H")) {
			return 7;
		} else if (a.equals("I")) {
			return 8;
		} else {
			return 9;
		}
	}

	public static String compfonc(String a, int b) {
		String end = "";
		int j = (int) (Math.random() * 10);
		if (j >= 5) {
			String firstletter = (String) a.substring(0, 1);
			String firstnumber = (String) a.substring(1, 2);
			int fn = BATTLESHIP.convstringtoint(firstnumber);
			end = firstletter + String.valueOf(fn + b - 1);
		} else {
			String firstletter = (String) a.substring(0, 1);
			String firstnumber = (String) a.substring(1, 2);
			int fl = BATTLESHIP.convstringtoint(firstletter);
			end = convinttostring(fl + b - 1) + firstnumber;
		}
		return end;
	}

	public static void misenplace(GAME game, String nombateau) {
		System.out.println(game.ActivetoString() + "choississez des coordonnées pour votre " + nombateau);
		boolean verif = false;
		while (!verif) {
			System.out.println("Coordonnée de début : ");
			String coordA = reader.next();
			if (game.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				String coordB = reader.next();
				if (game.Grille.contains(coordB)) {
					ArrayList<String> test = game.test(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (game.ActivePlayer.length()))) {
							SHIP ship = game.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						if (nombateau.equals("carier")) {
							SHIP carier = new SHIP(coordA, coordB, "carier");
							if (carier.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.setCarier(carier);
								game.ActivePlayer.battlecrew.add(carier);
							}
						} else if (nombateau.equals("battleship")) {
							SHIP battleship = new SHIP(coordA, coordB, "battleship");
							if (battleship.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.setBattleship(battleship);
								game.ActivePlayer.battlecrew.add(battleship);
							}
						} else if (nombateau.equals("cruiser")) {
							SHIP cruiser = new SHIP(coordA, coordB, "cruiser");
							if (cruiser.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.setCruiser(cruiser);
								game.ActivePlayer.battlecrew.add(cruiser);
							}
						} else if (nombateau.equals("destroyer")) {
							SHIP destroyer = new SHIP(coordA, coordB, "destroyer");
							if (destroyer.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.setDestroyer(destroyer);
								game.ActivePlayer.battlecrew.add(destroyer);
							}
						} else if (nombateau.equals("submarine")) {
							SHIP submarine = new SHIP(coordA, coordB, "submarine");
							if (submarine.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.setSubmarine(submarine);
								game.ActivePlayer.battlecrew.add(submarine);
							}
						}

					}
				}
			}
		}
	}

	public static void misenplaceordi(GAME game, String nombateau) {
		boolean verif = false;
		while (!verif) {
			ArrayList<String> liste = COMPUTER.hasardcontruc(game, nombateau);
			int resu = 0;
			for (String a : liste) {
				int i = 0;
				while ((resu < 1) && (i < (game.OppositePlayer.length()))) {
					SHIP ship = game.OppositePlayer.battlecrew.get(i);
					if (ship.localisation.contains(a)) {
						resu = +1;
					}
					i++;
				}
			}
			if (resu == 0) {
				if (nombateau.equals("carier")) {
					SHIP carier = new SHIP(liste.get(0), liste.get(4), "carier");
					if (carier.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setCarier(carier);
						//System.out.println(carier.getLocalisation());
						game.OppositePlayer.battlecrew.add(carier);
					}
				} else if (nombateau.equals("battleship")) {
					SHIP battleship = new SHIP(liste.get(0), liste.get(3), "battleship");
					if (battleship.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setBattleship(battleship);
						//System.out.println(battleship.getLocalisation());
						game.OppositePlayer.battlecrew.add(battleship);
					}
				} else if (nombateau.equals("cruiser")) {
					SHIP cruiser = new SHIP(liste.get(0), liste.get(2), "cruiser");
					if (cruiser.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setCruiser(cruiser);
						//System.out.println(cruiser.getLocalisation());
						game.OppositePlayer.battlecrew.add(cruiser);
					}
				} else if (nombateau.equals("destroyer")) {
					SHIP destroyer = new SHIP(liste.get(0), liste.get(1), "destroyer");
					if (destroyer.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setDestroyer(destroyer);
						//System.out.println(destroyer.getLocalisation());
						game.OppositePlayer.battlecrew.add(destroyer);
					}
				} else if (nombateau.equals("submarine")) {
					SHIP submarine = new SHIP(liste.get(0), liste.get(2), "submarine");
					if (submarine.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setSubmarine(submarine);
						//System.out.println(submarine.getLocalisation());
						game.OppositePlayer.battlecrew.add(submarine);
					}
				}
			}
		}
	}
}
