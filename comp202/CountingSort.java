package Sorting;

public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  CountingSort()
  {
    name = "Counting Sort";
  }
  
  private K minKey;
  private K maxKey;
  
  private String getClass(K element)
  {
    Class<?> cls = element.getClass();
    if(cls != Integer.class)
    {
      if(cls == Pair.class)
      {
        Pair<?,?> tmp = (Pair<?,?>) element;
        Class<?> cls2 = tmp.k.getClass();
        if(cls2 != Integer.class)
        {
          return "Other"; //Other but pair
        }
        else
        {
          return "IntegerKey";
        }
      }
      else
      {
        return "Other";
      }
    }
    else
    {
      return "Integer";
    }
  }
  
  @Override
  public void sort(K[] inputArray) {
    
    if(inputArray == null)
    {
      System.out.println("Null array");
      return;
    }
    if(inputArray.length < 1)
    {
      System.out.println("Empty array");
      return;
    }   
    
    String clazz = getClass(inputArray[0]);
    if(!clazz.equals("Other"))
    {
      getMinMax(inputArray);
      if(clazz.equals("Integer"))
      {
        //System.out.println("Sorting as Integer array.");
        csortV1((Integer[]) inputArray);
      }
      else if (clazz.equals("IntegerKey"))
      {
        //System.out.println("Sorting as Pair array with Integer keys.");
        csortV2((Pair<?,?> []) inputArray);
      }
    }
    else
      System.out.println("Can only sort Integers or Pairs with Integer keys.");
  }
  
  //version 1
  public void csortV1(Integer[] inputArray)
  {
    Integer min = (Integer) minKey;
    Integer max = (Integer) maxKey;
    int k = max-min+1;
    int[] aux = new int[k];
    for(int i = 0; i < inputArray.length; i++)
      aux[inputArray[i]-min]++;
    int d = 0;
    for (int i = 0; i < k; i++)
    {
      for (int j = 0; j < aux[i]; j++)
      {
        
        inputArray[d] = i+min;
        d++;
      }
    }
  }
  
  //version 2, make sure to overwrite at the end
  public void csortV2(Pair<?,?>[] inputArray)
  {
    //TODO: Implement this at some point
  }
  
  private void getMinMax(K[] inputArray)
  {
    minKey = inputArray[0];
    maxKey = inputArray[0];
    
    for(K elem : inputArray)
    {
      if(elem.compareTo(minKey) < 0)
        minKey = elem;
      if(elem.compareTo(maxKey) > 0)
        maxKey = elem;
    }
  }

}
