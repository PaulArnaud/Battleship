import java.util.ArrayList;

public class Game {
	// notion de joueur actif et oppos� ( seulement pour le mode 2joueur) sinon le joueur est le joueur actif de fa�on permanente contre l'ordi
	public Player ActivePlayer;
	public Player OppositePlayer;
	public ArrayList<String> Grille;

	public Player getActivePlayer() {
		return ActivePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		ActivePlayer = activePlayer;
	}

	public Player getOppositePlayer() {
		return OppositePlayer;
	}

	public void setOppositePlayer(Player oppositePlayer) {
		OppositePlayer = oppositePlayer;
	}

	public String ActivetoString() {
		return "[ActivePlayer=" + ActivePlayer + "]";
	}

	public String OppositetoString() {
		return "[OppositePlayer=" + OppositePlayer + "]";
	}

	public void changePlayer() {
		Player TempPlayer;
		TempPlayer = this.ActivePlayer;
		this.ActivePlayer = this.OppositePlayer;
		this.OppositePlayer = TempPlayer;
	}
	// fonction vraie ou fausse si la partie est termin�e ou non
	public boolean IsOver() {
		return ActivePlayer.isDown() || OppositePlayer.isDown();
	}
	// initialise la liste des positions possibles
	public void setGrille() {
		ArrayList<String> Grille = new ArrayList<String>();
		Grille.add("A0");
		Grille.add("A1");
		Grille.add("A2");
		Grille.add("A3");
		Grille.add("A4");
		Grille.add("A5");
		Grille.add("B0");
		Grille.add("B1");
		Grille.add("B2");
		Grille.add("B3");
		Grille.add("B4");
		Grille.add("B5");
		Grille.add("C0");
		Grille.add("C1");
		Grille.add("C2");
		Grille.add("C3");
		Grille.add("C4");
		Grille.add("C5");
		Grille.add("D0");
		Grille.add("D1");
		Grille.add("D2");
		Grille.add("D3");
		Grille.add("D4");
		Grille.add("D5");
		Grille.add("E0");
		Grille.add("E1");
		Grille.add("E2");
		Grille.add("E3");
		Grille.add("E4");
		Grille.add("E5");
		Grille.add("F0");
		Grille.add("F1");
		Grille.add("F2");
		Grille.add("F3");
		Grille.add("F4");
		Grille.add("F5");
		//1�re partie 36 positions
		Grille.add("G0");
		Grille.add("G1");
		Grille.add("G2");
		Grille.add("G3");
		Grille.add("G4");
		Grille.add("G5");
		Grille.add("G6");
		Grille.add("A6");
		Grille.add("B6");	
		Grille.add("C6");		
		Grille.add("D6");		
		Grille.add("E6");		
		Grille.add("F6");
		//2�me partie 36 + 13 positions
		Grille.add("G7");
		Grille.add("A7");
		Grille.add("B7");
		Grille.add("C7");
		Grille.add("D7");
		Grille.add("E7");
		Grille.add("F7");
		Grille.add("H0");
		Grille.add("H1");
		Grille.add("H2");
		Grille.add("H3");
		Grille.add("H4");
		Grille.add("H5");
		Grille.add("H6");
		Grille.add("H7");
		// 3�me partie  36 +13 + 15 positions
		Grille.add("A8");
		Grille.add("B8");
		Grille.add("C8");
		Grille.add("D8");
		Grille.add("E8");
		Grille.add("F8");
		Grille.add("G8");
		Grille.add("H8");
		Grille.add("I0");
		Grille.add("I1");
		Grille.add("I2");
		Grille.add("I3");
		Grille.add("I4");
		Grille.add("I5");
		Grille.add("I6");
		Grille.add("I7");
		Grille.add("I8");
		// 4�me partie 36 +13 +15 + 17 positions
		Grille.add("I9");
		Grille.add("A9");
		Grille.add("B9");
		Grille.add("C9");
		Grille.add("D9");
		Grille.add("E9");
		Grille.add("F9");
		Grille.add("G9");
		Grille.add("H9");
		Grille.add("J1");
		Grille.add("J2");
		Grille.add("J3");
		Grille.add("J4");
		Grille.add("J5");
		Grille.add("J6");
		Grille.add("J7");
		Grille.add("J8");
		Grille.add("J9");
		Grille.add("J0");
		this.Grille = Grille;
	}
}