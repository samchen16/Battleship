package com.mygdx.battleship.geneticalgo;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.utils.Json.Serializable;
import com.mygdx.battleship.Point;

public class Population implements java.io.Serializable{
	public Individual[] individuals;
	public int individualSize;
	//PriorityQueue
	 public Population(int size, int iS) {
        individuals = new Individual[size];
        individualSize = iS;
        // Initialise population
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual(individualSize);
        }
	}
	public int getSize(){
		return individuals.length;
	}
	public int getIndividualSize(){
		return individualSize;
	}
	public Individual getIndividual(int i){
		return individuals[i];
	}
	public void setIndividual(int i, Individual indiv){
		individuals[i] = indiv;
	}
	public void setIndividualFitness(int i, int fitness){
		individuals[i].setFitness(fitness);
	}
    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < individuals.length; i++) {
            if (fittest.getFitness() > getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
}
