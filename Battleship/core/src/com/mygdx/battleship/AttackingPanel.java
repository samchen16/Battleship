package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AttackingPanel extends GridPanel{
	
	public AttackingPanel (Grid g) {
		super(g, new Vector2(0,0));
	}
	
	public AttackingPanel (Grid g, Vector2 pos) {
		super(g, pos);
	}
	
	public void draw (SpriteBatch batch) {
	    super.draw(batch);
		
	    for(int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				if (grid.getHit(x, y)) {
					//batch.draw(img, x * grid.getCellSize(), y * grid.getCellSize());
				}
				else if (grid.getMiss(x, y)) {
					//batch.draw(img, x * grid.getCellSize(), y * grid.getCellSize());
				}
				else if (grid.getAttackable(x, y)) {
					//batch.draw(img, (x + 0.5) * grid.getCellSize(), (y + 0.5) * grid.getCellSize());
				}
			}
		}
		
		
		
	}
	
}
