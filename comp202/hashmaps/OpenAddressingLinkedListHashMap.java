package comp202examples;

import java.util.LinkedList;

public class OpenAddressingLinkedListHashMap  extends AbstractHashMap{

  class Entry
  {
    String k;
    Integer v;
    
    Entry(String String, Integer Integer)
    {
      k = String;
      v = Integer;
    }
    
    public String toString()
    {
      return "(" + k.toString() + ", "+ v.toString() + ")";
    }
  }
  
  Entry defunct;
  private LinkedList<Entry>[] buckets;
  
  OpenAddressingLinkedListHashMap()
  {
    this(997, 0.6f);
  }
  
  OpenAddressingLinkedListHashMap(int initSize)
  {
    this(initSize, 0.6f);
  }
  
  OpenAddressingLinkedListHashMap(int initSize, float criticalAlpha)
  {
    cAlpha = criticalAlpha;
    defunct = new Entry(null,null);
    reset(initSize);
  }
  
  void reset(int size)
  {
    n = 0;
    N = size; 
    buckets = new LinkedList[N];//java.lang.reflect.Array.newInstance(cl, N); //
  }
  
  @Override
  public Integer get(String key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean put(String key, Integer value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Integer remove(String key) {
    // TODO Auto-generated method stub
    return null;
  }

}
