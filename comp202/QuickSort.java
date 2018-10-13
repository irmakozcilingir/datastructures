package Sorting;

import java.util.Random;

public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {
  public indexPair tmp;
  static Random r = new Random();
  
  QuickSort()
  {
    name = "Quicksort";
  }
  
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
      //ip = partition2Way(inputArray,lo,hi,p);
      ip = partition(inputArray,lo,hi,p);
      qsort(inputArray,lo,ip.p1);
      qsort(inputArray,ip.p2,hi);
    }
  }
  
  public indexPair partition2Way(K[] inputArray, int lo, int hi, int p)
  {
    swap(inputArray, p, hi);
    int u = lo;
    int g = hi-1;
    K pivot = inputArray[hi];

    while (u <= g)
    {
      while(u <= g && inputArray[u].compareTo(pivot) <= 0)
        u++;
      while(u <= g && inputArray[g].compareTo(pivot) > 0)
        g--;
      if(u <= g)
      {
        swap(inputArray, u, g);
        u++;
        g--;
      }
    }

    swap(inputArray, u, hi);
    indexPair tmp = new indexPair(u-1,u+1);
    return tmp;
  }

  
  protected indexPair partition(K[] inputArray, int lo, int hi, int p)
  {
    swap(inputArray, p, hi);
    int e = lo;
    int u = lo;
    int g = hi+1;
    int cmpRes;
    K pivot = inputArray[hi];
    while (u < g)
    {
      cmpRes = inputArray[u].compareTo(pivot);
      if (cmpRes < 0)
        swap(inputArray, u++, e++);
      else if (cmpRes == 0) 
        u++;
      else 
        swap(inputArray, u, --g);
    }
    indexPair tmp = new indexPair(e,g);
    return tmp;
  }
  
  protected int pickPivot(K[] inpuArray, int lo, int hi)
  {
    return randomIntRange(lo,hi);
  }
  
  public int randomIntRange(int min, int max)
  {
    return r.nextInt((max - min)) + min;
  }

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
