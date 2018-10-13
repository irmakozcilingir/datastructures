package comp202examples;

abstract public class AbstractHashMap implements iHashMap {
  // The abstract base class for a <String, Integer> hashmap
  // The reason for not using generics can be found by googleing "generic arrays in Java". 
  // I do not want you to concentrate on this in your solution
  
  // Current size of the hashmap
  int n;
  
  // Capacity of the hashmap
  int N;
  
  // Critical load factor to expand the underlying array
  float cAlpha;
  
  public int size()
  {
    return n;
  }
  
  public boolean isEmpty()
  {
    return n == 0;
  }
  
  protected int hashValue(int hashCode, int iter)
  {
    return (Math.abs(hashCode + iter) % N) ;
  }
  
  protected int hashValue(int hashCode)
  {
    return hashValue(hashCode, 0);
  }
  
  protected int hashValue(String key)
  {
    return hashValue(key.hashCode(), 0);
  }
  
  protected int hashValue(String key, int iter)
  {
    return hashValue(key.hashCode(), iter);
  }
  
}
