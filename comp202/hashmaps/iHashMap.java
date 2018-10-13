package comp202examples;

public interface iHashMap {
  // The interface for a <String, Integer> hashmap
  // The reason for not using generics can be found by googleing "generic arrays in Java". 
  // I do not want you to concentrate on this in your solution
  
  // Return the value given key, return null if key does not exist
  // Check to make sure if key is null or not
  Integer get(String key);
  
  // Insert the value with the given key. Update the value if the key already exists. Return true if successful, else return false
  // Check to make sure if key and/or value is null or not
  boolean put(String key, Integer value);
  
  // Remove the (key,value) pair given the key and return the erased value
  // Return null if 
  Integer remove(String key);
  
  // Return the number of elements in the hashmap
  // Be careful, this is not always the size of the underlying container!
  int size();
  
  // Returns true if the hasmap is empty, false otherwise
  boolean isEmpty();
  
}
