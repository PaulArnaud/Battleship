import java.util.ArrayList;

public class COMPUTER extends PLAYER {
	// attaque
	public ArrayList<String> myshoots;
	public ArrayList<String> currentboat;
	public String state;
	// "défense"
	public ArrayList<SHIP> battlecrew;
	public SHIP carier;
	public SHIP destroyer;
	public SHIP submarine;
	public SHIP cruiser;
	public SHIP battleship;
	public ArrayList<String> gril;
	
	public ArrayList<String> getMyshoots() {
		return myshoots;
	}

	public void setMyshoots(ArrayList<String> myshoots) {
		this.myshoots = myshoots;
	}

	public ArrayList<String> getCurrentboat() {
		return currentboat;
	}

	public void setCurrentboat(ArrayList<String> currentboat) {
		this.currentboat = currentboat;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBattlecrew(ArrayList<SHIP> battlecrew) {
		this.battlecrew.add(carier);
		this.battlecrew.add(battleship);
		this.battlecrew.add(cruiser);
		this.battlecrew.add(submarine);
		this.battlecrew.add(destroyer);
	}
	/// constructeur

	public SHIP hasardcontruc(GAME n,String name) {
		String posfinal;
		if (name.equals("carier")){
			int i = (int)(Math.random() *36) ;
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a,5);
			return new SHIP(a,posfinal,"carier");
		}
		else if (name.equals("battleship")) {
			int i = (int)(Math.random() *49) ;
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a,4);
			return new SHIP(a,posfinal,"battleship");
		}
		else if (name.equals("cruiser")) {
			int i = (int)(Math.random() *64) ;
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a,3);
			return new SHIP(a,posfinal,"cruiser");
		}
		else if (name.equals("submarine")) {
			int i = (int)(Math.random() *64) ;
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a,3);
			return new SHIP(a,posfinal,"submarine");
		}
		else  {
			int i = (int)(Math.random() *81) ;
			String a = n.Grille.get(i);
			posfinal = BATTLESHIP.compfonc(a,2);
			return new SHIP(a,posfinal,"destroyer");
		}
	}

}
