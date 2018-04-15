import java.util.ArrayList;

import java.util.Scanner;

public class BATTLESHIP {

	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		// Cr�ation d'une nouvelle partie
		GAME newgame = new GAME();
		newgame.setGrille();

		// Cr�ation des 2 joueurs
		PLAYER player1 = new PLAYER();
		COMPUTER player2 = new COMPUTER();

		// Cr�ation de deux strings pour les input des coordonn�es des bateaux
		// Mise en place de joueur courant et joueur oppos�
		newgame.setActivePlayer(player1);
		newgame.setOppositePlayer(player2);

		// Cr�ation de la variable de tir
		String shoot;

		// Cr�ation de la variable battlecrew
		ArrayList<SHIP> battlecrew;

		System.out.println("d�but de l �tape ordi");
		/////// testing
		misenplaceordi(newgame, "carier");
		misenplaceordi(newgame, "battleship");
		misenplaceordi(newgame, "cruiser");
		misenplaceordi(newgame, "submarine");
		misenplaceordi(newgame, "destroyer");
		////// fin test
		System.out.println("fin de l �tape ordi");
		// Mise en place de tous les bateaux du joueur 1
		System.out.println("la grille est compos�e de A0 , .... , A9 jusque J0,....,J9");
		misenplace(newgame, "carier");
		misenplace(newgame, "battleship");
		misenplace(newgame, "cruiser");
		misenplace(newgame, "submarine");
		misenplace(newgame, "destroyer");

		// Mise en place des bateaux du joueur 2
		///// il faut v�rifier ////

		// D�but de la partie
		while (!newgame.IsOver()) {
			// demande des coordonn�es du tir
			System.out.println(newgame.ActivetoString() + " Choisissez une position � attaquer(exemple: A1, J10)");
			System.out.println("Coordonn�e du tir :");
			shoot = reader.next();
			// si le joueur tir sur une case d�j� essay�e on lui redemande des coordonn�es
			while (newgame.ActivePlayer.hasAlreadyShot(shoot)) {
				System.out.println(newgame.ActivetoString()
						+ " Choisissez une nouvelle position, vous avez d�j� attaqu� ici(exemple: A1, J10) ");
				System.out.println("Coordonn�e du tir :");
				shoot = reader.next();
			}
			newgame.ActivePlayer.myShoots.add(shoot);
			System.out.println(newgame.ActivePlayer.myShoots);
			// on instancie le crew du joueur adverse pour savoir si on touche
			battlecrew = newgame.OppositePlayer.getBattlecrew();
			int i = 0;
			String res = "A l�eau";
			SHIP ship;
			// on boucle tant qu'il y a des bateaux dans la liste et que l'on a pas tout
			// parcouru
			// et qu'on ne touche pas
			while ((i < (newgame.OppositePlayer.length())) && (res.equals("A l�eau"))) {
				ship = battlecrew.get(i);
				// System.out.println(ship);
				// si c'est touch�
				if (ship.isHit(shoot)) {
					// on regarde si c'est coul�
					if (ship.isDestroyed()) {
						res = "Touch� Coul�";
						newgame.OppositePlayer.removeShip(ship);
					} else {
						res = "Touch�";
					}
				}
				i = i + 1;
			}
			System.out.println(res);
			newgame.changePlayer();
		}

		// on regarde qui a gagn� et qui a perdu
		if (newgame.ActivePlayer.length() == 0) {
			System.out.println(newgame.OppositetoString() + "a gagn�");
		} else {
			System.out.println(newgame.ActivetoString() + "a gagn�");
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
		System.out.println(game.ActivetoString() + "choississez des coordonn�es pour votre " + nombateau);
		boolean verif = false;
		while (!verif) {
			System.out.println("Coordonn�e de d�but : ");
			String coordA = reader.next();
			if (game.Grille.contains(coordA)) {
				System.out.println("Coordonn�e de fin : ");
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
						}

						else if (nombateau.equals("destroyer")) {
							SHIP destroyer = new SHIP(coordA, coordB, "destroyer");
							if (destroyer.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.setDestroyer(destroyer);
								game.ActivePlayer.battlecrew.add(destroyer);
							}
						}

						else if (nombateau.equals("submarine")) {
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
		System.out.println("�tape 1");
		while (!verif) {
			ArrayList<String> liste = COMPUTER.hasardcontruc(game, nombateau);
			System.out.println(liste);
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
						game.OppositePlayer.battlecrew.add(carier);
						System.out.println("�tape2");
					}
				} else if (nombateau.equals("battleship")) {
					SHIP battleship = new SHIP(liste.get(0), liste.get(3), "battleship");
					if (battleship.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setBattleship(battleship);
						game.OppositePlayer.battlecrew.add(battleship);
						System.out.println("�tape2.5");
					}
				} else if (nombateau.equals("cruiser")) {
					SHIP cruiser = new SHIP(liste.get(0), liste.get(2), "cruiser");
					if (cruiser.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setCruiser(cruiser);
						game.OppositePlayer.battlecrew.add(cruiser);
						System.out.println("�tape3");
					}
				}

				else if (nombateau.equals("destroyer")) {
					SHIP destroyer = new SHIP(liste.get(0), liste.get(1), "destroyer");
					if (destroyer.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setDestroyer(destroyer);
						game.OppositePlayer.battlecrew.add(destroyer);
						System.out.println("�tape4");
					}
				}

				else if (nombateau.equals("submarine")) {
					SHIP submarine = new SHIP(liste.get(0), liste.get(2), "submarine");
					if (submarine.etat.equals("valide")) {
						verif = true;
						game.OppositePlayer.setSubmarine(submarine);
						game.OppositePlayer.battlecrew.add(submarine);
						System.out.println("�tape5");
					}
				}
			}
		}
	}
}
