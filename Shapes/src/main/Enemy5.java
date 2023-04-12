package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy5 extends GameObject {

	public Enemy5(double x, double y, double angle, Handler handler) {
		super(x, y, angle, handler);
		
		// required attributes
		id = ID.enemy;
		color = Color.pink;
		
		// positioning and size
		radius = 20;
		width = radius * 2;
		height = radius * 2;
		
		// global increase
		damage = 30;
		
		// kill increase
		experience = 35;
		
		// strength
		health = 100;
		maxHealth = 100;
		
		// agility
		movementSpeed = 1;
		
		// global attributes
		vulnerable = false;
		bounceEnergy = 30;
		damageType = "magical";
		
	}

	@Override
	public void tick() {
		if (health <= 0) {
			die();
		} else {
			if (bounceEnergy <= 0) {
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
			} else {
				bounce(angle);
				travel();
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
