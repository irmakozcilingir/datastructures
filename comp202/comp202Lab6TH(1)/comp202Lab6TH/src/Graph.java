import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


/**
 * The graph class, specialized to act as a computational network as described
 * in the project document.
 * 
 * @author baakgun, kurmanbek
 *
 */

public class Graph {
	HashMap<String, Vertex> vertices;
	Set<Edge> edges;
	HashSet<Vertex> inputVertices;
	HashSet<Vertex> outputVertices;
	List<Vertex> topologicallySortedVertices;
	List<Vertex> reverseTopologicallySortedVertices;

	static PrintWriter out;

	public Graph() {
		vertices = new HashMap<String, Vertex>();
		edges = new HashSet<Edge>();
		inputVertices = new HashSet<Vertex>();
		outputVertices = new HashSet<Vertex>();
	}

	/**
	 * Adds a vertex to the graph with the given name and the activation function.
	 * Returns the newly added vertex.
	 * 
	 * @param name:
	 *          name of the neuron
	 * @param activation:
	 *          activation/transfer function of the neuron
	 * @return
	 */
	public Vertex addVertex(String name, String activation) {
		Vertex v = vertices.get(name);
		if (v == null) {
			v = new Vertex(name, activation);
			vertices.put(name, v);
		} else {
			v.initActivation(activation);
		}
		return v;
	}

	public Vertex addVertex(String name) {
		return this.addVertex(name, "LinFunc");
	}

	/**
	 * Checks whether two vertices are neighbors.
	 * 
	 * @param v
	 * @param u
	 * @return 1 if v has an outgoing edge to u, -1 if u has an outgoing edge to v
	 *         and 0 if no edge between them exists.
	 */
	public int neighborInfo(Vertex v, Vertex u) {
		if (v.outgoingEdges.containsKey(u))
			return 1;
		else if (v.incomingEdges.containsKey(u))
			return -1;
		return 0;
	}

	/**
	 * Reset the internal variables of all the vertices so that next calculations
	 * are correct
	 * 
	 */
	public void refresh() {
		for (Vertex v : vertices.values()) {
			v.incomingSum = 0;
			v.delta = 0;
		}
		vertices.get("bias").incomingSum = 1;
	}

	// figure out which vertices are inputs and which vertices are outputs
	protected void updateInputOutputVertices() {
		inputVertices.clear();
		outputVertices.clear();
		for (Vertex v : vertices.values()) {
			if (v.incomingEdges.size() == 0)
				inputVertices.add(v);
			else if (v.outgoingEdges.size() == 0)
				outputVertices.add(v);
		}
	}

	// add a bias vertex as input to all vertices but the input ones
	protected void addBiasToAll() {
		String bias = "bias";
		addVertex(bias);
		vertices.get(bias).incomingSum = 1;
		updateInputOutputVertices();
		for (Vertex v : vertices.values()) {
			if (v.incomingEdges.keySet().contains(vertices.get(bias))) {
				continue;
			}
			if (!inputVertices.contains(v)) {
				addEdge("bias", v.name, 1.0);
			}
		}
	}

	// Add bias, topologically sort
	public boolean checkAndInitialize() {
		addBiasToAll();
		topologicallySortedVertices = topologicalSort();
		if (topologicallySortedVertices != null) {
			reverseTopologicallySortedVertices = topologicalSort();
			Collections.reverse(reverseTopologicallySortedVertices);
		}
		return topologicallySortedVertices == null;
	}

	/**
	 * This method adds a directed edge between the given vertex names. It creates
	 * the vertices if they do not exist. If an edge already exists, its weight is
	 * updated. If an edge in a reverse direction exists or if the start vertex
	 * and the end vertex names are the same, it returns null
	 * 
	 * @param startVertexName
	 * @param endVertexName
	 * @param weight
	 * @return a valid edge if successful, null otherwise
	 */
	public Edge addEdge(String startVertexName, String endVertexName, double weight) {
		// TODO: Implement this yourself
		// There is a detailed description of what is required in the document!
		// As a summary make sure you handle:
		// 1. Check if the vertices have the same name (return null if they have the
		// same name)
		// 2. Add new vertices if they are not in the graph (take a look at the
		// addVertex method)
		// 3. Update the edge weight if a given edge already exists in the correct
		// direction. (take a look at the neihgborInfo method)
		// 4.Return null if a given edge already exists in the wrong direction.
		// (take a look at the neihgborInfo method)
		// 5. If there is no edge between the vertices, create a new edge. Make sure
		// to update all necessary containers (Hint: There are 3 of them)
		// 6. Finally, return the valid edge (either the newly created one or the
		// updated one)
		if (startVertexName.equals(endVertexName)) {
			return null;
		}
		if (!vertices.containsKey(startVertexName)) {
			addVertex(startVertexName);
		}
		if (!vertices.containsKey(endVertexName)) {
			addVertex(endVertexName);
		}
				
		if (neighborInfo(vertices.get(startVertexName), vertices.get(endVertexName)) == 1) {
			Edge e = vertices.get(startVertexName).outgoingEdges.get(vertices.get(endVertexName));
			e.setWeight(weight);
			return e;
		}else if (neighborInfo(vertices.get(startVertexName), vertices.get(endVertexName)) == -1){
			return null;
		}else if(neighborInfo(vertices.get(startVertexName), vertices.get(endVertexName)) == 0){
			Edge ee = new Edge(vertices.get(startVertexName),vertices.get(endVertexName),weight);
			vertices.get(startVertexName).outgoingEdges.put(vertices.get(endVertexName), ee);
			vertices.get(endVertexName).incomingEdges.put(vertices.get(startVertexName), ee);
			return ee;
		}else
			return null;
	}

	/**
	 * Algorithm to topologically sort the vertices of the graph. Returns null if
	 * the graph is cyclic or the ordered list of vertices otherwise Feel free to
	 * add helper methods
	 * 
	 * @return
	 */
	public ArrayList<Vertex> topologicalSort() {

		ArrayList<Vertex> t = new ArrayList<>();
		ArrayList<Vertex> visited = new ArrayList<>();

		for (String s : vertices.keySet()) {
			Vertex v = vertices.get(s);
			if (!visited.contains(v)) {
				if (!pass(v,t,visited)) {
					return null;	
				}
			}
		}
		return t;
	}

	private boolean pass(Vertex v, ArrayList<Vertex> tt, ArrayList<Vertex> visited) {      //helper method
		// TODO Auto-generated method stub
		if (visited.contains(v)) {
			return false;
		}
		visited.add(v);
		for (Vertex vv : v.getOutgoingNeighbors()) {
			if (!tt.contains(vv)) {
				if (!pass(vv,tt,visited)) {
					return false;
				}
			}
		}
		tt.add(0, v);    //adding v with index 0
		return true;
	}

	/**
	 * Function to calculate the output of the network given the input
	 * 
	 * @param inputs
	 * @return
	 */
	public HashMap<String, Double> calculate(HashMap<String, Double> inputs) {
		// Refresh is already called for you
		refresh();  
		// TODO: Implement this yourself
		// There is a detailed description of what is required in the document!
		// As a summary make sure you handle:
		// 1. Map the input vector to the correct vertices
		// 2. Populate the incomingSum of all the vertices (pay attention to edge
		// weights and the getOutput method of the vertex class)
		// 3. Create a new hashmap for the output and fill it correctly. Then return
		// this hashmap.
		// Remember the arguments about the topologically sorted vertices
		for (String s : vertices.keySet()) {
			if (inputs.containsKey(s)) {
				vertices.get(s).incomingSum = inputs.get(s);
			}
		}
		
		double sum = 0;
		double o = 0;
		boolean empty = false;
		Edge e = null;

		HashMap<String,Double> h = new HashMap<String,Double>();

		ArrayList<Vertex> list = topologicalSort();

		for(int i=0; i<list.size(); i++){
			Vertex v = list.get(i);
			List<Vertex> out = v.getOutgoingNeighbors();

			if(out.isEmpty())
				empty = true;

			if(inputVertices.contains(v)){
				o = v.getOutput();
				h.put(v.name, o);

				if(!empty){
					for(int j=0; j<out.size(); j++){
						e = v.outgoingEdges.get(out.get(j)); 
						e.edgeWeight = e.edgeWeight * o;
					}
				}
			} else {
				List<Vertex> in = v.getIncomingNeighbors();

				for(int k=0; k<in.size(); k++){
					e = v.incomingEdges.get(in.get(k));  
					sum = sum + e.edgeWeight;
				}

				v.incomingSum = sum;
				o = v.getOutput();
				h.put(v.name, o);

				if(!empty){
					for(int j=0; j<out.size(); j++){
						e = v.outgoingEdges.get(out.get(j));  
						e.edgeWeight = e.edgeWeight * o;
					}
				}
			}
		}
		return h;
	}


	/**
	 * Function to train the network given input-output pairs
	 * 
	 * @param inputs
	 * @return
	 */
	public double train(List<HashMap<String, Double>> iData, List<HashMap<String, Double>> oData, double alpha,
			double epsilon, int maxIter) {

		int i = 0;
		double err = Double.MAX_VALUE;

		HashMap<Edge, Double> wuCumulative = new HashMap<Edge, Double>();

		while (i < maxIter && err > epsilon) {
			for (Edge e : edges) {
				wuCumulative.put(e, 0.);
			}
			err = 0;
			for (int j = 0; j < iData.size(); j++) {
				err += singleBackwardPass(iData.get(j), oData.get(j), wuCumulative);
			}
			err = Math.sqrt(err / iData.size()); 
			// Cumulative/batch weight update for learning
			for (Edge e : edges) {
				e.edgeWeight += alpha * wuCumulative.get(e);
			}
			i++;
		}
		return err;
	}

	/**
	 * Weight update calculation for a single input-output pair
	 * 
	 * @param inputs
	 * @param outputs
	 * @param cumulativeUpdate
	 * @return
	 */

	public double singleBackwardPass(HashMap<String, Double> inputs, HashMap<String, Double> outputs,
			HashMap<Edge, Double> cumulativeUpdate) {
		// TODO: Implement this yourself
		// There is a detailed description of what is required in the document!
		// As a summary make sure you handle:
		// 1. Call calculate to get the network output and the incoming sums for all
		// the vertices
		// 2a. Starting from the output vertices, calculate the delta
		// field for all the vertices, making sure you handle the output vertices
		// correctly. (pay attention to edge
		// weights, the getOutput and the getBackDeriv method of the vertex class)
		// 2b. In the meantime, calculate the squared training error for the given
		// input-output pair. Do not forget the 1/2 part!
		// 3. After this step, you need to go over all the edges, and calculate
		// their update. You need to accumulate these updates in cumulativeUpdate
		// hashmap. Do not overwrite existing updates!
		// 4. Finally, return the squared training error.
		// 5. Remember the arguments about the topologically sorted vertices
		//HashMap<String, Double> h = calculate(inputs);
	
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder();
		List<Vertex> sortedVertices = new ArrayList<Vertex>(vertices.values());
		Collections.sort(sortedVertices);
		for (Vertex v : sortedVertices) {
			ss.append(v + System.lineSeparator());
			for (Edge e : v.outgoingEdges.values())
				ss.append(e + System.lineSeparator());
		}
		return ss.toString();
	}

	public static void printMap(Map<?, ?> m) {
		for (Map.Entry<?, ?> entry : m.entrySet()) {
			out.print("(" + entry.getKey() + ", " + entry.getValue() + ")");
		}
		out.println();
	}

	public static void printList(List<?> l) {
		Iterator<?> itr = l.iterator();
		out.print("( ");
		while (itr.hasNext()) {
			Object e = itr.next();
			out.print(e + ", ");
		}
		out.print(")");
	}

	public static void printData(List<HashMap<String, Double>> data) {
		for (HashMap<String, Double> instance : data) {
			printMap(instance);
		}
	}

	public static boolean createGraphAndReadDataFromFile(String fileName, Graph G, List<HashMap<String, Double>> iData,
			List<HashMap<String, Double>> oData) {

		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			boolean vReadMode = false, eReadMode = false, iReadMode = false, oReadMode = false;
			while ((line = br.readLine()) != null) {
				String[] splt = line.split(" ");
				if (splt.length == 1 && splt[0].equals("Vertices")) {
					vReadMode = true;
				} else if (splt.length == 1 && splt[0].equals("Edges")) {
					eReadMode = true;
					vReadMode = false;
				} else if (splt.length == 1 && splt[0].equals("Inputs")) {
					iReadMode = true;
					eReadMode = false;
					vReadMode = false;
				} else if (splt.length == 1 && splt[0].equals("Outputs")) {
					oReadMode = true;
					iReadMode = false;
					eReadMode = false;
					vReadMode = false;
				}
				if (vReadMode && splt.length == 2) {
					out.println("Adding vertex " + splt[0] + " " + splt[1]);
					G.addVertex(splt[0], splt[1]);
				} else if (eReadMode && splt.length == 3) {
					out.println("Adding edge " + splt[0] + " " + splt[1] + " " + splt[2]);
					Edge e = G.addEdge(splt[0], splt[1], Double.parseDouble(splt[2]));
					if (e == null)
						return false;
				} else if (iReadMode && splt.length >= 2) {
					out.println("Adding data for input: " + splt[0]);
					for (int i = 1; i < splt.length; ++i) {
						if (i > iData.size()) {
							iData.add(new HashMap<String, Double>());
						}
						iData.get(i - 1).put(splt[0], Double.parseDouble(splt[i]));
					}

				} else if (oReadMode && splt.length >= 2) {
					out.println("Adding data for output: " + splt[0]);
					for (int i = 1; i < splt.length; ++i) {
						if (i > oData.size()) {
							oData.add(new HashMap<String, Double>());
						}
						oData.get(i - 1).put(splt[0], Double.parseDouble(splt[i]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean checkData(List<HashMap<String, Double>> iData, List<HashMap<String, Double>> oData) {
		Set<String> iTmp = iData.get(0).keySet();
		Set<String> oTmp = oData.get(0).keySet();
		for (String vertexName : iTmp) {
			if (!inputVertices.contains(vertices.get(vertexName)) && !vertexName.equals("bias")) {
				// out.println("1");
				return false;
			}
		}
		for (String vertexName : oTmp) {
			if (!outputVertices.contains(vertices.get(vertexName)) && !vertexName.equals("bias")) {
				// out.println("2");
				return false;
			}
		}
		for (Vertex v : inputVertices) {
			if (!iTmp.contains(v) && !v.name.equals("bias")) {
				// out.println("3" + v);
				return false;
			}
		}
		for (Vertex v : outputVertices) {
			if (!oTmp.contains(v) && !v.name.equals("bias")) {
				// out.println("4");
				return false;
			}
		}
		return true;
	}

	public void calculateAndPrint(List<HashMap<String, Double>> iData) {
		out.println("Outputs: ");
		HashMap<String, Double> calculatedOutputs;
		for (int j = 0; j < iData.size(); j++) {
			calculatedOutputs = calculate(iData.get(j));
			out.println("For the input: ");
			printMap(iData.get(j));
			out.println("The output is: ");
			printMap(calculatedOutputs);
		}
	}

	public static void main(String args[]) throws FileNotFoundException {
		// To debug, you can change the below filenames so that you can work on a
		// single file/
		String[] fileNames = {
				"addEdgeTest1.txt", //tests adding a self edge 
				"addEdgeTest2.txt", //tests adding an edge in the wrong direction
				"addEdgeTest3.txt", //tests overriding an edge value
				"cyclic.txt",       //a cyclic graph
				"list.txt",         //a linear graph
				"example.txt",      //example in the document
				"test.txt",         //a more involved example
				"impossible.txt",   //an un-learnable example (can you guess why?)
		"multiOutput.txt"}; //example with multiple output neurons

		for (String fileName : fileNames) {
			out = new PrintWriter(new File("output_" + fileName));
			out.println("Processing: " + fileName);
			Graph G = new Graph();
			// Input data
			List<HashMap<String, Double>> iData = new ArrayList<>();
			// Output data
			List<HashMap<String, Double>> oData = new ArrayList<>();
			if(!createGraphAndReadDataFromFile(fileName, G, iData, oData)) {
				out.println("Moving on to the next file since a problem is detected in graph creation");
				out.close();
				continue;
			}

			out.println("Input data:");
			printData(iData);
			out.println("Output data:");
			printData(oData);

			boolean cyclic = G.checkAndInitialize();

			out.println("Before training");
			out.print(G);

			out.println("Is the graph cyclic: " + cyclic);

			if (cyclic) {
				out.println("Moving on to the next file since a cycle is detected");
				out.close();
				continue;
			}

			out.println("Topologically sorted vertices: ");
			printList(G.topologicallySortedVertices);
			out.println();

			if (!G.checkData(iData, oData)) {
				out.println(
						"Moving on to the next file since the input or output data does not correspond to the input or output vertices");
				out.close();
				continue;
			}
			G.calculateAndPrint(iData);

			if (iData.size() != oData.size()) {
				out.println("Moving on to the next file since the number of inputs and outputs do not match.");
				out.close();
				continue;
			}


			double err = G.train(iData, oData, 0.05, 0.001, 10000);
			out.println("Training finished with error: " + err);
			out.println("After training");
			out.print(G);
			G.calculateAndPrint(iData);

			out.println();
			out.close();
		}
	}
}
