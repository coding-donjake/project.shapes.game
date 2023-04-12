package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy3 extends GameObject {

	public Enemy3(double x, double y, Handler handler) {
		super(x, y, handler);
		
		// required attributes
		id = ID.enemy;
		color = Color.blue;
		
		// positioning and size
		radius = 8;
		width = radius * 2;
		height = radius * 2;
		
		// global increase
		damage = 30;
		
		// kill increase
		experience = 30;
		
		// strength
		health = 10;
		maxHealth = 10;
		
		// agility
		movementSpeed = 4;
		
		// global attributes
		damageType = "magical";
	}

	@Override
	public void tick() {
		if (health <= 0) {
			die();
		} else {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					followPoint(object.x, object.y);
					travel();
					if (Game.checkCollision(x, y, radius, object.x, object.y, object.radius)) {
						if (object.reflection <= 0) {
							if (object.alive && object.vulnerable) {
								object.takeDamage(damageType, damage);
								object.takeExperience(2);
								alive = false;
							}
						} else {
							
						}
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
		
		g.setColor(Color.black);
		g.drawOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);

		// health bar
		g.setColor(Color.red);
		g.fillRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)((health / maxHealth) * width), 5);
		g.setColor(Color.black);
		g.drawRect((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)) - 10, (int)width, 5);
	}

}
