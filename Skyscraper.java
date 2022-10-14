public class Skyscraper extends Building{
	
	private int height;
	
	public Skyscraper(String symbol, int width, int length, int height) {
		super(symbol, width, length);
		this.height = height;
	}
	
	public boolean isValidPlacement(Building[][] array, int x, int y) {
		if(!super.isValidPlacement(array, x, y)) {
			return false;
		}
		
		if(height>=9) {
			return false;
		}
		
		if(height<(super.getLength()*super.getWidth())) {
			return false;
		}
		
		return true;
	}
	
	public String toString() {		
		return String.valueOf(height);
	}
}
