package Board;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Candies.*;
import GUI.HighScoreWindow;
import ScoreBoard.ScoreBoard;


public class Board extends JPanel{

	private JFrame Parent;
	private static Candy[][] mat;
	private int totalScores=0;
	private int stepScore=10;
	private int moves=0;
	private Candy[] line;
	private static Tests tests;
	
	public Board(JFrame Parent)
	{
		this.Parent = Parent;
		mat = new Candy[9][9];
		line = new Candy[2];
		tests = new Tests(this);
		initiate();
	}
	
	private void initiate(){
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				Candy candy = new Candy(this.Random(), new Point(i,j), this);
				mat[i][j] = candy;
				add(candy, ((i*9)+j));
			}
		}
		while (isNotLineOver()) {
			for (int i=8; i>=0; i--) {
				for (int j=8; j>=0; j--) {
					Candy c = getPointInMat(i, j);
					if (tests.isAnySeria(c)) {
						applyLine(c);
						pushAllDown();
						fillBoard();
					}
				}
			}
		}
		resetTotalScores();
		this.Parent.pack();
		this.Parent.validate();
	}
	
	public void resetTotalScores()
	{
		this.totalScores = 0;
	}
	
	public Candy[] getLine()
	{
		return line;
	}
	
	public void setInLine(Candy c, int i)
	{
		line[i] = c;
	}
				
	public Candy[][] getMat()
	{
		return mat;
	}
	
	public Candy getPointInMat (int i, int j)
	{
		return (mat[i][j]);
	}

	public int gettotalScores()
	{
		return totalScores;
	}
	
	public int getStepScores()
	{
		return stepScore;
	}

	public void setStepScores(int newStepScores)
	{
		this.stepScore = newStepScores;
	}

	public void addtotalScores (int add)
	{
		this.totalScores = (totalScores+add);
	}
	
	public void addStepScores (int add)
	{
		this.stepScore = (stepScore+add);
	}

	public int getMoves()
	{
		return moves;
	}

	public void addToMoves (int add)
	{
		this.moves = (moves+add);
	}

	public void pushAllDown()
	{
		for (int j=0; j<9; j++) {
			for (int i=8; i>0; i--) {
				int w=i-1;
				while ((mat[i][j].getkind()==0) && (w>=0)) {
					mat[i][j].combine(mat[w][j]);
					w--;
				}
			}
		}	
	}
	
	public int Random()
	{
		int random = (int)((Math.random() * 6) + 1);
			return random;		
	}
	
	public boolean isValidPos (int i, int j)
	{
		return (i>=0 && i<9 && j>=0 && j<9);
	}
	
	public void crushThreeHorizontal (Candy c)
	{
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (checkToRemove(i, j-1, c)) {
			this.getPointInMat(i, j-1).crush();
			if (checkToRemove(i, j-2, c)) {
				this.getPointInMat(i, j-2).crush();
			}		
		}
		
		if (checkToRemove(i, j+1, c)) {
			this.getPointInMat(i, j+1).crush();
			if (checkToRemove(i, j+2, c)) {
				this.getPointInMat(i, j+2).crush();
			}		
		}	
		c.crush();
	}
	
	
	public void crushThreeVertical (Candy c)
	{
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (checkToRemove(i-1, j, c)) {
			this.getPointInMat(i-1, j).crush();
			if (checkToRemove(i-2, j, c)) {
				this.getPointInMat(i-2,j).crush();
			}		
		}
		
		if (checkToRemove(i+1, j, c)) {
			this.getPointInMat(i+1,j).crush();
			if (checkToRemove(i+2, j, c)) {
				this.getPointInMat(i+2,j).crush();
			}		
		}
		c.crush();
	}

	public void crushFourHorizontal (Candy c)
	{
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (checkToRemove(i, j-1, c)) {
			this.getPointInMat(i, j-1).crush();
			if (checkToRemove(i, j-2, c)) {
				this.getPointInMat(i, j-2).crush();
				if (checkToRemove(i, j-3, c)) {
					this.getPointInMat(i, j-3).crush();
				}
			}		
		}
		
		if (checkToRemove(i, j+1, c)) {
			this.getPointInMat(i, j+1).crush();
			if (checkToRemove(i, j+2, c)) {
				this.getPointInMat(i, j+2).crush();
				if (checkToRemove(i, j+3, c)) {
					this.getPointInMat(i, j+3).crush();
				}
			}		
		}
		if(c.getkind()%6 != 0){
			int tempKind = c.getkind()%6;
			c.crush();
			c.setkind(tempKind+12);
		}
		else{
			c.crush();
			c.setkind(18);
		}
	}


	public void crushFourVertical (Candy c)
	{
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (checkToRemove(i-1, j, c)) {
			this.getPointInMat(i-1,j).crush();
			if (checkToRemove(i-2, j, c)) {
				this.getPointInMat(i-2,j).crush();
				if (checkToRemove(i-3, j, c)) {
					this.getPointInMat(i-3,j).crush();
				}
			}		
		}
		
		if (checkToRemove(i+1, j, c)) {
			this.getPointInMat(i+1,j).crush();
			if (checkToRemove(i+2, j, c)) {
				this.getPointInMat(i+2,j).crush();
				if (checkToRemove(i+3, j, c)) {
					this.getPointInMat(i+3,j).crush();
				}
			}		
		}
		if(c.getkind()%6 != 0){
			int tempKind = c.getkind()%6;
			c.crush();
			c.setkind(tempKind+6);
		}
		else{
			c.crush();
			c.setkind(12);
		}
	}

	public void crushFiveHorizontal (Candy c)
	{
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (checkToRemove(i, j-1, c)) {
			this.getPointInMat(i, j-1).crush();
			if (checkToRemove(i, j-2, c)) {
				this.getPointInMat(i, j-2).crush();
				if (checkToRemove(i, j-3, c)) {
					this.getPointInMat(i, j-3).crush();
					if (checkToRemove(i, j-4, c)) {
						this.getPointInMat(i, j-4).crush();
					}
				}
			}		
		}
		
		if (checkToRemove(i, j+1, c)) {
			this.getPointInMat(i, j+1).crush();
			if (checkToRemove(i, j+2, c)) {
				this.getPointInMat(i, j+2).crush();
				if (checkToRemove(i, j+3, c)) {
					this.getPointInMat(i, j+3).crush();
					if (checkToRemove(i, j+4, c)) {
						this.getPointInMat(i, j+4).crush();
					}
				}
			}		
		}
		c.crush();
		c.setkind(25);
	}

	public void crushFiveVertical (Candy c)
	{
		int i = c.getPoint().getrow();
		int j = c.getPoint().getColumn();
		
		if (checkToRemove(i-1, j, c)) {
			this.getPointInMat(i-1,j).crush();
			if (checkToRemove(i-2, j, c)) {
				this.getPointInMat(i-2,j).crush();
				if (checkToRemove(i-3, j, c)) {
					this.getPointInMat(i-3,j).crush();
					if (checkToRemove(i-4, j, c)) {
						this.getPointInMat(i-4,j).crush();
					}
				}
			}		
		}
		
		if (checkToRemove(i+1, j, c)) {
			this.getPointInMat(i+1,j).crush();
			if (checkToRemove(i+2, j, c)) {
				this.getPointInMat(i+2,j).crush();
				if (checkToRemove(i+3, j, c)) {
					this.getPointInMat(i+3,j).crush();
					if (checkToRemove(i+4, j, c)) {
						this.getPointInMat(i+4,j).crush();
					}
				}
			}		
		}
		c.crush();
		c.setkind(25);
	}

	public void crushTOrL (Candy c)
	{
		int tempKind;
		if(c.getkind() % 6 != 0)
			tempKind = c.getkind()%6;
		else
			tempKind = 6;
		this.crushThreeHorizontal(c);
		c.setkind(tempKind);
		this.crushThreeVertical(c);
		c.crush();
		c.setkind(tempKind+18);
	}
	
	public boolean checkToRemove (int i, int j, Candy c)
	{
		return ((isValidPos(i,j)) && (this.getPointInMat(i,j).getkind()!=0) && (this.getPointInMat(i,j).equals(c)));
	}
	
	public void addPress(Candy c){
		if((line[0] != null) && (line[1] != null)){
			line[0].createIcon(line[0].getkind());
			line[0].setClicked(false);
			line[1].createIcon(line[1].getkind());
			line[0] = c;
			line[1] = null;
		}
		else{ 
			if(line[0] == null){
				line[0] = c;
			}
			else{
				if(isClose(c, line[0])){
					line[1] = c;
				}
				else{
					line[0].createIcon(line[0].getkind());
					line[0].setClicked(false);
					line[0] = c;
				}
			}
		}
	}
	
	private boolean isClose(Candy c1, Candy c2){
			return( (((c1.getPoint().getrow() - c2.getPoint().getrow() == 1) ||
					(c1.getPoint().getrow() - c2.getPoint().getrow() == -1)) &&
					(c1.getPoint().getColumn() - c2.getPoint().getColumn() == 0))
					
					||
					
					(((c1.getPoint().getColumn() - c2.getPoint().getColumn() == 1) ||
					(c1.getPoint().getColumn() - c2.getPoint().getColumn() == -1)) &&
					(c1.getPoint().getrow() - c2.getPoint().getrow() == 0)) );
	}

	public boolean isTwoCandyPressed() {
		boolean ans = false;
		if((line[0] != null) && (line[1] != null))
			ans = true;
			return ans;
	}

	public boolean isLegal()
	{
		boolean ans = false;
		(getLine()[0]).combine(getLine()[1]);
		if   (   (getLine()[0].getkind()>6 && getLine()[1].getkind()>6) ||
				(tests.isAnySeria(this.getLine()[0])) ||  (tests.isAnySeria(this.getLine()[1])) ||
				(getLine()[0].getkind()==25 || getLine()[1].getkind()==25)) {
			ans = true;
		}	
		(getLine()[1]).combine(getLine()[0]);
		return ans;
	}
	
	public void crush(Candy c) {
		if (isLegal()) {
			line();
			this.Parent.setTitle("score : " + this.gettotalScores()+ ". moves left: " + (20-getMoves()));
			if(endGame()){
				JOptionPane.showMessageDialog(null, "Game over. Your final score is " + this.gettotalScores());
				ScoreBoard k = new ScoreBoard();
				if(k.isHighScore(this.gettotalScores())){
					HighScoreWindow hs = new HighScoreWindow(this.gettotalScores());
					hs.setVisible(true);
				}
			}
		}
	}
	
	public void line (){
		addToMoves(1);
		setStepScores(10);
		//case of 2 special candies
		if (getLine()[0].getkind()>6 && getLine()[1].getkind()>6) {
			specialLine();
		}
		
		//case of color-bump
		else if (getLine()[0].getkind()==25 || getLine()[1].getkind()==25) {
			this.colorBumpCase();
		}
		
		//regular case
		else {
			(getLine()[0]).combine(getLine()[1]);
			int candyNum = 1;
			while (candyNum>=0) {
				Candy c = getLine()[candyNum];
				if (tests.isAnySeria(c)) {
					applyLine(c);
					
				}
				pushAllDown();
				fillBoard();
				this.Parent.pack();
				this.Parent.validate();
				candyNum--;
			}
		}
		this.Parent.pack();
		this.Parent.validate();
		addStepScores(getStepScores());
		lineCont();
		///finishLine
	}
	
	public boolean endGame()
	{
		if (getMoves()>=20) {
			return true;
		}
		return false;
	}
	
	public void specialLine()
	{
		if ((getLine()[0].getkind()==25) || (getLine()[1].getkind()==25)) {
		
			//put the bump first
			if (getLine()[1].getkind()==25) {
				Candy replace = getLine()[0];
				getLine()[0] = getLine()[1];
				getLine()[1] = replace;
			}
			
			//case of 2 color-bump
			if (getLine()[1].getkind()==25) {
				for (int i=0; i<9; i++) {
					for (int j=0; j<9; j++) {
						mat[i][j].setkind(0);
					}
				}
				this.addtotalScores(810);
			}
			
			//case of bump & wrapped
			else if (getLine()[1].between(19, 24)) {
				getLine()[0].setKindToBump(getLine()[1].getkind()%6);
				this.colorBumpCrushAll(getLine()[1]);
				getLine()[0].setkind(0);
				this.pushAllDown();
				this.fillBoard();
				this.Parent.pack();
				this.Parent.validate();
				int random = Random();
				getLine()[0].setKindToBump(random);
				getLine()[1].setkind(random);
				this.colorBumpCrushAll(getLine()[1]);
								
			}
			
			//case of bump & striped
			else if (getLine()[1].between(7, 18)) {
				int toUpdate = getLine()[1].getkind();
				for (int i=0; i<9; i++) {
					for (int j=0; j<9; j++) {
						if (mat[i][j].equals(getLine()[1])) {
						getPointInMat(i, j).setkind(toUpdate);
						}
					}
				}
				this.Parent.pack();
				this.Parent.validate();
				for (int i=0; i<9; i++) {
					for (int j=0; j<9; j++) {
						if (getPointInMat(i, j).getkind()==toUpdate) {
							getPointInMat(i, j).crush();
						}
					}
				}
				this.Parent.pack();
				this.Parent.validate();				
			}
			
			// case of bump & regular
			else if (getLine()[1].between(1, 6)) {
				getLine()[0].setKindToBump(getLine()[1].getkind()%6);
				this.colorBumpCrushAll(getLine()[1]);
				getLine()[0].setkind(0);
			}
			
		}//end of color-bump
		
		
		// at least one is a wrapped
		else if ((getLine()[0].between(19, 24)) || (getLine()[1].between(19, 24))) {
			
			//case of 2 wrapped
			if ((getLine()[0].between(19, 24)) && (getLine()[1].between(19, 24))) {
			
				getLine()[0].bigCrush();
				getLine()[1].bigCrush();							
			}
			
			//case of wrapped & striped
			else if ((getLine()[0].between(7, 18)) || (getLine()[1].between(7, 18))) {
				
				int i = getLine()[1].getPoint().getrow();
				int j = getLine()[1].getPoint().getColumn();
				
				for (int row = i-1; row<=i+1; row++) {
					mat[row][j].setkind(7);
					mat[row][j].crush();
				}
				
				for (int col = j-1; col<=j+1; col++) {
					mat[i][col].setkind(13);
					mat[i][col].crush();
				}
				
			}			
			
		}//end of wrapped
		
		
		//case of striped & striped
		else {
			int i = getLine()[1].getPoint().getrow();
			int j = getLine()[1].getPoint().getColumn();
			mat[i][j].setkind(7);
			mat[i][j].crush();
			mat[i][j].setkind(13);
			mat[i][j].crush();
		}
		
	}

	
	public void applyLine(Candy c)
	{
		if (tests.is5(c)) {
			if (tests.isHorizontal5(c)) {
				crushFiveHorizontal(c);
			}
			else crushFiveVertical(c);
		}
		else if (tests.is4(c)) {
			if (tests.isHorizontal4(c)) {
				crushFourHorizontal(c);
			}
			else crushFourVertical(c);
		}
		else if (tests.isTorL(c)) {
				System.out.println("Test passed for T or L");
				crushTOrL(c);
		}
		else {
			if (tests.isHorizontal3(c)) {
				crushThreeHorizontal(c);
			}
			else crushThreeVertical(c);
		}
		this.Parent.pack();
		this.Parent.validate();
	}
	
	public void lineCont()
	{
		pushAllDown();
		fillBoard();
		this.Parent.pack();
		this.Parent.validate();
		while (isNotLineOver()) {
			for (int i=8; i>=0; i--) {
				for (int j=8; j>=0; j--) {
					Candy c = getPointInMat(i, j);
					if (tests.isAnySeria(c)) {
						applyLine(c);
						addStepScores(getStepScores());
						pushAllDown();
						fillBoard();
						this.Parent.pack();
						this.Parent.validate();
					}
				}
			}
		}
	}
	
	public boolean isNotLineOver()
	{
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				Candy c = getPointInMat(i, j);
				if (tests.isAnySeria(c)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void colorBumpCase(){
	
		//verify that line[0] is the bump
		if (getLine()[1].getkind()==25) {
			Candy replace = getLine()[0];
			getLine()[0] = getLine()[1];
			getLine()[1] = replace;
		}
		getLine()[0].setKindToBump(getLine()[1].getkind()%6);
		this.colorBumpCrushAll(getLine()[1]);
		getLine()[0].setkind(0);
	}
	
	public void colorBumpCrushAll (Candy c) {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				Candy c1 = getPointInMat(i, j);
				if (c1.getkind()!=0 && (c1.getkind()%6)==(c.getKindToBump())) {
					c1.crush();
				}
			}
		}
	}
	
	public void fillBoard()
	{
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (mat[i][j].getkind()==0) {
					mat[i][j].setkind(this.Random());
					this.Parent.pack();
					this.Parent.validate();
				}
			}
		}
		
	}
}
