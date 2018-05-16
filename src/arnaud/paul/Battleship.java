package arnaud.paul;

import java.util.ArrayList;
import java.util.Scanner;

public class Battleship {

	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Voulez faire une nouvelle partie ? oui/non");
		String avis = reader.next();

		while (avis.equals("oui")) {
			String type;
			System.out.println(
					"Pour jouer contre un autre joueur taper 0\n" + "pour jouer contre l'ordi de niveau 1 taper 1\n"
							+ "pour jouer contre l'ordi de niveau 2 taper 2\n"
							+ "pour joeur contre l'ordi de niveau 3 taper 3\n" + "Choix : ");
			type = reader.next();
			while (!type.equals("0") && !type.equals("1") && !type.equals("2") && !type.equals("3")) {
				System.out.println("Mauvais choix");
				type = reader.next();
			}
			String redo = "oui";
			int numberofparty = 0;
			// Création de la variable de tir
			String shoot;
			Game newgame = new Game(type);
			Player first = newgame.ActivePlayer;
			while (redo.equals("oui")) {
				if (numberofparty != 0) {
					newgame = new Game(newgame.adversary(first), first);
					first = newgame.ActivePlayer;
				} else {
					System.out.println("Veuillez saisir un nom pour le joueur 1 ");
					String playername = reader.next();
					newgame.ActivePlayer.setPlayername(playername);
					if (type.equals("0")) {
						System.out.println("Veuillez saisir un nom pour le joueur 2");
						playername = reader.next();
						newgame.OppositePlayer.setPlayername(playername);
					}
				}

				System.out.println("Mise en place des bateaux de " + newgame.ActivePlayer.getPlayername());
				System.out.println("La grille est composée de " + Config.limleft + Config.limtop + ", .... ,"
						+ Config.limleft + Config.limbottom + " jusque " + Config.limright + Config.limtop + ",....,"
						+ Config.limright + Config.limbottom);
				/*
				 * implementboatplayer(newgame, "carrier"); implementboatplayer(newgame,
				 * "battleship"); implementboatplayer(newgame, "cruiser");
				 * implementboatplayer(newgame, "submarine");
				 */
				implementboatplayer(newgame, "destroyer");

				if (type.equals("0")) {
					newgame.changePlayer();
					System.out.println("Mise en place des bateaux de " + newgame.OppositePlayer.getPlayername());
					System.out.println("La grille est composée de " + Config.limleft + Config.limtop + ", .... ,"
							+ Config.limleft + Config.limbottom + " jusque " + Config.limright + Config.limtop
							+ ",....," + Config.limright + Config.limbottom);
					implementboatplayer(newgame, "carrier");
					implementboatplayer(newgame, "battleship");
					implementboatplayer(newgame, "cruiser");
					implementboatplayer(newgame, "submarine");
					implementboatplayer(newgame, "destroyer");
					newgame.changePlayer();
				} else {
					System.out.println("Mise en place des bateaux de l'ordinateur !! ");
					newgame.ComputerPlayer.implementboatcomputer("carrier");
					System.out.println("Carrier Done");
					newgame.ComputerPlayer.implementboatcomputer("battleship");
					System.out.println("Battleship Done");
					newgame.ComputerPlayer.implementboatcomputer("cruiser");
					System.out.println("Cruiser Done");
					newgame.ComputerPlayer.implementboatcomputer("submarine");
					System.out.println("Submarine Done");
					newgame.ComputerPlayer.implementboatcomputer("destroyer");
					System.out.println("Destroyer Done");
					System.out.println("Fin de la mise en place des bateaux de l'ordinateur !! ");
				}
				System.out.println("Début de la partie !!!!!!!!!");

				Player temp;

				// Début de la partie
				while (!newgame.IsOver()) {
					if (type.equals("0")) {
						temp = newgame.OppositePlayer;
					} else {
						temp = newgame.ComputerPlayer;
					}

					if (type.equals("0")) {
						System.out.println("Voici la carte de vos tirs");
						showshoot(newgame.ActivePlayer, newgame.OppositePlayer);
						System.out.println("Voici la carte des tirs de votre adversaire");
						showshoot(newgame.OppositePlayer, newgame.ActivePlayer);
					} else {
						System.out.println("Voici la carte de vos tirs");
						showshoot(newgame.ActivePlayer, newgame.ComputerPlayer);
						System.out.println("Voici la carte des tirs de votre adversaire");
						showshoot(newgame.ComputerPlayer, newgame.ActivePlayer);
					}
					System.out.println(newgame.ActivePlayer.getPlayername()
							+ " ,choisissez une position à attaquer(exemple:" + Config.limleft + Config.limbottom + ","
							+ Config.limright + Config.limbottom + ")");
					System.out.println("Coordonnée du tir :");
					shoot = reader.next();
					// si le joueur tir sur une case déjé essayée on lui redemande des coordonnées
					while (newgame.ActivePlayer.hasAlreadyShot(shoot) || (!Config.isCorrect(shoot))) {
						System.out.println(newgame.ActivePlayer.getPlayername()
								+ " ,choisissez une nouvelle position, vous avez déjé attaqué ici ou la position n'est pas valide ! ");
						System.out.println("Coordonnée du tir :");
						shoot = reader.next();
					}
					int i = 0;
					String res = "A l'eau";
					Ship ship;
					while ((i < (temp.length())) && (res.equals("A l'eau"))) {
						ship = temp.getBattlecrew().get(i);
						if (ship.isHit(shoot)) {
							if (newgame.ActivePlayer.hasAlreadyShot(shoot)) {
								res = "Touché";
							} else {
								newgame.ActivePlayer.myShoots.add(shoot);
								ship.lifepoint--;
								if (ship.isDestroyed()) {
									res = "Touché Coulé";
									temp.removeShip(ship);
								} else {
									res = "Touché";
								}
							}
						}
						i = i + 1;
					}
					if (!newgame.ActivePlayer.hasAlreadyShot(shoot)) {
						newgame.ActivePlayer.myShoots.add(shoot);
					}
					System.out.println("C'est : " + res);

					if (!newgame.IsOver() && !type.equals("0")) {
						System.out.println("C'est à l'ordinateur de jouer : ");
						// showshoot(newgame.ComputerPlayer, newgame.ActivePlayer);
						if (type.equals("1")) {
							shoot = newgame.ComputerPlayer.shootlvlmin();
						} else if (type.equals("2")) {
							shoot = newgame.ComputerPlayer.hasard();
						} else if (type.equals("3")) {
							shoot = newgame.ComputerPlayer.shoot();
						}
						System.out.println("l'ordinateur a frappé en " + shoot);
						i = 0;
						res = "A l'eau";
						while ((i < (newgame.ActivePlayer.length())) && (res.equals("A l'eau"))) {
							if (newgame.ComputerPlayer.hasAlreadyShot(shoot)) {
								if (newgame.ComputerPlayer.isFind(shoot)) {
									res = "Touché";
								}
								i = newgame.ActivePlayer.length();
							} else {
								ship = newgame.ActivePlayer.getBattlecrew().get(i);
								if (ship.isHit(shoot)) {
									newgame.ComputerPlayer.setState("tir");
									newgame.ComputerPlayer.getCurrentboat().add(shoot);
									ship.lifepoint--;
									newgame.ComputerPlayer.myShoots.add(shoot);
									if (ship.isDestroyed()) {
										res = "Touché Coulé";
										newgame.ActivePlayer.removeShip(ship);
										newgame.ComputerPlayer.setState("chasse");
										newgame.ComputerPlayer.setDirstate("top");
										newgame.ComputerPlayer.setCurrentboat(new ArrayList<String>());
									} else {
										res = "Touché";
									}
								}
								i = i + 1;
							}
						}
						if (!newgame.ComputerPlayer.hasAlreadyShot(shoot)) {
							newgame.ComputerPlayer.myShoots.add(shoot);
						}
						System.out.println("C'est : " + res);
						System.out.println("Fin du tour de l'ordinateur");
					} else if (type.equals("0")) {
						newgame.changePlayer();
					}
				}
				// on regarde qui a gagné et qui a perdu
				if (newgame.ActivePlayer.length() == 0) {
					if (type.equals("0")) {
						System.out.println(newgame.OppositePlayer.getPlayername() + " a gagné");
					} else {
						System.out.println(newgame.ComputerPlayer.getPlayername() + " a gagné");
					}

				} else {
					System.out.println(newgame.ActivePlayer.getPlayername() + " a gagné");
				}
				if (type.equals("0")) {
					System.out.println("Voulez faire une nouvelle partie avec votre adversaire ? oui/non");
					redo = reader.next();
				} else {
					redo = "non";
				}
				if (redo.equals("oui") && type.equals("0")) {
					numberofparty = +1;
					newgame.ActivePlayer.reset();
					newgame.OppositePlayer.reset();
				}

			}
			System.out.println("Voulez faire une nouvelle partie ? oui/non");
			avis = reader.next();
		}
		System.out.println("Session terminée");
	}

	public static void implementboatplayer(Game game, String nombateau) {
		// mise en place des bateaux pour le joueur, elle vérifie si les choix faits par
		// le joueur sont possibles
		System.out.println(game.ActivePlayer.getPlayername() + " , choississez des coordonnées pour votre " + nombateau
				+ Config.boatsize(nombateau));
		boolean verif = false;
		while (!verif) {
			System.out.println("Coordonnée de début : ");
			String coordA = reader.next();
			if (Config.isCorrect(coordA)) {
				System.out.println("Coordonnée de fin : ");
				String coordB = reader.next();
				if (Config.isCorrect(coordB)) {
					ArrayList<String> test = Config.locacalcul(coordA, coordB);
					int resu = 0;
					for (String a : test) {
						int i = 0;
						while ((resu < 1) && (i < (game.ActivePlayer.length()))) {
							Ship ship = game.ActivePlayer.battlecrew.get(i);
							if (ship.localisation.contains(a)) {
								resu = +1;
							}
							i++;
						}
					}
					if (resu == 0) {
						if (nombateau.equals("carrier")) {
							Ship carrier = new Ship(coordA, coordB, test, "carrier");
							if (carrier.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.battlecrew.add(carrier);
							}
						} else if (nombateau.equals("battleship")) {
							Ship battleship = new Ship(coordA, coordB, test, "battleship");
							if (battleship.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.battlecrew.add(battleship);
							}
						} else if (nombateau.equals("cruiser")) {
							Ship cruiser = new Ship(coordA, coordB, test, "cruiser");
							if (cruiser.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.battlecrew.add(cruiser);
							}
						} else if (nombateau.equals("destroyer")) {
							Ship destroyer = new Ship(coordA, coordB, test, "destroyer");
							if (destroyer.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.battlecrew.add(destroyer);
							}
						} else if (nombateau.equals("submarine")) {
							Ship submarine = new Ship(coordA, coordB, test, "submarine");
							if (submarine.etat.equals("valide")) {
								verif = true;
								game.ActivePlayer.battlecrew.add(submarine);
							}
						}
					}
				}
			}
		}
	}

	public static void showshoot(Player active, Player opposite) {
		String res = "";
		String coord = "";
		System.out.println("  A B C D E F G H I J");
		for (int i = Config.convstringtoint(Config.limtop); i <= Config.convstringtoint(Config.limbottom); i++) {
			res = String.valueOf(i);
			for (int j = Config.convstringtoint(Config.limleft); j <= Config.convstringtoint(Config.limright); j++) {
				coord = Config.convinttostring(j) + String.valueOf(i);
				if (active.myShoots.contains(coord)) {
					if (opposite.isInCrew(coord)) {
						res = res + " " + "x";
					} else {
						res = res + " " + "o";
					}
				} else {
					res = res + " " + "~";
				}
			}
			System.out.println(res);
		}
	}
}
