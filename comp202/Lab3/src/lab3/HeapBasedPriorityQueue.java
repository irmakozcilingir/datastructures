package lab3;

//A heap based priority queue implementation. Look at the comment in the AdaptablePriorityQueue interface for what each method should do
//You do not need to modify the underlying heap but if you think you must, contact your TA or the instructor
public class HeapBasedPriorityQueue<Key extends Comparable<Key>, Value> extends ArrayBasedHeap<Key, Value> implements AdaptablePriorityQueue<Key, Value> {
	public HeapBasedPriorityQueue() {
		super();
	}

	public HeapBasedPriorityQueue(boolean isMin) {
		super();
		setType(isMin);
	}

	@Override
	public void setType(boolean type) {
		if (isEmpty())
			isMin = type;
		else if (type != isMin)
			System.out.println("Changing PQ type when the heap is not empty is not yet implemented");
	}

	@Override
	public Key replaceKey(Key k, Value v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getValue().equals(v)) {
				Key key = nodes.get(i).getKey();
				nodes.get(i).k = k;
				downheap(i); 
				upheap(i);
				return key;
			}
		}
		return null;
	}

	@Override
	public Pair<Key, Value> pop() {
		// TODO Auto-generated method stub
		if (nodes.isEmpty() || !(isHeap()))                   // checking for the heap, returns null 
			return null;  									 //	if nodes is empty or it is not a proper heap
		Pair<Key, Value> p = new Pair<Key, Value>(nodes.get(0).getKey(), nodes.get(0).getValue());
		remove();
		return p;
	}

	@Override
	public Value popValue() {
		// TODO Auto-generated method stub
		if (nodes.isEmpty() || !(isHeap()))
			return null;
		Value v = nodes.get(0).getValue();
		remove();
		return v;
	}

	@Override
	public Key popKey() {
		// TODO Auto-generated method stub
		if (nodes.isEmpty() || !(isHeap()))
			return null;
		Key k = nodes.get(0).getKey();
		remove();
		return k;
	}

	@Override
	public Pair<Key, Value> top() {
		// TODO Auto-generated method stub
		if (nodes.isEmpty() || !(isHeap()))
			return null;  
		Pair<Key, Value> p = new Pair<Key, Value>(nodes.get(0).getKey(), nodes.get(0).getValue());
		return p;

	}

	@Override
	public Value topValue() {
		// TODO Auto-generated method stub
		if (nodes.isEmpty() || !(isHeap()))
			return null;
		Value v = nodes.get(0).getValue();
		return v;

	}

	@Override
	public Key topKey() {
		// TODO Auto-generated method stub
		if (nodes.isEmpty() || !(isHeap()))
			return null;
		Key k = nodes.get(0).getKey();
		return k;
	}

	@Override
	public Value remove(Key k) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getKey().equals(k)) {
				Value v = nodes.get(i).getValue();
				nodeSwap(i, nodes.size() - 1);
				downheap(i); 
				upheap(i);
				return v;
			}
		}
		return null;
	}

	@Override
	public Key findByValue(Value v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getValue().equals(v)) {
				Key key = nodes.get(i).getKey();
				return key;
			}
		}
		return null;
	}


}