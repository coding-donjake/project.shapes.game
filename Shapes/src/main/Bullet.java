package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject {
	
	public Bullet(double x, double y, double mx, double my, double damage, double attackRange, Handler handler) {
		this.x = x;
		this.y = y;
		this.damage= damage;
		this.attackRange = attackRange;
		this.handler = handler;
		id = ID.bullet;
		color = Color.white;
		movementSpeed = 5;
		radius = 5;
		width = radius * 2;
		height = radius * 2;
		calcVelocities(mx, my);
	}

	@Override
	void tick() {
		x += velX;
		y += velY;
		travelledDistance += movementSpeed;
		if (travelledDistance >= attackRange) {
			alive = false;
		}
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.enemy) {
				if (Game.checkCollision(x, y, radius, object.x, object.y, object.radius)) {
					object.health -= damage;
					alive = false;
				}
				break;
			}
		}
	}

	@Override
	void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
	}
	
	private void calcVelocities(double x, double y) {
		double thetaX = x - (Main.WIDTH / 2);
		double thetaY = y - (Main.HEIGHT / 2);
		angle = Math.atan2(thetaY, thetaX);
		velX = (movementSpeed * Math.cos(angle));
		velY = (movementSpeed * Math.sin(angle));
	}

}
