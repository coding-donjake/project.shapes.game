package main;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GameObject {
	
	public ID id;
	public Color color;
	public Handler handler;
	
	public boolean alive = true;
	public boolean moveUp, moveDown, moveLeft, moveRight;
	public boolean shooting;
	
	public double angle;
	public double x, y, mx, my;
	public double velX, velY, movementSpeed, travelledDistance;
	public double attackSpeed, attackCD, attackRange;
	public double width, height, radius;
	
	public double damage, health, maxHealth;
	
	abstract void tick();
	abstract void render(Graphics2D g);

}
