package com.mygdx.battleship.geneticalgo;

public class GeneticAlgorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    /* Public methods */
    
    public static Population evolve(Population population) {
        Population nextGeneration = new Population(population.getSize(), population.getIndividualSize());

        // Keep our best individual
        if (elitism) {
            nextGeneration.setIndividual(0, population.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < population.getSize(); i++) {
            Individual indiv1 = tournamentSelection(population);
            Individual indiv2 = tournamentSelection(population);
            Individual newIndiv = crossover(indiv1.genes, indiv2.genes);
            nextGeneration.setIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < nextGeneration.getSize(); i++) {
            mutate(nextGeneration.getIndividual(i));
        }

        return nextGeneration;
    }

	public static Individual crossover(double[] a, double[] b){
		double[] cross = new double[a.length];
		for (int i = 0; i < a.length; i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                cross[i] = a[i];
            } else {
            	cross[i] = b[i];
            }
        }
		return new Individual(cross);
	}
	
	private static void mutate(Individual a) {
        for (int i = 0; i < a.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene	
            	a.set(i, Math.random());
            }
        }
    }
    // Select individuals for crossover
    private static Individual tournamentSelection(Population population) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, population.individualSize);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.getSize());
            tournament.setIndividual(i, population.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}