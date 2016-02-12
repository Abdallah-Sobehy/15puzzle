package puzzle15;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.lang.reflect.Method;

public class TestComplexity {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Stub de la méthode généré automatiquement
		
		// this is for invoke a method solve on one shuffle board and compute the time take by the method to solve it
		// the time is given in nano second.
		Complexity comp = new Complexity();
		
		Class cl = Class.forName("puzzle15.Solver2");
		if(cl==null){
			System.out.println("class not found");
		}
		Method[] methods = comp.getMethod(cl);
		Method met=null;
		for(int j=0;j<methods.length;j++){
			
			if(methods[j].getName().contains("solve")){
				met = methods[j];
			}
			
		}
		Board board = new Board();
	    // from this tes we took a random shuffle of 47 move with a seed 5 
		board.shuffle(47, 5);
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("!                                                     !");
		System.out.println("!  Time performance of the solver method depending to the fitness function");
		System.out.println("!                                                     !");
		System.out.println("------------------------------------------------------");
		
		long[] times1  = comp.runTimer(met,cl, board,1);
		long[] times2  = comp.runTimer(met,cl, board,1);
		long[] times3  = comp.runTimer(met,cl, board,1);
		
		System.out.println("For fitness function 1");		
		System.out.println("------------------------------------------------------");
		
		
		// convertion in milli second. the runTimer give the time in nano second for a better precision
		// we convert it in milli to facilitate the reading
		times1[0] = NANOSECONDS.toMillis(times1[0]);
		times1[1] = NANOSECONDS.toMillis(times1[1]);
		times1[2] = NANOSECONDS.toMillis(times1[2]);
		System.out.println("time elapsed by task,mesured with wall clock nano in millisecond: "+times1[0]);
	    System.out.println("time elapsed by task,mesured with managementFactory user in millisecond: "+times1[1]);
		System.out.println("time elapsed by task,mesured with managementFactory cpu in millisecond: "+times1[2]);
		System.out.println("------------------------------------------------------");
		
		System.out.println("For fitness function 2");		
		System.out.println("------------------------------------------------------");
		
		// convertion in milli second. the runTimer give the time in nano second for a better precision
		// we convert it in milli to facilitate the reading
		times2[0] = NANOSECONDS.toMillis(times2[0]);
		times2[1] = NANOSECONDS.toMillis(times2[1]);
		times2[2] = NANOSECONDS.toMillis(times2[2]);
		System.out.println("time elapsed by task,mesured with wall clock nano in millisecond: "+times2[0]);
	    System.out.println("time elapsed by task,mesured with managementFactory user in millisecond: "+times2[1]);
		System.out.println("time elapsed by task,mesured with managementFactory cpu in millisecond: "+times2[2]);
		System.out.println("------------------------------------------------------");
		
		System.out.println("For fitness function 3");		
		System.out.println("------------------------------------------------------");
		
		// convertion in milli second. the runTimer give the time in nano second for a better precision
		// we convert it in milli to facilitate the reading
		times3[0] = NANOSECONDS.toMillis(times3[0]);
		times3[1] = NANOSECONDS.toMillis(times3[1]);
		times3[2] = NANOSECONDS.toMillis(times3[2]);
		System.out.println("time elapsed by task,mesured with wall clock nano in millisecond: "+times3[0]);
	    System.out.println("time elapsed by task,mesured with managementFactory user in millisecond: "+times3[1]);
		System.out.println("time elapsed by task,mesured with managementFactory cpu in millisecond: "+times3[2]);
		System.out.println("------------------------------------------------------");


	}

}
