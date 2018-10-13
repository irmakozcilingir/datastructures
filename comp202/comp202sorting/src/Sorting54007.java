import java.util.Arrays;

public class Sorting54007<K extends Comparable<K>> extends AbstractArraySort<K> {


	Sorting54007(){
		name = "Sorting54007";
	}

	@Override
	public void sort(K[] inputArray){
		mergeSort(inputArray, inputArray, 0, inputArray.length-1);
	}

	public void mergeSort(K[] inputArray, K[] aux, int lo, int hi){          // aux array is not a copy of inputArray anymore, we can swap it with inputArray to save some time
		if(lo < hi){
			if (hi <= lo + 7){                     //uses insertion sort (which is more efficient for small data) if the range is small enough
				insertionSort(inputArray, lo, hi);
				return; 
			}

			int mid = lo + (hi-lo)/2; //same as (lo+hi)/2 but avoids overflow if lo+hi > INTEGER_MAX

			mergeSort(aux, inputArray, lo, mid);
			mergeSort(aux, inputArray, mid+1, hi);

			if(inputArray[mid+1].compareTo(inputArray[mid])>=0){      //avoids unnecessary merge operations if it is already sorted
				return;  
			}	
			merge(inputArray, aux, lo, mid, hi);
		}
	}

	private void merge(K[] inputArray, K[] aux, int lo, int mid, int hi)
	{
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++)
		{
			if(i > mid){
				aux[k] = inputArray[j++];
			}else if(j > hi){
				aux[k] = inputArray[i++];
			}
			else if (inputArray[j].compareTo(inputArray[i])<0){
				aux[k] = inputArray[j++];
			}
			else{
				aux[k] = inputArray[i++];
			}
		} 
	}
	public void insertionSort(K[] inputArray, int lo, int hi) {
		int a, b;

		for (b = lo + 1; b <= hi; b++) {
			K temp = inputArray[b];
			a = b;

			while (a > lo && (inputArray[a - 1].compareTo(temp) >=0)) {
				inputArray[a] = inputArray[a - 1];
				--a;
			}
			inputArray[a] = temp;
		}
	}


	//	public void insertionSort(K[] inputArray, int lo, int hi) {
	//		for (int i = lo; i <= hi; i++)
	//			for (int j = i; j > lo && (inputArray[j].compareTo(inputArray[j-1])<0); j--)
	//				swap(inputArray, j, j-1);
	//
	//	}

}
