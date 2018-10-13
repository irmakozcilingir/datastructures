package lab3;

//A simple class that holds a key-value pair
public class Pair<Key, Value> {
  public Key k;
  public Value v;
  
  Pair(Key key, Value value)
  {
    k = key;
    v = value;
  }
  
  Pair()
  {
    k = null;
    v = null;
  }

}
