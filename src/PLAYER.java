
public class PLAYER {
	
	public SHIP carier;
	public SHIP destroyer;
	public SHIP submarine;
	public SHIP cruiser;
	public SHIP battleship;
	public SHIP[] battlecrew;
	public String[] myShoots;

	public SHIP getCarier() {
		return carier;
	}

	public void setCarier(SHIP carier) {
		this.carier = carier;
	}

	public SHIP getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(SHIP destroyer) {
		this.destroyer = destroyer;
	}

	public SHIP getSubmarine() {
		return submarine;
	}

	public void setSubmarine(SHIP submarine) {
		this.submarine = submarine;
	}

	public SHIP getCruiser() {
		return cruiser;
	}

	public void setCruiser(SHIP cruiser) {
		this.cruiser = cruiser;
	}

	public SHIP getBattleship() {
		return battleship;
	}

	public void setBattleship(SHIP battleship) {
		this.battleship = battleship;
	}

	public SHIP[] getBattlecrew() {
		return battlecrew;
	}

	public void setBattlecrew() {
		battlecrew[0]= carier;
		battlecrew[1]= destroyer;
		battlecrew[2]= submarine;
		battlecrew[3]= cruiser;
		battlecrew[4]= battleship;	
	}

	public String[] getMyShoots() {
		return myShoots;
	}

	public void setMyShoots(String[] myshoots) {
		myShoots = myshoots;
	}

	public boolean isDown(){
		return false;
	} /* on regarde si Battlecrew est videC*/
	
	public boolean hasAlreadyShot(String shoot){
		return true;
	}
	
	public int length() {
		return 0;
	}

	public void removeShip(SHIP sp) {
	}
}
