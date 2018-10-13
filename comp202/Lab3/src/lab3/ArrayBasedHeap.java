package lab3;

import java.util.ArrayList;

public class ArrayBasedHeap<Key extends Comparable<Key>, Value> {
  protected class Node {
    Key k;
    Value v;

    Node(Key key, Value value) {
      k = key;
      v = value;
    }

    Key getKey() {
      return k;
    }

    Value getValue() {
      return v;
    }

    void setValue(Value value) {
      v = value;
    }

    public String toString() {
      return "(" + k.toString() + ", " + v.toString() + ")";
    }
  }

  protected ArrayList<Node> nodes;
  boolean isMin;

  // Is this a minimum heap or a maximum heap?
  ArrayBasedHeap(boolean isMin) {
    this.isMin = isMin;
    nodes = new ArrayList<Node>();
  }

  // Default minimum
  ArrayBasedHeap() {
    this(true);
  }

  // Check whether the heap is empty.
  public boolean isEmpty() {
    return nodes.isEmpty();
  }

  // Get number of nodes in the heap.
  public int size() {
    return nodes.size();
  }

  // Insert a value with the given key.
  public void insert(Key key, Value value) {
    for (int i = 0; i < nodes.size(); i++) {
      if (getKey(i).equals(key)) {
        nodes.get(i).setValue(value);
        return;
      }
    }
    nodes.add(new Node(key, value));
    upheap(nodes.size() - 1);
  }

  // Remove the first node in the heap and return its value.
  public Value remove() {
    Value retVal = getValue(0);
    nodeSwap(0, nodes.size() - 1);
    nodes.remove(nodes.size() - 1);
    downheap(0);
    return retVal;
  }

  // Get the first node in the heap.
  public Node peek() {
    if (nodes.isEmpty())
      return null;
    else
      return nodes.get(0);
  }

  // Get the key of the 'i'th node in the heap.
  public Key getKey(int i) {
    return nodes.get(i).getKey();
  }

  // Get the value of the 'i'th node in the heap.
  public Value getValue(int i) {
    return nodes.get(i).getValue();
  }

  // Swap two nodes given their indices.
  protected void nodeSwap(int ind1, int ind2) {
    nodes.set(ind1, nodes.set(ind2, nodes.get(ind1)));
  }

  // The upheap operation (see the book or slides)
  protected void upheap(int nodeIndex) {
    if (nodeIndex >= nodes.size() || nodeIndex <= 0)
      return;
    int parentIndex = (nodeIndex - 1) / 2;
    if (isMin) {
      if (getKey(nodeIndex).compareTo(getKey(parentIndex)) < 0) {
        nodeSwap(nodeIndex, parentIndex);
        upheap(parentIndex);
      }
    } else {
      if (getKey(nodeIndex).compareTo(getKey(parentIndex)) > 0) {
        nodeSwap(nodeIndex, parentIndex);
        upheap(parentIndex);
      }
    }
  }

  // The downheap operation (see the book or slides)
  void downheap(int nodeIndex)
  {
      if(nodeIndex >= nodes.size() || nodeIndex < 0)
          return;

      int leftChildIndex = 2*nodeIndex+1;
      int rightChildIndex = 2*nodeIndex+2;
      int ind = nodeIndex;
      int lastElement = nodes.size()-1;

      if(isMin) {
          if(leftChildIndex <= lastElement && getKey(ind).compareTo(getKey(leftChildIndex))>0)
              ind = leftChildIndex;
          if(rightChildIndex <= lastElement && getKey(ind).compareTo(getKey(rightChildIndex))>0)
              ind = rightChildIndex;
      }
      else {
          if(leftChildIndex <= lastElement && getKey(ind).compareTo(getKey(leftChildIndex))<0)
              ind = leftChildIndex;
          if(rightChildIndex <= lastElement && getKey(ind).compareTo(getKey(rightChildIndex))<0)
              ind = rightChildIndex;
      }
      if(ind != nodeIndex) {
          nodeSwap(ind, nodeIndex);
          downheap(ind);
      }
  }

  // Check whether heap property is satisfied.
  public boolean isHeap() {
    return _isHeap(0, nodes.size());
  }

  private boolean _isHeap(int i, int size) {
    int lc = 2 * i + 1;
    int rc = 2 * i + 2;

    boolean lcExists = lc < size;
    boolean rcExists = rc < size;

    if (!lcExists && !rcExists)
      return true;

    Key parKey = getKey(i);

    boolean retVal = true;
    if (isMin) {
      if (lcExists)
        retVal = retVal && (parKey.compareTo(getKey(lc)) < 0) && _isHeap(2 * i + 1, size);
      if (rcExists)
        retVal = retVal && (parKey.compareTo(getKey(rc)) < 0) && _isHeap(2 * i + 2, size);
      return retVal;
    } else {
      if (lcExists)
        retVal = retVal && (parKey.compareTo(getKey(lc)) > 0) && _isHeap(2 * i + 1, size);
      if (rcExists)
        retVal = retVal && (parKey.compareTo(getKey(rc)) > 0) && _isHeap(2 * i + 2, size);
      return retVal;
    }
  }

  // Print the heap using level order traversal
  public void printLevelOrder() {
    for (int i = 0; i < nodes.size(); i++)
      System.out.println(nodes.get(i));
  }

}