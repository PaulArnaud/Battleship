import java.util.ArrayList;

public class Game {
	// notion de joueur actif et oppos� ( seulement pour le mode 2joueur) sinon le joueur est le joueur actif de fa�on permanente contre l'ordi
	public Player ActivePlayer;
	public Player OppositePlayer;
	public ArrayList<String> Grille;

	public Game() {
		this.Grille = createGrille();
	}
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
	public ArrayList<String> createGrille() {
		String coord ="";
		ArrayList<String> Grille = new ArrayList<String>();
		for (int i = Config.convstringtoint(Config.limtop);i<= Config.convstringtoint(Config.limbottom);i++) {
			for (int j = Config.convstringtoint(Config.limleft);j<= Config.convstringtoint(Config.limright);j++) {
				coord = Config.convinttostring(j)+String.valueOf(i);
				Grille.add(coord);
			}
		}
		return Grille;
	}
}