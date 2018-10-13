public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {  
  QuickSort()
  {
    name = "Quicksort";
  }
  
  //useful if we want to return a pair of indices from the partition function
  class indexPair {
    int p1, p2;
    
    indexPair(int pos1, int pos2)
    {
      p1 = pos1;
      p2 = pos2;
    }
    
    public String toString()
    {
      return "(" + Integer.toString(p1) + ", " + Integer.toString(p2) + ")";
    }
  }
  
  @Override
  public void sort(K[] inputArray)
  {
    if(inputArray == null  || inputArray.length == 0)
    {
      return;
    }
    qsort(inputArray, 0, inputArray.length-1);
  }
  
  protected void qsort(K[] inputArray, int lo, int hi)
  {
    int p;
    indexPair ip;
    if (lo < hi)
    {
      p = pickPivot(inputArray,lo,hi);
      ip = partition(inputArray,lo,hi,p);
      qsort(inputArray,lo,ip.p1);
      qsort(inputArray,ip.p2,hi);
    }
  }
  
  protected indexPair partition(K[] inputArray, int lo, int hi, int p)
  {
    //TODO: Implement a partitioning algorithm, should be in-place
    return null;
  }
  
  protected int pickPivot(K[] inpuArray, int lo, int hi)
  {
    //TODO: Pick a pivot selection method and implement it
    return -1;
  }

  // A function that you can use to check if your partition is indeed a correct one for quicksort
  public boolean isPartitioned(K[] array, int lo, int hi, int p)
  {
    for(int i = lo; i < p; i++)
    {
      if(array[i].compareTo(array[p]) > 0)
      {
        System.out.println(array[i] + " " + array[p] + " " + Integer.toString(i));
        return false;
      }
    }
    for(int i = p; i < hi; i++)
    {
      if(array[i].compareTo(array[p]) < 0)
      {
        System.out.println(array[i] + " " + array[p] + " " + Integer.toString(i));
        return false;
      }
    }
    return true;
  }
}
