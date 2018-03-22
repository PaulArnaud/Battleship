import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class SHIP {
    public int row;
    public int col;
    public List localisation;
    
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public List getLocalisation() {
		return localisation;
	}
	
	/*public void setLocalisation(int row,int col) {
		
		this.localisation = localisation;
	}*/
	
	public boolean  isHit(int pos){
		if (this.localisation.contains(pos))
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
