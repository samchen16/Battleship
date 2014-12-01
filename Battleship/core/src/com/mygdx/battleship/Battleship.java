package com.mygdx.battleship;

import java.awt.Point;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battleship extends ApplicationAdapter {
	SpriteBatch batch;
	GameState gamestate;
	AttackingPanel p1AttackingPanel;
	AttackingPanel p2AttackingPanel;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gamestate = new GameState();
		p1AttackingPanel = new AttackingPanel (gamestate.p1Grid);
		p2AttackingPanel = new AttackingPanel (gamestate.p2Grid);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		p1AttackingPanel.draw(batch);
		batch.end();
	}
	
	public void dispose () {
		p1AttackingPanel.dispose();
		p2AttackingPanel.dispose();
	}
}
