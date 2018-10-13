package Sorting;

public class InsertionSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  InsertionSort()
  {
    name = "Insertion sort";
  }
  
  @Override
  public void sort(K[] inputArray) 
  {
    for(int i = 1; i < inputArray.length; i++)
    {
      int j = i;
      while(j > 0 && inputArray[j-1].compareTo(inputArray[j])>0)
      {
        swap(inputArray,j-1,j);
        j--;
      }
    }
  }
}
