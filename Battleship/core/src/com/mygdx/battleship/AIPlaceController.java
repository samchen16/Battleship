package com.mygdx.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Comparator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.battleship.geneticalgo.Individual;
import com.mygdx.battleship.geneticalgo.Population;

public class AIPlaceController extends Actor {
	Grid grid;
	PlacementPanel placementPanel;
	Random random1;
	ArrayList<Point> placeList; //list of possible locations
	Individual currentPlacement;
	private Ship[] shipPlacement;
	/* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

	public AIPlaceController (Grid g, Ship[] ships, PlacementPanel pp) {
		grid = g;
		random1 = new Random();
		placeList = new ArrayList<Point>();
		placementPanel = pp;
		shipPlacement = ships;
		for (int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				placeList.add(new Point(x,y));
			}
		}
		Ship[] finalPlacement;
//		while(finalPlacement==null){
			Population p = new Population(placeList.size()/5, ships.length*2);
			
//		}
	}
	public Ship[] evaluate(Individual indiv){
		for(int i = 0; i<shipPlacement.length; i++){
			Point p = randPlace(indiv.get(i));
			boolean b = randOrient(indiv.get(i+1));
			if(b){
				shipPlacement[i].changeOrientation();
			}
			shipPlacement[i].setLocation(p.x, p.y);
		}
		return shipPlacement;
	}
	public Point randPlace(double prob){
		int index = (int) Math.round(prob*placeList.size());
		return placeList.remove(index);
	}
	public boolean randOrient(double prob){
		double d = Math.random();
		if(d<prob)
			return true;
		return false;
	}
}
