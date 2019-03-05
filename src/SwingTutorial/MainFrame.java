package SwingTutorial;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private JPanel contentPane;
	private MainPanel mainPanel;
	
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
	}
}
