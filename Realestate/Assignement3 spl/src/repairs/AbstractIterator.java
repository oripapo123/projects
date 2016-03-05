package repairs;

import java.util.Vector;

import assets.AssetContent;

public abstract class AbstractIterator {
	 AssetContent content;
	 int index;
	 Vector<Integer> quantities;
	
	AbstractIterator(AssetContent content, Vector<Integer> quantities){
		this.content=content;
		this.quantities=quantities;
		index=0;
	}

	
	public boolean hasNext() {
		return index<quantities.size();
	}

	public void next() {
		index++;
	}

	
	public void back() {
		index--;		
	}
	
	public int getQuantity(){
		int i=quantities.get(index);
		return i;
	}
	
}
