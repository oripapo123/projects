package Tile;

public class TilePlace {
	private int line;
	private int column;
	
	public TilePlace(int line, int column){
		this.line = line;
		this.column = column;
	}
	
	public int getLine(){
		return line;
	}
	
	public int getColumn(){
		return column;
	}
	
	public String toString(){
		return ("(" + getLine() + "," + getColumn() + ")");
	}
}
