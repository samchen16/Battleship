package com.mygdx.battleship;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Battleship extends Game {
	
	public static final int WIDTH=480,HEIGHT=800;
	
	SpriteBatch batch;
	GameState gamestate;
	AttackingPanel p1AttackingPanel;
	AttackingPanel p2AttackingPanel;
	
	Stage stage;
	Table table;
	
	@Override
	public void create () {
		Skin skin = new Skin();
		
		batch = new SpriteBatch();
		gamestate = new GameState();
		p1AttackingPanel = new AttackingPanel (gamestate.p1Grid);
		p2AttackingPanel = new AttackingPanel (gamestate.p2Grid);
		stage = new Stage();
		table = new Table();
		
		Button button = new Button();
		button.setWidth(500);
		button.setHeight(500);
		ButtonStyle style = new ButtonStyle();
		button.setStyle(style);
		table.add(button);

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
        stage.draw();
		
		batch.begin();
		//p1AttackingPanel.draw(batch);
		batch.end();
	}
	
	public void dispose () {
		p1AttackingPanel.dispose();
		p2AttackingPanel.dispose();
	}
	
	
}
