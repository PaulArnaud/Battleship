import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class PLAYER {
	
	public SHIP Carier;
	public SHIP Destroyer;
	public SHIP Subbmarine;
	public SHIP Cruiser;
	public SHIP Battleship;
	public List Battlecrew;
	public List MyShoots;
	
	

	public SHIP getCarier() {
		return Carier;
	}

	public void setCarier(SHIP carier) {
		Carier = carier;
	}
	
	public SHIP getDestroyer() {
		return Destroyer;
	}
	
	public void setDestroyer(SHIP destroyer) {
		Destroyer = destroyer;
	}
	
	public SHIP getSubbmarine() {
		return Subbmarine;
	}

	public void setSubbmarine(SHIP subbmarine) {
		Subbmarine = subbmarine;
	}

	public SHIP getCruiser() {
		return Cruiser;
	}

	public void setCruiser(SHIP cruiser) {
		Cruiser = cruiser;
	}

	public SHIP getBattleship() {
		return Battleship;
	}

	public void setBattleship(SHIP battleship) {
		Battleship = battleship;
	}

	public List getBattlecrew() {
		return Battlecrew;
	}

	public void setBattlecrew(List battlecrew) {
		Battlecrew = battlecrew;
	}

	public List getMyShoots() {
		return MyShoots;
	}

	public void setMyShoots(List myShoots) {
		MyShoots = myShoots;
	}

	public boolean isDown(){
		return false;
	} /* on regarde si Battlecrew est videC*/


}
