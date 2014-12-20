package com.mygdx.battleship;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Comparator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.battleship.geneticalgo.GeneticAlgorithm;
import com.mygdx.battleship.geneticalgo.Individual;
import com.mygdx.battleship.geneticalgo.Population;

public class AIPlaceController extends Actor {
	Grid grid;
	PlacementPanel placementPanel;
	Random random1;
	ArrayList<Point> placeList; //list of possible locations
	int currentPopIndex = 0;
	private Ship[] shipPlacement;
	/* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

	public AIPlaceController (Grid g, Ship[] ships, PlacementPanel pp) throws IOException {
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
		Ship[] finalPlacement = null;
//		while(finalPlacement==null){
		//	Population p = new Population(placeList.size()/5, ships.length*2);
			
//		}
		Population p;
	      FileInputStream fis;
		try {
			fis = new FileInputStream("population.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			currentPopIndex = ois.readInt();
			p = (Population) ois.readObject();
		    ois.close();
		} catch (Exception e) {
			currentPopIndex = 0;
			p = new Population(placeList.size()/5, ships.length*2);
		}
     // Population 
      while(finalPlacement == null){
    	  if(currentPopIndex>=p.getSize()){
    		  currentPopIndex = 0;
  			  p = GeneticAlgorithm.evolve(p);
  			  writeOutput(currentPopIndex, p);
    	  }
    	  finalPlacement = evaluate(p.getIndividual(currentPopIndex));
    	  for(Ship s: finalPlacement){
    		  if(!g.addShip(s)){
    			  finalPlacement = null;
    			  break;
    		  }
    	  }
    	  rebuildPlaceList();
    	  currentPopIndex++;
      }
	}
	public void rebuildPlaceList(){
		ArrayList<Point> a = new ArrayList<Point>();
		for (int x = 0; x < grid.getNumCellsX(); x++) {
			for (int y = 0; y < grid.getNumCellsY(); y++) {
				a.add(new Point(x,y));
			}
		}
		placeList = a;
	}
	public void writeOutput(int i, Population p) throws IOException{
			FileOutputStream fos = new FileOutputStream("population.tmp");
	      ObjectOutputStream oos = new ObjectOutputStream(fos);

	      oos.writeInt(i);
	      oos.writeObject(p);
	      oos.close();
	}
	public Ship[] evaluate(Individual indiv){
		for(int i = 0; i<shipPlacement.length; i++){
			System.out.println("i = " + i);
			System.out.println("indiv = "+indiv.get(i));
			
			Point p = randPlace(indiv.get(i));
			System.out.println("p = " + p);
			boolean b = randOrient(indiv.get(i+1));
			if(b){
				shipPlacement[i].changeOrientation();
			}
			shipPlacement[i].setLocation(p.x, p.y);
		}
		return shipPlacement;
	}
	public Point randPlace(double prob){
		int index = (int)prob*placeList.size();
		System.out.println("placelist.size = "+placeList.size());
		System.out.println("prob*placelist.size "+prob*placeList.size());
		System.out.println("Math.round(prob*placelist.size "+(Math.round(prob*placeList.size())));
		System.out.println("index = " + index);
		return placeList.remove(index);
	}
	public boolean randOrient(double prob){
		double d = Math.random();
		if(d<prob)
			return true;
		return false;
	}
}
