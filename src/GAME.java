
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
	
	public void changePlayer() {
		PLAYER TempPlayer;
		TempPlayer = this.ActivePlayer;
		this.ActivePlayer = this.OppositePlayer;
		this.OppositePlayer = TempPlayer;
	}

}
