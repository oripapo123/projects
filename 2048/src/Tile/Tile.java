package Tile;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Tile extends JLabel{
	
	static private String[][] Designs = new String[3][14]; 
	static private boolean designLoaded = false;
	static private int designValue = 0;
	private int value;
	
	public Tile (int designValue)
	{
		if(!designLoaded)
			loadDesigns();
		setDesignValue(designValue);
		tileDesign("images/numbers/0.jpg");
		value = 0;
		
	}
	
	public Tile (int designValue, int value)
	{
		if(!designLoaded)
			loadDesigns();
		setDesignValue(designValue);
		setValue(value);
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setDesignValue(int designValue){
		this.designValue = designValue;
	}
	
	public int getDesignValue(){
		return this.designValue;
	}
	
	public void setDesignLoaded(boolean designLoaded){
		this.designLoaded = designLoaded;
	}
	
	public boolean getDesignLoaded(){
		return this.designLoaded;
	}
	
	public void setValue (int newValue)
	{
		this.value = newValue;
		switch(value){
		
		case 0:
			tileDesign(Designs[getDesignValue()][0]);
		    break;
		
		case 2:
			tileDesign(Designs[getDesignValue()][1]);
		    break;
		
		case 4:
			tileDesign(Designs[getDesignValue()][2]);
		    break;
		    
		case 8:
			tileDesign(Designs[getDesignValue()][3]);
		    break;
		    
		case 16:
			tileDesign(Designs[getDesignValue()][4]);
		    break;
		    
		case 32:
			tileDesign(Designs[getDesignValue()][5]);
		    break;
		    
		case 64:
			tileDesign(Designs[getDesignValue()][6]);
		    break;
		    
		case 128:
			tileDesign(Designs[getDesignValue()][7]);
		    break;
		    
		case 256:
			tileDesign(Designs[getDesignValue()][8]);
		    break;
		    
		case 512:
			tileDesign(Designs[getDesignValue()][9]);
		    break;
		    
		case 1024:
			tileDesign(Designs[getDesignValue()][10]);
		    break;
		    
		case 2048:
			tileDesign(Designs[getDesignValue()][11]);
			break;
		    
		case 4096:
			tileDesign(Designs[getDesignValue()][12]);
			break;
			
		case 8192:
			tileDesign(Designs[getDesignValue()][13]);
			break;
		}	
	}
		
	public void multiValue ()
	{
		setValue(getValue()*2);
	}
	
	public boolean equals (Tile other)
	{
		if (this.getValue()==other.getValue())
			return true;
		return false;
	}
	
	private void tileDesign(String imagePath){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    ImageIcon icon = new ImageIcon((Image)image);
	    super.setIcon(icon);
	}
	
	private void loadDesigns(){
		designLoaded = true;
		Designs[0][0] = "images/numbers/0.jpg";
		Designs[1][0] = "images/babes/0.jpg";
		Designs[2][0] = "images/beers/0.jpg";
		for(int i=1; i<14; i++){
			Designs[0][i] = "images/numbers/" + ((int)Math.pow(2, i)) + ".jpg";
			Designs[1][i] = "images/babes/" + ((int)Math.pow(2, i)) + ".jpg";
			Designs[2][i] = "images/beers/" + ((int)Math.pow(2, i)) + ".jpg";
		}
	}
	
	public String toString()
	{
		return Integer.toString(this.getValue());
	}
	
}