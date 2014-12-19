package com.mygdx.battleship;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
class SelectShipPanelListener extends ChangeListener {
	Grid grid;
	GameState gamestate;
	SelectShipPanel selectshippanel;
	public SelectShipPanelListener (Grid g, GameState gs, SelectShipPanel ssp) {
		grid = g;
		gamestate = gs;
		selectshippanel = ssp;
		// Connect to view
		for (int i = 0; i < ssp.shipButtons.length; i++) {
			ssp.shipButtons[i].addListener(this);
		}
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		GridButton b = (GridButton) actor;
		System.out.println(selectshippanel);
		System.out.println(selectshippanel.gbpToShip);
		System.out.println(selectshippanel.gbpToShip.get(b));
		gamestate.selectedShip = selectshippanel.gbpToShip.get(b);
	}
}
class ToggleOrientationListener extends ChangeListener {
	GameState gamestate;
	public ToggleOrientationListener (Grid g, GameState gs, SelectShipPanel ssp) {
		ssp.toggleOrientation.addListener(this);
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		TextButton b = (TextButton) actor;
    	if (gamestate.selectedShip == null) {
    		return;
    	}
		gamestate.selectedShip.changeOrientation();    	
	}
}

class FinishPlacementListener extends ChangeListener {
	GameState gamestate;
	SelectShipPanel ssp;
	
	public FinishPlacementListener (Grid g, GameState gs, SelectShipPanel ssp) {
		ssp.finishPlacement.addListener(this);
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
	}
}

public class SelectShipPanel extends Table {
	public HashMap<GridButton, Ship> gbpToShip;
	public GridButtonPanel[] shipButtons;
	public TextButton toggleOrientation;
	public TextButton finishPlacement;
	public Ship[] ships;
	public SelectShipPanel (Ship[] s) {
		ships = s;
		gbpToShip = new HashMap<GridButton, Ship>();
		Skin skin = new Skin();
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
		LabelStyle labelStyle = new LabelStyle (new BitmapFont(), new Color(1.0f,1.0f,1.0f,1.0f));
		this.add(new Label("Select a ship below, \nthen click any grid square to place.", labelStyle)).pad(5);
		this.row();
		toggleOrientation = new TextButton("Flip Selected", skin);
		this.add(toggleOrientation).pad(5);
		this.row();
		Table t = new Table();
		//setSkin(skin);
		//this.add(new Label("Select a ship to place", skin));
		//System.out.println("shipbuttons lengtg = "+ships);
		shipButtons = new GridButtonPanel[ships.length];
		for(int i = 0; i<shipButtons.length; i++){
			shipButtons[i] = new GridButtonPanel(ships[i].getWidth(), ships[i].getHeight(), "=");
			shipButtons[i].makeButtonGrid(skin);
			shipButtons[i].pad(10);
			t.add(shipButtons[i]);
			for(int j = 0; j<ships[i].getWidth(); j++)
			{		
				for(int k = 0; k<ships[i].getHeight(); k++)
					gbpToShip.put((GridButton) shipButtons[i].actors[j][k], ships[i]);
			}
		}
		this.add(t);
		this.row();
		finishPlacement = new TextButton("Done Placing All", skin);
		this.add(finishPlacement).pad(5);
	}
}