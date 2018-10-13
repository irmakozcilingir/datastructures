public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  // Auxiliary array to be used in merging
  private K[] auxArray;
  
  MergeSort()
  {
    name = "Mergesort";
  }
  
  @Override
  @SuppressWarnings ("unchecked")
  public void sort(K[] inputArray) {
    this.auxArray = (K[]) new Comparable[inputArray.length];
    mergeSort(inputArray, 0, inputArray.length-1);
    this.auxArray = null;
  }
  
  void mergeSort(K[] inputArray, int lo, int hi)
  {
    if(lo < hi)
    {
      int mid = lo + (hi-lo)/2; //same as (lo+hi)/2 but avoids overflow if lo+hi > INTEGER_MAX
      mergeSort(inputArray, lo, mid);
      mergeSort(inputArray, mid+1, hi);
      merge(inputArray,lo,mid,hi);
    }
  }
  
  void merge(K[] inputArray, int lo, int mid, int hi)
  {
    //TODO: Implement the merging algorithm, note that you need to use the auxiliary array! YOu can change the function signature if you want to
  }
}
