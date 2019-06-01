package SwingTutorial.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logger.Logger2;

public class MainFrame extends JFrame {
	private JPanel contentPane;
	MainPanel mainPanel;
	
	public MainFrame(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		this("Default Name");
	}
	
	void initialize() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		contentPane = (JPanel)getContentPane();
		mainPanel = new MainPanel();
		contentPane.add(mainPanel);
		
		mainPanel.addCircle(new Circle(new Point(150, 250), 100, Color.BLUE, Color.RED));
		mainPanel.repaint();
		
		
	}
}
