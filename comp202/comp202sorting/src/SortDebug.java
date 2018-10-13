/**
 * The simple version of the sort test, useful for debugging your individual algorithms
 * 
 * @author baakgun
 *
 */
public class SortDebug {
  
  
  public static void main(String[] args)
  { 
    AbstractArraySort<Integer> debug;   
    //Uncomment the one you are debugging
    InsertionSort<Integer> iSortInt = new InsertionSort<Integer>(); debug = iSortInt;
    //QuickSort<Integer> qSortInt = new QuickSort<Integer>(); debug = qSortInt;
    //MergeSort<Integer> mSortInt = new MergeSort<Integer>(); debug = mSortInt;
    //HeapSort<Integer> hSortInt = new HeapSort<Integer>();   debug = hSortInt;
    
    //Number of elements to test. Increase to more than 100 to get non-zero sorting times
    int n = 10;
    
    Integer[] randIntegers = new Integer[n];
    for(int i = 0; i < n; i++)
      randIntegers[i] = SortTest.randomIntRange(0, 2*n);
    
    if(n < 101)
    {
      System.out.println("Before sorting:");
      AbstractArraySort.printArray(randIntegers);
    }
    
    SortTest.benchMark(debug, randIntegers, true);
    
    if(n < 101)
    {
      System.out.println("After sorting:");
      AbstractArraySort.printArray(randIntegers);
    }
  }
}
