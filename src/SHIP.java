
public class SHIP {
    public String start;
    public String end;
    public int[] localisation;
    
	public int getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public List getLocalisation() {
		return localisation;
	}
	
	public void setLocalisation(String start,String end) {
		int[] loca;
		String firstletter = start[0];
		String firstnumber = start[1];
		String secondletter = end[0];
		String secondnumber = end[1];
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
		if (this.localisation.contains(pos))
			loca.remove(pos);
			return true;
		else 
			return false;
	}
	
	public boolean isDestroyed(){
		if (this.localisation.isEmpty())
			return true;
		else
			return false;
	}
}
