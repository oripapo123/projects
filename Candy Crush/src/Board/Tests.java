package Board;

import Candies.Candy;

public class Tests {

	public static Board board;
	
	public Tests (Board board1) {
		board = board1;
	}
	
	public boolean isVertical3(Candy c){
		boolean ans = false;
		
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if (board.checkToRemove(i-1,j, c)) {
			if ((board.checkToRemove(i-2, j, c)) || (board.checkToRemove(i+1, j, c))) {
				ans = true;
			}
		}
		else if ((board.checkToRemove(i+1, j, c)) && (board.checkToRemove(i+2, j, c))) {
				ans = true;
		}
		return ans;
	}

	public boolean isHorizontal3(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if (board.checkToRemove(i,j-1, c)) {
			if ((board.checkToRemove(i,j-2, c)) || (board.checkToRemove(i,j+1, c))) {
				ans = true;
			}
		}
		else if ((board.checkToRemove(i,j+1, c)) && (board.checkToRemove(i,j+2, c))) {
			ans = true;
		}
		return ans;
	}

	public boolean isVertical4(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if(isVertical3(c)) {
			if (inBetweenVertical(c)) {
				if ((board.checkToRemove(i+2,j, c)) || (board.checkToRemove(i-2,j, c))) {
					ans = true;
				}
			}
		}
		return ans;		
	}


	public boolean isHorizontal4(Candy c){
		boolean ans = false;
		
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
	
		if(isHorizontal3(c)) {
			if (inBetweenHorizontal(c)) {
				if ((board.checkToRemove(i,j-2, c)) || (board.checkToRemove(i,j+2, c))) {
					ans = true;
				}
			}
		}
		return ans;
	}	
		
	public boolean isHorizontal5(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (isHorizontal4(c)) {
			if ((board.checkToRemove(i,j+2, c)) && (board.checkToRemove(i,j-2, c))) {
				ans = true;
			}
		}
		return ans;
	}
	
	public boolean isVertical5(Candy c)
	{
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (isVertical4(c)) {
			if ((board.checkToRemove(i+2,j, c)) && (board.checkToRemove(i-2,j, c))) {
				ans = true;
			}
		}
		return ans;
	}

	public boolean isUpT(Candy c){
		boolean ans = false;
		
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if(inBetweenHorizontal(c) && board.checkToRemove(i-1,j, c) && board.checkToRemove(i-2,j, c)) {
				ans = true;
		}
		return ans;
	}

	public boolean isDownT(Candy c){
		boolean ans = false;
		
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if(inBetweenHorizontal(c) && board.checkToRemove(i+1,j, c) && board.checkToRemove(i+2,j, c)) {
			ans = true;
		}
		return ans;
	}

	public boolean isLeftT(Candy c){
		boolean ans = false;
		
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if(inBetweenVertical(c) && board.checkToRemove(i,j-1, c) && board.checkToRemove(i,j-2, c)) {
				ans = true;
		}
		return ans;
	}

	public boolean isRightT(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if(inBetweenVertical(c) && board.checkToRemove(i,j+1, c) && board.checkToRemove(i,j+2, c)) {
			ans = true;
		}
		return ans;
	}

	public boolean inBetweenHorizontal(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if ((board.checkToRemove(i,j-1, c)) && (board.checkToRemove(i,j+1, c))) {
			ans = true;
		}
		return ans;
	}

	public boolean inBetweenVertical(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if ((board.checkToRemove(i-1,j, c)) && (board.checkToRemove(i+1,j, c))) {
			ans = true;
		}
		return ans;
	}

	public boolean isLeftDownL(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if(board.checkToRemove(i-1,j, c) && board.checkToRemove(i-2,j, c) && board.checkToRemove(i,j+1, c) && board.checkToRemove(i,j+2, c)){
			ans = true;
		}
		return ans;
	}

	public boolean isRightDownL(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if(board.checkToRemove(i-1,j, c) && board.checkToRemove(i-2,j, c) && board.checkToRemove(i,j-1, c) && board.checkToRemove(i,j-2, c)){
			ans = true;
		}
		return ans;
	}

	public boolean isLeftUpL(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if(board.checkToRemove(i+1,j, c) && board.checkToRemove(i+2,j, c) && board.checkToRemove(i,j+1, c) && board.checkToRemove(i,j+2, c)){
			ans = true;
		}
		return ans;
	}

	public boolean isRightUpL(Candy c){
		boolean ans = false;
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		if(board.checkToRemove(i+1,j, c) && board.checkToRemove(i+2,j, c) && board.checkToRemove(i,j-1, c) && board.checkToRemove(i,j-2, c)){
			ans = true;
		}
		return ans;
	}
	
	public boolean is3 (Candy c) {
		return (isHorizontal3(c) || isVertical3(c));
	}
	
	public boolean is4 (Candy c) {
		return (isHorizontal4(c) || isVertical4(c));
	}

	public boolean is5 (Candy c) {
		return (isHorizontal5(c) || isVertical5(c));
	}
	
	public boolean isT (Candy c) {
		return (isUpT(c) || isDownT(c) || isRightT(c) || isLeftT(c));
	}
	
	public boolean isL (Candy c) {
		return (isRightUpL(c) || isRightDownL(c) || isLeftUpL(c) || isLeftDownL(c));
	}
	
	public boolean isTorL (Candy c) {
		return (isT(c) || isL(c));
	}
	
	public boolean isAnySeria (Candy c) {
		return (is3(c) || is4(c) || is5(c) || isT(c) || isL(c));
	}
	
}
