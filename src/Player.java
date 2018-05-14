import java.util.ArrayList;

public class Player {
	public ArrayList<Ship> battlecrew;
	public ArrayList<Ship> losses;
	public ArrayList<String> myShoots;
	public String playername;
	
	public Player() {
		battlecrew = new ArrayList<Ship>();
		myShoots = new ArrayList<String>();
		losses = new ArrayList<Ship>();
	}
	public void setPlayername(String a) {
		this.playername = a;
	}
	
	public String getPlayername() {
		return playername;
	}
	
	public ArrayList<Ship> getBattlecrew() {
		return battlecrew;
	}
	
	public ArrayList<Ship> getLosses() {
		return losses;
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
		losses.add(sp);
		battlecrew.remove(sp);
	}
	
	public boolean isInCrew(String a) {
		int n = this.losses.size();
		int m = this.battlecrew.size();
		boolean res = false;
		for (int i =0; i<n;i++) {
			if (this.losses.get(i).isHit(a)) {
				res = true;
			}
		}
		for (int j= 0;j<m;j++) {
			if (this.battlecrew.get(j).isHit(a)){
				res = true ;
			}
		}
		return res;
	}
}