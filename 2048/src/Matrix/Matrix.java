package Matrix;

import javax.swing.*;

import GUI.HighScoreWindow;
import ScoreBoard.ScoreBoard;
import Tile.Tile;
import Tile.TilePlace;

public class Matrix extends JPanel {

	private static int Design = 0;
	private static Tile[][] mat;
	private int score;
	private boolean hasWon;
	
	public Matrix(int Design)
	{
		setDesign(Design);
		mat = new Tile[4][4];
		super.setEnabled(false);
		initiate();
		score = 0;
	}
	
	private void initiate(){
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				Tile tile = new Tile(Design, 0);
				mat[i][j] = tile;
				add(tile, ((i*4)+j));
			}
		}
		addRandomNumber();
		addRandomNumber();
	}
	
	private boolean hasVerticMoves()
	{
		for (int j=0; j<4; j++) {
			for (int i=0; i<3; i++) {
				if ((mat[i][j].getValue() != 0) && (mat[i+1][j].getValue() != 0) &&
					(mat[i][j].equals(mat[i+1][j])))
					return true;
			}
		}
		return false;
	}
	
	private boolean hasHorizonMoves()
	{
		for (int i=0; i<4; i++) {
			for (int j=0; j<3; j++) {
				if (mat[i][j].getValue() != 0 && mat[i][j+1].getValue() != 0 &&
					mat[i][j].equals(mat[i][j+1]))
					return true;
			}
		}
		return false;
	}
	private boolean hasMoveDown()
	{
		for (int i=0; i<3; i++) {
			for (int j=0; j<4; j++) {
				int w=i+1;
				if (mat[i][j].getValue() != 0) {
					while (w<4) {
						if (mat[w][j].getValue() == 0)
							return true;
						w++;
					}
				}	
			}
		}
		return false;
	}
	
	private boolean hasMoveLeft()
	{
		for (int i=0; i<4; i++) {
			for (int j=3; j>0; j--) {
				int w=j-1;
				if (mat[i][j].getValue() != 0) {
					while (w>=0) {
						if (mat[i][w].getValue() == 0)
							return true;
						w--;
					}
				}	
			}
		}
		return false;
	}
	
	private boolean hasMoveRight()
	{
		for (int i=0; i<4; i++) {
			for (int j=0; j<3; j++) {
				int w=j+1;
				if (mat[i][j].getValue() != 0) {
					while (w<4) {
						if (mat[i][w].getValue() == 0)
							return true;
						w++;
					}
				}	
			}
		}
		return false;
	}

private boolean hasMoveUp()
{
	for (int i=3; i>0; i--) {
		for (int j=0; j<4; j++) {
			int w=i-1;
			if (mat[i][j].getValue() != 0) {
				while (w>=0) {
					if (mat[w][j].getValue() == 0)
						return true;
					w--;
				}
			}	
		}
	}
	return false;
}

private boolean hasMoves(){
	boolean hasMoves = false;
	int emptyNumber = emptyNumber();
	if (emptyNumber != 0)
		hasMoves = true;
	else
		for(int i=0; i<3 && !hasMoves; i++){
			for(int j=0; j<3; j++){
				if((mat[i][j].equals(mat[i+1][j])) || (mat[i][j].equals(mat[i][j+1])))
					hasMoves = true;
			}
		}
	for(int i=0; i<3 && !hasMoves; i++){
		if(mat[i][3].equals(mat[i+1][3]))
			hasMoves = true;
	}
	return hasMoves;
}
	
	private int emptyNumber(){
		int emptyNumber = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
					
				if(mat[i][j].getValue() == 0)
					emptyNumber++;
			}
		}
		return emptyNumber;
	}
	
	private int[][] emptySpaces(){
		int emptyNumber = emptyNumber();
		int index = 0;
		int [][] emptySpaces = new int [emptyNumber][2];
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(mat[i][j].getValue() == 0){
					emptySpaces[index][0] = i;
					emptySpaces[index][1] = j;
					index++;
				}
			}
		}
		return emptySpaces;
	}
		
	private TilePlace randomize(){
		TilePlace randomNum;
		int [][] emptySpaces = emptySpaces();
		int random = ((int)((Math.random() * emptySpaces.length)));
		randomNum = new TilePlace(emptySpaces[random][0], emptySpaces[random][1]);
		return randomNum;
	}
	
	public void addRandomNumber(){
		Tile newTile;
		TilePlace place = randomize();
		int randomNum = (int)((Math.random() * 4) + 1);
		if(randomNum > 1)
			newTile = new Tile(getDesign(), 2);
		else
			newTile = new Tile(getDesign(), 4);
		remove((place.getLine() *4) + (place.getColumn()));
		add(newTile, (place.getLine() * 4) + (place.getColumn()));
		mat[place.getLine()][place.getColumn()] = newTile;
	}
	
	public String toString()
	{
		String s = "";
	for (int i=0; i<4; i++) {
		for (int j=0; j<4; j++) {
			if (mat[i][j].getValue() == 0) {
				s = s + "0 ";
			}
			else s = s + mat[i][j].toString() + " ";
		}
		s = s+ "\n";
	}
	return s;
}

public Tile[][] getMat()
{
	return mat;
}

public void setMat (Tile[][] newMat)
{
	this.mat = newMat;
}

public int getScore()
{
	return score;
}

public void setScore(int newScore)
{
	this.score = newScore;
}

public void addScore (int add)
{
	this.score = (score+add);
}

public void moveLeft() throws Exception
{
	//push all the Tiles left
	if((hasHorizonMoves()) || (hasMoveLeft())){
	this.pushLeft();
	
	//search for couples
	for (int i=0; i<4; i++) {
		for (int j=0; j<3; j++) {
			if (mat[i][j].getValue() != 0 && mat[i][j+1].getValue() != 0 &&
					mat[i][j].equals(mat[i][j+1])) {
					mat[i][j].multiValue();
					mat[i][j+1].setValue(0);
					score = score + (mat[i][j].getValue());
					
					if ((mat[i][j].getValue()==2048) && (hasWon == false)){
						JOptionPane.showMessageDialog(null, "Congratulations! You win!! Press OK to continue playing.");
						hasWon = true;
					}
			}
		}
	}
			
	//push all left again
	this.pushLeft();
	this.addRandomNumber();
	boolean cont = this.hasMoves();
	if (!cont){
		JOptionPane.showMessageDialog(null, "Game Over");
		ScoreBoard k = new ScoreBoard();
		if(k.isHighScore(score)){
			HighScoreWindow hs = new HighScoreWindow(score);
			hs.setVisible(true);
		}
	}
	}
}

private void pushLeft()
{	
	for (int i=0; i<4; i++) {
		for (int j=0; j<3; j++) {
			int w=j+1;
			while ((mat[i][j].getValue() == 0) && (w<4)) {
				mat[i][j].setValue(mat[i][w].getValue());
				mat[i][w].setValue(0);
				w++;
			}
		}
	}	
}

public void moveRight() throws Exception
{
	//push all the Tiles right
	if((hasHorizonMoves()) || (hasMoveRight())){
	this.pushRight();
	
	//search for couples
	for (int i=0; i<4; i++) {
		for (int j=3; j>0; j--) {
			if (mat[i][j].getValue() != 0 && mat[i][j-1].getValue() != 0 &&
				mat[i][j].equals(mat[i][j-1])) {
					mat[i][j].multiValue();
					mat[i][j-1].setValue(0);
					score = score + mat[i][j].getValue();
					if ((mat[i][j].getValue()==2048) && (hasWon == false)){
						hasWon = true;
						JOptionPane.showMessageDialog(null, "Congratulations! You win!! Press OK to continue playing.");
					}
			}
		}
	}
			
	//push all right again
	this.pushRight();
	this.addRandomNumber();
	boolean cont = this.hasMoves();
	if (!cont){
		JOptionPane.showMessageDialog(null, "Game Over");
		ScoreBoard k = new ScoreBoard();
		if(k.isHighScore(score)){
			HighScoreWindow hs = new HighScoreWindow(score);
			hs.setVisible(true);
		}
	}
}
}

private void pushRight()
{
	for (int i=0; i<4; i++) {
		for (int j=3; j>=0; j--) {
			int w=j-1;
			while ((mat[i][j].getValue() == 0) && (w>=0)) {
				mat[i][j].setValue(mat[i][w].getValue());
				mat[i][w].setValue(0);
				w--;
			}
		}
	}	
}

public void moveUp() throws Exception
{
	//push all the Tiles up
	if((hasVerticMoves()) || (hasMoveUp())){
	this.pushUp();
	
	//search for couples
	for (int j=0; j<4; j++) {
		for (int i=0; i<3; i++) {
			if (mat[i][j].getValue() != 0 && mat[i+1][j].getValue() != 0 &&
				mat[i][j].equals(mat[i+1][j])) {
					mat[i][j].multiValue();
					mat[i+1][j].setValue(0);
					score = score + mat[i][j].getValue();
					if ((mat[i][j].getValue()==2048) && (hasWon == false)){
						JOptionPane.showMessageDialog(null, "Congratulations! You win!! Press OK to continue playing.");
						hasWon = true;
					}
			}
		}
	}
			
	//push all up again
	this.pushUp();
	this.addRandomNumber();
	boolean cont = this.hasMoves();
	if (!cont){
		JOptionPane.showMessageDialog(null, "Game Over");
		ScoreBoard k = new ScoreBoard();
		if(k.isHighScore(score)){
			HighScoreWindow hs = new HighScoreWindow(score);
			hs.setVisible(true);
		}
	}
	}
}

private void pushUp()
{
	for (int j=0; j<4; j++) {
		for (int i=0; i<4; i++) {
			int w=i+1;
			while ((mat[i][j].getValue() == 0) && (w<4)) {
				mat[i][j].setValue(mat[w][j].getValue());
				mat[w][j].setValue(0);
				w++;
			}
		}
	}	
}

public void moveDown() throws Exception
{
	//push all the Tiles down
	if((hasVerticMoves()) || (hasMoveDown())){
	this.pushDown();
	
	//search for couples
		for (int j=0; j<4; j++) {
			for (int i=3; i>0; i--) {
				if (mat[i][j].getValue() != 0 && mat[i-1][j].getValue() != 0 &&
					mat[i][j].equals(mat[i-1][j])) {
						mat[i][j].multiValue();
						mat[i-1][j].setValue(0);
						score = score + mat[i][j].getValue();
						if ((mat[i][j].getValue()==2048) && (hasWon == false)){
							JOptionPane.showMessageDialog(null, "Congratulations! You win!! Press OK to continue playing.");
							hasWon = true;
						}
				}
			}
		}
			
		//push all down again
		this.pushDown();
		this.addRandomNumber();
		boolean cont = this.hasMoves();
		if (!cont){
			JOptionPane.showMessageDialog(null, "Game Over");
			ScoreBoard k = new ScoreBoard();
			if(k.isHighScore(score)){
				HighScoreWindow hs = new HighScoreWindow(score);
				hs.setVisible(true);
			}
		}
		}
	}
	
	private void pushDown()
	{
		for (int j=0; j<4; j++) {
			for (int i=3; i>0; i--) {
				int w=i-1;
				while ((mat[i][j].getValue()) == 0 && (w>=0)) {
					mat[i][j].setValue(mat[w][j].getValue());
					mat[w][j].setValue(0);
					w--;
				}
			}
		}	
	}
	
	public void setDesign(int design){
		this.Design = design;
	}
	
	public int getDesign(){
		return Design;
	}

}
