package SwingTutorial.Layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel{
	LeftPanel leftPanel;
	TopPanel topPanel;
	CenterPanel centerPanel1;
	CenterPanel centerPanel2;
	JLabel myLabel;
	JButton eastButton;
	
	public MainPanel() {
		initialize();
	}
	
	void prepairEastButton() {
		eastButton = new JButton("ChangeFrame");
		eastButton.setSize(new Dimension(100, 100));
        
		eastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(Component component: MainPanel.this.getComponents()) {
					System.out.println(component.getClass());
					if(component == centerPanel1) {
						MainPanel.this.remove(component);
						MainPanel.this.add(centerPanel2);
						System.out.println("centerPanel1 Removed");
						break;
					}
					if(component == centerPanel2) {
						MainPanel.this.remove(component);
						MainPanel.this.add(centerPanel1);
						System.out.println("centerPanel2 Removed");
						break;
					}
				}
				MainPanel.this.revalidate();
				MainPanel.this.repaint();
			}
		});
		
		this.add(eastButton,BorderLayout.EAST);
	}
	
	void prepairLeftPanel() {
		leftPanel=new LeftPanel();
        this.add(leftPanel,BorderLayout.WEST);
	}
	
	void prepairTopPanel() {
		topPanel=new TopPanel();
        this.add(topPanel,BorderLayout.NORTH);
	}
	
	void prepairCenterPanel1() {
		centerPanel1=new CenterPanel();
        this.add(centerPanel1,BorderLayout.CENTER);
	}
	
	void prepairCenterPanel2() {
		centerPanel2=new CenterPanel(Color.BLUE);
        //this.add(centerPanel1,BorderLayout.CENTER);
	}
	
	void prepairMyLabel() {
		myLabel=new JLabel("Hi");
        this.add(myLabel,BorderLayout.SOUTH);
	}
	
	void initialize() {
		this.setLayout(new BorderLayout());
        
        prepairLeftPanel();
        prepairTopPanel();
        prepairCenterPanel1();
        prepairCenterPanel2();
        prepairEastButton();
        prepairMyLabel();
	}
}
