import java.util.ArrayList;

public class Player {
	public ArrayList<Ship> savebattlecrew;
	public ArrayList<Ship> battlecrew;
	public ArrayList<String> myShoots;
	public String playername;
	public int[][] map;
	
	public void loadMap(){
		map = new int[10][10];
		for (int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				map[i][j] = 0;
			}
		}
	}
	public Player() {
		battlecrew = new ArrayList<Ship>();
		savebattlecrew = new ArrayList<Ship>();
		myShoots = new ArrayList<String>();
		loadMap();
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
	
	public void showGrilleTir(){
		String res = "";
		int square;
		System.out.println("  A B C D E F G H I J");///////////////////ligne à modifier en cas de modification de la carte
		for (int i = 0; i < 10;i++){//////////////ligne à modifier 10 correspond à la hauteur
			res = String.valueOf(i);
			for (int j =0; j<10;j++){///////////ligne à modifier 10 correspond à la longueur
				square = map[j][i];
				if (square == 2){
					res = res + " " + "x" ;
				}
				else if (square == 1){
					res = res + " " + "o";
				}
				else {
					res = res + " " + "~" ;
				}
			}
			System.out.println(res);
		}
	}
	
	public void updatemap(String pos,int res){
		int i = Config.convstringtoint(Config.getLetter(pos)); 
		int j = Config.getNumber(pos);
		if (res == 0){
			map[i][j] = 1;
		}
		else{
			map[i][j] = 2;
		}
	}
}