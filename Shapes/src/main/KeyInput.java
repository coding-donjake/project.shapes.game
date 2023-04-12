package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	public Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// player move up
		if (key == KeyEvent.VK_W) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveUp = true;
					object.moveDown = false;
				}
			}
		}
		// player move down
		if (key == KeyEvent.VK_S) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveUp = false;
					object.moveDown = true;
				}
			}
		}
		// player move left
		if (key == KeyEvent.VK_A) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveLeft = true;
					object.moveRight = false;
				}
			}
		}
		// player move right
		if (key == KeyEvent.VK_D) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveLeft = false;
					object.moveRight = true;
				}
			}
		}
		// increaseStrength
		if (key == KeyEvent.VK_R) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.increaseStrength();
				}
			}
		}
		// increaseAgility
		if (key == KeyEvent.VK_F) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.increaseAgility();
				}
			}
		}
		// increaseIntelligence
		if (key == KeyEvent.VK_V) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.increaseIntelligence();
				}
			}
		}
		// cast1
		if (key == KeyEvent.VK_1) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					if (object.ability1 && object.cd1 >= 4000 && object.mana > 30) {
						object.cast1 = 30;
						object.cd1 = 0;
						object.mana -= 30;
					}
				}
			}
		}
		// cast1
		if (key == KeyEvent.VK_E) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					if (!object.shooting) {
						object.shooting = true;
					} else {
						object.shooting = false;
					}
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		// player move up
		if (key == KeyEvent.VK_W) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveUp = false;
				}
			}
		}
		// player move down
		if (key == KeyEvent.VK_S) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveDown = false;
				}
			}
		}
		// player move left
		if (key == KeyEvent.VK_A) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveLeft = false;
				}
			}
		}
		// player move right
		if (key == KeyEvent.VK_D) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveRight = false;
				}
			}
		}
	}

}
