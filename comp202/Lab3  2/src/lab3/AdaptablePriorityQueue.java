package lab3;

public interface AdaptablePriorityQueue<Key extends Comparable <Key>, Value> {

  // Set the type of the priority queue, whether it is a minimum or a maximum one. 
  // You do not need to handle the case when this function is called while the PQ is not empty
	void setType(boolean isMin);
	
	// Check whether priority queue is empty.
  boolean isEmpty();

  // Get number of nodes in priority queue.
  int size();

	// Insert a new node with given key and value. Updates the value if the key already exists. 
	void insert(Key k, Value v);

	// Replace the key of the value and return the old key
	// Return null if the value does not exists
	Key replaceKey(Key k, Value v);

	// Remove the appropriate node and return its <key,value> pair based on the priority queue type (min or max).
	// Pair is a simple class that holds a key-value pair (see Pair.java)
	// Return null if the PQ is empty
  Pair<Key, Value> pop();
	
	// Remove the appropriate node and return its value based on the priority queue type (min or max).
  // Return null if the PQ is empty
	Value popValue();

	// Remove the appropriate node and return its key based on the priority queue type (min or max).
	// Return null if the PQ is empty
	Key popKey();

  // Return the appropriate <key,value> pair based on the priority queue type (min or max) but do not remove it
  // Pair is a simple class that holds a key-value pair (see Pair.java)
  // Return null if the PQ is empty
  Pair<Key, Value> top();
	
	// Return the appropriate value based on the priority queue type (min or max) but do not remove it
  // Return null if the PQ is empty
	Value topValue();

	// Return the appropriate key based on the priority queue type (min or max) but do not remove it
  // Return null if the PQ is empty
	Key topKey();

	// Remove the node with given key and return its value
  // Return null if the key does not exists
	Value remove(Key k);

	// Return the key for the given value, assume that the values will be unique
  // Return null if the key does not exists
	Key findByValue(Value v);

}
