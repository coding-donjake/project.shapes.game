package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends GameObject {

	public Player(double x, double y, Handler handler) {
		super(x, y, handler);
		
		// required attributes
		id = ID.player;
		color = Color.white;
		
		// positioning and size
		radius = 25;
		width = radius * 2;
		height = radius * 2;
		
		// global increase
		damage = 10;
		
		// strength
		health = 100;
		maxHealth = 100;
		regen = 0.03;
		
		// agility
		movementSpeed = 2;
		attackSpeed = 5;
		attackRange = 200;
		
		// intelligence
		mana = 50;
		maxMana = 50;
		
		// abilities
		cd1 = 4000;
		cd1 = 8000;
		cd1 = 12000;
		
	}

	@Override
	public void tick() {
		if (health <= 0) {
			alive = false;
		} else {
			cooldown();
			levelUp();
			regen();
			reload();
			travel();
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);
		
		g.setColor(Color.black);
		g.drawOval((int)(Camera.calcX(x, handler) - (width / 2)), (int)(Camera.calcY(y, handler) - (height / 2)), (int)width, (int)height);

		// cursor
		g.setColor(Color.green);
		g.drawLine((int)mx, 0, (int)mx, Main.HEIGHT);
		g.drawLine(0, (int)my, Main.WIDTH, (int)my);
		
		// health bar
		g.setColor(Color.white);
		g.fillRect(10, 10, 200, 20);
		g.setColor(Color.green);
		g.fillRect(10, 10, (int)((health / maxHealth) * 200), 20);
		g.setColor(Color.black);
		g.drawRect(10, 10, 200, 20);
    	g.drawString((int)health + " / " + (int)maxHealth, 15, 25);
    	
    	// mana bar
		g.setColor(Color.white);
		g.fillRect(10, 40, 200, 20);
		g.setColor(Color.blue);
		g.fillRect(10, 40, (int)((mana / maxMana) * 200), 20);
		g.setColor(Color.black);
		g.drawRect(10, 40, 200, 20);
    	g.drawString((int)mana + " / " + (int)maxMana, 15, 55);
    	
    	// statistics
		g.setColor(Color.white);
		g.fillRect(10, 70, 100, 160);
		g.setColor(Color.black);
		g.drawRect(10, 70, 100, 160);
		g.drawString("EXP: " + experience, 15, 85);
		g.drawString("Regen: " + (int)(regen * 100), 15, 97);
		g.drawString("Armor: " + (int)armor, 15, 109);
		g.drawString("Magic R: " + (int)magicResistance, 15, 121);
		g.drawString("Damage: " + (int)damage, 15, 133);
		g.drawString("Magic D: " + (int)magicDamage, 15, 145);
		g.drawString("Attack S: " + (int)attackSpeed, 15, 156);
		g.drawString("Attack R: " + (int)attackRange, 15, 167);
		g.drawString("Move S: " + (int)movementSpeed, 15, 178);
		g.drawString("STR: " + strength, 15, 189);
		g.drawString("AGI: " + agility, 15, 200);
		g.drawString("INT: " + intelligence, 15, 211);
		g.drawString("LVL: " + level, 15, 222);
		
		// ability1
		g.setColor(Color.white);
		g.fillRect(Main.WIDTH - 180, 10, 50, 50);
		if (!ability1) {
			g.setColor(Color.red);
			g.fillRect(Main.WIDTH - 180, 10, 50, 50);
		} else {
			g.setColor(Color.black);
			g.drawString("Skill 1", Main.WIDTH - 175, 38);
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(Main.WIDTH - 180, (int)(10 + (50 * (cd1 / 4000.0))), 50, (int)(50 - (50 * (cd1 / 4000.0))));
		}
		g.setColor(Color.black);
		g.drawRect(Main.WIDTH - 180, 10, 50, 50);
	}

}
