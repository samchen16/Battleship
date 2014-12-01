package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GridPanel {

	protected Grid grid;
	protected Vector2 position;
	private Texture gridTex;
	
	public GridPanel (Grid g, Vector2 pos) {
		grid = g;
		position = pos;
		int width = grid.getNumCellsX() * (int) grid.getCellSize();
		int height = grid.getNumCellsY() * (int) grid.getCellSize();
		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		pixmap.setColor(Color.BLACK);
        for (int x = 0; x <= grid.getNumCellsX(); x++) {
        	int xPos = x * (int) grid.getCellSize();
        	pixmap.drawLine(xPos, 0, xPos, pixmap.getHeight()-1);
        }
        for (int y = 0; y <= grid.getNumCellsY(); y++) {
    		int yPos = y * (int) grid.getCellSize();
        	pixmap.drawLine(0, yPos, pixmap.getWidth()-1, yPos);
    	}
        gridTex = new Texture(pixmap);
	}
	
	public void draw (SpriteBatch batch) {
		batch.draw(gridTex, position.x, position.y);
	}
	
	public void dispose() {
		gridTex.dispose();
	}
	
}
