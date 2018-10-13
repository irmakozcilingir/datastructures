package comp202examples;

public class LinProbHashMap extends AbstractHashMap {

  class Entry
  {
    String k;
    Integer v;
    
    Entry(String String, Integer Integer)
    {
      k = String;
      v = Integer;
    }
    
    boolean isDefunct()
    {
      return k == null && v == null;
    }
    
    public String toString()
    {
      return "(" + k.toString() + ", "+ v.toString() + ")";
    }
  }
  
  Entry defunct;
  private Entry[] buckets;
  
  LinProbHashMap()
  {
    this(7, 0.6f);
  }
  
  LinProbHashMap(int initSize)
  {
    this(initSize, 0.6f);
  }
  
  LinProbHashMap(int initSize, float criticalAlpha)
  {
    cAlpha = criticalAlpha;
    defunct = new Entry(null,null);
    reset(initSize);
  }
  
  void reset(int size)
  {
    n = 0;
    N = size; 
    buckets = new Entry[N];//java.lang.reflect.Array.newInstance(cl, N); //
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
