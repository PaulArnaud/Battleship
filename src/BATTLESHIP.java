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
		PLAYER player2 = new PLAYER();

		// Création de deux strings pour les input des coordonnées des bateaux
		String coordA;
		String coordB;

		// Mise en place de joueur courant et joueur opposé
		newgame.setActivePlayer(player1);
		newgame.setOppositePlayer(player2);

		// Création de la variable de tir
		String shoot;

		// Création de la variable battlecrew
		ArrayList<SHIP> battlecrew;

		// Mise en place de tous les bateaux du joueur 1
		System.out.println("Joueur 1 choississez des coordonnées pour votre Carier");
		boolean verif1 = false;
		while (!verif1) {
			System.out.println("Coordonnée de début : ");
			coordA = reader.next();
			if (newgame.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				coordB = reader.next();
				if (newgame.Grille.contains(coordB)) {
					ArrayList<String> test = newgame.test(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (newgame.ActivePlayer.length()))) {
							SHIP ship = newgame.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						SHIP carier1 = new SHIP(coordA, coordB, "carier");
						if (carier1.etat.equals("valide")){
							verif1 = true;
							player1.setCarier(carier1);
							player1.battlecrew.add(carier1);
						}
						

					}
				}
			}
		}
		
		System.out.println("Joueur 1 choississez des coordonnées pour votre Battleship");
		boolean verif2 = false;
		while (!verif2) {
			System.out.println("Coordonnée de début : ");
			coordA = reader.next();
			if (newgame.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				coordB = reader.next();
				if (newgame.Grille.contains(coordB)) {
					ArrayList<String> test = newgame.test(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (newgame.ActivePlayer.length()))) {
							SHIP ship = newgame.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						SHIP battleship1 = new SHIP(coordA, coordB, "battleship");
						if (battleship1.etat.equals("valide")){
							verif2 = true;
							player1.setBattleship(battleship1);
							player1.battlecrew.add(battleship1);
						}

					}
				}
			}
		}

		System.out.println("Joueur 1 choississez des coordonnées pour votre Cruiser");
		boolean verif3 = false;
		while (!verif3) {
			System.out.println("Coordonnée de début : ");
			coordA = reader.next();
			if (newgame.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				coordB = reader.next();
				if (newgame.Grille.contains(coordB)) {
					ArrayList<String> test = newgame.test(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (newgame.ActivePlayer.length()))) {
							SHIP ship = newgame.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						SHIP cruiser1 = new SHIP(coordA, coordB, "cruiser");
						if (cruiser1.etat.equals("valide")){
							verif3 = true;
							player1.setCruiser(cruiser1);
							player1.battlecrew.add(cruiser1);
						}
					}
				}
			}
		}

		System.out.println("Joueur 1 choississez des coordonnées pour votre Submarine");
		boolean verif4 = false;
		while (!verif4) {
			System.out.println("Coordonnée de début : ");
			coordA = reader.next();
			if (newgame.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				coordB = reader.next();
				if (newgame.Grille.contains(coordB)) {
					ArrayList<String> test = newgame.test(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (newgame.ActivePlayer.length()))) {
							SHIP ship = newgame.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						SHIP submarine1 = new SHIP(coordA, coordB, "submarine");
						if (submarine1.etat.equals("valide")) {
							verif4 = true;
							player1.setSubmarine(submarine1);
							player1.battlecrew.add(submarine1);
						}
					}
				}
			}
		}

		System.out.println("Joueur 1 choississez des coordonnées pour votre Destroyer");
		boolean verif5 = false;
		while (!verif5) {
			System.out.println("Coordonnée de début : ");
			coordA = reader.next();
			if (newgame.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				coordB = reader.next();
				if (newgame.Grille.contains(coordB)) {
					ArrayList<String> test = newgame.test(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (newgame.ActivePlayer.length()))) {
							SHIP ship = newgame.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						SHIP destroyer1 = new SHIP(coordA, coordB, "destroyer");
						if (destroyer1.etat.equals("valide")) {
							verif5 = true;
							player1.setDestroyer(destroyer1);
							player1.battlecrew.add(destroyer1);
						}
					}
				}
			}
		}

		// Mise en place des bateaux du joueur 2
		///// il faut vérifier ////
		SHIP carier2 = new SHIP("A1", "A5", "carier");
		player2.setCarier(carier2);
		SHIP battleship2 = new SHIP("B1", "B4", "battleship");
		player2.setBattleship(battleship2);
		SHIP cruiser2 = new SHIP("C1", "C3", "cruiser");
		player2.setCruiser(cruiser2);
		SHIP submarine2 = new SHIP("D1", "D3", "submarine");
		player2.setSubmarine(submarine2);
		SHIP destroyer2 = new SHIP("E1", "E2", "destroyer");
		player2.setDestroyer(destroyer2);
		player2.setBattlecrew();

		// Début de la partie
		while (!newgame.IsOver()) {
			// demande des coordonnées du tir
			System.out.println(newgame.ActivetoString() + " Choisissez une position à attaquer(exemple: A1, J10)");
			System.out.println("Coordonnée du tir :");
			shoot = reader.next();
			// si le joueur tir sur une case déjà essayée on lui redemande des coordonnées
			while (newgame.ActivePlayer.hasAlreadyShot(shoot)) {
				System.out.println(newgame.ActivetoString()
						+ " Choisissez une nouvelle position, vous avez déjà attaqué ici(exemple: A1, J10) ");
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
				System.out.println(ship);
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
			newgame.changePlayer();
		}

		// on regarde qui a gagné et qui a perdu
		if (newgame.ActivePlayer.length() == 0) {
			System.out.println(newgame.OppositetoString() + "a gagné");
		} else {
			System.out.println(newgame.ActivetoString() + "a gagné");
		}
	}

}
