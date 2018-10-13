package Sorting;

public class HeapSort<K extends Comparable<K>> extends AbstractArraySort<K> {
  
  // Pointer to the last element of the heap
  int lastElement;
    
  HeapSort()
  {
    name = "Heapsort";
    lastElement = -1;
  }
  
  @Override
  public void sort(K[] inputArray) {
    buildHeap(inputArray);
    while(lastElement > 0)
    {
      swap(inputArray, 0, lastElement);
      lastElement--;
      downheap(inputArray, 0);
    }
  }
  
  void buildHeap(K[] inputArray)
  { 
    lastElement = inputArray.length-1;
    for(int i = inputArray.length/2; i>=0; i--)
      downheap(inputArray,i);
  }

  private void downheap(K[] inputArray, int i)
  {
    int lc = 2*i+1;
    int rc = 2*i+2;
    int ind = i;

    
    if(lc <= lastElement && inputArray[ind].compareTo(inputArray[lc])<0)
      ind = lc;
    if(rc <= lastElement && inputArray[ind].compareTo(inputArray[rc])<0)
      ind = rc;
    if(ind != i)
    {
      swap(inputArray, i, ind);
      downheap(inputArray, ind);
    }
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
