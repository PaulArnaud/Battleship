package arnaud.paul.Battleship;

import java.util.Scanner;

public class Battleship {

	public static Scanner reader = new Scanner(System.in);
	public static String type;
	public static String[] tab = { "carrier"/* ,"submarine","destroyer","battleship","cruiser" */ };
	public static int vs;
	public static boolean modeIA;

	public static void main(String[] args) {

		System.out.println("Voulez faire une nouvelle partie ? oui/non");
		String avis = readmsg();

		while (avis.equals("oui")) {
			System.out.println(
					"Pour jouer contre un autre joueur taper 0\n" + "pour jouer contre l'ordi de niveau 1 taper 1\n"
							+ "pour jouer contre l'ordi de niveau 2 taper 2\n"
							+ "pour joeur contre l'ordi de niveau 3 taper 3\n" + "Choix : ");
			type = readmsg();
			while (!type.equals("0") && !type.equals("1") && !type.equals("2") && !type.equals("3")) {
				System.out.println("Mauvais choix");
				type = readmsg();
			}
			String redo = "oui";
			int numberofparty = 0;
			Player player1 = new Player();
			System.out.println("Veuillez saisir un nom pour le joueur 1 ");
			String playername = readmsg();
			player1.setPlayername(playername);

			Player player2;
			if (type.equals("0")) {
				player2 = new Player();
				System.out.println("Veuillez saisir un nom pour le joueur 2");
				playername = readmsg();
				player2.setPlayername(playername);
			} else if (type.equals("3")) {
				player2 = new Computer(3);
				player2.setPlayername("Computer");
			} else if (type.equals("2")) {
				player2 = new Computer(2);
				player2.setPlayername("Computer");
			} else {
				player2 = new Computer(1);
				player2.setPlayername("Computer");
			}

			Game newgame = new Game(player1, player2);
			Player first = newgame.ActivePlayer;
			while (redo.equals("oui")) {
				if (numberofparty != 0) {
					newgame = new Game(newgame.adversary(first), first);
					first = newgame.ActivePlayer;
				}
				System.out.println("Mise en place des bateaux de " + newgame.ActivePlayer.getPlayername());
				for (int i = 0; i < tab.length; i++) {
					newgame.ActivePlayer.implementboat(tab[i]);
				}
				System.out.println("Mise en place des bateaux de " + newgame.OppositePlayer.getPlayername());
				for (int i = 0; i < tab.length; i++) {
					newgame.OppositePlayer.implementboat(tab[i]);
				}
				System.out.println("Début de la partie !!!!!!!!!");
				Player winner = newgame.party();
				System.out.println(winner.getPlayername() + " a gagné");
				System.out.println("Voulez faire une nouvelle partie avec votre adversaire ? oui/non");
				redo = readmsg();
				if (redo.equals("oui")) {
					numberofparty = +1;
					newgame.ActivePlayer.reset();
					newgame.OppositePlayer.reset();
				}
			}
			System.out.println("Voulez faire une nouvelle partie ? oui/non");
			avis = readmsg();
		}
		System.out.println("Session terminée");
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

	public static String readmsg() {
		String message = reader.next();
		return message;
	}

}
