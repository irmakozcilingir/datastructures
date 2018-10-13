package comp202graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * You should fill out this class.
 * Some implementations can be found in Graph.java for you to look at
 * 
 * @author baakgun
 *
 * @param <VertexType>
 */
public class GraphPractice<VertexType> extends AbstractGraph<VertexType> {

	GraphPractice() {
		this(false, false);
	}

	GraphPractice(boolean directedEdges, boolean weightedEdges) {
		this.directedEdges = directedEdges;
		this.weightedEdges = weightedEdges;

		if (weightedEdges)
			DEFAULT_EDGE_W = Float.MAX_VALUE;
		else
			DEFAULT_EDGE_W = 0;

		vertexMap = new HashMap<VertexType, Vertex<VertexType>>();
		edges = new HashSet<Edge<VertexType>>();
	}

	public void recursiveDFS(VertexType start) {
		//TODO: Implement
		ArrayList<Vertex<VertexType>> visited = new ArrayList<Vertex<VertexType>>();
		Vertex<VertexType> u = vertexMap.get(start);
		recursiveDFS(u, visited);

	}
	protected void recursiveDFS(Vertex<VertexType> u, ArrayList<Vertex<VertexType>> visited) {
		System.out.println("Visiting: " + u);
		visited.add(u);

		/*for (Vertex<VertexType> v : u.getNeighborsAsVertex()) {
			if (!visited.contains(v))
				recursiveDFS(v, visited);
		}*/
		for (int i = 0; i < u.getNeighborsAsVertex().size(); i++) {
			if (!visited.contains(u.getOutgoingNeighborsAsVertex().get(i))) {
				recursiveDFS(u.getOutgoingNeighborsAsVertex().get(i),visited);
			}
		}
	}

	public void iterativeDFS(VertexType start) {
		//TODO: Implement 
		Vertex<VertexType> v = vertexMap.get(start);
		ArrayList<Vertex<VertexType>> visited = new ArrayList<>();
		Stack<Vertex<VertexType>> stack = new Stack<>();
		stack.push(v);

		while (!stack.isEmpty()) {
			Vertex<VertexType> u = stack.pop();     //this part is different
			if (!visited.contains(u)){
				System.out.println("Visiting: " + u);
				visited.add(u);
			}
			for (Vertex<VertexType> w : u.getNeighborsAsVertex()) {
				if (!visited.contains(w))
					stack.push(w);
			}
		}
	}	

	protected void iterativeDFS(Vertex<VertexType> start, ArrayList<Vertex<VertexType>> visited) {
		Stack<Vertex<VertexType>> stack = new Stack<>();
		stack.push(start);
		while (!stack.isEmpty()) {
			Vertex<VertexType> u = stack.pop();     //this part is different
			if (!visited.contains(u)){
				System.out.println("Visiting: " + u);
				visited.add(u);
			}
			for (Vertex<VertexType> v : u.getNeighborsAsVertex()) {
				if (!visited.contains(v))
					stack.push(v);
			}
		}
	}

	public void iterativeBFS(VertexType start) {
		//TODO: Implement 
		Vertex<VertexType> s= vertexMap.get(start);
		ArrayList<Vertex<VertexType>> visited = new ArrayList<Vertex<VertexType>>();
		Queue<Vertex<VertexType>> queue = new LinkedList<>();
		queue.add(s);

		while (!queue.isEmpty()) {
			Vertex<VertexType> u = queue.poll();  // this part is different
			if (!visited.contains(u)) {
				System.out.println("Visiting: " + u);
				visited.add(u);
			}
			for (Vertex<VertexType> v : u.getNeighborsAsVertex()) {
				if (!visited.contains(v))
					queue.add(v);
			}
		}		
	}

	public void iterativeBFS(Vertex<VertexType> v, ArrayList<Vertex<VertexType>> visited) {
		//TODO: Implement 

		Queue<Vertex<VertexType>> queue = new LinkedList<>();
		queue.add(v);

		while (!queue.isEmpty()) {
			Vertex<VertexType> u = queue.poll();  // this part is different
			if (!visited.contains(u)) {
				System.out.println("Visiting: " + u);
				visited.add(u);
			}
			for (Vertex<VertexType> w : u.getNeighborsAsVertex()) {
				if (!visited.contains(w))
					queue.add(w);
			}
		}		
	}


	public void DFT() {
		//TODO: Implement 
		ArrayList<Vertex<VertexType>> visited = new ArrayList<Vertex<VertexType>>();

		for (Vertex<VertexType> v : vertexMap.values()) {
			if (!visited.contains(v)) {
				System.out.println("New DFS starting from " + v);
				iterativeDFS(v, visited);
			}
		}
	}

	public void BFT() {
		//TODO: Implement 
		ArrayList<Vertex<VertexType>> visited = new ArrayList<Vertex<VertexType>>();

		for (Vertex<VertexType> v : vertexMap.values()) {
			if (!visited.contains(v)) {
				System.out.println("New BFS starting from " + v);
				iterativeBFS(v, visited);
			}
		}
	}

	public ArrayList<VertexType> findPathBFS(VertexType start, VertexType end) {
		//TODO: Implement 
		ArrayList<Vertex<VertexType>> visited = new ArrayList<Vertex<VertexType>>();
		ArrayList<VertexType> path = new ArrayList<VertexType>();
		HashMap<Vertex<VertexType>, Vertex<VertexType>> parents = new HashMap<Vertex<VertexType>, Vertex<VertexType>>();

		Queue<Vertex<VertexType>> queue = new LinkedList<>();

		queue.add((Vertex<VertexType>) start);
		while (!queue.isEmpty()) {
			Vertex<VertexType> u = queue.poll();
			if (!visited.contains(u)) {
				visited.add(u);
			}
			for (Vertex<VertexType> v : u.getNeighborsAsVertex()) {
				if (!visited.contains(v)) {
					parents.put(v, u);
					if (v.equals(end)) {
						Vertex<VertexType> p = v;
						while (parents.get(p) != null) {
							path.add(p.data);
							p = parents.get(p);
						}
						path.add(start);
						return path;
					} else
						queue.add(v);
				}
			}
		}
		Collections.reverse(path);
		return path;
	}

	// Below methods do not have implementation in Graph.java!
	public ArrayList<VertexType> findPathDFS(VertexType start, VertexType end) {
		//TODO: Implement. You can either use the iterative or the recursive version of DFS
		return null;
	}

	/**
	 * This function returns all the connected components of the graph.
	 * Each entry of the returned ArrayList correspond to a connected component.
	 * These entries hold the connected vertices. 
	 * E.g. if the Graph is A-B, E-F, you should return:
	 * ((A,B),(E-F)), where each matching parentheses represents an ArrayList
	 * 
	 */
	public ArrayList<ArrayList<VertexType>> findConnectedComponents()
	{
		//TODO: You can either use DFS or BFS
		
		return null;
	}

	/**
	 * This function returns a HashMap<VertexType, Integer> such that given a vertex, we can get its label.
	 * 
	 * Implementation is similar to findConnectedComponents but requires some tweaking.
	 * 
	 * An alternative implementation would be to have a label entry in the Vertex class and set it
	 * correctly. 
	 * 
	 */
	public HashMap<VertexType, Integer> connectedComponentLabels()
	{
		//TODO: You can either use DFS or BFS
		return null;
	}

	/**
	 * This function returns true if the graph is cyclic, false otherwise. I suggest you use DFS.
	 * 
	 * Remember that the way we did it in class differs between undirected and directed graphs. 
	 */
	public boolean isCyclic(){
		//ArrayList<Vertex<VertexType>> map = new ArrayList<>();

		for (Vertex<VertexType> v : vertexMap.values()) {
			//if (!map.contains(v)) {
				Stack<Vertex<VertexType>> s = new Stack<>();
				ArrayList<Vertex<VertexType>> visited = new ArrayList<>();
				s.push(v);
				while (!s.isEmpty()) {
					Vertex<VertexType> u = s.pop();    
					if (!visited.contains(u)){
						System.out.println("Visiting: " + u);
						visited.add(u);
					}else{
						return true;
					}
					for (Vertex<VertexType> w : u.getNeighborsAsVertex()) {
						if (!visited.contains(w))
							s.push(w);
					}
				}
			}
		//}
		return false;
	}
}
