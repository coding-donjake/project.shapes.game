package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy1 extends GameObject {
	
	public Enemy1(double x, double y, double damage, Handler handler) {
		this.x = x;
		this.y = y;
		this.damage= damage;
		this.handler = handler;
		id = ID.enemy;
		color = Color.red;
		movementSpeed = 0.5;
		radius = 10;
		width = radius * 2;
		height = radius * 2;
		health = 50;
		maxHealth = 50;
	}

	void tick() {
		if (health <= 0) {
			alive = false;
		}
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				calcVelocities(object.x, object.y);
				if (Game.checkCollision(x, y, radius, object.x, object.y, object.radius)) {
					object.health -= damage;
					alive = false;
				}
				break;
			}
		}
		x += velX;
		y += velY;
	}
	
	void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
		
		// health bar
		g.setColor(Color.red);
		g.drawRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)width, 5);
		g.fillRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)((health / maxHealth) * width), 5);
	}
	
	private void calcVelocities(double x, double y) {
		double thetaX = x - this.x;
		double thetaY = y - this.y;
		angle = Math.atan2(thetaY, thetaX);
		velX = (movementSpeed * Math.cos(angle));
		velY = (movementSpeed * Math.sin(angle));
	}

}
