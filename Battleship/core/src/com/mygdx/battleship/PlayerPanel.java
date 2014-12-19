package com.mygdx.battleship;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PlayerPanel extends Table {
	AttackingPanel attackPanel;
	PlacementPanel placementPanel;
	public PlayerPanel(Grid myPlayer, Grid opponent, boolean disableAll){
		attackPanel = new AttackingPanel(opponent);
		placementPanel = new PlacementPanel(myPlayer);
		this.add(attackPanel).pad(5).expand().fill();
		this.row();
		this.add(placementPanel).pad(5).expand().fill();
		//placementPanel.debug();
	}
/*	public void resize(int width, int height) {
		System.out.println("playerPanel resize "+width +" , "+ height);
		attackPanel.resize(width, height/2);
		placementPanel.resize(width, height/2);
	}*/
}
