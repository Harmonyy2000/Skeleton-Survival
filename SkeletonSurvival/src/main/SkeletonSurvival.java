package main;

import javax.swing.JFrame;

public class SkeletonSurvival {

	public static void main(String[] args) {
		JFrame gameWindow = new JFrame();
		GamePanel gamePanel = new GamePanel();
		
		gameWindow.setTitle("Skeleton Survival");
		gameWindow.setResizable(false);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.add(gamePanel);
		gameWindow.pack();
		
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		
		gamePanel.startGameThread();
	}

}
