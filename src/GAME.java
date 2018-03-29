import java.util.ArrayList;

public class GAME {
	public PLAYER ActivePlayer;
	public PLAYER OppositePlayer;
	public ArrayList<String> Grille;

	public PLAYER getActivePlayer() {
		return ActivePlayer;
	}
	public void setActivePlayer(PLAYER activePlayer) {
		ActivePlayer = activePlayer;
	}
	public PLAYER getOppositePlayer() {
		return OppositePlayer;
	}
	public void setOppositePlayer(PLAYER oppositePlayer) {
		OppositePlayer = oppositePlayer;
	}

	public String ActivetoString() {
		return "[ActivePlayer=" + ActivePlayer + "]";
	}

	public String OppositetoString() {
		return "[OppositePlayer=" + OppositePlayer + "]";
	}
	public void changePlayer() {
		PLAYER TempPlayer;
		TempPlayer = this.ActivePlayer;
		this.ActivePlayer = this.OppositePlayer;
		this.OppositePlayer = TempPlayer;
	}

	public boolean IsOver() {
		return ActivePlayer.isDown() || OppositePlayer.isDown();
	}

	public void setGrille(){
		ArrayList<String> Grille = new ArrayList<String>();
		Grille.add("A1"); Grille.add("A2"); Grille.add("A3"); Grille.add("A4");Grille.add("A5"); Grille.add("A6"); Grille.add("A7"); Grille.add("A8"); Grille.add("A9"); Grille.add("A10"); 
		Grille.add("B1"); Grille.add("B2"); Grille.add("B3"); Grille.add("B4");Grille.add("B5"); Grille.add("B6"); Grille.add("B7"); Grille.add("B8"); Grille.add("B9"); Grille.add("B10");
		Grille.add("C1"); Grille.add("C2"); Grille.add("C3"); Grille.add("C4");Grille.add("C5"); Grille.add("C6"); Grille.add("C7"); Grille.add("C8"); Grille.add("C9"); Grille.add("C10");
		Grille.add("D1"); Grille.add("D2"); Grille.add("D3"); Grille.add("D4");Grille.add("D5"); Grille.add("D6"); Grille.add("D7"); Grille.add("D8"); Grille.add("D9"); Grille.add("D10");
		Grille.add("E1"); Grille.add("E2"); Grille.add("E3"); Grille.add("E4");Grille.add("E5"); Grille.add("E6"); Grille.add("E7"); Grille.add("E8"); Grille.add("E9"); Grille.add("E10");
		Grille.add("F1"); Grille.add("F2"); Grille.add("F3"); Grille.add("F4");Grille.add("F5"); Grille.add("F6"); Grille.add("F7"); Grille.add("F8"); Grille.add("F9"); Grille.add("F10");
		Grille.add("G1"); Grille.add("G2"); Grille.add("G3"); Grille.add("G4");Grille.add("G5"); Grille.add("G6"); Grille.add("G7"); Grille.add("G8"); Grille.add("G9"); Grille.add("G10");
		Grille.add("H1"); Grille.add("H2"); Grille.add("H3"); Grille.add("H4");Grille.add("H5"); Grille.add("H6"); Grille.add("H7"); Grille.add("H8"); Grille.add("H9"); Grille.add("H10");
		Grille.add("I1"); Grille.add("I2"); Grille.add("I3"); Grille.add("I4");Grille.add("I5"); Grille.add("I6"); Grille.add("I7"); Grille.add("I8"); Grille.add("I9"); Grille.add("I10");
		Grille.add("J1"); Grille.add("J2"); Grille.add("J3"); Grille.add("J4");Grille.add("J5"); Grille.add("J6"); Grille.add("J7"); Grille.add("J8"); Grille.add("J9"); Grille.add("J10");
		this.Grille = Grille;
	}
	
	public ArrayList<String> test(String start,String end) {
		ArrayList<String> loca = new ArrayList<String>();
		String firstletter = (String)start.substring(0, 1);
		//System.out.println(firstletter);
		String firstnumber = (String)start.substring(1, 2);
		//System.out.println(firstnumber);
		String secondletter = (String)end.substring(0, 1);
		//System.out.println(secondletter);
		String secondnumber = (String)end.substring(1, 2);
		//System.out.println(secondnumber);		
		if (firstletter.equals(secondletter)){
			int fn = convstringtoint(firstnumber);
			
			int sn = convstringtoint(secondnumber);
			
			for (int i = fn ; i <= sn; i++) {
				loca.add(firstletter + i);
			}
		}
		else if (firstnumber.equals(secondnumber)){
			int fl = convstringtoint(firstletter);
			int sl = convstringtoint(secondletter);
			for (int i = fl ; i <=sl ; i++) {
				loca.add(convinttostring(i) + firstnumber);
			}	
		}
		else {
			System.out.println("ErrorTest");
		}
			
		return loca;
	}
	
	// Fonction de conversion d'un string vers un int
		public int convstringtoint(String a) {
			if (a.equals("1")) {
				return 1;
			}
			else if (a.equals("2")) {
				return 2;
			}
			else if (a.equals("3")) {
				return 3;
			}	
			else if (a.equals("4")) {
				return 4;
			}
			else if (a.equals("5")) {
				return 5;
			}
			else if (a.equals("6")) {
				return 6;
			}
			else if (a.equals("7")) {
				return 7;
			}
			else if (a.equals("8")) {
				return 8;
			}
			else if (a.equals("9")) {
				return 9;
			}
			else if (a.equals("10")) {
				return 10;
			}
			else if (a.equals("A")) {
				return 1;
			}
			else if (a.equals("B")) {
				return 2;
			}
			else if (a.equals("C")) {
				return 3;
			}	
			else if (a.equals("D")) {
				return 4;
			}
			else if (a.equals("E")) {
				return 5;
			}
			else if (a.equals("F")) {
				return 6;
			}
			else if (a.equals("G")) {
				return 7;
			}
			else if (a.equals("H")) {
				return 8;
			}
			else if (a.equals("I")) {
				return 9;
			}
			else if (a.equals("J")) {
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
				return "Errorconvertionint";
			}
		}
}
