package Sorting;

public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  // Auxilary array to be used in merging
  private K[] auxArray;
  
  MergeSort()
  {
    name = "MergeSort";
  }
  
  @Override
  @SuppressWarnings ("unchecked")
  public void sort(K[] inputArray) {
    this.auxArray = (K[]) new Comparable[inputArray.length];
    mergeSort(inputArray, 0, inputArray.length-1);
    this.auxArray = null;
  }
  
  void mergeSort(K[] inputArray, int lo, int hi){
    if(lo < hi){
      int mid = lo + (hi-lo)/2; //same as (lo+hi)/2 but avoids overflow if lo+hi > INTEGER_MAX
      mergeSort(inputArray, lo, mid);
      mergeSort(inputArray, mid+1, hi);
      merge(inputArray,lo,mid,hi);
    }
  }
  
  void merge(K[] inputArray, int lo, int mid, int hi){
    System.arraycopy(inputArray, lo, auxArray, lo, hi-lo+1);
    int i = lo; 
    int j = mid+1;
    int k = lo;
    
    while(k <= hi){
      if(j > hi || (i <= mid && auxArray[i].compareTo(auxArray[j])<=0))
        inputArray[k++] = auxArray[i++];
      else
        inputArray[k++] = auxArray[j++];
    }
  }
}
