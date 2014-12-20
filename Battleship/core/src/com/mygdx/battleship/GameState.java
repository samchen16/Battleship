package com.mygdx.battleship;

public class GameState {

	public Grid p1Grid;
	public Grid p2Grid;
	public Ship[] p1Ships;
	public Ship[] p2Ships;
	public Ship selectedShip;
	public boolean playerTurn;

	public boolean shipPlacementPhase;
	public boolean playerPlacementDone;
	public boolean AIPlacementDone;
	public boolean gameFinish;
	private int gridSizeX = 10;
	private int gridSizeY = 10;
	private int cellSize = 25;
	
	Ship[] shipList;
	
	public GameState (Ship[] p1S, Ship[] p2S) {
		p1Grid = new Grid(gridSizeX, gridSizeY, cellSize, p1S);
		p2Grid = new Grid(gridSizeX, gridSizeY, cellSize, p2S);
		p1Ships = p1S;
		p2Ships = p2S;
		playerTurn = true;
		shipPlacementPhase = true;
		gameFinish = false;
	}
	
	public GameState (int x, int y, int cs, Ship[] p1S, Ship[] p2S) {
		p1Ships = p1S;
		p2Ships = p2S;
		p1Grid = new Grid(x, y, cs, p1S);
		p2Grid = new Grid(x, y, cs, p2S);	
	}
	public Ship getSelectedShip(){
		return selectedShip;
	}
	
}
