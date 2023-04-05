package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public Handler handler;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				object.shooting = true;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				object.shooting = false;
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				object.mx = mx;
				object.my = my;
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if (object.id == ID.player) {
				object.mx = mx;
				object.my = my;
			}
		}
	}

}
