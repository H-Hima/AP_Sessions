package SwingTutorial.Layouts;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LeftPanel extends JPanel{
	
	JButton jb1;
	JButton jb2;
	JButton jb3;
	
	public LeftPanel() {
		initialize();
	}
	
	void initialize() {
		removeAll();
		
    	BoxLayout boxLayoutY = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        this.setLayout(boxLayoutY);
        this.setBorder(new EmptyBorder(new Insets(10, 30, 50, 10)));
        
        this.setPreferredSize(new Dimension(300, 100));
        
        jb1 = new JButton("Button 1");
        jb1.setPreferredSize(new Dimension(100, 200));
        this.add(jb1);
        
        this.add(Box.createRigidArea(new Dimension(0, 60)));
        
        jb2 = new JButton("Button 2");
        this.add(jb2); 
        
        this.add(Box.createGlue());
        
        this.add(Box.createRigidArea(new Dimension(0, 60)));
        
        jb3 = new JButton("Button 3");
        this.add(jb3);
    }
}
