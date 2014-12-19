package com.mygdx.battleship;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Battleship extends Game {
	
	public static final int WIDTH=480,HEIGHT=800;
	
	SpriteBatch batch;
	GameState gamestate;
	PlayerPanel p1View;
	PlayerPanel p2View;
	PlacementPanel p1AttackingPanel;
	PlacementPanel p2AttackingPanel;
	Table rootTable;
	SplitPane sp;
	Stage stage;
	@Override
	public void create () {
		/*OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, this.WIDTH, this.HEIGHT/2);
		camera.translate(0, 0);*/
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
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
		TextButton nameLabel = new TextButton("text", skin);
		
		//stage.getViewport().setCamera(camera);
		//batch = new SpriteBatch();
		gamestate = new GameState();
		rootTable = new Table();
		rootTable.setFillParent(true);
		rootTable.debug();
		//rootTable.add(nameLabel).expand();
		//rootTable.row();
		//rootTable.add(new TextButton("text2", skin)).expand();
		p1View = new PlayerPanel(gamestate.p1Grid, gamestate.p2Grid, false);
		p2View = new PlayerPanel(gamestate.p2Grid, gamestate.p1Grid, true);
		rootTable.add(p2View).pad(5).expand().fill();		
		rootTable.add(p1View).pad(5).expand().fill();
		p1View.debug();
		p2View.debug();
		
		// Disables automatic rendering calls
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		
		// Create listeners
		AttackingPanelListener p1AttackingListener = new AttackingPanelListener(gamestate.p1Grid, gamestate, p1View.attackPanel);
		AttackingPanelListener p2AttackingListener = new AttackingPanelListener(gamestate.p2Grid, gamestate, p2View.attackPanel);
		PlacementPanelListener p1PlacementListener = new PlacementPanelListener(gamestate.p1Grid, gamestate, p1View.placementPanel);
		PlacementPanelListener p2PlacementListener = new PlacementPanelListener(gamestate.p2Grid, gamestate, p2View.placementPanel);
		
		
		
		
//		p1AttackingPanel = new PlacementPanel (gamestate.p1Grid, playerView.getWidth(), playerView.getHeight());
		//rootTable.add(new PlacementPanel(gamestate.p2Grid));//p1View);
		//p2AttackingPanel = new PlacementPanel (gamestate.p2Grid);
		stage.addActor(rootTable);
		
		//sp = new SplitPane ((Actor)p1AttackingPanel, (Actor)p2AttackingPanel, true, new Skin());

	}/*
	public void resize (int width, int height) {
		System.out.println("battleship resize "+width +" , "+ height);
	    stage.getViewport().update(width, height, true);
	    this.p1View.resize(width/2, height);
	    this.p2View.resize(width/2, height);
	}*/
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    //rootTable.debug();
		//p1View.debug();
		//p1View.attackPanel.debug();
		//p1View.placementPanel.debug();
/*		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
        stage.draw();
		
		batch.begin();

		p1AttackingPanel.draw(batch);
		//sp.draw(batch, 1);
		batch.end();*/
		//super.render();
		stage.act();
		stage.draw();
//		rootTable.drawDebug(stage);
	}
/*	public void dispose () {
		p1AttackingPanel.dispose();
		//p2AttackingPanel.dispose();
	}*/
}
