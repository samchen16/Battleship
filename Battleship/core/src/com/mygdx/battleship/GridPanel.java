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

public abstract class GridPanel extends Table{

	protected Grid grid;
	protected Vector2 position;
	private Texture gridTex;
	private Actor[][] actors;
	protected Skin skin;
	public GridPanel (Grid g, Vector2 pos){
		//int actorWidth = Gdx.graphics.getWidth() / rowActors;
		//int actorHeight = Gdx.graphics.getHeight() /  columnActors;
		grid = g;
		position = pos;
		actors = new Actor[g.getNumCellsX()][g.getNumCellsY()];		
	}
	
	protected void makeButtonGrid(Skin s){
		for (int i = 0; i < actors[0].length; i++){
			  for (int j = 0; j < actors.length; j++){
				  actors[i][j] = new TextButton(" ", s);
				  this.add(actors[i][j]).width(grid.getCellSize()).height(grid.getCellSize());
			   }
			   this.row();
		}
	}
	/*
	public void draw (SpriteBatch batch) {
		//batch.draw(gridTex, position.x, position.y);
	}
	
	public void dispose() {
		gridTex.dispose();
	}
	*/
}
