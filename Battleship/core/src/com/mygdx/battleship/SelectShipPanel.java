package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class SelectShipPanel extends Table {
	private Ship selectedShip;
	public SelectShipPanel (Ship[] ships) {
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
		Table t = new Table();
		//setSkin(skin);
		//this.add(new Label("Select a ship to place", skin));
		GridButtonPanel[] shipButtons = new GridButtonPanel[ships.length];
		for(int i = 0; i<shipButtons.length; i++){
			shipButtons[i] = new GridButtonPanel(ships[i].getWidth(), ships[i].getHeight(), "=");
			shipButtons[i].makeButtonGrid(skin);
			shipButtons[i].pad(10);
			t.add(shipButtons[i]);//.expand().fill();
			//this.row();
//			shipButtons[i].debug();
		}
		this.add(t);

	}
}