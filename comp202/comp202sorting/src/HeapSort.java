public class HeapSort<K extends Comparable<K>> extends AbstractArraySort<K> {
  
  // Pointer to the last element of the heap, use the existing of this variable as a hint
  int lastElement;
    
  HeapSort()
  {
    name = "Heapsort";
    lastElement = -1;
  }
  
  @Override
  public void sort(K[] inputArray) {
    buildHeap(inputArray);
    //You can check if you are correctly building the heap
    if(isHeap(inputArray))
    {
      //TODO: Complete the heapsort algorithm here!
    }
    else
    {
      System.out.println("The array is not heapified!");
    }
  }
  
  void buildHeap(K[] inputArray)
  {
    //TODO: Build the array, there is an O(n) version that I will cover in class 
    
  }
  
  /**
   * Check whether the array represents a heap or not
   * 
   * @param inputArray
   * @return true if the array is a heap, false otherwise
   */
  public boolean isHeap(K[] inputArray)
  {
    return _isHeap(inputArray, 0, inputArray.length);
  }
  
  private boolean _isHeap(K[] inputArray, int i, int size)
  {
    int lc = 2*i+1;
    int rc = 2*i+2;
    
    boolean lcExists = lc < size;
    boolean rcExists = rc < size;
    
    if (!lcExists && !rcExists)
      return true;
 
    boolean retVal = true;

    if(lcExists)
      retVal = retVal && (inputArray[i].compareTo(inputArray[lc])>=0) && _isHeap(inputArray, 2*i+1, size);
    if(rcExists)
      retVal = retVal && (inputArray[i].compareTo(inputArray[rc])>=0) && _isHeap(inputArray, 2*i+2, size);
    return retVal;
  }
}
