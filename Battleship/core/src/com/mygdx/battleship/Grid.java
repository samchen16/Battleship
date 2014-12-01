package com.mygdx.battleship;

import java.awt.Point;

public class Grid {

	private float cellSize;
	private int numCellsX;
	private int numCellsY;
	private int numHits;
	private int numMisses;
	private int numShips;
	private boolean[][] hit;
	private boolean[][] attackable;
	private Ship[][] ships;
	
	public Grid (int x, int y, int cs) {
		cellSize = cs;
		numCellsX = x;
		numCellsY = y;
		numHits = 0;
		numMisses = 0;
		hit = new boolean[x][y];
		attackable = new boolean[x][y];
		ships = new Ship[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				hit[i][j] = false;
				attackable[i][j] = true;
			}
		}
	}
	
	public float getCellSize () {
		return cellSize;
	}
	
	public int getNumCellsX () {
		return numCellsX;
	}
	
	public int getNumCellsY () {
		return numCellsY;
	}
	
	public int getNumShips () {
		return numShips;
	}
	
	public int getNumHits () {
		return numHits;
	}
	
	public int getNumMisses () {
		return numMisses;
	}
	
	public boolean getHit (int x, int y) {
		return hit[x][y];
	}
	
	public boolean getMiss (int x, int y) {
		return !hit[x][y] && !attackable[x][y];
	}

	public boolean getAttackable (int x, int y) {
		return attackable[x][y];
	}
	
	public boolean hasShip (int x, int y) {
		if (ships[x][y] != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addShip (Ship s) {
		for (int x = 0; x < s.getWidth(); x++) {
			for (int y = 0; y < s.getHeight(); y++) {
				ships[s.getX() + x][s.getY() + y] = s;
			}
		}
		numShips++;
	}
	
	private void removeShip (Ship s) {
		for (int x = 0; x < s.getWidth(); x++) {
			for (int y = 0; y < s.getHeight(); y++) {
				ships[s.getX() + x][s.getY() + y] = null;
			}
		}
		numShips--;
	}
	
	private void setHit (int x, int y) {
		hit[x][y] = true;
		attackable[x][y] = false;
		numHits++;
	}
	
	private void setMiss (int x, int y) {
		hit[x][y] = false;
		attackable[x][y] = false; 
		numMisses++;
	}
	
	// Returns ship that got attacked or null if none
	public Ship attack (int x, int y) {
		if (!getAttackable(x,y)) {
			return null;
		}
		
		Ship s = null;
		if (hasShip(x,y)) {
			setHit(x,y);
			s = ships[x][y];
			removeShip(s);
		}
		else {
			setMiss(x,y);
		}
		return s;
	}
	
}
