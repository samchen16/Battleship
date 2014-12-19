package com.mygdx.battleship;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
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
	AttackingPanel attackingPanel;
	Random random;
	ArrayList<Point> attackList;
	
	// Grid cell that AI will attack
	int targetIndex;
	Point target;
	
	// Grid cell containing ship that AI most recently hit
	boolean foundShip;
	Point recentHit;
	
	
	public AIAttackController (Grid g, AttackingPanel ap) {
		grid = g;
		random = new Random();
		foundShip = false;
		target = new Point();
		recentHit = new Point();
		attackList = new ArrayList<Point>();
		attackingPanel = ap;
		for (int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				attackList.add(new Point(x,y));
			}
		}
	}
	
	
	public void decideTarget () {
		// If no ship has been found, pick a random grid cell to attack
		if (!foundShip) {
			int targetIndex = random.nextInt(attackList.size());
			target = attackList.get(targetIndex);
			return;
		}
		
		// Else target the grid cell with the highest heuristic function value
		double max = Double.NEGATIVE_INFINITY;
		for (int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				double current = heuristic(x, y);
				if (current > max) {
					max = current;
					target.x = x;
					target.y = y;
				}
			}
		}
		targetIndex = attackList.lastIndexOf(target);

	}
	
	public void attackTarget () {
		// Send events to click button corresponding to the grid cell the AI wants to attack
		InputEvent e = new InputEvent();
		e.setType(InputEvent.Type.touchDown);
		InputEvent e2 = new InputEvent();
		e2.setType(InputEvent.Type.touchUp);
		attackingPanel.actors[target.x][target.y].fire(e);
		attackingPanel.actors[target.x][target.y].fire(e2);
		
		attackList.remove(targetIndex);
		System.out.println("AI attacks (" + target.x + " " + target.y + ")");
	}
	
	public double heuristic (int x, int y) {
		return 1.0;
	}
	
}
