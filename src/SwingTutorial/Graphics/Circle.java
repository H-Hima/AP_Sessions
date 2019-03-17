package SwingTutorial.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle implements Drawable{
	Point center;
	int radius;
	Color borderColor;
	Color fillColor;
	int thickness = 10;
	
	public Circle(Point center, int radius, Color borderColor, Color fillColor) {
		this.center = center;
		this.radius = radius;
		this.borderColor=borderColor;
		this.fillColor = fillColor;
	}
	
	boolean isIn(Point other) {
		return Math.sqrt(
				(other.getX()-center.getX())*(other.getX()-center.getX()) + 
				(other.getY()-center.getY())*(other.getY()-center.getY())
				) < radius;
	}

	@Override
	public void render(Graphics2D G) {
		// TODO Auto-generated method stub
		G.setColor(borderColor);
		G.fillOval((int)center.getX()-radius, (int)center.getY()-radius, radius*2, radius*2);
		G.setColor(fillColor);
		G.fillOval((int)center.getX()+thickness/2-radius, (int)center.getY()+thickness/2-radius, radius*2-thickness, radius*2-thickness);
	}
	
	
}
