package repairs;

import java.util.Vector;

import assets.AssetContent;

public class RTIIterator extends AbstractIterator{

	private Vector<String> tools;

	protected RTIIterator(AssetContent content, Vector<String> tools, Vector<Integer> quantities) {
		super(content, quantities);
		this.tools=tools;
	}


	public String getRepairTool(){
		return tools.get(index);
	}
}
