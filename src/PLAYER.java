import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class PLAYER {
	
	public SHIP Carier;
	public SHIP Destroyer;
	public SHIP Subbmarine;
	public SHIP Cruiser;
	public SHIP Battleship;
	public SHIP[] Battlecrew;
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

	public SHIP[] getBattlecrew() {
		return Battlecrew;
	}

	public void setBattlecrew() {
		Battlecrew[0]= Carier;
		Battlecrew[1]= Destroyer;
		Battlecrew[2]= Subbmarine;
		Battlecrew[3]= Cruiser;
		Battlecrew[4]= Battleship;	
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
	
	public boolean hasAlreadyShot(int shoot){
		return true;
	}
	
	public int length() {
		return 0;
	}

	public void removeShip(SHIP sp) {
	}
}
