
public class SHIP {
    public String start;
    public String end;
    public String[] localisation;
    
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
	public String[] getLocalisation() {
		return localisation;
	}
	
	public void setLocalisation(String start,String end) {
		String[] loca;
		String firstletter = start.substring(0, 1);
		String firstnumber = start.substring(1, 2);
		String secondletter = end.substring(0, 1);
		String secondnumber = end.substring(1, 2);
		if (firstletter == secondletter){
			for i in ((int)firstnumber,(int)secondnumber) do
				loca.add(firstletter + "i");
		}
		elsif (firstnumber == secondnumber){
			for i in ((int)firstletter,(int)secondletter) do 
				loca.add((letter)i + firstnumber);
		}
		else {
			return ERROR
		}
			
		this.localisation = loca;
	}
	
	public boolean  isHit(String pos){
		if (this.localisation.contains(pos)) {
			loca.remove(pos);
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
}
