package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	public GameWindow(GamePanel gamePanel) {
		this.setTitle("Skeleton Survival");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(gamePanel);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {

			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}

		});
	}

}
