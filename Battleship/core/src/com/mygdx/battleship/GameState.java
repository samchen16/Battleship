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
	private int gridSizeX = 10;
	private int gridSizeY = 10;
	private int cellSize = 25;
	
	Ship[] shipList;
	
	public GameState (Ship[] s) {
		p1Grid = new Grid(gridSizeX, gridSizeY, cellSize, s);
		p2Grid = new Grid(gridSizeX, gridSizeY, cellSize, s.clone());
		playerTurn = true;
		shipPlacementPhase = false;
		Ship[] shipList = s;
		
		// Give players Milton Bradley version's ships
		//p1Grid.addShip(new Ship(2,1));
		//p1Grid.addShip(new Ship(3,1));
		//p1Grid.addShip(new Ship(3,1));
		//p1Grid.addShip(new Ship(4,1));
		Ship s1 = new Ship(5,1);
		s1.setLocation(0, 0);
		p1Grid.addShip(s1);
		s1 = new Ship(2,1);
		s1.setLocation(7, 0);
		p1Grid.addShip(s1);
		s1 = new Ship(3,1);
		s1.setLocation(4, 5);
		p1Grid.addShip(s1);
		s1 = new Ship(3,1);
		s1.setLocation(1, 8);
		p1Grid.addShip(s1);
		s1 = new Ship(4,1);
		s1.setLocation(6, 9);
		p1Grid.addShip(s1);
		
		//p2Grid.addShip(new Ship(2,1));
		//p2Grid.addShip(new Ship(3,1));
		//p2Grid.addShip(new Ship(3,1));
		//p2Grid.addShip(new Ship(4,1));
		s1 = new Ship(5,1);
		s1.setLocation(0, 0);
		p2Grid.addShip(s1);
		s1 = new Ship(2,1);
		s1.setLocation(7, 0);
		p2Grid.addShip(s1);
		s1 = new Ship(3,1);
		s1.setLocation(4, 5);
		p2Grid.addShip(s1);
		s1 = new Ship(3,1);
		s1.setLocation(1, 8);
		p2Grid.addShip(s1);
		s1 = new Ship(4,1);
		s1.setLocation(6, 9);
		p2Grid.addShip(s1);
	}
	
	public GameState (int x, int y, int cs, Ship[] sL) {
		p1Grid = new Grid(x, y, cs, sL);
		p2Grid = new Grid(x, y, cs, sL);	
	}
	public Ship getSelectedShip(){
		return selectedShip;
	}
	
}
