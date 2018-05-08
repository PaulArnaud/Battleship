import java.util.ArrayList;
import java.util.Scanner;

public class Battleship {

	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Voulez faire une nouvelle partie ? oui/non");
		String avis = reader.next();

		while (avis.equals("oui")) {

			// Création d'une nouvelle partie
			Game newgame = new Game();
			newgame.createGrille();
			// Création des 2 joueurs
			Player player1 = new Player();
			Computer player2 = new Computer();
			System.out.println("Veuillez saisir un nom pour le joueur");
			String playername = reader.next();
			player1.setPlayername(playername);
			player2.setPlayername("Computer");
			// Mise en place de joueur courant et joueur opposé
			newgame.setActivePlayer(player1);
			newgame.setOppositePlayer(player2);

			// Création de la variable de tir
			String shoot;

			// Création de la variable battlecrew
			ArrayList<Ship> battlecrew;

			System.out.println("Mise en place des bateaux de l'ordinateur !! ");
			implementboatcomputer(newgame, "carrier");
			System.out.println("Carrier Done");
			implementboatcomputer(newgame, "battleship");
			System.out.println("Battleship Done");
			implementboatcomputer(newgame, "cruiser");
			System.out.println("Cruiser Done");
			implementboatcomputer(newgame, "submarine");
			System.out.println("Submarine Done");
			implementboatcomputer(newgame, "destroyer");
			System.out.println("Destroyer Done");
			player2.SaveBattlecrew();
			System.out.println("Fin de la mise en place des bateaux de l'ordinateur !! ");
			System.out.println(player2.savebattlecrew.get(4).getLocalisation());

			System.out.println("Mise en place des bateaux du joueur");
			System.out.println("La grille est composée de "+Config.limittop+Config.limitleft+", .... ,"+Config.limittop + Config.limitright+" jusque " +Config.limitbottom+Config.limitleft+",....,"+Config.limitbottom+Config.limitright);
			//implementboatplayer(newgame, "carrier");
			//implementboatplayer(newgame, "battleship");
			//implementboatplayer(newgame, "cruiser");
			//implementboatplayer(newgame, "submarine");
			implementboatplayer(newgame, "destroyer");
			player1.SaveBattlecrew();
			System.out.println("Début de la partie !!!!!!!!!");
		

			
			// Début de la partie
			while (!newgame.IsOver()) {
				// demande des coordonnées du tir
				System.out.println("Voici la carte de vos tirs");
				showshoot(player1,player2);
				System.out.println(player1.getPlayername() + " ,choisissez une position à attaquer(exemple:"+Config.limittop + Config.limitright+","+Config.limitbottom+Config.limitright+")");
				System.out.println("Coordonnée du tir :");
				shoot = reader.next();
				// si le joueur tir sur une case déjé essayée on lui redemande des coordonnées
				while (newgame.ActivePlayer.hasAlreadyShot(shoot) || (!newgame.Grille.contains(shoot))) {
					System.out.println(player1.getPlayername()
							+ " ,choisissez une nouvelle position, vous avez déjé attaqué ici ou la position n'est pas valide ! ");
					System.out.println("Coordonnée du tir :");
					shoot = reader.next();
				}
				newgame.ActivePlayer.myShoots.add(shoot);
				// on instancie le crew du joueur adverse pour savoir si on touche
				battlecrew = newgame.OppositePlayer.getBattlecrew();
				int i = 0;
				String res = "A l'eau";
				Ship ship;
				// on boucle tant qu'il y a des bateaux dans la liste et que l'on a pas tout
				// parcouru
				// et qu'on ne touche pas
				while ((i < (newgame.OppositePlayer.length())) && (res.equals("A l'eau"))) {
					ship = battlecrew.get(i);
					// System.out.println(ship);
					// si c'est touché
					if (ship.isHit(shoot)) {
						ship.removepos(shoot);
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
				System.out.println("C'est : " + res);
				// newgame.changePlayer();
				if (!newgame.IsOver()) {
					System.out.println("C'est à l'ordinateur de jouer : ");
					shoot = player2.shoot(newgame);
					newgame.OppositePlayer.myShoots.add(shoot);
					System.out.println("l'ordinateur a frappé en " + shoot);
					battlecrew = newgame.ActivePlayer.getBattlecrew();
					i = 0;
					res = "A l'eau";
					while ((i < (newgame.ActivePlayer.length())) && (res.equals("A l'eau"))) {
						ship = battlecrew.get(i);
						// System.out.println(ship);
						// si c'est touché
						if (ship.isHit(shoot)) {
							player2.setState("tir");
							ship.removepos(shoot);
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
					System.out.println("C'est : " + res);
					System.out.println("Fin du tour de l'ordinateur");
				}
			}
			// on regarde qui a gagné et qui a perdu
			if (newgame.ActivePlayer.length() == 0) {
				System.out.println(player2.getPlayername() + " a gagné");
			} else {
				System.out.println(player1.getPlayername() + " a gagné");
			}
			System.out.println("Voulez faire une nouvelle partie ? oui/non");
			avis = reader.next();
		}
		System.out.println("Session terminée");
	}

	public static void implementboatplayer(Game game, String nombateau) {
		// mise en place des bateaux pour le joueur, elle vérifie si les choix faits par
		// le joueur sont possibles
		System.out.println(game.ActivePlayer.getPlayername() + " , choississez des coordonnées pour votre " + nombateau + Config.boatsize(nombateau));
		boolean verif = false;
		while (!verif) {
			System.out.println("Coordonnée de début : ");
			String coordA = reader.next();
			if (game.Grille.contains(coordA)) {
				System.out.println("Coordonnée de fin : ");
				String coordB = reader.next();
				if (game.Grille.contains(coordB)) {
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

	public static void implementboatcomputer(Game game, String nombateau) {
		// fonction qui place les bateaux de l'ordi au hasard
		boolean verif = false;
		while (!verif) {
			ArrayList<String> liste = Computer.hasardcontruc(game, nombateau);
			int resu = 0;
			for (String a : liste) {
				int i = 0;
				while ((resu < 1) && (i < (game.OppositePlayer.length()))) {
					Ship ship = game.OppositePlayer.battlecrew.get(i);
					if (ship.localisation.contains(a)) {
						resu = +1;
					}
					i++;
				}
			}
			if (resu == 0) {
				if (nombateau.equals("carrier")) {
					Ship carrier = new Ship(liste.get(0), liste.get(4), liste, "carrier");
					if (carrier.etat.equals("valide")) {
						verif = true;
						//System.out.println(carrier.getLocalisation());
						game.OppositePlayer.battlecrew.add(carrier);
					}
				} else if (nombateau.equals("battleship")) {
					Ship battleship = new Ship(liste.get(0), liste.get(3), liste, "battleship");
					if (battleship.etat.equals("valide")) {
						verif = true;
						//System.out.println(battleship.getLocalisation());
						game.OppositePlayer.battlecrew.add(battleship);
					}
				} else if (nombateau.equals("cruiser")) {
					Ship cruiser = new Ship(liste.get(0), liste.get(2), liste, "cruiser");
					if (cruiser.etat.equals("valide")) {
						verif = true;
						//System.out.println(cruiser.getLocalisation());
						game.OppositePlayer.battlecrew.add(cruiser);
					}
				} else if (nombateau.equals("destroyer")) {
					Ship destroyer = new Ship(liste.get(0), liste.get(1), liste, "destroyer");
					if (destroyer.etat.equals("valide")) {
						verif = true;
						//System.out.println(destroyer.getLocalisation());
						game.OppositePlayer.battlecrew.add(destroyer);
					}
				} else if (nombateau.equals("submarine")) {
					Ship submarine = new Ship(liste.get(0), liste.get(2), liste, "submarine");
					if (submarine.etat.equals("valide")) {
						verif = true;
						//System.out.println(submarine.getLocalisation());
						game.OppositePlayer.battlecrew.add(submarine);
					}
				}
			}
		}
	}
	
	public static void showshoot(Player active,Player opposite) {
		String res = "";
		String coord ="";
		System.out.println("  A B C D E F G H I J");
		for (int i = Config.convstringtoint(Config.limitleft);i<= Config.convstringtoint(Config.limitright);i++) {
			res = String.valueOf(i);
			for (int j = Config.convstringtoint(Config.limittop);j<= Config.convstringtoint(Config.limitbottom);j++) {
				coord = Config.convinttostring(j)+String.valueOf(i);
				if (active.myShoots.contains(coord)){
					if (opposite.isIn(coord)){
						res = res + " " + "x" ;
					}
					else {
						res = res + " " + "o" ;
					}
				}	
				else {
					res = res + " " + "~" ;
				} 
			}
			System.out.println(res);
		}
	}
}
