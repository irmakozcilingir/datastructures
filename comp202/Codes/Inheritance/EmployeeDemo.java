
public class EmployeeDemo {

	public static void main(String[] args) {

		Employee e = new Employee("Veli", new Date(1, 1, 2010));
		System.out.println(e);
		Employee e1 = new Employee();

		System.out.println(e1);

		// HourlyEmployee he = new HourlyEmployee("Ali",
		// new Date(1,1,2010), 1000, 20);

		/*
		 * HourlyEmployee can call inherited methods:
		 */
		// he.setName("Veli");

		/*
		 * The overridden version of toString will be called:
		 */
		// System.out.println(he);

		/*
		 * The following would return a compiler error, as he does not have
		 * direct access to the private members of the Employee class.
		 */
		// he.name = "Ali";

		SalariedEmployee se = new SalariedEmployee("Veli", new Date(1, 1, 2010), 100000);

		/*
		 * The following will compile correctly as he is also an employee and se
		 * inherits the method boolean equals(Employee e) from the Employee
		 * class Both conditions will return true, since all objects have the
		 * same name and hireDate This may not be the desired behavior. May be
		 * we want to announce false if the calling object and the argument
		 * object have different types. We will see how to fix this problem in
		 * coming chapters.
		 */
		/*
		 * if(se.equals(he)){ System.out.println("se and he are the same"); }
		 * 
		 * if(e.equals(he)){ System.out.println("e and he are the same"); }
		 */
	}

}
