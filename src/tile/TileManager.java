package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gamePanel;
	Tile[] floorTiles;
	int[][] map;
	int[][] rotateMap;
	double  emptyTilePercent = 0.9;
	
	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		floorTiles = new Tile[8];
		map = generateMap(gamePanel.maxScreenCol, gamePanel.maxScreenRow, floorTiles.length);
		rotateMap = rotateTiles(gamePanel.maxScreenCol, gamePanel.maxScreenRow);
		getTileImage();
	}

	public void getTileImage() {
		try {
			for (int x = 0; x < floorTiles.length; x++) {
				floorTiles[x] = new Tile();
				floorTiles[x].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor_" + x + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int row = 0;
		int col = 0;
		int x = 0;
		int y = 0;
		
		while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
			switch(rotateMap[col][row]) {
			case 0: 
				g2.drawImage(floorTiles[map[col][row]].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // No rotation
				break;
			case 1: 
				g2.drawImage(floorTiles[map[col][row]].image, x + gamePanel.tileSize, y, -gamePanel.tileSize, gamePanel.tileSize, null); // Horizontal flip
				break;
			case 2: 
				g2.drawImage(floorTiles[map[col][row]].image, x, y + gamePanel.tileSize, gamePanel.tileSize, -gamePanel.tileSize, null); // Vertical flip
				break;
			case 3: 
				g2.drawImage(floorTiles[map[col][row]].image, x + gamePanel.tileSize, y + gamePanel.tileSize, -gamePanel.tileSize, -gamePanel.tileSize, null); // Both flips
				break;
			}
			col++;
			x += gamePanel.tileSize;
			
			if (col == gamePanel.maxScreenCol) {
				row++;
				col = 0;
				x = 0;
				y += gamePanel.tileSize;
			}
		}
	}
	
	public int[][] generateMap(int col, int row, int options) {
		int[][] newMap = new int[col][row];
		
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < col; y++) {
				if (Math.random() <= emptyTilePercent) {
					newMap[y][x] = 0;
				} else {
					newMap[y][x] = (int) (Math.random() * options);
				}
			}
		}
		
		return newMap;
	}
	
	public int[][] rotateTiles(int col, int row) {
		int[][] rotateMap = new int[col][row];

		for (int x = 0; x < row; x++) {
			for (int y = 0; y < col; y++) {
				rotateMap[y][x] = (int) Math.round(Math.random() * 3);
			}
		}

		return rotateMap;
	}
	
}
