public class City {
	
	private int width;
	private int length;
	private Building[][] layout;
	
	public City(int x, int y) {
		width = x;
		length = y;
		layout = new Building[width][length];
		
	}
	
	public boolean addStructure(int x, int y, Building building) {
		if(!building.isValidPlacement(layout, x, y)) {
			return false;
		}else {
			for(int i = 0; i<building.getLength(); i++) {
				for(int j = 0; j<building.getWidth(); j++)
				layout[x+j][y+i] = building;
			}
		}
		return true;
	}
	
	public String toString() {
		String map = "";
		for(int i = 0; i<length; i++) {
			for(int j = 0; j<width; j++) {
				if(layout[j][i] == null) {
					map = map + "." + "  ";
				}else {
					map = map + layout[j][i].toString() + "  ";
				}	
			}
			if(i != length-1) {
				map = map + "\n";
			}
		}
		return map;
	}	
}
