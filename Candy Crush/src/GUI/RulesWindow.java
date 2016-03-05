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
		" These are the rules : The game board is 9x9 by size," + "\n" + "\n" +   
		" you will have to choose two candies to switch between" + "\n" + "\n" +
		" their places in order to create a three in a row" + "\n" + "\n" +
		" or column, four in a row or column, five in a row or " + "\n" + "\n" +
		" column, an L shape or a T shape. Whenever this is done," + "\n" + "\n" +
		" a chain reaction is occured all over the board, granting" + "\n" + "\n" +
		" you more and more points!" + "\n" + "\n" +
		" try to get as much points as you can. Good Luck!");
		
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
