package comp202graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class includes implementation of certain methods,
 * for you to look at if you get stuck.
 * 
 * @author baakgun
 *
 * @param <VertexType>
 */
public class Graph<VertexType> extends AbstractGraph<VertexType> {

	Graph() {
		this(false, false);
	}

	Graph(boolean directedEdges, boolean weightedEdges) {
		this.directedEdges = directedEdges;
		this.weightedEdges = weightedEdges;

		if (weightedEdges)
			DEFAULT_EDGE_W = Float.MAX_VALUE;
		else
			DEFAULT_EDGE_W = 0;

		vertexMap = new HashMap<VertexType, Vertex<VertexType>>();
		edges = new HashSet<Edge<VertexType>>();
	}

	protected void recursiveDFS(Vertex<VertexType> u, HashSet<Vertex<VertexType>> visited) {
		System.out.println("Visiting: " + u);
		visited.add(u);

		for (Vertex<VertexType> v : u.getNeighborsAsVertex()) {
			if (!visited.contains(v))
				recursiveDFS(v, visited);
		}
	}

	public void recursiveDFS(VertexType start) {
		HashSet<Vertex<VertexType>> visited = new HashSet<Vertex<VertexType>>();
		Vertex<VertexType> u = vertexMap.get(start);
		recursiveDFS(u, visited);
	}

	protected void iterativeDFS(VertexType start) {
		iterativeDFS(vertexMap.get(start));
	}

	protected void iterativeDFS(Vertex<VertexType> start) {
		HashSet<Vertex<VertexType>> visited = new HashSet<Vertex<VertexType>>();

		iterativeDFS(start, visited);
	}

	protected void iterativeDFS(Vertex<VertexType> start, HashSet<Vertex<VertexType>> visited) {
		ArrayDeque<Vertex<VertexType>> stack = new ArrayDeque<Vertex<VertexType>>();

		stack.push(start);
		while (!stack.isEmpty()) {
			Vertex<VertexType> u = stack.pop();
			if (!visited.contains(u))
			{
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
		iterativeBFS(vertexMap.get(start));
	}

	protected void iterativeBFS(Vertex<VertexType> start) {
		HashSet<Vertex<VertexType>> visited = new HashSet<Vertex<VertexType>>();

		iterativeBFS(start, visited);
	}

	protected void iterativeBFS(Vertex<VertexType> start, HashSet<Vertex<VertexType>> visited) {
		ArrayDeque<Vertex<VertexType>> queue = new ArrayDeque<Vertex<VertexType>>();

		queue.push(start);
		while (!queue.isEmpty()) {
			Vertex<VertexType> u = queue.removeLast();
			if (!visited.contains(u)) {
				System.out.println("Visiting: " + u);
				visited.add(u);
			}
			for (Vertex<VertexType> v : u.getNeighborsAsVertex()) {
				if (!visited.contains(v))
					queue.push(v);
			}
		}
	}

	public void DFT() {
		HashSet<Vertex<VertexType>> visited = new HashSet<Vertex<VertexType>>();

		for (Vertex<VertexType> v : vertexMap.values()) {
			if (!visited.contains(v))
			{
				System.out.println("New DFS starting from " + v);
				iterativeDFS(v, visited);
			}
		}
	}

	public void BFT() {
		HashSet<Vertex<VertexType>> visited = new HashSet<Vertex<VertexType>>();

		for (Vertex<VertexType> v : vertexMap.values()) {
			if (!visited.contains(v))
			{
				System.out.println("New BFS starting from " + v);
				iterativeBFS(v, visited);
			}
		}
	}

	public ArrayList<VertexType> findPathBFS(VertexType start, VertexType end) {
		return findPathBFS(vertexMap.get(start), vertexMap.get(end));
	}

	protected ArrayList<VertexType> findPathBFS(Vertex<VertexType> start, Vertex<VertexType> end) {
		HashSet<Vertex<VertexType>> visited = new HashSet<Vertex<VertexType>>();

		return findPathBFS(start, end, visited);
	}

	protected ArrayList<VertexType> findPathBFS(Vertex<VertexType> start, Vertex<VertexType> end, HashSet<Vertex<VertexType>> visited) {
		ArrayList<VertexType> path = new ArrayList<VertexType>();
		HashMap<Vertex<VertexType>, Vertex<VertexType>> parents = new HashMap<Vertex<VertexType>, Vertex<VertexType>>();

		ArrayDeque<Vertex<VertexType>> queue = new ArrayDeque<Vertex<VertexType>>();

		queue.push(start);
		while (!queue.isEmpty()) {
			Vertex<VertexType> u = queue.removeLast();
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
						path.add(start.data);
						return path;
					} else
						queue.push(v);
				}
			}
		}
		Collections.reverse(path);
		return path;
	}
}
