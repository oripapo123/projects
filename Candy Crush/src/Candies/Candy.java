package Candies;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Board.*;
//import Interfaces.*;

public class Candy extends JLabel implements MouseListener{
	
	private boolean clicked;
	static private String[] Designs = new String[51]; 
	private static boolean designLoaded = false;
	protected int kind;
	protected Point position;
	protected Board board;
	protected static int kindToBump = 0;
	
	public Candy (int kind, Point position, Board board)
	{
		addMouseListener(this);
		if(!getDesignLoaded()){
			loadDesigns();
		}
		this.kind = kind;
		this.position = position;
		this.board = board;	
		createIcon(kind);
	}
	
	public int getKindToBump(){
		return this.kindToBump;
	}
	
	public void setKindToBump(int kindToBump){
		this.kindToBump = kindToBump;
	}
	
	public int getkind()
	{
		return this.kind;
	}
	
	public void setkind (int newkind)
	{
		this.kind = newkind;
		createIcon(newkind);
	}
	
	public Point getPoint()
	{
		return this.position;
	}
	
	public void setPoint (Point other)
	{
		this.position = other;
	}
	
	public Board getBoard()
	{
		return this.board;
	}
	
	public void crush()
	{
		
		board.addtotalScores(board.getStepScores());
		
		int i = this.getPoint().getrow();
		int j = this.getPoint().getColumn();
			
		//case of horizontal-striped
		if (this.between(7, 12)) {
			this.setkind(0);
			for(int b=0; b<9; b++) {
				if(b!=j)
					board.getPointInMat(i, b).crush();
			}
		}
		
		//case of vertical-striped
		else if (this.between(13, 18)) {
			this.setkind(0);
			for(int b=0; b<9; b++) {
				if(b!=i)
					board.getPointInMat(b, j).crush();
			}
		}
		
		//case of wrapped
		else if (this.between(19, 24)) {
			this.setkind(0);
			for (int row=i-1; row<=i+1; row++) {
				for (int col=j-1; col<=j+1; col++) {
					if (row>=0 && row<9 && col>=0 && col<9) {
						if((row != i) || (col != j))
							board.getPointInMat(row, col).crush();	
					}
				}
			}
		}//wrapped
		
		//case of color-bump
		else if (this.getkind()==25) {
			this.setkind(0);
			for (int h=0; h<9; h++) {
				for (int v=0; v<9; v++) {
					if ((board.getPointInMat(h, v).getkind()>0) && ((board.getPointInMat(h, v).getkind()%6)==kindToBump)) {
						board.getPointInMat(h, v).crush();
					}
				}
			}
		}
		
		
		
		this.setkind(0);
	}
	
	//crush of double wrapped
	public void bigCrush()
	{
		int i = this.getPoint().getrow();
		int j = this.getPoint().getColumn();
		this.setkind(0);
		for (int row=i-2; row<=i+2; row++) {
			for (int col=j-2; col<=j+2; col++) {
				if (row>=0 && row<9 && col>=0 && col<9) {
					if((row != i) || (col != j))
						board.getPointInMat(row, col).crush();	
				}
			}
		}
	}
	
	public void combine(Candy other){
		int tempKind = this.getkind();
		this.setkind(other.getkind());
		other.setkind(tempKind);
	}
	
	public void createIcon(int kind){
		switch(kind){
		case 0:
			candyDesign(Designs[0]);
			this.setClicked(false);
		break;
		case 1:
			candyDesign(Designs[1]);
			this.setClicked(false);
		break;
		case 2:
			candyDesign(Designs[2]);
			this.setClicked(false);
		break;
		case 3:
			candyDesign(Designs[3]);
			this.setClicked(false);
		break;
		case 4:
			candyDesign(Designs[4]);
			this.setClicked(false);
		break;
		case 5:
			candyDesign(Designs[5]);
			this.setClicked(false);
		break;
		case 6:
			candyDesign(Designs[6]);
			this.setClicked(false);
		break;
		case 7:
			candyDesign(Designs[7]);
			this.setClicked(false);
		break;
		case 8:
			candyDesign(Designs[8]);
			this.setClicked(false);
		break;
		case 9:
			candyDesign(Designs[9]);
			this.setClicked(false);
		break;
		case 10:
			candyDesign(Designs[10]);
			this.setClicked(false);
		break;
		case 11:
			candyDesign(Designs[11]);
			this.setClicked(false);
		break;
		case 12:
			candyDesign(Designs[12]);
			this.setClicked(false);
		break;
		case 13:
			candyDesign(Designs[13]);
			this.setClicked(false);
		break;
		case 14:
			candyDesign(Designs[14]);
			this.setClicked(false);
		break;
		case 15:
			candyDesign(Designs[15]);
			this.setClicked(false);
		break;
		case 16:
			candyDesign(Designs[16]);
			this.setClicked(false);
		break;
		case 17:
			candyDesign(Designs[17]);
			this.setClicked(false);
		break;
		case 18:
			candyDesign(Designs[18]);
			this.setClicked(false);
		break;
		case 19:
			candyDesign(Designs[19]);
			this.setClicked(false);
		break;
		case 20:
			candyDesign(Designs[20]);
			this.setClicked(false);
		break;
		case 21:
			candyDesign(Designs[21]);
			this.setClicked(false);
		break;
		case 22:
			candyDesign(Designs[22]);
			this.setClicked(false);
		break;
		case 23:
			candyDesign(Designs[23]);
			this.setClicked(false);
		break;
		case 24:
			candyDesign(Designs[24]);
			this.setClicked(false);
		break;
		case 25:
			candyDesign(Designs[25]);
			this.setClicked(false);
		break;
	case 26:
		candyDesign(Designs[26]);
		this.setClicked(true);
	break;
	case 27:
		candyDesign(Designs[27]);
		this.setClicked(true);
	break;
	case 28:
		candyDesign(Designs[28]);
		this.setClicked(true);
	break;
	case 29:
		candyDesign(Designs[29]);
		this.setClicked(true);
	break;
	case 30:
		candyDesign(Designs[30]);
		this.setClicked(true);
	break;
	case 31:
		candyDesign(Designs[31]);
		this.setClicked(true);
	break;
	case 32:
		candyDesign(Designs[32]);
		this.setClicked(true);
	break;
	case 33:
		candyDesign(Designs[33]);
		this.setClicked(true);
	break;
	case 34:
		candyDesign(Designs[34]);
		this.setClicked(true);
	break;
	case 35:
		candyDesign(Designs[35]);
		this.setClicked(true);
	break;
	case 36:
		candyDesign(Designs[36]);
		this.setClicked(true);
	break;
	case 37:
		candyDesign(Designs[37]);
		this.setClicked(true);
	break;
	case 38:
		candyDesign(Designs[38]);
		this.setClicked(true);
	break;
	case 39:
		candyDesign(Designs[39]);
		this.setClicked(true);
	break;
	case 40:
		candyDesign(Designs[40]);
		this.setClicked(true);
	break;
	case 41:
		candyDesign(Designs[41]);
		this.setClicked(true);
	break;
	case 42:
		candyDesign(Designs[42]);
		this.setClicked(true);
	break;
	case 43:
		candyDesign(Designs[43]);
		this.setClicked(true);
	break;
	case 44:
		candyDesign(Designs[44]);
		this.setClicked(true);
	break;
	case 45:
		candyDesign(Designs[45]);
		this.setClicked(true);
	break;
	case 46:
		candyDesign(Designs[46]);
		this.setClicked(true);
	break;
	case 47:
		candyDesign(Designs[47]);
		this.setClicked(true);
	break;
	case 48:
		candyDesign(Designs[48]);
		this.setClicked(true);
	break;
	case 49:
		candyDesign(Designs[49]);
		this.setClicked(true);
	break;
	case 50:
		candyDesign(Designs[50]);
		this.setClicked(true);
	break;
	}
	}

	protected void loadDesigns(){
		setDesignLoaded(true);
		for(int i=0; i<51; i++){
			Designs[i] = "images/" + (i) + ".jpg";
		}
	}
	
	private void candyDesign(String imagePath){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    ImageIcon icon = new ImageIcon((Image)image);
	    super.setIcon(icon);
	}
	
	public boolean equals (Candy other)
	{
		return ((this.getkind()%6) == (other.getkind()%6));
	}

	public static boolean getDesignLoaded() {
		return designLoaded;
	}

	public static void setDesignLoaded(boolean designLoaded) {
		Candy.designLoaded = designLoaded;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void mousePressed(MouseEvent e) {
		this.getBoard().addPress(this);
		if((isClicked() == true)){
			createIcon(kind);
		}
		
		else{
			createIcon(kind+25);
		}
		if(this.getBoard().isTwoCandyPressed())
			board.crush(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean between (int min, int max)
	{
		return (min <= this.getkind() && this.getkind() <= max);
	}
}

