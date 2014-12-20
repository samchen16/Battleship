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
	Population population;
	private Ship[] shipPlacement;

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
		FileInputStream fis;
		try {
			fis = new FileInputStream("population.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			currentPopIndex = ois.readInt()+1;
			population = (Population) ois.readObject();
		    ois.close();
		} catch (Exception e) {
			currentPopIndex = 0;
			population = new Population(placeList.size()/5, ships.length*2);
		}
     // Population 
      while(finalPlacement == null){
    	  if(currentPopIndex>=population.getSize()){
    		  currentPopIndex = 0;
  			  population = GeneticAlgorithm.evolve(population);
  			  writeOutput(currentPopIndex, population);
    	  }
    	  finalPlacement = evaluate(population.getIndividual(currentPopIndex));
    	  for(int i = 0; i<finalPlacement.length; i++){
    		  if(!grid.addShip(finalPlacement[i])){ //if ship placement is invalid
    			  for(int j = 0; j<i; j++){
    				  grid.removeShip(finalPlacement[j]); //remove all previous add attempts
    			  }
    			  finalPlacement = null;
    			  break;
    		  }
    	  }
    	 // rebuildPlaceList();
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
			//System.out.println("i = " + i);
			//System.out.println("indiv = "+indiv.get(i));
			
			Point p = randPlace(indiv.get(i));
			//System.out.println("p = " + p);
			boolean b = randOrient(indiv.get(i+1));
			if(b){
				shipPlacement[i].changeOrientation();
			}
			shipPlacement[i].setLocation(p.x, p.y);
		}
		rebuildPlaceList();
		return shipPlacement;
	}
	public Point randPlace(double prob){
		int index = (int)Math.round(prob*placeList.size());
		//System.out.println("index = "+index);
		/*System.out.println("placelist.size = "+placeList.size());
		System.out.println("prob*placelist.size "+prob*placeList.size());
		System.out.println("Math.round(prob*placelist.size "+(Math.round(prob*placeList.size())));
		System.out.println("index = " + index);*/
		return placeList.remove(index);
	}
	public boolean randOrient(double prob){
		double d = Math.random();
		if(d<prob)
			return true;
		return false;
	}
	public void setCurrentPlacementFitness(int fitness){
		population.setIndividualFitness(currentPopIndex, fitness);
	}
}
