package com.mygdx.battleship;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Battleship extends Game {
	
	public static final int WIDTH=700,HEIGHT=700;
	
	boolean paused = false;
	SpriteBatch batch;
	GameState gamestate;
	PlayerPanel p1View;
	PlayerPanel p2View;
	SelectShipPanel shipSelect;
	Label p1Stats;
	Label p2Stats;
	Stage stage;
	Table rootTable;
	
	AIAttackController attackAI;
	
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Ship[] miltonBradley = new Ship[]{new Ship(2,1), new Ship(3,1), new Ship(3,1), new Ship(4,1), new Ship(5,1)};
		gamestate = new GameState(miltonBradley);
		rootTable = new Table();
		rootTable.setFillParent(true);
		//rootTable.debug();
		p1View = new PlayerPanel(gamestate.p1Grid, gamestate.p2Grid, false);
		p2View = new PlayerPanel(gamestate.p2Grid, gamestate.p1Grid, true);
		float w = p1View.attackPanel.actors[0][0].getWidth();
		float h = p1View.attackPanel.actors[0][0].getHeight();
		shipSelect = new SelectShipPanel(miltonBradley);
	
		rootTable.add(p1View).pad(5).expand().fill();
		rootTable.add(p2View).pad(5).expand().fill();	
		p1View.debug();
		p2View.debug();
		rootTable.row();
		rootTable.add(shipSelect).expand().fill();
		//shipSelect.debug();
		// Disables automatic rendering calls
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		
		// Create listeners
		AttackingPanelListener p1AttackingListener = new AttackingPanelListener(gamestate.p2Grid, gamestate, p1View.attackPanel);
		AttackingPanelListener p2AttackingListener = new AttackingPanelListener(gamestate.p1Grid, gamestate, p2View.attackPanel);
		SelectShipPanelListener p1SelectShipListener = new SelectShipPanelListener (gamestate.p1Grid, gamestate, shipSelect);
		SelectShipPanelListener p2SelectShipListener = new SelectShipPanelListener (gamestate.p2Grid, gamestate, shipSelect);
		PlacementPanelListener p1PlacementListener = new PlacementPanelListener(gamestate.p1Grid, gamestate, p1View.placementPanel);
		PlacementPanelListener p2PlacementListener = new PlacementPanelListener(gamestate.p2Grid, gamestate, p2View.placementPanel);
		
		// Create attacking AI and ship placement AI
		attackAI = new AIAttackController(gamestate.p1Grid, p2View.attackPanel);
		
		// Create labels to display player scores and stats
		//rootTable.row();
		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));		
		skin.add("default", new BitmapFont());
		Color c = new Color(1.0f,1.0f,1.0f,1.0f);
		LabelStyle labelStyle = new LabelStyle (new BitmapFont(), c);
		p1Stats = new Label(" ", labelStyle);
		p2Stats = new Label(" ", labelStyle);
		Table t = new Table();
		t.add(p1Stats).pad(5);//.expand().fill();
		t.add(p2Stats).pad(5);//.expand().fill();
		rootTable.add(t);
		stage.addActor(rootTable);
	}

	public void update () {
		if (gamestate.shipPlacementPhase)  {
			return;
		}
			
		
		//Update stat labels
		p1Stats.setText("Player1 Stats \n" +
						"Hits: " + gamestate.p2Grid.getNumHits() + "\n" +
						"Misses: " + gamestate.p2Grid.getNumMisses() + "\n" +
						"Ships Remaining: " + gamestate.p1Grid.getNumShips());
		p2Stats.setText("Player2 Stats \n" +
						"Hits: " + gamestate.p1Grid.getNumHits() + "\n" +
						"Misses: " + gamestate.p1Grid.getNumMisses() + "\n" +
						"Ships Remaining: " + gamestate.p2Grid.getNumShips());
		
		// Check for player wins
		if (gamestate.p1Grid.getNumShips() == 0) {
			//System.out.println("Player 2 wins!");
		}
		else if (gamestate.p2Grid.getNumShips() == 0) {
			//System.out.println("Player 1 wins!");
		}
		
		// Disable buttons if it is not that player's turn
		if (gamestate.playerTurn) {
			p2View.attackPanel.setDisabled(true);
			p1View.attackPanel.setDisabled(false);
		}
		else {
			p1View.attackPanel.setDisabled(true);
			p2View.attackPanel.setDisabled(false);
		}
		
		// If AI's turn, then let AI attack
		if (!gamestate.playerTurn) {
			attackAI.decideTarget();
			attackAI.attackTarget();
			Gdx.graphics.requestRendering();
		}
		
	}
	@Override
	public void render () {
		if (!paused) {
			update ();
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}
}
