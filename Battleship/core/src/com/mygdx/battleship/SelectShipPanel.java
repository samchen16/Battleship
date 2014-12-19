package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class SelectShipPanel extends Table {
	public SelectShipPanel (Ship[] ships) {
		GridButtonPanel[] shipButtons = new GridButtonPanel[ships.length];
		for(int i = 0; i<shipButtons.length; i++){
			shipButtons[i] = new GridButtonPanel(ships[i].getWidth(), ships[i].getHeight());
			this.add(shipButtons[i]).expand().fill();
		}
	}
}