package SwingTutorial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel{
	private JButton button;
	private JButton button2;
	private JTextField textField;
	
	ArrayList<Point> points;
	
	public MainPanel() {
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	void initialize() {
		points=new ArrayList<>();
		
		setLayout(null);
		
		button = new JButton("MyButton");
		button.setSize(100, 80);
		button.setLocation(150,300);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				button.setBackground(Color.RED);
			}
		});
		add(button);
		
		button2 = new JButton("MyButton");
		button2.setSize(100, 80);
		button2.setLocation(450,200);
				
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				button2.setBackground(Color.GREEN);
			}
		});
		add(button2);
		
		textField = new JTextField("Initial String");
		textField.setSize(100, 80);
		textField.setLocation(450,100);
		
		class MyListener implements MouseListener, MouseMotionListener {
			
			Point prevPossition = null;
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				prevPossition = null;
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				prevPossition = new Point(arg0.getXOnScreen(), arg0.getYOnScreen());
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				textField.setBackground(Color.CYAN);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				textField.setBackground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				textField.setBackground(Color.YELLOW);
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(prevPossition != null) {
					int dx = (int)(arg0.getXOnScreen() - prevPossition.getX());
					int dy = (int)(arg0.getYOnScreen() - prevPossition.getY());
					Point currentLocation = textField.getLocation();
					textField.setLocation(new Point((int)currentLocation.getX()+dx, (int)currentLocation.getY()+dy));
					prevPossition = arg0.getLocationOnScreen();
				}
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		}
		
		MyListener listener = new MyListener();
		textField.addMouseListener(listener);
		textField.addMouseMotionListener(listener);
		add(textField);
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				textField.setText("("+arg0.getX()+","+arg0.getY()+")");
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void addPoint(Point p) {
		points.add(p);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		render((Graphics2D)g);
	}
	
	void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(100, 100, 300, 400);
	}
}
