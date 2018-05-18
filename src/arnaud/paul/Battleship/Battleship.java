package arnaud.paul.Battleship;

public class Battleship {

	

	public static void main(String[] args) {

		System.out.println("Voulez faire une nouvelle partie ? oui/non");
		String avis = Config.readmsg();

		while (avis.equals("oui")) {
			System.out.println(
					"Pour jouer contre un autre joueur taper 0\n" + "pour jouer contre l'ordi de niveau 1 taper 1\n"
							+ "pour jouer contre l'ordi de niveau 2 taper 2\n"
							+ "pour joeur contre l'ordi de niveau 3 taper 3\n" + "Choix : ");
			Config.type = Config.readmsg();
			while (!Config.type .equals("0") && !Config.type .equals("1") && !Config.type .equals("2") && !Config.type .equals("3")) {
				System.out.println("Mauvais choix");
				Config.type = Config.readmsg();
			}
			String redo = "oui";
			int numberofparty = 0;
			Player player1 = new Player();
			System.out.println("Veuillez saisir un nom pour le joueur 1 ");
			String playername = Config.readmsg();
			player1.setPlayername(playername);

			Player player2;
			if (Config.type.equals("0")) {
				player2 = new Player();
				System.out.println("Veuillez saisir un nom pour le joueur 2");
				playername = Config.readmsg();
				player2.setPlayername(playername);
			} else if (Config.type.equals("3")) {
				player2 = new Computer(3);
				player2.setPlayername("Computer");
			} else if (Config.type.equals("2")) {
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
				for (int i = 0; i < Config.tab.length; i++) {
					newgame.ActivePlayer.implementboat( Config.tab[i]);
				}
				System.out.println("Mise en place des bateaux de " + newgame.OppositePlayer.getPlayername());
				for (int i = 0; i <  Config.tab.length; i++) {
					newgame.OppositePlayer.implementboat( Config.tab[i]);
				}
				System.out.println("Début de la partie !!!!!!!!!");
				Player winner = newgame.party();
				System.out.println(winner.getPlayername() + " a gagné");
				System.out.println("Voulez faire une nouvelle partie avec votre adversaire ? oui/non");
				redo = Config.readmsg();
				if (redo.equals("oui")) {
					numberofparty = +1;
					newgame.ActivePlayer.reset();
					newgame.OppositePlayer.reset();
				}
			}
			System.out.println("Voulez faire une nouvelle partie ? oui/non");
			avis = Config.readmsg();
		}
		System.out.println("Session terminée");
	}
}
