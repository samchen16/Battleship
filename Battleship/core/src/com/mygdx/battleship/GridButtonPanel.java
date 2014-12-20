package com.mygdx.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

class GridButton extends TextButton {
	// indices of button in grid
	public int x;
	public int y;
	
	public GridButton(String text, Skin skin, int i, int j) {
		super(text, skin);
		x = i;
		y = j;
	}
}

public class GridButtonPanel extends Table{

	protected int numCellsX;
	protected int numCellsY;
	private Texture gridTex;
	public Actor[][] actors;
	protected Skin skin;
	public String defaultText;
	public GridButtonPanel (int w, int h, String s){
		numCellsX = w;
		numCellsY = h;
		defaultText = s;
		actors = new Actor[numCellsX][numCellsY];	
		this.debug();
	}
	
	protected void makeButtonGrid(Skin s){
		for (int j = 0; j < numCellsY; j++){
			for (int i = 0; i < numCellsX; i++){
				  actors[i][j] = new GridButton(defaultText, s, i, numCellsY -1 -j);
				  this.add(actors[i][j]).expand().fill();
			  }
			  this.row();
		}
	}	
	public void setDisabled(boolean b) {
		for (int i = 0; i < numCellsX; i++) {
			for (int j = 0; j < numCellsY; j++) {
				if (actors[i][j] != null) {
					GridButton gb = (GridButton) actors[i][j];
					gb.setDisabled(b);
				}
			}
		}
	}
}
