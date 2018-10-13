
public class EmployeeDemo {

	public static void printEmployee(Employee e){
		System.out.println(e);
	}
	
	public static void printHourlyEmployee(HourlyEmployee he){
		System.out.println(he);
	}
	
	public static void main(String[] args) {
		HourlyEmployee he = new HourlyEmployee("Ali", 
				new Date(1,1,2010), 1000, 20);
		SalariedEmployee se = new SalariedEmployee("Veli", 
				new Date(1,1,2010), 12000);
		/*
		 * concat method inherited from Employee uses
		 * the overridden definitions of the toString function
		 */
		System.out.println(he.concat(se));
		
		
		/*
		 * Upcasting and Downcasting
		 */
		
		/*
		 * A HourlyEmployee is an Employee. Thus the following is true.
		 * With the following statement, e still points at a 
		 * HourlyEmployee object.
		 */
		Employee e = he;
		
		
 		/*
		 * The following statement calls the toString method of 
		 * the HourlyEmployee class.
		 */
		System.out.println(e);
		
		/*
		 * The following would throw a compiler error as Employee type variable cannot
		 * directly call getPay() methods.
		 */
		//System.out.println(e.getPay());
		
		/*
		 * To access the methods of HourlyEmployee 
		 * through Employee reference, we need to downcast
		 */
 		System.out.println(((HourlyEmployee) e).getPay());
		// The following would give a run time error:
		// System.out.println(((SalariedEmployee) e).getPay());
		
		// after the new operator e becomes an Employee object
		e = new Employee (he);
		/*
		 * The following statement calls the toString method of 
		 * the Employee class.
		 */
		System.out.println(e);
		
		/*
		 * This time, both conditions will return false as the calling objects and the
		 * argument objects have different types.
		 */
		
		if(se.equals(he)){
			System.out.println("se and he are the same");
		} else{
			System.out.println("se and he are not the same");
		}
		
		if(e.equals(he)){
			System.out.println("e and he are the same");
		} else{
			System.out.println("se and he are not the same");
		}
		
		
		/*
		 * A HourlyEmployee is also an Employee:
		 */
		printEmployee(e);
		printEmployee(he);
		printHourlyEmployee(he);
		/*
		 * An Employee is not a HourlyEmployee. Thus the following 
		 * would throw a compilation error:
		 * printHourlyEmployee(e);
		 */
		
	}

}
