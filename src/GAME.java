
public class GAME {
	public PLAYER ActivePlayer;
	public PLAYER OppositePlayer;
	
	public PLAYER getActivePlayer() {
		return ActivePlayer;
	}
	public void setActivePlayer(PLAYER activePlayer) {
		ActivePlayer = activePlayer;
	}
	public PLAYER getOppositePlayer() {
		return OppositePlayer;
	}
	public void setOppositePlayer(PLAYER oppositePlayer) {
		OppositePlayer = oppositePlayer;
	}
	
	public String ActivetoString() {
		return "[ActivePlayer=" + ActivePlayer + "]";
	}
	
	public String OppositetoString() {
		return "[OppositePlayer=" + OppositePlayer + "]";
	}
	public void changePlayer() {
		PLAYER TempPlayer;
		TempPlayer = this.ActivePlayer;
		this.ActivePlayer = this.OppositePlayer;
		this.OppositePlayer = TempPlayer;
	}
	
	public boolean IsOver() {
		return ActivePlayer.isDown() || OppositePlayer.isDown();
	}
}
