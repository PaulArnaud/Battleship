import java.util.ArrayList;

public class SHIP {
    public String start;
    public String end;
    public ArrayList<String> localisation;
    
    
    
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
	
	public void setLocalisation(String start,String end) {
		ArrayList<String> loca = new ArrayList<String>();;
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
	public int convstringtoint(String a) {
		return 1;
	}
	
	public String convinttostring(int a) {
		return "a";
	}
}