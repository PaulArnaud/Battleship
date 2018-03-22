import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class BATTLESHIP {
	public static Scanner reader = new Scanner(System.in);

	public static void main(){

		// Initialisation //
		String coordA;
		String coordB;
		int cA;
		int cB;
	
		GAME newgame = new GAME();
	
		PLAYER player1 = new PLAYER();
		PLAYER player2 = new PLAYER();
	
		System.out.println("Joueur choississez des coordonn�es pour votre Carrier");
		coordA= reader.next("D=");
		coordB= reader.next("F=");
		cA = conversion(coordA);
		cB = conversion(coordB);
		///// il faut v�rifier ////
		SHIP carier = new SHIP();
		carier.setCol(cA);
		carier.setRow(cB);
		player1.setCarier(carier);
	
		System.out.println("Joueur choississez des coordonn�es pour votre Battleship");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		// conversion //
		// cA = int(coordA)
		// cB = int(coordB)
		SHIP battleship = new SHIP();
		player1.setBattleship(battleship);
	
		System.out.println("Joueur choississez des coordonn�es pour votre Cruiser");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		// conversion //
		// cA = int(coordA)
		// cB = int(coordB)
		SHIP cruiser = new SHIP();
		player1.setCruiser(cruiser);
	
		System.out.println("Joueur choississez des coordonn�es pour votre Submarine");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		// conversion //
		// cA = int(coordA)
		// cB = int(coordB)
		SHIP submarine = new SHIP();
		player1.setSubbmarine(submarine);
	
		System.out.println("Joueur choississez des coordonn�es pour votre Destroyer");
		coordA=reader.next("D=");
		coordB=reader.next("F=");
		// conversion //
		// cA = int(coordA)
		// cB = int(coordB)
		SHIP destroyer = new SHIP();
		player1.setDestroyer(destroyer);
	
		newgame.setActivePlayer(player1);
		newgame.setOppositePlayer(player2);
		PLAYER activeplayer = newgame.getActivePlayer();
		PLAYER oppositeplayer = newgame.getOppositePlayer();
		String shoot;
		int s;
		SHIP[] battlecrew;
		while (!newgame.IsOver()) {
			System.out.println( newgame.ActivetoString()  +  " Choisissez une position � attaquer(exemple: A1, J10)" );
			shoot = reader.next("Shoot = ");
			s = conversion(shoot);
			while (activeplayer.hasAlreadyShot(s)){
				System.out.println( newgame.ActivetoString() +  " Choisissez une nouvelle position, vous avez d�j� attaqu� ici(exemple: A1, J10) ");
				shoot = reader.next("Shoot = ");
				s = conversion(shoot);
			}
			
			battlecrew = oppositeplayer.getBattlecrew();
			int i = 0;
			String res="A l�eau";
			SHIP ship;
			while ((i<(oppositeplayer.length()))&&(res == "A l�eau")) {
				ship = battlecrew[i];
				if (ship.isHit(s)) {
					if (ship.isDestroyed()) {
						res="Touch� Coul�";
						oppositeplayer.removeShip(ship);
					}
					else {
						res="Touch�";
					}
				}
				i = i+1;
			}
			System.out.println(res);
			newgame.changePlayer();
		}
		if (activeplayer.length()==0) {
			System.out.println( newgame.OppositetoString() + "a gagn�");
		}
		else {
			System.out.println( newgame.ActivetoString() + "a gagn�");
		}
		}
	
	
		public static int conversion(String a) {
			return 0;
		}
	
}
