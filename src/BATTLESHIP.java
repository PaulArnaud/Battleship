import java.util.ArrayList;
import java.util.Scanner;

public class BATTLESHIP {
	
	public static Scanner reader = new Scanner(System.in);

	public static void main(){
		// Création d'une nouvelle partie
		GAME newgame = new GAME();
		// Création des 2 joueurs
		PLAYER player1 = new PLAYER();
		PLAYER player2 = new PLAYER();
		// Création de deux strings pour les input des coordonnées des bateaux
		String coordA;
		String coordB;
		// Mise en place de joueur courant et joueur opposé
		newgame.setActivePlayer(player1);
		newgame.setOppositePlayer(player2);
		PLAYER activeplayer = newgame.getActivePlayer();
		PLAYER oppositeplayer = newgame.getOppositePlayer();
		// Création de la variable de tir
		String shoot;
		// Création de la variable battlecrew
		ArrayList<SHIP> battlecrew;
	

		// Mise en place de tous les bateaux du joueur 1
		System.out.println("Joueur 1 choississez des coordonnées pour votre Carier");
		coordA= reader.next("D=");
		coordB= reader.next("F=");
		///// il faut vérifier ////
		SHIP carier1 = new SHIP(coordA,coordB,"carier");
		player1.setCarier(carier1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Battleship");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP battleship1 = new SHIP(coordA,coordB,"battleship");
		player1.setBattleship(battleship1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Cruiser");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP cruiser1 = new SHIP(coordA,coordB,"cruiser");
		player1.setCruiser(cruiser1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Submarine");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP submarine1 = new SHIP(coordA,coordB,"submarine");
		player1.setSubmarine(submarine1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Destroyer");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP destroyer1 = new SHIP(coordA,coordB,"destroyer");
		player1.setDestroyer(destroyer1);
		player1.setBattlecrew();
		
		// Mise en place des bateaux du joueur 2
		System.out.println("Joueur 1 choississez des coordonnées pour votre Carier");
		coordA= reader.next("D=");
		coordB= reader.next("F=");
		///// il faut vérifier ////
		SHIP carier2 = new SHIP(coordA,coordB,"carier");
		player2.setCarier(carier2);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Battleship");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP battleship2 = new SHIP(coordA,coordB,"battleship");
		player2.setBattleship(battleship2);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Cruiser");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP cruiser2 = new SHIP(coordA,coordB,"cruiser");
		player2.setCruiser(cruiser2);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Submarine");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP submarine2 = new SHIP(coordA,coordB,"submarine");
		player2.setSubmarine(submarine2);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Destroyer");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP destroyer2 = new SHIP(coordA,coordB,"destroyer");
		player2.setDestroyer(destroyer2);
		player2.setBattlecrew();

		// Début de la partie
		while (!newgame.IsOver()) {
			// demande des coordonnées du tir
			System.out.println( newgame.ActivetoString()  +  " Choisissez une position à attaquer(exemple: A1, J10)" );
			shoot = reader.next("Shoot = ");
			// si le joueur tir sur une case déjà essayée on lui redemande des coordonnées
			while (activeplayer.hasAlreadyShot(shoot)){
				System.out.println( newgame.ActivetoString() +  " Choisissez une nouvelle position, vous avez déjà attaqué ici(exemple: A1, J10) ");
				shoot = reader.next("Shoot = ");
			}
			// on instancie le crew du joueur adverse pour savoir si on touche
			battlecrew = oppositeplayer.getBattlecrew();
			int i = 0 ;
			String res = "A l’eau" ;
			SHIP ship;
			// on boucle tant qu'il y a des bateaux dans la liste et que l'on a pas tout parcouru
			// et qu'on ne touche pas
			while ( ( i < ( oppositeplayer.length() ) ) && ( res == "A l’eau" ) ) {
				ship = battlecrew.get(i) ;
				// si c'est touché
				if ( ship.isHit(shoot) ) {
					// on regarde si c'est coulé
					if ( ship.isDestroyed() ) {
						res = "Touché Coulé" ;
						oppositeplayer.removeShip(ship) ;
					}
					else {
						res = "Touché" ;
					}
				}
				i = i + 1 ;
			}
			System.out.println(res) ;
			newgame.changePlayer() ;
		}
		// on regarde qui a gagné et qui a perdu
		if ( activeplayer.length() == 0 ) {
			System.out.println( newgame.OppositetoString() + "a gagné" ) ;
		}
		else {
			System.out.println( newgame.ActivetoString() + "a gagné" ) ;
		}
		}
	
}
