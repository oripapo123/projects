package Board;

public class Point {
	
		private int row;
		private int column;
		
		public Point(int row, int column){
			this.row = row;
			this.column = column;
		}
		
		public int getrow(){
			return row;
		}
		
		public int getColumn(){
			return column;
		}
		
		public String toString(){
			return ("(" + getrow() + "," + getColumn() + ")");
		}
}
