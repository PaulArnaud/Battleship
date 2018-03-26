import java.util.ArrayList;

public class SHIP {
    public String start; // début du bateau
    public String end; // fin du bateau
    public ArrayList<String> localisation; // listes de toutes les cases sur lesquelles le bateau se situe
    
    // Fonction de création d'un bateau
	public SHIP(String start, String end, String name) {
		super();
		this.start = start;
		this.end = end;
		this.setLocalisation(start,end);
		if ((name == "carrier") && ( this.localisation.size() == 5)){
			System.out.println("OK");
		}
		else if ((name == "battleship") && ( this.localisation.size() == 4)){
			System.out.println("OK");
		}
		
		else if ((name == "cruiser") && ( this.localisation.size() == 3)){
			System.out.println("OK");
		}
		
		else if ((name == "submarine") && ( this.localisation.size() == 3)){
			System.out.println("OK");
		}
		
		else if ((name == "destroyer") && ( this.localisation.size() == 2)){
			System.out.println("OK");
		}
		else {
			System.out.println("Error");
			localisation = null;
		}
		
	}
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public ArrayList<String> getLocalisation() {
		return localisation;
	}
	
	// Fonction pour remplir la liste des coordonnées du bateau
	public void setLocalisation(String start,String end) {
		ArrayList<String> loca = new ArrayList<String>();
		String firstletter = start.substring(0, 1);
		String firstnumber = start.substring(1, 2);
		String secondletter = end.substring(0, 1);
		String secondnumber = end.substring(1, 2);
		if (firstletter == secondletter){
			int fn = convstringtoint(firstnumber);
			int sn = convstringtoint(secondnumber);
			for (int i = fn ; i <= sn; i++) {
				loca.add(firstletter + "i");
			}
		}
		else if (firstnumber == secondnumber){
			int fl = convstringtoint(firstletter);
			int sl = convstringtoint(secondletter);
			for (int i = fl ; i <=sl ; i++) {
				loca.add(convinttostring(i) + firstnumber);
			}	
		}
		else {
			System.out.println("Error");
		}
			
		this.localisation = loca;
	}
	
	public boolean isHit(String pos){
		if (this.localisation.contains(pos)) {
			localisation.remove(pos);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isDestroyed(){
		if (this.localisation.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Fonction de conversion d'un string vers un int
	public int convstringtoint(String a) {
		if (a=="1") {
			return 1;
		}
		else if (a=="2") {
			return 2;
		}
		else if (a=="3") {
			return 3;
		}	
		else if (a=="4") {
			return 4;
		}
		else if (a=="5") {
			return 5;
		}
		else if (a=="6") {
			return 6;
		}
		else if (a=="7") {
			return 7;
		}
		else if (a=="8") {
			return 8;
		}
		else if (a=="9") {
			return 9;
		}
		else if (a=="10") {
			return 10;
		}
		else if (a=="A") {
			return 1;
		}
		else if (a=="B") {
			return 2;
		}
		else if (a=="C") {
			return 3;
		}	
		else if (a=="D") {
			return 4;
		}
		else if (a=="E") {
			return 5;
		}
		else if (a=="F") {
			return 6;
		}
		else if (a=="G") {
			return 7;
		}
		else if (a=="H") {
			return 8;
		}
		else if (a=="I") {
			return 9;
		}
		else if (a=="J") {
			return 10;
		}
		else {
			return -1;
		}
	}
	
	// Conversion d'un int vers un string
	public String convinttostring(int a) {
		if (a==0) {
			return "A";
		}
		else if (a==1) {
			return "B";
		}
		else if (a==2) {
			return "C";
		}	
		else if (a==3) {
			return "D";
		}
		else if (a==4) {
			return "E";
		}
		else if (a==5) {
			return "F";
		}
		else if (a==6) {
			return "G";
		}
		else if (a==7) {
			return "H";
		}
		else if (a==8) {
			return "I";
		}
		else if (a==9) {
			return "J";
		}
		else {
			return "Error";
		}
	}
}