package puzzle15;

import java.io.IOException;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import puzzle15.Complexity;

public class TestProfiling {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Stub de la méthode généré automatiquement

		Complexity comp = new Complexity();
		List<String> listPackage = comp.listOfPackages("");
		System.out.println(listPackage.size());
		
		
		System.out.println("------------------------------------------------------");
		System.out.println("!                                                     !");
		System.out.println("   Profiling the project  ");
		System.out.println("!                                                     !");
		System.out.println("------------------------------------------------------");
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("!                                                     !");
		System.out.println("   List of packages  ");
		System.out.println("!                                                     !");
		System.out.println("------------------------------------------------------");
		
		for(int i=0;i< listPackage.size();i++){
			
			System.out.println(listPackage.get(i));
		}
       
		List<String> listOurPackage = comp.listOfPackages("puzzle15");
		listOurPackage.add("tests");
		System.out.println("number of our package "+listOurPackage.size());
		for(int i=0;i< listOurPackage.size();i++){
			System.out.println();
			System.out.println("------------------------------------------------------");
			System.out.println("!                                                     !");
			System.out.println("!   List of classes of our package: "+listOurPackage.get(i));
			System.out.println("!                                                     !");
			System.out.println("------------------------------------------------------");
			System.out.println();
			
			Class[] classes = comp.getClasses(listOurPackage.get(i));
			
			for(int k=0;k<classes.length;k++){
				System.out.println("------------------------------------------------------");
				System.out.println("!"+classes[k].getName());
				System.out.println("!                                                     !");
				System.out.println("------------------------------------------------------");
				
				Field[] fields = comp.getFields(classes[k]);
				System.out.println("! Attributes");
				System.out.println("size of attribute "+fields.length);
				for(int m=0;m<fields.length;m++){
					System.out.println("! "+fields[m].getType().getName()+": "+fields[m].getName());
				}
				Method[] methods = comp.getMethod(classes[k]);
				System.out.println("! Methods");
				for(int l=0;l<methods.length;l++){
					//Parameter[] params = comp.getParameters(methods[i]);
					System.out.print("! "+methods[l].getReturnType().getName()+": "+methods[l].getName()+"(");
					//for(int m=0;m<params.length;m++){
						//System.out.print(" "+params[m].getType().getName()+" "+params[m].getName()+"  ");
					//}
					System.out.println(")");
				}
			}
			
			
		}
		
		
		

	}

}
