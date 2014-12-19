package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

class AttackingPanelListener extends ChangeListener {
	Grid grid;
	public AttackingPanelListener (Grid g) {
		grid = g;
	}
	
	public void changed(ChangeEvent event, Actor actor) {
		GridButton gb = (GridButton) actor;
    	grid.attack(gb.x, gb.y);
    	if (grid.isAttackable(gb.x, gb.y)){
    		gb.setText(" ");
    	}
    	else if (grid.isHit(gb.x, gb.y)) {
    		gb.setText("X");
    	}
    	else if (grid.isMiss(gb.x, gb.y)) {
    		gb.setText("O");
    	}
	}
}

public class AttackingPanel extends GridPanel{
	
	public AttackingPanel (Grid g) {
		super(g, new Vector2(100,0));
		AttackingPanelListener listener = new AttackingPanelListener(g);
		
		skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());
		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		makeButtonGrid(skin, listener);
	}
	
	public AttackingPanel (Grid g, Vector2 pos) {
		super(g, pos);
	}
	
	public void draw (SpriteBatch batch) {
	    //super.draw(batch);
		
	    for(int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				if (grid.isHit(x, y)) {
					//batch.draw(img, x * grid.getCellSize(), y * grid.getCellSize());
				}
				else if (grid.isMiss(x, y)) {
					//batch.draw(img, x * grid.getCellSize(), y * grid.getCellSize());
				}
				else if (grid.isAttackable(x, y)) {
					//batch.draw(img, (x + 0.5) * grid.getCellSize(), (y + 0.5) * grid.getCellSize());
				}
			}
		}
		
		
		
	}
	
}
