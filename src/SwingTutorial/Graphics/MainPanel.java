package SwingTutorial.Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logger.Logger;

public class MainPanel extends JPanel implements Drawable{
	private JButton button;
	private JButton button2;
	private JTextField textField;
	
	int thinkness = 100;
	boolean increasing = true;
	
	ArrayList<Circle> points;
	
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
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try {
					getGraphics().fillRect(0, 100, 600, 600);
				}
				catch (Exception ex) {
					Logger.getLogger().error(ex.getMessage());
					for(Object object: ex.getStackTrace())
						Logger.getLogger().error(object.toString());
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true) {
					try {
						repaint();
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}
	
	void addCircle(Circle p) {
		points.add(p);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		render((Graphics2D)g);
	}
	
	public void render(Graphics2D G) {
		G.setColor(Color.BLACK);
		G.fillRect(100, 100, 300, 400);
		
		for(Circle circle:points) {
			circle.render(G);
		}
		
		if(increasing) {
			thinkness += 1;
			if(thinkness>300)
				increasing=false;
		}
		else {
			thinkness -= 1;
			if(thinkness<50)
				increasing=true;
		}
		try {
			//Graphics G = getGraphics();
			G.setColor(Color.WHITE);
			G.clearRect(0, 0, 2000, 1000);
			G.setColor(Color.ORANGE);
			
			BufferedImage image = ImageIO.read(new File("C:/Users/Hima/Pictures/4.PNG"));
			
			G.drawImage(image, thinkness, 180, this);
		}
		catch (Exception ex) {
			Logger.getLogger().error(ex.getMessage());
			for(Object object: ex.getStackTrace())
				Logger.getLogger().error(object.toString());
		}
	}
}
