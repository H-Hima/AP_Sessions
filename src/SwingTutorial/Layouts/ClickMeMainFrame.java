package SwingTutorial.Layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClickMeMainFrame extends JFrame {
	
	JPanel contentPane;
	
	MainPanel mainPanel;
	
	void prepairMainPanel() {
		mainPanel = new MainPanel();
        contentPane.add(mainPanel);
	}
	
	public ClickMeMainFrame(String name) {
		super(name);
        initialize();
	}
	
    void initialize() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(700,700));
        
    	contentPane=(JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        prepairMainPanel();
    }
}
