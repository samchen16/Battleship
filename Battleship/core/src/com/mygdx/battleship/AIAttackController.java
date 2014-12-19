package com.mygdx.battleship;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

class Point {
	public int x;
	public int y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(int xx, int yy) {
		x = xx;
		y = yy;
	}
	
	public boolean equals(Object other) {
		Point p = (Point) other;
		return (x == p.x && y == p.y);
	}
}

public class AIAttackController extends Actor{
	Grid grid;
	Ship[] ships; 
	AttackingPanel attackingPanel;
	Random random;
	ArrayList<Point> attackList;
	Point maxShipSize;
	double[][] hValues;
	AttackingPanelListener apl;
	
	// Grid cell that AI will attack
	int targetIndex;
	Point target;
	
	// Grid cell containing ship that AI most recently hit
	boolean foundShip;
	Point recentHit;
	
	
	public AIAttackController (Grid g, AttackingPanel ap, Ship[] sl, AttackingPanelListener apl2) {
		grid = g;
		random = new Random();
		apl = apl2;
		foundShip = false;
		target = new Point();
		recentHit = new Point();
		attackList = new ArrayList<Point>();
		attackingPanel = ap;
		ships = sl;
		hValues = new double[grid.getNumCellsX()][grid.getNumCellsY()];
		
		for (int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				attackList.add(new Point(x,y));
			}
		}

		maxShipSize = new Point();
		maxShipSize.x = Integer.MIN_VALUE;
		maxShipSize.y = Integer.MIN_VALUE;
		for (Ship s : ships) {
			if (s.getWidth() > maxShipSize.x) {
				maxShipSize.x = s.getWidth();
			}
			if (s.getHeight() > maxShipSize.y) {
				maxShipSize.y = s.getHeight();
			}
		}
	}
	
	public void decideTarget () {
		foundShip = apl.foundShip;
		apl.recentHit = apl.recentHit;
		
		// If no ship has been found, pick a random grid cell to attack
		if (!foundShip) {
			int targetIndex = random.nextInt(attackList.size());
			target = attackList.get(targetIndex);
			return;
		}
		
		// Else target the grid cell with the highest heuristic function value
		calculateHeuristic();
		/*double maxHv = Double.NEGATIVE_INFINITY;
		for (int x = 0; x < hValues.length ; x++) {
			for (int y = 0; y < hValues[x].length; y++) {
				if (!attackList.contains(new Point(x,y))) {
					continue;
				}
				
				if (hValues[x][y] > maxHv) {
					maxHv = hValues[x][y];
					target.x = x;
					target.y = y;
				}
			}
		}*/
		double maxHv = Double.MIN_VALUE;
		
		
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if (!attackList.contains(new Point(recentHit.x + x, recentHit.y + y))) {
					continue;
				}
				if (inBounds(recentHit.x + x, recentHit.y + y, hValues) && hValues[recentHit.x + x][recentHit.y + y] > maxHv) {
					maxHv = hValues[recentHit.x + x][recentHit.y + y];
					target.x = recentHit.x + x;
					target.y = recentHit.y + y;
				}
			}
		}
								
		targetIndex = attackList.lastIndexOf(target);
		printHeuristic();

	}
	
	public void printHeuristic() {
		String s = "";
		for (int j = hValues[0].length-1; j >= 0; j--) {
			s = "";
			for (int i = 0; i < hValues.length; i ++) {
				s = s + " " + hValues[i][j];
			}
			System.out.println(s);
		}
	}
	
	public void attackTarget () {
		// Send events to click button corresponding to the grid cell the AI wants to attack
		System.out.println ("set to " + target.x + " " + target.y);
		InputEvent e = new InputEvent();
		e.setType(InputEvent.Type.touchDown);
		InputEvent e2 = new InputEvent();
		e2.setType(InputEvent.Type.touchUp);
		attackingPanel.actors[target.x][grid.getNumCellsY() -1 -target.y].fire(e);
		attackingPanel.actors[target.x][grid.getNumCellsX() -1 -target.y].fire(e2);
		
		attackList.remove(target);
		//System.out.println("AI attacks (" + target.x + " " + target.y + ")");
	}
	
	public void calculateHeuristic () {
		double A = 1.0;
		double B = 20.0;
		double C = -3.0;
		Point range = new Point (1,1);
		
		for (int i = 0; i < hValues.length; i++) {
			for (int j = 0; j < hValues[i].length; j++) {
				hValues[i][j] = 0;
			}
		}
		
		for (Ship s : ships) {
			for (int x = 0; x < hValues.length; x++) {
				for (int y = 0; y < hValues[x].length; y++) {
					
					if(canPlace(s, x, y)) {
						for (int i = 0; i < s.getWidth(); i++) {
							hValues[x+ i][y] += A;
						}
						for (int j = 0; j < s.getHeight(); j++) {
							hValues[x][y + j] += A;
						}
					}
					
					if (grid.isHit(x, y)) {
						for (int i = -1; i < 2; i++) {
							if (!inBounds(x + i, y, hValues)) {
								continue;
							}
							hValues[x + i][y] += B;
						}
						for (int j = -1; j < 2; j++) {
							if (!inBounds(x, y + j, hValues)) {
								continue;
							}
							hValues[x][y + j] += B;	
						}
					}
					else if (grid.isMiss(x, y)) {
						for (int i = -1; i < 2; i++) {
							if (!inBounds(x + i, y, hValues)) {
								continue;
							}
							hValues[x + i][y] += C;
						}
						for (int j = -1; j < 2; j++) {
							if (!inBounds(x, y + j, hValues)) {
								continue;
							}
							hValues[x][y + j] += C;	
						}
					}
				}
			}	
		}

	}
	
	private boolean inBounds (int x, int y, double[][] a) {
		return (x > -1) && (x < a.length) && (y > -1) && (y < a[0].length); 
	}
	
	private boolean canPlace (Ship s, int x, int y) {
		int maxX = x + s.getWidth() - 1;
		int maxY = y + s.getHeight() -1;
		if (!(maxX < grid.getNumCellsX() && maxY < grid.getNumCellsY())) {
			return false;
		}
		boolean canPlace = true;
		for (int i = x; i < s.getWidth(); i++) {
			for (int j = y; j < s.getHeight(); j++) {
				canPlace = canPlace && (grid.isAttackable(i, j) || grid.isHit(i, j));
			}
		}
		return canPlace;
	}
}
