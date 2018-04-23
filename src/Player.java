import java.util.ArrayList;

public class Player {
	
	public Ship carier;
	public Ship destroyer;
	public Ship submarine;
	public Ship cruiser;
	public Ship battleship;
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
		myShoots = new ArrayList<String>();
		loadMap();
	}
	public void setPlayername(String a) {
		this.playername = a;
	}
	
	public String getPlayername() {
		return playername;
	}
	
	public Ship getCarier() {
		return carier;
	}

	public void setCarier(Ship carier) {
		this.carier = carier;
	}

	public Ship getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(Ship destroyer) {
		this.destroyer = destroyer;
	}

	public Ship getSubmarine() {
		return submarine;
	}

	public void setSubmarine(Ship submarine) {
		this.submarine = submarine;
	}

	public Ship getCruiser() {
		return cruiser;
	}

	public void setCruiser(Ship cruiser) {
		this.cruiser = cruiser;
	}

	public Ship getBattleship() {
		return battleship;
	}

	public void setBattleship(Ship battleship) {
		this.battleship = battleship;
	}

	public ArrayList<Ship> getBattlecrew() {
		return battlecrew;
	}

	public void setBattlecrew() {
		this.battlecrew.add(carier);
		this.battlecrew.add(battleship);
		this.battlecrew.add(cruiser);
		this.battlecrew.add(submarine);
		this.battlecrew.add(destroyer);	
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
	
	public void affichageGrilleTir(){
		String res = "";
		int square;
		System.out.println("  A B C D E F G H I J");
		for (int i = 0; i < 10;i++){
			res = String.valueOf(i);
			for (int j =0; j<10;j++){
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
		int i = Battleship.convstringtoint(pos.substring(0,1)); 
		int j = Battleship.convstringtoint(pos.substring(1,2));
		if (res == 0){
			map[i][j] = 1;
		}
		else{
			map[i][j] = 2;
		}
	}
}