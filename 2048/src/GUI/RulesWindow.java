package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RulesWindow extends JFrame{

	public RulesWindow(){
		super("Rules");
		WindowAdapter exitListener = new WindowAdapter() {

		       
	        public void windowClosing(WindowEvent e) {
	        	WelcomeWindow window = new WelcomeWindow();
	            setVisible(false);
	            }
	    };
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    addWindowListener(exitListener);
		this.setResizable(false);
		this.setLocation(450, 220);
		this.setPreferredSize(new Dimension(400,400));
		this.pack();
		this.setVisible(true);
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout();
		grid.setColumns(1);
		panel.setLayout(grid);
		
		JTextArea rules = new JTextArea(
		" These are the rules : The game board is 4x4 by size," + "\n" + "\n" +   
		" every game start there will be 2 randomly" + " generated" + "\n" + "\n" +
		" tiles somewhere on the board, numbered 2 or 4." + "\n" + "\n" + 
		" the goal of the game is to use the arrow keys to smash" + "\n" + "\n" +
		" similar tiles together to create a new, bigger one, and" + "\n" + "\n" +
		" earning points. The ultimate goal of the game is to get" + "\n" + "\n" +
		" to the '2048' tile and by that winning the game. After" + "\n" + "\n" +
		" getting to the '2048' tile the game continues, up to '8192'" + "\n" + "\n" +
		" tile. Do you think you can make it...?");
		
		Font font = rules.getFont();
		float size = font.getSize() + 4.0f;
		rules.setFont( font.deriveFont(size) );
		rules.setBackground(null);
		rules.setEditable(false);
		rules.setEnabled(false);
		rules.setDisabledTextColor(new Color(999999));
		
		panel.add(rules);
		getContentPane().add(panel);
	}
	
}
