package com.mygdx.battleship;

public class GameState {

	public Grid p1Grid;
	public Grid p2Grid;
	public boolean playerTurn;
	
	private int gridSizeX = 10;
	private int gridSizeY = 10;
	private int cellSize = 25;
	
	
	public GameState () {
		p1Grid = new Grid(gridSizeX, gridSizeY, cellSize);
		p2Grid = new Grid(gridSizeX, gridSizeY, cellSize);
		
		// Give players Milton Bradley version's ships
		//p1Grid.addShip(new Ship(2,1));
		//p1Grid.addShip(new Ship(3,1));
		//p1Grid.addShip(new Ship(3,1));
		//p1Grid.addShip(new Ship(4,1));
		Ship s = new Ship(5,1);
		s.setLocation(0, 0);
		p1Grid.addShip(s);
		//p2Grid.addShip(new Ship(2,1));
		//p2Grid.addShip(new Ship(3,1));
		//p2Grid.addShip(new Ship(3,1));
		//p2Grid.addShip(new Ship(4,1));
		Ship ss = new Ship(5,1);
		ss.setLocation(0, 0);
		p2Grid.addShip(ss);
	}
	
	public GameState (int x, int y, int cs) {
		p1Grid = new Grid(x, y, cs);
		p2Grid = new Grid(x, y, cs);	
	}
	
	
}
