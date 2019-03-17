package SwingTutorial.Layouts;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TopPanel extends JPanel{
	
	JTextField textField1;
	JTextField textField2;
	
	public TopPanel() {
		initialize();
	}
	
	void prepairTextField1() {
		textField1 = new JTextField("Default Value 1");
        this.add(textField1);
	}
	
	void prepairTextField2() {
		textField2 = new JTextField("Default Value 2");
        this.add(textField1);
	}
	
	void initialize() {
		BoxLayout boxLayoutX = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(boxLayoutX);
        this.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        
        prepairTextField1();
        prepairTextField2();
	}
}
