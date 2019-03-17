package SwingTutorial.Layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CenterPanel extends JPanel{
	
	static Random random=new Random();
	
	JButton button;
	Color color;
	
	public CenterPanel() {
		this(Color.RED);
	}
	
	public CenterPanel(Color color) {
		this.color = color;
		initialize();
	}
	
	void prepairButton() {
		button=new JButton("Click me!");
        button.setLocation(new Point(100,100));
        button.setSize(new Dimension(100,60));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterPanel.this.setBackground(Color.YELLOW);
            }
        });

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setLocation(new Point(random.nextInt(CenterPanel.this.getSize().width-40),random.nextInt(CenterPanel.this.getSize().height-40)));
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        
        this.add(button);
	}
	
	void initialize() {
		this.setPreferredSize(new Dimension(400,500));
        this.setBackground(color);
        this.setLayout(null);

        prepairButton();
	}
}
