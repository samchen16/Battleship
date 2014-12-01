package com.mygdx.battleship;

import java.awt.Point;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;

public class Battleship extends ApplicationAdapter {
	SpriteBatch batch;
	GameState gamestate;
	AttackingPanel p1AttackingPanel;
	AttackingPanel p2AttackingPanel;
	SplitPane sp;
	Stage stage;
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		batch = new SpriteBatch();
		gamestate = new GameState();
		p1AttackingPanel = new AttackingPanel (gamestate.p1Grid);
		p2AttackingPanel = new AttackingPanel (gamestate.p2Grid);
		stage.addActor(p1AttackingPanel);
		//sp = new SplitPane ((Actor)p1AttackingPanel, (Actor)p2AttackingPanel, true, new Skin());
	}
	
	@Override
	public void render () {
/*		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		p1AttackingPanel.draw(batch);
		//sp.draw(batch, 1);
		batch.end();*/
		super.render();
		stage.draw();
	}
	/*
	public void dispose () {
		p1AttackingPanel.dispose();
		p2AttackingPanel.dispose();
	}
	*/
}
