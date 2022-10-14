public class Building {
	private String symbol;
	private int width;
	private int length;
	
	public Building(String symbol, int width, int length) {
		this.symbol = symbol;
		this.width = width;
		this.length = length;
	}
	

	public int getWidth() {
		return width;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isValidPlacement(Building[][] array, int x, int y) {
		int placement;
		
		for(int i=0; i<=width; i++) {
			placement = x+i;
			if(placement>array.length || placement<0) {
				return false;
			}
		}
		
		for(int i=0; i<length; i++) {
			placement = y+i;
			if(placement>array[0].length-1 || placement<0) {
				return false;
			}
		}
		
		for(int i=0; i<width; i++) {
			placement = x+i;
			if(array[placement][y] != null) {
				return false;
			}	
		}
		
		for(int i=0; i<length; i++) {
			placement = y+i;
			if(array[x][placement] != null) {
				return false;
			}	
		}
		
		return true;
	}
	
	public String toString() {
		return symbol;
	}
}