import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The vertex class, specialized to act as an artificial neuron as described in the project document.
 * 
 * @author baakgun, kurmanbek
 *
 */

public class Vertex implements Comparable<Vertex> {

  //Name of the vertex. The instances of this class can be sorted with their name.
  String name;
  
  // Variable to keep the incoming weighted sum.
  double incomingSum;
  
  //Variable to keep the partial derivative of the error with respect to the incomingSum
  double delta;
  
  //The activation/transfer function
  Activation f;
  
  HashMap<Vertex, Edge> incomingEdges;
  HashMap<Vertex, Edge> outgoingEdges;

  public Vertex(String name, String activation) {
    incomingSum = 0;
    delta = 0;
    this.name = name;
    initActivation(activation);
    incomingEdges = new HashMap<Vertex, Edge>();
    outgoingEdges = new HashMap<Vertex, Edge>();
  }

  public Vertex(String name) {
    this(name, "LinFunc");
  }

  // Given the activation function name, initializes the function
  public void initActivation(String activation) {
    if (activation.equals("Sigmoid")) {
      f = new Sigmoid();
    } else if (activation.equals("ReLU")) {
      f = new ReLU();
    } else if (activation.equals("LinFunc")) {
      f = new LinFunc();
    } else {
      System.err.println("Invalid activation function!");
    }
  }

  // Returns a list of outgoing neighbors
  public List<Vertex> getOutgoingNeighbors() {
    return new ArrayList<Vertex>(outgoingEdges.keySet());
  }

  // Returns a list of incoming neighbors
  public List<Vertex> getIncomingNeighbors() {
    return new ArrayList<Vertex>(incomingEdges.keySet());
  }

  //Passes the incoming sum through the activation function and returns the result
  public double getOutput() {
    return f.func(incomingSum);
  }

 //Passes the incoming sum through the derivative of the activation function and returns the result
  public double getBackDeriv() {
    return f.der(incomingSum);
  }

  public String toSting() {
    return name;
  }

  /**
   * The below is overridden so that different vertex objects with the same name
   * map to the same bucket
   * 
   */
  @Override
  public int hashCode() {
    return name.hashCode();
  }

  /**
   * The below is overridden so that different vertex objects with the same name
   * are treated as being equal. Also needed for hashing.
   * 
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
    {
      if(obj.getClass() == String.class)
        return this.name.equals(obj);
      else
        return false;
    }
    Vertex tmp = (Vertex) obj;
    return (this.name.equals(tmp.name));
  }

  @Override
  public String toString() {
    return name;
  }

  /**
   * The below is overriden so that vertices can be sorted by their name.
   */
  
  @Override
  public int compareTo(Vertex that) {
    return this.name.compareTo(that.name);
  }
}