package GUI;

import javax.swing.*;

import Board.Board;
import ScoreBoard.ScoreBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class GamePanel extends JFrame{

	private final Board panel;
	private final GridLayout mat;
	public final ScoreBoard scoreboard;
	
public GamePanel(){
	super("score : 0. moves left: 20");
	WindowAdapter exitListener = new WindowAdapter() {

	       
        public void windowClosing(WindowEvent e) {
        	WelcomeWindow window = new WelcomeWindow();
            setVisible(false);
            }
    };
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(exitListener);
	setFocusable(true);
	this.setLocation(350, 0);
	scoreboard = new ScoreBoard();
	panel = new Board(this);
	mat = new GridLayout(9,9);
	panel.setLayout(mat);
	getContentPane().add(panel);
	setTitle("score : " + panel.gettotalScores()+ ". moves left: 20");
	this.setResizable(false);
	this.pack();
	this.setVisible(true);
	}
}