package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ScoreBoard.ScoreBoard;

public class ScoresWindow extends JFrame{

	ScoreBoard scores;
	
	public ScoresWindow(int k){
		super("High Scores");
		WindowAdapter exitListener = new WindowAdapter() {

		       
	        public void windowClosing(WindowEvent e) {
	        	WelcomeWindow window = new WelcomeWindow();
	            setVisible(false);
	            }
	    };
	    	if(k == 1){
	    		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    		addWindowListener(exitListener);
	    	}
		this.setResizable(false);
		this.setLocation(450, 220);
		this.setPreferredSize(new Dimension(400,400));
		this.pack();
		this.setVisible(true);
		scores = new ScoreBoard();
		JTextArea Scores = new JTextArea("High Scores : " + "\n" + "\n" + scores.toString());
		Font font = Scores.getFont();
		float size = font.getSize() + 4.0f;
		Scores.setFont( font.deriveFont(size) );
		Scores.setBackground(null);
		Scores.setEditable(false);
		Scores.setEnabled(false);
		Scores.setDisabledTextColor(new Color(999999));
		JPanel panel = new JPanel();
		panel.add(Scores);
		getContentPane().add(panel);
	}
	
}
