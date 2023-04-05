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
					object.moveLeft = false;
					object.moveRight = false;
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
					object.moveLeft = false;
					object.moveRight = false;
				}
			}
		}
		// player move left
		if (key == KeyEvent.VK_A) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject object = handler.objects.get(i);
				if (object.id == ID.player) {
					object.moveUp = false;
					object.moveDown = false;
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
					object.moveUp = false;
					object.moveDown = false;
					object.moveLeft = false;
					object.moveRight = true;
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
