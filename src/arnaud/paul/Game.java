package arnaud.paul;

public class Game {
	// notion de joueur actif et oppos� ( seulement pour le mode 2joueur) sinon le
	// joueur est le joueur actif de fa�on permanente contre l'ordi
	public Player ActivePlayer;
	public Player OppositePlayer;
	public Computer ComputerPlayer;


	

	public Game(String a) {
		if (a.equals("0")) {
			this.ActivePlayer = new Player();
			this.OppositePlayer = new Player();
		} else {
			this.ActivePlayer = new Player();
			this.ComputerPlayer = new Computer();
			this.ComputerPlayer.setPlayername("Computer");
		}
	}

	public Game(Player player1, Player player2) {
		this.ActivePlayer = player1;
		this.OppositePlayer = player2;
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
		if (ComputerPlayer == null) {
			return ActivePlayer.isDown() || OppositePlayer.isDown();
		} else  {
			return ActivePlayer.isDown() || ComputerPlayer.isDown();
		}
	}

	public Player adversary(Player name) {
		if (this.ActivePlayer == name) {
			return this.OppositePlayer;
		} else {
			return this.ActivePlayer;
		}
	}
}