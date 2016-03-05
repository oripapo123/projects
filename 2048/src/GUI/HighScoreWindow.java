package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ScoreBoard.ScoreBoard;

public class HighScoreWindow extends JFrame {

	private int newScore;
	
	public HighScoreWindow(int score){
		super("New High Score!!");
		newScore = score;
		this.setVisible(true);
		this.setPreferredSize(new Dimension(250, 150));
		this.setLocation(500, 300);
		JPanel panel = new JPanel();
		
		JTextArea text = new JTextArea("Congratulations," +"\n" + "You reached the High Score Table!" + "\n" + "Enter your name:");
		text.setBackground(null);
		text.setEditable(false);
		text.setEnabled(false);
		text.setDisabledTextColor(new Color(999999));
		
		final JTextField box = new JTextField();
		box.setPreferredSize(new Dimension(100, 25));
		box.setAlignmentX(CENTER_ALIGNMENT);
		box.setAlignmentY(CENTER_ALIGNMENT);
		
		JButton exit = new JButton("Submit");
		exit.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickExit(box.getText());
            }
        });
		
		panel.add(text);
		panel.add(box);
		panel.add(exit);
		this.getContentPane().add(panel);
		this.pack();
	}
	
	private void clickExit(String name){
		ScoreBoard newScore = new ScoreBoard();
		newScore.addScore(name + "$" + this.newScore);
		this.setVisible(false);
		ScoresWindow scores = new ScoresWindow(0);
		scores.setVisible(true);
	}
	
}
