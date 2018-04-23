
public class Coordonate {
	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public int lengthCoordonate() {
		return this.value.length();
	}
	
	public String getLetter() {
		char a = this.value.charAt(0);
		return Character.toString(a);
		
	}
	
	public String getNumber(int i) {
		char a = this.value.charAt(i);
		return Character.toString(a);
	}
	
}
