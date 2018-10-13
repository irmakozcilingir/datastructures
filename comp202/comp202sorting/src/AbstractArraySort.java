/**
 * Abstract class for sorting arrays. It defines several useful methods. 
 * You must override the sort method to implement a concrete class.
 * 
 * Do not include any other class (e.g. benchmarkLog) in your own class! 
 *
 * @author baakgun
 * 
 * @param <K>
 */
public abstract class AbstractArraySort<K extends Comparable<K>> {
  
  String name;
  
  /**
   * Override!
   * @param inputArray
   */
  public abstract void sort(K[] inputArray);
  
  /**
   * swap function for arrays
   * 
   * @param arr:  Array
   * @param pos1: first element to be swapped
   * @param pos2: second element to be swapped
   */
  protected void swap(K[] arr, int pos1, int pos2){
    K temp = arr[pos1];
    arr[pos1] = arr[pos2];
    arr[pos2] = temp;
  }
  
  /**
   * A function to check whether the given array is sorted between lo and hi
   * 
   * @param array
   * @param lo
   * @param hi
   * @return true if sorted, false otherwise
   */
  public static <K extends Comparable<K>> boolean isSorted(K[] array, int lo, int hi)
  {
    for(int i = lo; i < hi; i++)
    {
      if(array[i].compareTo(array[i+1]) > 0)
      {
        System.out.println(array[i] + " " + array[i+1] + " " + Integer.toString(i));
        return false;
      }
    }
    return true;
  }
  
  /**
   * Prints the input array. 
   * Useful in debugging.
   * 
   * @param array
   */
  
  public static <K> void printArray(K[] array)
  {
    System.out.print("[");
    for(int i = 0; i < array.length-1; i++)
      System.out.print(array[i].toString() + ", ");
    System.out.print(array[array.length-1].toString());
    System.out.println("]");
  }
  
  /**
   * Prints the input array between lo and hi (both inclusive)
   * Useful in debugging.
   * @param array
   * @param lo
   * @param hi
   */
  public static <K> void printArray(K[] array, int lo, int hi)
  {
    System.out.print("[");
    for(int i = lo; i < hi; i++)
      System.out.print(array[i].toString() + ", ");
    System.out.print(array[hi].toString());
    System.out.println("]");
  }
  
  @Override
  public String toString()
  {
    return name;
  }

}
