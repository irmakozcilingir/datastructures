public class Sorting99999<K extends Comparable<K>> extends AbstractArraySort<K> {

  Sorting99999() {
    this.name = "Sorting99999";
  }

  @Override
  public void sort(K[] inputArray) {
    // Insertion sort, implemented for you.
    for (int i = 1; i < inputArray.length; i++) {
      int j = i;
      while (j > 0 && inputArray[j - 1].compareTo(inputArray[j]) > 0) {
        swap(inputArray, j - 1, j);
        j--;
      }
    }
  }
}
