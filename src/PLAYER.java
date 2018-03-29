import java.util.ArrayList;

public class PLAYER {
	
	public SHIP carier;
	public SHIP destroyer;
	public SHIP submarine;
	public SHIP cruiser;
	public SHIP battleship;
	public ArrayList<SHIP> battlecrew;
	public ArrayList<String> myShoots;
	
	public PLAYER() {
		battlecrew = new ArrayList<SHIP>();
		myShoots = new ArrayList<String>();
	}

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

	public ArrayList<SHIP> getBattlecrew() {
		return battlecrew;
	}

	public void setBattlecrew(SHIP a,SHIP b,SHIP c,SHIP d,SHIP e) {
		this.battlecrew.add(a);
		this.battlecrew.add(b);
		this.battlecrew.add(c);
		this.battlecrew.add(d);
		this.battlecrew.add(e);	
	}

	public ArrayList<String> getMyShoots() {
		return myShoots;
	}

	public void setMyShoots(ArrayList<String> myshoots) {
		myShoots = myshoots;
	}

	public boolean isDown(){
		return battlecrew.isEmpty();
	} 
	
	public boolean hasAlreadyShot(String shoot){
		return this.myShoots.contains(shoot);
	}
	
	public int length() {
		return battlecrew.size();
	}

	public void removeShip(SHIP sp) {
		battlecrew.remove(sp);
	}
}
