package comp202graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

  public static void printPath(ArrayList<String> path)
  {
    if (path.isEmpty())
      System.out.println("No Path!");
    else
    {
      for (String p : path)
        System.out.println(p);
    }
  }
  
  public static void main(String[] args) {
    //Play around with isdirected, for now we are not using anything weight related
    boolean isDirected = false;
    
    //To test your own implementation, compare the outputs with the Graph version
    //IMPORTANT: You need to write your own test methods for the ones that are not provided.
    
    //Graph<String> G = new Graph<String>(isDirected, false);
    
    GraphPractice<String> G = new GraphPractice<String>(isDirected, false);
    
    G.addVertex("A");
    G.addVertex("B");
    G.addVertex("C");
    G.addVertex("D");
    G.addVertex("E");
    G.addVertex("F");

    G.addEdge("A", "B");
    G.addEdge("A", "C");
    G.addEdge("A", "F");
    G.addEdge("B", "D");
    G.addEdge("E", "C");
    G.addEdge("D", "E");

    G.addVertex("T");
    G.addVertex("U");
    G.addVertex("V");
    G.addVertex("W");
    G.addVertex("Y");
    G.addVertex("Z");

    G.addEdge("T", "U");
    G.addEdge("U", "V");
    G.addEdge("U", "W");
    G.addEdge("Z", "T");
    G.addEdge("Z", "Y");
    
    //Behaviors change if the graph is directed!

    System.out.println("Testing recursive DFS");
    G.recursiveDFS("A");
    System.out.println();
    System.out.println("Testing iterative DFS");
    G.iterativeDFS("A");
    System.out.println();
    System.out.println("Testing iterative BFS");
    G.iterativeBFS("A");
    System.out.println();
    System.out.println("Testing DFT");
    G.DFT();
    System.out.println();
    System.out.println("Testing BFT");
    G.BFT();

    System.out.println();
    System.out.println("Testing path finding with BFS");
    System.out.println("From A to E");
    ArrayList<String> path = G.findPathBFS("A", "E");
    printPath(path);
    System.out.println("From A to T");
    path = G.findPathBFS("A", "T");
    printPath(path);
    System.out.println("From V to T");
    path = G.findPathBFS("V", "T");
    printPath(path);
    
    System.out.println();
    System.out.println("Testing Connected Components");
    HashMap<String,Integer> CC = G.connectedComponentLabels();
    System.out.println(CC.get("A"));
    System.out.println(CC.get("T"));
    System.out.println(CC.get("Z"));
    
    
  }
}
