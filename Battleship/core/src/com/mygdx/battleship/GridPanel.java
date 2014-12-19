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

public abstract class GridPanel extends Table{

	protected Grid grid;
//	protected Vector2 position;
	private Texture gridTex;
	private Actor[][] actors;
	protected Skin skin;
	public GridPanel (Grid g, Vector2 pos){
		//int actorWidth = Gdx.graphics.getWidth() / rowActors;
		//int actorHeight = Gdx.graphics.getHeight() /  columnActors;
		grid = g;
//		position = pos;
		actors = new Actor[g.getNumCellsX()][g.getNumCellsY()];
		this.debug();
		//setFillParent(true);
	}
	
	protected void makeButtonGrid(Skin s, ChangeListener cl){
		for (int i = 0; i < actors[0].length; i++){
			  for (int j = 0; j < actors.length; j++){
				  actors[i][j] = new TextButton(" ", s);
				  this.add(actors[i][j]).expand().fill();//.width(grid.getCellSize()).height(grid.getCellSize());
//				  this.add(actors[i][j]).width(this.getMaxWidth()/actors[0].length).height(this.getMaxHeight()/actors.length);
					System.out.println("actor["+i+"]["+j+"]= "+actors[i][j].getWidth() +" , "+ +actors[i][j].getHeight());
					System.out.println("actor["+i+"]["+j+"] at "+actors[i][j].getX() +" , "+ +actors[i][j].getY());
			  }
			   this.row();
		}
	}/*
	public void resize(int width, int height) {
		System.out.println("gridpanel resize "+width +" , "+ height);
		for (int i = 0; i < actors[0].length; i++){
			  for (int j = 0; j < actors.length; j++){
				  if(actors[i][j]!=null){
					  actors[i][j].setSize(width/grid.getNumCellsX(), height/grid.getNumCellsY());
						System.out.println("actor["+i+"]["+j+"]= "+actors[i][j].getWidth() +" , "+ +actors[i][j].getHeight());
						System.out.println("actor["+i+"]["+j+"] at "+actors[i][j].getX() +" , "+ +actors[i][j].getY());
				  }
			   }
		}
	}
	public void draw (SpriteBatch batch) {
		batch.draw(gridTex, position.x, position.y);
	}
	
	public void dispose() {
		gridTex.dispose();
	}
	*/
}
