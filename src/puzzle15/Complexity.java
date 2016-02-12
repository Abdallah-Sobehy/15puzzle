package puzzle15;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;



/**
 * 
 * @author renaud,abdallah,fred,mohsin
 * 
 * code to profiling the project using the reflection
 * give the view of the whole class and method implemented
 * also compute the complexity in term of time used to execute the solve<br>
 * 
 */
public class Complexity {
	
	

	/**
	 * get the list of package which begin by filter, presents in the defaults classloaders 
	 * if filter is empty,all the package will be return
	 * @param filter 
	 * @return list of full name of the package present in the classloader
	 */
	public List<String> listOfPackages(String filter){
		List<String> result = new ArrayList<String>();
		for(Package p: Package.getPackages()){
			if(p.getName().startsWith(filter)){
				result.add(p.getName());    
			}
				
		}
		return result;
	}
	
	/**
	 * get the list of class of the package names packageName which are presented in the context classloader
	 * @param packageName
	 * @return classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Class[] getClasses(String packageName) throws ClassNotFoundException, IOException{
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class> classes = new ArrayList<Class>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes.toArray(new Class[classes.size()]);
	}
	

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
	

	/**
	 * get the list of method of the class classMethod
	 * @param classMethod
	 * @return array of type Method.
	 */
	public Method[] getMethod(Class classMethod ){
		//return classMethod.getMethods();
		return classMethod.getDeclaredMethods();
	}

	
	
	/**
	 * get the list of contructor of the class classConstruct
	 * @param classConstruct
	 * @return array of type Constructor
	 */
	
	public Constructor <? extends Object> [] getConstructors(Class classConstruct){
		return classConstruct.getConstructors();
	}
	
	/**
	 * get the list of parameters of one method
	 * @param m
	 * @return array of type Parameter
	 */
	//public Parameter[]  getParameters(Method m){
		// return m.getParameters();
	//}
	/**
	 * get the list of contructor of the class classConstruct
	 * @param classConstruct
	 * @return array of type Constructor
	 */
	
	public Field[] getFields(Class classField){
		//return classField.getFields();
		return classField.getDeclaredFields();
	}
	
	/**
	 * this function lauch the method that we want to compute complexity
	 * @param methodToTimer method that we want to compute the complexity
	 * @param objet objet possessor of the method that we want to compute complexity
	 * @param arguments of the method methodTimer
	 */
	public void runFunction(Method methodToTimer,Class cl,Object arguments, int fit){
		try{
			Object objet = cl.getConstructor(int.class).newInstance(fit);
			
			if(arguments==null){
				System.out.println("objet is null");
			}
			methodToTimer.invoke(objet, arguments);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	
	}
	/**
	 * get the time spend by the system to execute the method given in parameter
	 * @param methodToTimer method that we want to compute the time complexity
	 * @param class of the method methodTimer
	 * @param arguments arguments of the method methodTimer
	 */
	public long[] runTimer(Method methodToTimer,Class cl,Object arguments,int fit){
		
		long[] differentTime = new long[3];
		long startTimeNs = System.nanoTime();
		runFunction(methodToTimer, cl, arguments,fit);
		long taskTimeNs = System.nanoTime()-startTimeNs;
		// duration mesured with wall clock
		differentTime[0] = taskTimeNs;
		
		long startTimeManCpu = getCpuTime();
		runFunction(methodToTimer, cl, arguments,fit);
		long taskTimeManCpu = getCpuTime()-startTimeManCpu;
		// duration of the running the method given by the management factory
		// this time take in account the time for system operation like I/O
		differentTime[2] = taskTimeNs =taskTimeManCpu ;
		
		long startTimeManU = getUserTime();
		runFunction(methodToTimer, cl, arguments,fit);
		long taskTimeManU = getUserTime()-startTimeManU;
		// duration of the running the method given by the management factory
		// this time doesn't take in account the time for system operation like I/O
		differentTime[1] = taskTimeNs =taskTimeManU ;
		
		return differentTime;
		}
	
	 
	/** 
	 * Get CPU time in nanoseconds.this time take in account the time for the I/O for the system and the time to compute the threat 
	 * @return the long representing the time spent in nano second
	 */
	public long getCpuTime( ) {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadCpuTime( ) : 0L;
	}
	 
	/** Get user time in nanoseconds.this is the exactly the time take by the cpu to compute the user threat
	 * it doesn't take in account the time for the system I/O   
	 * 
	 * @return the long representing the time spent in nano second
	 */
	public long getUserTime( ) {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	    return bean.isCurrentThreadCpuTimeSupported( ) ?
	        bean.getCurrentThreadUserTime( ) : 0L;
	}

	
	
}
