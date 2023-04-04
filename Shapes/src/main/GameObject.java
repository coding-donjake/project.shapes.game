package main;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GameObject {
	
	public Color color;
	
	public double x, y;
	public double width, height, radius;
	
	abstract void tick();
	abstract void render(Graphics2D g);

}
