package com.mygdx.battleship.geneticalgo;

public class Individual{
	double[] genes;
	int fitness = Integer.MAX_VALUE;
	/* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
	public Individual(int size){
		genes = new double[size];
		for(int i = 0; i<genes.length; i++){
			genes[i] = Math.random();
		}
	}
	public Individual(double[] g){
		genes = g;
	}
	public int size(){
		return genes.length;
	}
	public void set(int index, double d){
		genes[index] = d;
	}
	public double get(int index){
		return genes[index];
	}
	public void setFitness(int f){
		fitness = f;
	}
	public int getFitness(){
		return fitness;
	}

}
