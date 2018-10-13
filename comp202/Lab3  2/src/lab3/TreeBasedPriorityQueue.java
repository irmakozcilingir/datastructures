package lab3;

import lab3.AvlTree.Node;

// An AVL Tree based priority queue implementation. Look at the comment in the AdaptablePriorityQueue interface for what each method should do
// You do not need to modify the underlying AVL tree but if you think you must, contact your TA or the instructor
public class TreeBasedPriorityQueue<Key extends Comparable<Key>, Value> extends AvlTree<Key, Value> implements AdaptablePriorityQueue<Key, Value> {

	boolean isMin;

	public TreeBasedPriorityQueue() {
		this(true);
	}

	public TreeBasedPriorityQueue(boolean isMin) {
		super();
		setType(isMin);
	}

	@Override
	public void setType(boolean type) {
		isMin = type;
	}

	@Override
	public Key replaceKey(Key k, Value v) {
		// TODO Auto-generated method stub
		Node n = findNode(v, root);     // using my helper method
		if (n != null) {
			Key kk = n.getKey();
			delete(kk);              //For easier rotation and balance purposes.
			insert(k, v);		    // Again for easier rotation and balance purposes.
			return kk;
		}
		return null;
	}
	private Node findNode(Value v, Node node) {
		if(node != null){
			if(node.value.equals(v)){
				return node;
			} 
			Node tnode = findNode(v, node.left);       //  Using a temporary node, starting from the left (and then right), searches for the same 
			if(tnode == null) {						  //  value in each node. If it does not exist, returns null. 	
				tnode = findNode(v, node.right);
			}
			return tnode;

		}
		return null;

	}

	@Override
	public Value popValue() {
		// TODO Auto-generated method stub
		Node nd = root;
		if(nd == null) return null;
		if (isMin) {
			while (true) {
				if (nd.left != null) {                // Trying to find the left most element (it is the smallest) if isMin returns true.
					nd = nd.left;
				}else{
				Value v = nd.getValue();
				delete(nd.getKey());               // Delete in here also handles rotation and balance issues.
				return v;
				}
			}
		}else{
			while (true) {
				if (nd.right != null) {		   // if inMin is false then the smallest element will be the right most one.		
					nd = nd.right;            // Same logic applies for the other pop and top methods 
				}else{
				Value v = nd.getValue();
				delete(nd.getKey());
				return v;
				}
			}
		}
	}

	@Override
	public Key popKey() {
		// TODO Auto-generated method stub
		Node nd = root;
		if(nd == null) return null;
		if (isMin) {
			while (true) {
				if (nd.left != null) {
					nd = nd.left;
				}else{
				Key k = nd.getKey();
				delete(k);
				return k;
				}
			}
		}else{
			while (true) {
				if (nd.right != null) {
					nd = nd.right;
				}else{
				Key k = nd.getKey();
				delete(k);
				return k;
				}
			}
		}
	}

	@Override
	public Pair<Key, Value> pop() {
		// TODO Auto-generated method stub
		Node nd = root;
		if(nd == null) return null;
		if (isMin) {
			while (true) {
				if (nd.left != null) {
					nd = nd.left;
				}else{
				Key k = nd.getKey();
				Value v = nd.getValue();
				delete(k);
				Pair<Key, Value> p = new Pair<Key,Value>(k,v);
				return p;
				}
			}
		}else{
			while (true) {
				if (nd.right != null) {
					nd = nd.right;
				}else{
				Key kk = nd.getKey();
				Value vv = nd.getValue();
				delete(kk);
				Pair<Key, Value> pp = new Pair<Key, Value>(kk, vv);
				return pp;
				}
			}
		}  

	}

	@Override
	public Value topValue() {
		// TODO Auto-generated method stub
		Node nd = root;
		if(nd == null) return null;
		if (isMin) {
			while (true) {
				if (nd.left != null) {
					nd = nd.left;
				}else{
				Value v = nd.getValue();
				return v;
				}
			}
		}else{
			while (true) {
				if (nd.right != null) {
					nd = nd.right;
				}else{
				Value v = nd.getValue();
				return v;
				}
			}
		}

	}

	@Override
	public Key topKey() {
		// TODO Auto-generated method stub
		Node nd = root;
		if(nd == null) return null;
		if (isMin) {
			while (true) {
				if (nd.left != null) {
					nd = nd.left;
				}else{
				Key k = nd.getKey();
				return k;
				}
			}
		}else{
			while (true) {
				if (nd.right != null) {
					nd = nd.right;
				}else{
				Key k = nd.getKey();
				return k;
				}
			}
		}

	}

	@Override
	public Pair<Key, Value> top() {
		// TODO Auto-generated method stub
		Node nd = root;
		if(nd == null) return null;
		if (isMin) {
			while (true) {
				if (nd.left != null) {
					nd = nd.left;
				}else{
				Key k = nd.getKey();
				Value v = nd.getValue();
				Pair<Key, Value> p = new Pair<Key,Value>(k,v);
				return p;
				}
			}
		}else{
			while (true) {
				if (nd.right != null) {
					nd = nd.right;
				}else{
				Key kk = nd.getKey();
				Value vv = nd.getValue();
				Pair<Key, Value> pp = new Pair<Key, Value>(kk, vv);
				return pp;
				}
			}
		}  

	}

	@Override
	public Value remove(Key k) {
		// TODO Auto-generated method stub
		Value v = get(k);
		if (v != null) {             // this if statement is for safety...
			delete(k);
			return v;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		size(root);
		return 0;
	}
	private int size(Node nd) { 
		if (nd == null) 
			return(0); 
		else { 
			return(size(nd.left) + 1 + size(nd.right));      // In a recursive way, incrementing at each step, applies 
		} 													// the method to its right and left children of the node
	} 													   //  (size o the children) until the node becomes null. At the end sums them all.

	@Override
	public Key findByValue(Value v) {
		// TODO Auto-generated method stub

		return findNode(v, root).getKey();            // the helper method that I implemented above
	}

}
