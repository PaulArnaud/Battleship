import java.util.ArrayList;

import java.util.Scanner;

public class BATTLESHIP {
	
	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args){
		
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
		
		// Création de la variable de tir
		String shoot;
		
		// Création de la variable battlecrew
		ArrayList<SHIP> battlecrew;
	

		// Mise en place de tous les bateaux du joueur 1
		System.out.println("Joueur 1 choississez des coordonnées pour votre Carier");
		System.out.println("Coordonnée de début : ");
		coordA= reader.next();
		System.out.println("Coordonnée de fin : ");
		coordB= reader.next();
		///// il faut vérifier ////
		SHIP carier1 = new SHIP(coordA,coordB,"carier");
		player1.setCarier(carier1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Battleship");
		System.out.println("Coordonnée de début : ");
		coordA=reader.next();
		System.out.println("Coordonnée de fin : ");
		coordB=reader.next();
		SHIP battleship1 = new SHIP(coordA,coordB,"battleship");
		player1.setBattleship(battleship1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Cruiser");
		System.out.println("Coordonnée de début : ");
		coordA=reader.next();
		System.out.println("Coordonnée de fin : ");
		coordB=reader.next();
		SHIP cruiser1 = new SHIP(coordA,coordB,"cruiser");
		player1.setCruiser(cruiser1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Submarine");
		System.out.println("Coordonnée de début : ");
		coordA=reader.next();
		System.out.println("Coordonnée de fin : ");
		coordB=reader.next();
		SHIP submarine1 = new SHIP(coordA,coordB,"submarine");
		player1.setSubmarine(submarine1);
	
		System.out.println("Joueur 1 choississez des coordonnées pour votre Destroyer");
		System.out.println("Coordonnée de début : ");
		coordA=reader.next();
		System.out.println("Coordonnée de fin : ");
		coordB=reader.next();
		SHIP destroyer1 = new SHIP(coordA,coordB,"destroyer");
		player1.setDestroyer(destroyer1);
		player1.setBattlecrew(carier1,battleship1,cruiser1,submarine1,destroyer1);
		
		
		
		// Mise en place des bateaux du joueur 2
		///// il faut vérifier ////
		SHIP carier2 = new SHIP("A1","A5","carier");
		player2.setCarier(carier2);
	
		SHIP battleship2 = new SHIP("B1","B4","battleship");
		player2.setBattleship(battleship2);
		
		SHIP cruiser2 = new SHIP("C1","C3","cruiser");
		player2.setCruiser(cruiser2);
	
		SHIP submarine2 = new SHIP("D1","D3","submarine");
		player2.setSubmarine(submarine2);
	
		SHIP destroyer2 = new SHIP("E1","E2","destroyer");
		player2.setDestroyer(destroyer2);
		player2.setBattlecrew(carier2,battleship2,cruiser2,submarine2,destroyer2);

		
		// Début de la partie
		while (!newgame.IsOver()) {
			// demande des coordonnées du tir
			System.out.println( newgame.ActivetoString()  +  " Choisissez une position à attaquer(exemple: A1, J10)" );
			System.out.println("Coordonnée du tir :");
			shoot = reader.next();
			// si le joueur tir sur une case déjà essayée on lui redemande des coordonnées
			while (newgame.ActivePlayer.hasAlreadyShot(shoot)){
				System.out.println( newgame.ActivetoString() +  " Choisissez une nouvelle position, vous avez déjà attaqué ici(exemple: A1, J10) ");
				System.out.println("Coordonnée du tir :");
				shoot = reader.next();
			}
			newgame.ActivePlayer.myShoots.add(shoot);
			System.out.println(newgame.ActivePlayer.myShoots);
			// on instancie le crew du joueur adverse pour savoir si on touche
			battlecrew = newgame.OppositePlayer.getBattlecrew();
			int i = 0 ;
			String res = "A l’eau" ;
			SHIP ship;
			// on boucle tant qu'il y a des bateaux dans la liste et que l'on a pas tout parcouru
			// et qu'on ne touche pas
			while ( ( i < ( newgame.OppositePlayer.length() ) ) && ( res.equals("A l’eau") ) ) {
				ship = battlecrew.get(i) ;
				System.out.println(ship);
				// si c'est touché
				if ( ship.isHit(shoot) ) {
					// on regarde si c'est coulé
					if ( ship.isDestroyed() ) {
						res = "Touché Coulé" ;
						newgame.OppositePlayer.removeShip(ship) ;
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
		if ( newgame.ActivePlayer.length() == 0 ) {
			System.out.println( newgame.OppositetoString() + "a gagné" ) ;
		}
		else {
			System.out.println( newgame.ActivetoString() + "a gagné" ) ;
		}
		}
	
}
