import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A comprehensive benchmarking/sorting program.
 * 
 * @author baakgun
 *
 */

public class SortTest {
  
  static Random r = new Random();

  public static int randomIntRange(int min, int max)
  {
    return r.nextInt((max - min) + 1) + min;
  }
  
  public static Integer[] randomIntRange(int min, int max, int n)
  {
    Integer[] randIntegers = new Integer[n];
    for(int i = 0; i < n; i++)
      randIntegers[i] = randomIntRange(min, max);
    return randIntegers;
  }
  
  public static Integer[] randomSorted(int min, int max, int n, boolean ascending)
  {
    int step = (max-min)/n;
    Integer[] randIntegers = new Integer[n];
    if(ascending)
    {
      randIntegers[0] = min + randomIntRange(0,step); 
      for(int i = 1; i < n; i++)
        randIntegers[i] = randIntegers[i-1]+randomIntRange(0, step);
    }
    else
    {
      randIntegers[0] = max - randomIntRange(0,step); 
      for(int i = 1; i < n; i++)
        randIntegers[i] = randIntegers[i-1]-randomIntRange(0, step);  
    }
    return randIntegers;
  }
  
  public static Integer[] chunksSorted(int min, int max, int n, int numUnits)//, boolean ascending)
  {
    
    Integer[] randIntegers = new Integer[n];
    int chunkSize = n/numUnits;
    int range = (max - min)/numUnits;
    Integer[] tmp;

    for(int i = 0; i < numUnits; i++)
    {
      tmp = randomIntRange(min + i*range, min+(i+1)*range-1, chunkSize);
      System.arraycopy(tmp, 0, randIntegers, i*chunkSize, chunkSize);
    }
    
    int remaining = n-numUnits*chunkSize;

    if(remaining > 0)
    {
      tmp = randomIntRange(min + numUnits*range, max, remaining);
      System.arraycopy(tmp, 0, randIntegers, numUnits*chunkSize, remaining);
    }
      
    return randIntegers;
  }
  
  public static Integer[] staggered(int min, int max, int n, int numUnits)//, boolean ascending)
  {
    
    Integer[] randIntegers = new Integer[n];
    int staggerSize = n/numUnits;
    int range = (max - min)/numUnits;
    Integer[] tmp;

    for(int i = 0; i < numUnits; i++)
    {
      tmp = randomIntRange(min + i*range, min+(i+1)*range-1, staggerSize);
      System.arraycopy(tmp, 0, randIntegers, i*staggerSize, staggerSize);
    }
    
    int remaining = n-numUnits*staggerSize;

    if(remaining > 0)
    {
      tmp = randomSorted(min, max, staggerSize, true);//randomIntRange(min + numUnits*range, max, remaining);
      System.arraycopy(tmp, 0, randIntegers, numUnits*staggerSize, remaining);
    }
      
    return randIntegers;
  }
  
  /**
   * Benchmarking function
   * 
   * @param sortAlg: sorting algorithm to benchmark
   * @param array:   data to sort
   * @param output:  whether to print output or not
   * @return
   */
  public static long benchMark(AbstractArraySort<Integer> sortAlg, Integer[] array, boolean output)
  {
    long timeA, timeB;
    if(output)
      System.out.println(sortAlg);
    timeA = System.currentTimeMillis();
    sortAlg.sort(array);
    timeB = System.currentTimeMillis();
    boolean sorted = false;
    if(output)
    {
      sorted = AbstractArraySort.isSorted(array,0,array.length-1);
      System.out.println("Issorted: " + Boolean.toString(sorted));
      System.out.println("Sorting took: " + (timeB - timeA) + " miliseconds");
      System.out.println();
    }
    assert sorted;
    return timeB - timeA;
  }
  
  public static void main(String[] args)
  {
    /* WARNING: Do not run anything else on your machine while you are benchmarking! */
    
    benchmarkLog log = new benchmarkLog();
    
    /* benchmarking parameters */
    
    // Number of elements to test. Once you feel comfortable, uncomment the remaining 2
    int dataLengths[] = {100,1000,10000};//,100000};// 1000000, 10000000};
    
    // Type of data to test, look at the pdf for descriptions
    String runTypes[] = {"uniform", "randomizedDuplicates", "constant", "sorted" , "sortedReverse", "staggered", "sortedChunks"};
    
    // Number of trials per test. The higher this value, the better the results assuming consistent OS scheduling
    int runsPerTrial = 10; //100

    /* Initializing the algorithms and filling in the "algorithm array" */
    QuickSort<Integer> qSortInt = new QuickSort<Integer>();
    InsertionSort<Integer> iSortInt = new InsertionSort<Integer>();
    MergeSort<Integer> mSortInt = new MergeSort<Integer>();
    HeapSort<Integer> hSortInt = new HeapSort<Integer>();
    JavaArraySort<Integer> jSortInt = new JavaArraySort<Integer>();
    
    ArrayList<AbstractArraySort<Integer>> algs = new ArrayList<AbstractArraySort<Integer>>();
    
    algs.add(mSortInt);
    algs.add(hSortInt);   
    algs.add(qSortInt);
    algs.add(iSortInt);
    algs.add(jSortInt);

    
    int c = 1000;
    Integer[] orig = SortTest.randomIntRange(0, c, c);
    Integer[] randIntegers = new Integer[c];

    /* Running all of them once to get better results. Run once and ignore results */
    for(AbstractArraySort<Integer> alg : algs)  
    {
      System.arraycopy(orig, 0, randIntegers, 0, c);
      benchMark(alg, randIntegers, false);
    }
    
    /* Let the benchmarking begin */
    
    // 4 nested loops follow? 1) data length, 2) data type, 3) iteration, 4) algorithm. 
    for(int n : dataLengths)
    {
      randIntegers = new Integer[n]; 
      for(String type : runTypes)
      {
        for(int i = 0; i < runsPerTrial; i++)
        {
          // re-sample the data for each iteration
          switch (type)
          {
            case "uniform":
              orig = SortTest.randomIntRange(0, 2*n, n);
              break;
            case "constant":
              Arrays.fill(orig, randomIntRange(0,n));
              break;
            case "sorted":
              orig = SortTest.randomSorted(0, n, n, true);
              break;
            case "sortedReverse":
              orig = SortTest.randomSorted(0, n, n, false);
              break;
            case "randomizedDuplicates":
              orig = SortTest.randomIntRange(0, 63, n);
              break;
            case "staggered":
              orig = SortTest.chunksSorted(0, n/2, n, Math.min(n/10, 100));
              break;
            case "sortedChunks":
              orig = SortTest.chunksSorted(0, n, n, Math.min(n/10, 100));
              break;
          }
          
          for(AbstractArraySort<Integer> alg : algs)  
          {
            if(alg.name.equals("Insertion sort") && n > 10000)
            {
              //System.out.println("Skipping insertion sort due to large data!");
              continue;
            }
            System.arraycopy(orig, 0, randIntegers, 0, n);
            
            // To get more info per run, you could pass true instead of false for the last element
            long result = benchMark(alg, randIntegers, false);
            log.add(alg.toString(), type, n, result);
          }
        }
      }
    }
    
    /* Getting the mean results. */
    // For the interested student: Sort the results for each case and display it as such! You can use the methods of the benchmarkLog class and even add your own. 
    for(int n : dataLengths)
    {
      for(String type : runTypes)
      {
        for(AbstractArraySort<Integer> alg : algs)
        {
          log.updateMean(alg.toString(), type, n);
          double meanResult = log.getMean(alg.toString(), type, n);
          if(meanResult != -1)
            System.out.println("Run length: " + n + " data type: " + type + ", algorithm: " + alg + " mean: " +  meanResult);
        }
        System.out.println();
      }
    }
  }
}
