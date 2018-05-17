package arnaud.paul;

import java.util.Scanner;

public class Battleship {

	public static Scanner reader = new Scanner(System.in);
	static String type;

	public static void main(String[] args) {

		String[] tab = {"carrier","submarine","destroyer","battleship","cruiser"};
		System.out.println("Voulez faire une nouvelle partie ? oui/non");
		String avis = reader.next();

		while (avis.equals("oui")) {
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
			Player player1 = new Player();
			System.out.println("Veuillez saisir un nom pour le joueur 1 ");
			String playername = reader.next();
			player1.setPlayername(playername);
			
			Player player2;
			if (type.equals("0")) {
				player2 = new Player();
				System.out.println("Veuillez saisir un nom pour le joueur 2");
				playername = reader.next();
				player2.setPlayername(playername);
			}
			else {
				player2 = new Computer();
				player2.setPlayername("Computer");
			}
			
			Game newgame = new Game(player1,player2);
			Player first = newgame.ActivePlayer;
			while (redo.equals("oui")) {
				if (numberofparty != 0) {
					newgame = new Game(newgame.adversary(first), first);
					first = newgame.ActivePlayer;
				} 
				System.out.println("Mise en place des bateaux de " + newgame.ActivePlayer.getPlayername());
				System.out.println("La grille est composée de " + Config.limleft + Config.limtop + ", .... ," + Config.limright + Config.limbottom);
				for (int i = 0;i<tab.length;i++){
					newgame.ActivePlayer.implementboat(tab[i]);
				}
				System.out.println("Mise en place des bateaux de " + newgame.OppositePlayer.getPlayername());
				System.out.println("La grille est composée de " + Config.limleft + Config.limtop + ",....," + Config.limright + Config.limbottom);
				for (int i = 0;i<tab.length;i++){
					newgame.OppositePlayer.implementboat(tab[i]);
				}
				System.out.println("Début de la partie !!!!!!!!!");
				
				// Début de la partie
				while (!newgame.IsOver()) {
					System.out.println(newgame.ActivePlayer.battlecrew);
					System.out.println("C'est à "+ newgame.ActivePlayer.getPlayername() + " de jouer : ");
					System.out.println("Voici la carte de vos tirs");
					showshoot(newgame.ActivePlayer, newgame.OppositePlayer);
					System.out.println("Voici la carte des tirs de votre adversaire");
					showshoot(newgame.OppositePlayer, newgame.ActivePlayer);
				
					shoot = newgame.ActivePlayer.shoot();
					int i = 0;
					String res = "A l'eau";
					while ((i < (newgame.OppositePlayer.length())) && (res.equals("A l'eau"))) {
						if (newgame.ActivePlayer.hasAlreadyShot(shoot)) {
							if (newgame.ActivePlayer.isFind(shoot)) {
								res = "Touché";
							}
							i = newgame.OppositePlayer.length();
						} else {
							Ship ship = newgame.OppositePlayer.getBattlecrew().get(i);
							if (ship.isHit(shoot)) {
								ship.lifepoint--;
								newgame.ActivePlayer.updateshoot(shoot);
								if (ship.isDestroyed()) {
									res = "Touché Coulé";
									newgame.OppositePlayer.removeShip(ship);
									newgame.ActivePlayer.doingthings();
								} else {
									res = "Touché";
								}
							}
							i = i + 1;
						}
					}
					if (!newgame.ActivePlayer.hasAlreadyShot(shoot)) {
						newgame.ActivePlayer.myShoots.add(shoot);
					}
					System.out.println("C'est : " + res);
					System.out.println("Fin du tour de "+ newgame.ActivePlayer.getPlayername());
					newgame.changePlayer();
				}
				// on regarde qui a gagné et qui a perdu
				if (newgame.ActivePlayer.length() == 0) {
					System.out.println(newgame.OppositePlayer.getPlayername() + " a gagné");
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
		}System.out.println("Session terminée");

	}

	public static void showshoot(Player active, Player opposite) {
		String res = "";
		String coord = "";
		System.out.println("  A B C D E F G H I J");
		for (int i = Config.limtoptoint; i <= Config.limbottomtoint; i++) {
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
