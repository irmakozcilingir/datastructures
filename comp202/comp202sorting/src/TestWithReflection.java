import java.lang.reflect.InvocationTargetException;

public class TestWithReflection {
  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
      NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    AbstractArraySort<Integer> debug;

    // Number of elements to test. Increase to more than 100 to get non-zero
    // sorting times
    int n = 1000;

    Integer[] randIntegers = new Integer[n];
    for (int i = 0; i < n; i++)
      randIntegers[i] = SortTest.randomIntRange(0, 2 * n);

    if (n < 101) {
      System.out.println("Before sorting:");
      AbstractArraySort.printArray(randIntegers);
    }

    //Change the name your class!
    String cname = "Sorting54007";

    Class<?> cls = Class.forName(cname);
    Object clsInstance = (Object) cls.newInstance();
    if (cls != AbstractArraySort.class && AbstractArraySort.class.isAssignableFrom(cls)) {
      debug = (AbstractArraySort) clsInstance;
      SortTest.benchMark(debug, randIntegers, true);
    }

    // SortTest.benchMark(debug, randIntegers, true);

    if (n < 101) {
      System.out.println("After sorting:");
      AbstractArraySort.printArray(randIntegers);
    }
  }
}
