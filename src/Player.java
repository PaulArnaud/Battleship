import java.util.ArrayList;

public class Player {
	public ArrayList<Ship> savebattlecrew;
	public ArrayList<Ship> battlecrew;
	public ArrayList<String> myShoots;
	public String playername;
	
	public Player() {
		battlecrew = new ArrayList<Ship>();
		savebattlecrew = new ArrayList<Ship>();
		myShoots = new ArrayList<String>();
	}
	public void setPlayername(String a) {
		this.playername = a;
	}
	public void SaveBattlecrew() {
		for (int i = 0; i<battlecrew.size();i++) {
			this.savebattlecrew.add(this.battlecrew.get(i).clone());
		}
	}
	
	public String getPlayername() {
		return playername;
	}
	
	public ArrayList<Ship> getBattlecrew() {
		return battlecrew;
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
	//fonction qui renvoie le nb de bateau restant du joueur
	public int length() {
		return battlecrew.size();
	}
	// fonction qui supprime un bateau de la liste des bateaux du joueur
	public void removeShip(Ship sp) {
		battlecrew.remove(sp);
	}
	
	public boolean isIn(String a) {
		int n = this.savebattlecrew.size();
		boolean res = false;
		for (int i =0; i<n;i++) {
			if (this.savebattlecrew.get(i).isHit(a)) {
				res = true;
			}
		}
		return res;
	}
}