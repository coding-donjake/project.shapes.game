package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(Main main) {
		JFrame frame = new JFrame("SHAPES");
		frame.setPreferredSize(new Dimension(Main.WIDTH + 16, Main.HEIGHT + 39));
		frame.setMaximumSize(new Dimension(Main.WIDTH + 16, Main.HEIGHT + 39));
		frame.setMinimumSize(new Dimension(Main.WIDTH + 16, Main.HEIGHT + 39));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(main);
		main.start();
	}

}
