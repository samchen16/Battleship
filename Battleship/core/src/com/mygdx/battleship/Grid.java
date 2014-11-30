package com.mygdx.battleship;

import java.awt.Point;

public class Grid {

	private Point numCells;
	private int numHits;
	private int numMisses;
	private boolean[][] hit;
	private boolean[][] attackable;
	private Ship[][] ships;
	
	public Grid (int x, int y) {
		numCells = new Point(x,y);
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
	
	public Point getNumCells () {
		return numCells;
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
	
	public void addShip (Ship s) {
		for (int x = 0; x < s.getWidth(); x++) {
			for (int y = 0; y < s.getHeight(); y++) {
				ships[s.getX() + x][s.getY() + y] = s;
			}
		}
	}
	
	public void removeShip (Ship s) {
		for (int x = 0; x < s.getWidth(); x++) {
			for (int y = 0; y < s.getHeight(); y++) {
				ships[s.getX() + x][s.getY() + y] = null;
			}
		}
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
		
		Ship s = ships[x][y];
		if (s != null) {
			setHit(x,y);
			removeShip(s);
		}
		else {
			setMiss(x,y);
		}
		return s;
	}
	
}
