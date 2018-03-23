import java.util.ArrayList;
import java.util.Scanner;

public class BATTLESHIP {
	
	public static Scanner reader = new Scanner(System.in);

	public static void main(){

		// Initialisation //
		String coordA;
		String coordB;
	
		GAME newgame = new GAME();
	
		PLAYER player1 = new PLAYER();
		PLAYER player2 = new PLAYER();
	
		System.out.println("Joueur choississez des coordonnées pour votre Carier");
		coordA= reader.next("D=");
		coordB= reader.next("F=");
		///// il faut vérifier ////
		SHIP carier = new SHIP(coordA,coordB,"carier");
		player1.setCarier(carier);
	
		System.out.println("Joueur choississez des coordonnées pour votre Battleship");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP battleship = new SHIP(coordA,coordB,"battleship");
		player1.setBattleship(battleship);
	
		System.out.println("Joueur choississez des coordonnées pour votre Cruiser");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP cruiser = new SHIP(coordA,coordB,"cruiser");
		player1.setCruiser(cruiser);
	
		System.out.println("Joueur choississez des coordonnées pour votre Submarine");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP submarine = new SHIP(coordA,coordB,"submarine");
		player1.setSubmarine(submarine);
	
		System.out.println("Joueur choississez des coordonnées pour votre Destroyer");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		SHIP destroyer = new SHIP(coordA,coordB,"destroyer");
		player1.setDestroyer(destroyer);
		player1.setBattlecrew();
	
		
		newgame.setActivePlayer(player1);
		newgame.setOppositePlayer(player2);
		PLAYER activeplayer = newgame.getActivePlayer();
		PLAYER oppositeplayer = newgame.getOppositePlayer();
		String shoot;
		ArrayList<SHIP> battlecrew;
		while (!newgame.IsOver()) {
			System.out.println( newgame.ActivetoString()  +  " Choisissez une position à attaquer(exemple: A1, J10)" );
			shoot = reader.next("Shoot = ");
			while (activeplayer.hasAlreadyShot(shoot)){
				System.out.println( newgame.ActivetoString() +  " Choisissez une nouvelle position, vous avez déjà attaqué ici(exemple: A1, J10) ");
				shoot = reader.next("Shoot = ");
			}
			
			battlecrew = oppositeplayer.getBattlecrew();
			int i = 0;
			String res="A l’eau";
			SHIP ship;
			while ((i<(oppositeplayer.length()))&&(res == "A l’eau")) {
				ship = battlecrew.get(i);
				if (ship.isHit(shoot)) {
					if (ship.isDestroyed()) {
						res="Touché Coulé";
						oppositeplayer.removeShip(ship);
					}
					else {
						res="Touché";
					}
				}
				i = i+1;
			}
			System.out.println(res);
			newgame.changePlayer();
		}
		if (activeplayer.length()==0) {
			System.out.println( newgame.OppositetoString() + "a gagné");
		}
		else {
			System.out.println( newgame.ActivetoString() + "a gagné");
		}
		}
	
}
