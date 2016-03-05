package repairs;

import java.util.Vector;

import assets.AssetContent;

public class RMIIterator extends AbstractIterator {

	private Vector<String> materials;

	protected RMIIterator(AssetContent content, Vector<String> mats, Vector<Integer> quantities) {
		super(content, quantities);
		materials=mats;
}


	public String getRepairMaterial(){
		return materials.get(index);
	}
}
