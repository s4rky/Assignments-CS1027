public class Marina extends Building {

	public Marina(String symbol, int width, int length) {
		super(symbol, width, length);
	}
	
	public boolean isValidPlacement(Building[][] array, int x, int y) {
		if(!super.isValidPlacement(array, x, y)) {
			return false;
		}
		
		if(x == 0 || x+super.getWidth() == array.length || y == 0 || y+super.getLength() == array[0].length) {
			return true;
		}else {
			return false;
		}
	}
}
