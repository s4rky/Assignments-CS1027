public class UniqueArray<T> {

	private T[] array;
	private int num;
	
	public UniqueArray() {
		array = (T[])new Object[5];
		num = 0;
	}
	
	private void expandCapacity() {
		T[] temp;
		temp = (T[])(new Object[array.length+5]);
		
		for(int i=0; i<array.length; i++) {
			temp[i] = array[i];
		}
		
		array = temp;
	}
	
	public void addItem(T element) {
		boolean check = false;
		
		for(int i=0; i<array.length; i++) {
			if(array[i] == element) {
				check = true;
			}
		}
		
		if(!check) {
			if(array[array.length-1] == null) {
				for(int i = 0; i < array.length; i++) {
					if(array[i] == null) {
						array[i] = element;
						break;
					}
				}
			}else {
				expandCapacity();
				for(int i=0; i<array.length; i++) {
					if(array[i] == null) {
						array[i] = element;
						break;
					}
				}
			}
			num++;
		}
	}
	
	public int getLength() {
		return array.length;
	}
	
	public int getNumElements() {
		return num;
	}
	
	public String toString() {
		String elements = "";
		
		for(int i=0; i<array.length; i++) {
			if(array[i] == null) {
				break;
			}
			if(i==num-1) {
				elements = elements + array[i];
			}else {
				elements = elements + array[i] + ", ";
			}
		}
		
		return elements;
	}
	
}
