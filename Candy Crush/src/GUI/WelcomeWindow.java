package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

public class WelcomeWindow extends JFrame{
	
	private final JPanel panel;
	private static int design = 0;

	public WelcomeWindow(){
		super("Candy Crush");
		this.setLocation(350, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(720,720));
		this.setResizable(false);
		
		
		panel = new JPanel();
		
		JButton newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(130,30));
		newGame.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickNewGame();
            }
        });
        
		JButton ScoreBoard = new JButton("Score Board");
		ScoreBoard.setPreferredSize(new Dimension(130,30));
		ScoreBoard.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickScoreBoard();
            }
        });
		
		JButton Rules = new JButton("Rules");
		Rules.setPreferredSize(new Dimension(130,30));
		Rules.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickRules();
            }
        });
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();
		
		
		
		panel.setLayout(new GridBagLayout());
		cons.gridx = 1;
		cons.gridy = 1;
		panel.add(newGame, cons);
		cons.gridx = 1;
		cons.gridy = 10;
		panel.add(ScoreBoard, cons);
		cons.gridx = 1;
		cons.gridy = -10;
		panel.add(Rules, cons);
		layout.setConstraints(newGame, cons);
		getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	
	private void clickNewGame(){
		this.setVisible(false);
		GamePanel game = new GamePanel();
	}
	
	private void clickScoreBoard(){
		this.setVisible(false);
		ScoresWindow scores = new ScoresWindow(1);
	}
	
	private void clickRules(){
		this.setVisible(false);
		RulesWindow rules = new RulesWindow();
	}
	
	public void setDesign(int newDesign){
		this.design = newDesign;
	}
	
	public int getDesign(){
		return this.design;
	}
}
