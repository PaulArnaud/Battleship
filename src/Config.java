import java.util.ArrayList;

public class Config {
	/* Si vous voulez modifier la carte, changer les limites ci dessous, lettre obligatoire, chiffre obligatoirement positif ou nul,
	 vous devez aussi modifier dans player, les fonctions qui affichent la carte au joueur 
	 Merci d'avoir lu */
	public static String limittop = "A" ;
	public static String limitbottom = "J";
	public static String limitright = "9";
	public static String limitleft = "0";

	private Config() {
	}

	public static int lengthCoordonate(String var) {
		return var.length();
	}

	public static String getLetter(String var) {
		char a = var.charAt(0);
		if (Character.isLetter(a)) {
			return Character.toString(a);
		} else {
			return "Error";
		}
	}

	public static int getNumber(String var) {
		int n = var.length();
		int res = 0;
		for (int i = 1; i < n; i++) {
			res = res + convstringtoint(Character.toString(var.charAt(i))) * puissance(10, n - i - 1);
		}
		return res;
	}

	public static int puissance(int a, int p) {
		int result = 1;
		for (int i = 0; i < p; i++) {
			result = result * a;
		}
		return (result);
	}

	// Fonction de conversion d'un string vers un int
	public static int convstringtoint(String a) {
		if (a.equals("1")) {
			return 1;
		} else if (a.equals("2")) {
			return 2;
		} else if (a.equals("3")) {
			return 3;
		} else if (a.equals("4")) {
			return 4;
		} else if (a.equals("5")) {
			return 5;
		} else if (a.equals("6")) {
			return 6;
		} else if (a.equals("7")) {
			return 7;
		} else if (a.equals("8")) {
			return 8;
		} else if (a.equals("9")) {
			return 9;
		} else if (a.equals("0")) {
			return 0;
		} else if (a.equals("A")) {
			return 0;
		} else if (a.equals("B")) {
			return 1;
		} else if (a.equals("C")) {
			return 2;
		} else if (a.equals("D")) {
			return 3;
		} else if (a.equals("E")) {
			return 4;
		} else if (a.equals("F")) {
			return 5;
		} else if (a.equals("G")) {
			return 6;
		} else if (a.equals("H")) {
			return 7;
		} else if (a.equals("I")) {
			return 8;
		} else if (a.equals("J")) {
			return 9;
		} else if (a.equals("K")) {
			return 10;
		} else if (a.equals("L")) {
			return 11;
		} else if (a.equals("M")) {
			return 12;
		} else if (a.equals("N")) {
			return 13;
		} else if (a.equals("O")) {
			return 14;
		} else if (a.equals("P")) {
			return 15;
		} else if (a.equals("Q")) {
			return 16;
		} else if (a.equals("R")) {
			return 17;
		} else if (a.equals("S")) {
			return 18;
		} else if (a.equals("T")) {
			return 19;
		} else if (a.equals("U")) {
			return 20;
		} else if (a.equals("V")) {
			return 21;
		} else if (a.equals("W")) {
			return 22;
		} else if (a.equals("X")) {
			return 23;
		} else if (a.equals("Y")) {
			return 24;
		} else if (a.equals("Z")) {
			return 25;
		} else {
			return -1;
		}
	}

	public static String compfonc(String a, int b) {// fonction qui retourne la position qui se situe une distance b de
		// la position a
		String end = "";
		int j = (int) (Math.random() * 10);
		if (j >= 5) {
			String firstletter = getLetter(a);
			String firstnumber = String.valueOf(getNumber(a));
			int fn = convstringtoint(firstnumber);
			end = firstletter + String.valueOf(fn + b - 1);
		} else {
			String firstletter = getLetter(a);
			String firstnumber = String.valueOf(getNumber(a));
			int fl = convstringtoint(firstletter);
			end = convinttostring(fl + b - 1) + firstnumber;
		}
		return end;
	}
	
	// Conversion d'un int vers un string
		public static String convinttostring(int a) {
			if (a == 0) {
				return "A";
			} else if (a == 1) {
				return "B";
			} else if (a == 2) {
				return "C";
			} else if (a == 3) {
				return "D";
			} else if (a == 4) {
				return "E";
			} else if (a == 5) {
				return "F";
			} else if (a == 6) {
				return "G";
			} else if (a == 7) {
				return "H";
			} else if (a == 8) {
				return "I";
			} else if (a == 9) {
				return "J";
			} else if (a == 10) {
				return "K";
			} else if (a == 11) {
				return "L";
			} else if (a == 12) {
				return "M";
			} else if (a == 13) {
				return "N";
			} else if (a == 14) {
				return "O";
			} else if (a == 15) {
				return "P";
			} else if (a == 16) {
				return "Q";
			} else if (a == 17) {
				return "R";
			} else if (a == 18) {
				return "S";
			} else if (a == 19) {
				return "T";
			} else if (a == 20) {
				return "U";
			} else if (a == 21) {
				return "V";
			} else if (a == 22) {
				return "W";
			} else if (a == 23) {
				return "X";
			} else if (a == 24) {
				return "Y";
			} else if (a == 25) {
				return "Z";
			} else {
				return "hors limite";
			}
		}
		
		// fonction qui construit la liste des positions d'un bateau
		public static ArrayList<String> locacalcul(String start, String end) {
			ArrayList<String> loca = new ArrayList<String>();
			String firstletter = getLetter(start);
			String firstnumber = String.valueOf(getNumber(start));
			String secondletter = getLetter(end);
			String secondnumber = String.valueOf(getNumber(end));
			if (firstletter.equals(secondletter)) {
				int fn = convstringtoint(firstnumber);
				int sn = convstringtoint(secondnumber);
				for (int i = fn; i <= sn; i++) {
					loca.add(firstletter + i);
				}
			} else if (firstnumber.equals(secondnumber)) {
				int fl = convstringtoint(firstletter);
				int sl = convstringtoint(secondletter);
				for (int i = fl; i <= sl; i++) {
					loca.add(convinttostring(i) + firstnumber);
				}
			} else {
				System.out.println("ErrorsetLocalisation");
			}
			return loca;
		}
		
		public static String boatsize(String a) {
			if (a.equals("carrier")){
				return "(size = 5)";
			}
			else if (a.equals("battleship")){
				return "(size = 4)";
			}
			else if (a.equals("submarine") || a.equals("cruiser")){
				return "(size = 3)";
			}
			else if (a.equals("destroyer")) {
				return "(size = 2)";
			}
			else {
				return "(size = unknown)";
			}
		}
}
