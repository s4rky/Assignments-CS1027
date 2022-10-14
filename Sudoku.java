public class Sudoku {
	LinearNode<Integer>[] board;
	
	public Sudoku(int array[][]) {
		board = new LinearNode[9];
		
		for(int i=0; i<array.length; i++) {	
			LinearNode elem = new LinearNode(array[i][0]);
			board[i] = elem;
		}
		
		for(int i=0; i<array.length; i++) {
			for(int j=1; j<array.length; j++) {		
				LinearNode elem = new LinearNode(array[i][j]);
				LinearNode last = board[i];
				
				while(last.getNext() != null) {
					last = last.getNext();
				}
				last.setNext(elem);
			}
		}
	}
	
	public boolean isValidRow(int row) {
		UniqueArray array = new UniqueArray();
		LinearNode last = board[row-1];
		String test = "";
		
		array.addItem(board[row-1]);
		
		while(last.getNext() != null) {
			last = last.getNext();
			array.addItem(last);
			test = test + last.getElement();
		}
		
		for (int i=0; i<test.length(); i++) {
			for (int j =i+1; j<test.length(); j++) {
				if (test.charAt(i) == test.charAt(j)) {
					return false;
				}
			}
		}
		
		if(test.contains("0")) {
			return false;
		}
		if(test.length()>9) {
			return false;
		}
		if(array.getNumElements() == 9) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isValidCol(int col) {
		UniqueArray array = new UniqueArray();
		String test = "";
		
		for(int i=0; i<board.length; i++) {
			LinearNode last = board[i];
			for(int j=0; j<(col-1); j++) {
				last = last.getNext();
			}
			array.addItem(last);
			test = test + last.getElement();
		}
		
		for (int i=0; i<test.length(); i++) {
			for (int j=i+1; j<test.length(); j++) {
				if (test.charAt(i) == test.charAt(j)) {
					return false;
				}
			}
		}
		
		if(test.contains("0")) {
			return false;
		}
		if(test.length()>9) {
			return false;
		}
		
		if(array.getNumElements() == 9) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isValidBox(int row, int col) {
		UniqueArray array = new UniqueArray();
		
		String test = "";
		
		if(row<1 || row>7) {
			return false;
		}
		
		if(col<1 || col>7) {
			return false;
		}
		
		for(int i=row-1; i<row+2; i++) {
			LinearNode last = board[i];
			
			for(int j=0; j<col-1; j++) {
				last = last.getNext();
			}
			for(int j=0; j<3; j++) {
				array.addItem(last);
				test = test + last.getElement();
				last = last.getNext();
			}
		}
		
		for (int i=0; i<test.length(); i++) {
			for (int j=i+1; j<test.length(); j++) {
				if (test.charAt(i) == test.charAt(j)) {
					return false;
				}
			}
		}
		
		if(test.contains("0")) {
			return false;
		}
		
		if(test.length()>9) {
			return false;
		}
		
		if(array.getNumElements() == 9) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isValidSolution() {
		for(int i = 1; i<=board.length; i++) {
			if(!isValidRow(i)) {
				return false;
			}
			
			if(!isValidCol(i)) {
				return false;
			}
		}
		
		if(!isValidBox(1,1)) {
			return false;
		}
		if(!isValidBox(1,4)) {
			return false;
		}
		if(!isValidBox(1,7)) {
			return false;
		}
		if(!isValidBox(4,1)) {
			return false;
		}
		if(!isValidBox(4,4)) {
			return false;
		}
		if(!isValidBox(4,7)) {
			return false;
		}
		if(!isValidBox(7,1)) {
			return false;
		}
		if(!isValidBox(7,4)) {
			return false;
		}
		if(!isValidBox(7,7)) {
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		String result = "";
		
		for(int j=0; j<board.length; j++) {
			LinearNode last = board[j];
			if(j == 0) {
				result = result +board[j].getElement() + "  ";
			}else {
				result = result + "\n" +board[j].getElement() + "  ";
			}
			for(int i=0; i<board.length; i++) {
				while(last.getNext() != null) {
					last = last.getNext();
					if(last.getNext() == null) {
						result = result + last.getElement();
					}else {
						result = result + last.getElement() + "  ";
					}
				}
			}
		}
		
		return result;
	}
}
