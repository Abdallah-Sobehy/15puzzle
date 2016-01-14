package tests;

import puzzle15.Board;
import puzzle15.Fitness;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Abdalla Sobehy, Fred Aklamanu, Mohsin Kazmi, Renaud
 * @version 1
 */

public class JUFitness {
	
	Board finalState = new Board();
	Board randomState = new Board();
	Fitness myFitness = new Fitness();
	float expectedFitnessRandom;
	float actualFitnessRandom;
	float expectedFitnessFinal;
	float actualFitnessFinal;
	
	@Before
	public void setUp() throws Exception {
		
		randomState.shuffle(4, 2);
		
		/** 
		 * State of the randomState of the Board after shuffling
		 * |01  | 02  | 03  | 04 |
		 * |05  | 06  | 07  | 08 |
		 * |09  | 10  | 12  | 15 |
		 * |13  | 14  | 11  | -1 |
		 * 
		 */
		
		expectedFitnessRandom = (float) 0.12705;
		expectedFitnessFinal = ( float ) 0.0;
		
		actualFitnessFinal = myFitness.fitness_function_1( finalState );
		actualFitnessRandom = myFitness.fitness_function_1( randomState );
		
	}


	@Test
	public void getFitness() {
		
		assertEquals(expectedFitnessFinal, actualFitnessFinal, 0.0 );
		assertEquals(expectedFitnessRandom, actualFitnessRandom, 0.001 );
		
	}
	
	@After
	public void tearDown() throws Exception {
		finalState = new Board();
		randomState = new Board();
		 myFitness = new Fitness();
	}

}
