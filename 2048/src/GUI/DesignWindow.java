package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ScoreBoard.ScoreBoard;

public class DesignWindow extends JFrame{

	ScoreBoard scores;
	
	public DesignWindow(){
		super("Design Choose");
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
		
		JTextArea Design = new JTextArea("Choose Design : ");
		Font font = Design.getFont();
		float size = font.getSize() + 4.0f;
		Design.setFont( font.deriveFont(size) );
		Design.setBackground(null);
		Design.setEditable(false);
		Design.setEnabled(false);
		Design.setDisabledTextColor(new Color(999999));
		
		JButton classic = new JButton("Classic");
		buttonDesign(classic, "images/numbers/4.jpg");
		classic.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickNumbers();
            }
        });
	    
		JButton babes = new JButton("Babes");
		buttonDesign(babes, "images/babes/16.jpg");
		babes.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickBabes();
            }
        });
	    
		JButton beers = new JButton("Beers");
		buttonDesign(beers, "images/beers/64.jpg");
		beers.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
               clickBeers();
            }
        });
		
		JPanel panel = new JPanel();
		panel.add(Design);
		panel.add(classic);
		panel.add(babes);
		panel.add(beers);
		
		getContentPane().add(panel);
	}
	
	private void buttonDesign(JButton button, String imagePath){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    ImageIcon icon = new ImageIcon((Image)image);
	    button.setIcon(icon);
	}
	
	private void clickNumbers(){
		WelcomeWindow welcome = new WelcomeWindow();
		welcome.setDesign(0);
		this.setVisible(false);
	}
	
	private void clickBabes(){
		WelcomeWindow welcome = new WelcomeWindow();
		welcome.setDesign(1);
		this.setVisible(false);
	}
	private void clickBeers(){
	WelcomeWindow welcome = new WelcomeWindow();
	this.setVisible(false);
	welcome.setDesign(2);
	}
}
