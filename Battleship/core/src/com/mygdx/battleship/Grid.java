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
	private Ship[] shipList;
	public Grid (int x, int y, int cs, Ship[] sL) {
		cellSize = cs;
		numCellsX = x;
		numCellsY = y;
		shipList = sL;
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
	public Ship[] getShipList(){
		return shipList;
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
	
	public boolean isHit (int x, int y) {
		return hit[x][y];
	}
	
	public boolean isMiss (int x, int y) {
		return !hit[x][y] && !attackable[x][y];
	}

	public boolean isAttackable (int x, int y) {
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
	
	public boolean canPlaceShip (Ship s) {
		int maxX = s.getX() + s.getWidth() - 1;
		int maxY = s.getY() + s.getHeight() -1;
		if (!(maxX < numCellsX && maxY < numCellsY)) {
			return false;
		}
		
		boolean canPlace = true;
		for (int x = 0; x < s.getWidth(); x++) {
			for (int y = 0; y < s.getHeight(); y++) {
				canPlace = (canPlace && ships[s.getX() + x][s.getY() + y] == null);
			}
		}
		return canPlace;
	}
	
	// Adds ship at position (i,j) starting from bottom left
	public boolean addShip (Ship s) {
		boolean canPlace = canPlaceShip(s);
		if (canPlace) {
			for (int x = 0; x < s.getWidth(); x++) {
				for (int y = 0; y < s.getHeight(); y++) {
					ships[s.getX() + x][s.getY() + y] = s;
				}
			}
			numShips++;
		}
		return canPlace;
	}
	
	 public void removeShip (Ship s) {
		 for (int x = 0; x < s.getWidth(); x++) {
			 for (int y = 0; y < s.getHeight(); y++) {
				 /*System.out.println("getx = "+s.getX()+" gety "+s.getY());
				 System.out.println("getx +x = "+(s.getX()+x)+" gety +y="+(s.getY()+y));*/
				 ships[s.getX() + x][s.getY() + y] = null;
             }
         }
         numShips--;
         s.setLocation(-1, -1);
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
		if (!isAttackable(x,y)) {
			return null;
		}
		Ship s = null;
		if (hasShip(x,y)) {
			setHit(x,y);
			s = ships[x][y];
			ships[x][y] = null;
			s.health--;
			if (s.health == 0) {
				// Announce a ship has been destroyed
				numShips--;
			}
		}
		else {
			setMiss(x,y);
		}
		return s;
	}
	
}
