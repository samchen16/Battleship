package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

class AttackingPanelListener extends ChangeListener {
	
	Grid grid;
	GameState gamestate;
	AttackingPanel attackPanel;
	
	public boolean foundShip;
	public Point recentHit;
	public boolean destroyed;
	
	public AttackingPanelListener (Grid g, GameState gs, AttackingPanel ap) {
		grid = g;
		gamestate = gs;
		foundShip = false;
		destroyed = false;
		recentHit = new Point();
		attackPanel = ap;
		// Connect to view
		for (int i = 0; i < ap.actors.length; i++) {
			for (int j = 0; j < ap.actors [i].length; j++) {
				ap.actors[i][j].addListener(this);
			}
		}
	}
	
	public void changed(ChangeEvent event, Actor actor) {
		GridButton b = (GridButton) actor;
    	if (!grid.isAttackable(b.x, b.y)) {
    		return;
    	}
		Ship s = grid.attack(b.x, b.y);
    	
    	// Change view depending on whether attack hit or missed
    	if (grid.isHit(b.x, b.y)) {
    		b.setText("X");
    		foundShip = true;
    		recentHit.x = b.x;
    		recentHit.y = b.y;
    		//System.out.println("hit " + b.x + " " + b.y);
    	}
    	else if (grid.isMiss(b.x, b.y)) {
    		b.setText("O");

    		foundShip = false;
    		//System.out.println("miss " + b.x + " " + b.y);
    	}
    	b.setDisabled(true);
    	if (s != null && s.health == 0) {
    		destroyed = true;
    	}
    	// Switches turns
    	gamestate.playerTurn = !gamestate.playerTurn;
    	//System.out.println(gamestate.playerTurn);
	}
}

public class AttackingPanel extends GridButtonPanel{
	
	public AttackingPanel (Grid g) {
		super(g.getNumCellsX(), g.getNumCellsY(), "~");
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
		textButtonStyle.checked = skin.newDrawable("white", Color.RED);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		makeButtonGrid(skin);
	}
	
}
