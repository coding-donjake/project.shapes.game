package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends GameObject {
	
	public Player(double x, double y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		id = ID.player;
		color = Color.blue;
		attackSpeed = 1;
		attackRange = 500;
		movementSpeed = 1;
		radius = 15;
		width = radius * 2;
		height = radius * 2;
		damage = 10;
		health = 100;
		maxHealth = 100;
	}

	@Override
	void tick() {
		if (moveUp) {
			y -= movementSpeed;
		}
		if (moveDown) {
			y += movementSpeed;
		}
		if (moveLeft) {
			x -= movementSpeed;
		}
		if (moveRight) {
			x += movementSpeed;
		}
		if (attackCD > 0) {
			attackCD -= attackSpeed;
		}
		if (shooting) {
			if (attackCD <= 0) {
				double thetaX = (Main.WIDTH / 2) - mx;
				double thetaY = (Main.HEIGHT / 2) - my;
				angle = Math.atan2(thetaY, thetaX);
				double x = ((radius + 5) * Math.cos(angle)) * -1;
				double y = ((radius + 5) * Math.sin(angle)) * -1;
				handler.objects.add(new Bullet(this.x + x, this.y + y, mx, my, damage, attackRange, handler));
				attackCD = 100;
			}
		}
	}

	@Override
	void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
		
		// health bar
		g.setColor(Color.green);
		g.drawRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)width, 5);
		g.fillRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)((health / maxHealth) * width), 5);
		
		// cursor
//		g.setColor(Color.green);
//		g.drawLine((int)mx, 0, (int)mx, Main.HEIGHT);
//		g.drawLine(0, (int)my, Main.WIDTH, (int)my);
	}

}
