package GUI;

import javax.swing.*;

import Matrix.Matrix;
import ScoreBoard.ScoreBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class GamePanel extends JFrame implements KeyListener{

	private final Matrix panel;
	private final GridLayout mat;
	public final ScoreBoard scoreboard;
	static int design = 1;
	
public GamePanel(int design){
	super("score : 0");
	WindowAdapter exitListener = new WindowAdapter() {

	       
        public void windowClosing(WindowEvent e) {
        	WelcomeWindow window = new WelcomeWindow();
            setVisible(false);
            }
    };
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(exitListener);
	setFocusable(true);
	this.design = design;
	this.setLocation(450, 220);
	scoreboard = new ScoreBoard();
	panel = new Matrix(this.design);
	addKeyListener(this);
	mat = new GridLayout(4,4);
	panel.setLayout(mat);
	getContentPane().add(panel);
	
	this.setResizable(false);
	this.setMaximumSize(new Dimension(400,400));
	this.pack();
	this.setVisible(true);
	}

public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch( keyCode ) { 
        case KeyEvent.VK_UP:
		try {
			panel.moveUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		setTitle("score : " + panel.getScore());
		pack();
		validate();
            break;
            
        case KeyEvent.VK_DOWN:
		try {
			panel.moveDown();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		setTitle("score : " + panel.getScore());
		pack();
		validate();
            break;
            
        case KeyEvent.VK_LEFT:
		try {
			panel.moveLeft();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		setTitle("score : " + panel.getScore());
		pack();
		validate();
            break;
            
        case KeyEvent.VK_RIGHT :
		try {
			panel.moveRight();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		setTitle("score : " + panel.getScore());
		pack();
		validate();
            break;
     }
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}