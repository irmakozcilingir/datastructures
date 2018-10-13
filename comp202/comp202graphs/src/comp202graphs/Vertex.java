package comp202graphs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A default vertex implementation
 * Note that you can modify the graph that uses this without calling the graph methods 
 * (e.g. add/remove vertices manually, add/remove edges manually). You need to be careful!
 * 
 * Only outgoingEdges are used in the undirected graph implementation
 * 
 * @author baakgun
 *
 * @param <VertexType>
 */
public class Vertex<VertexType> {

  VertexType data;

  HashMap<Vertex<VertexType>, Edge<VertexType>> incomingEdges;
  HashMap<Vertex<VertexType>, Edge<VertexType>> outgoingEdges;

  Vertex(VertexType inData) {
    data = inData;

    incomingEdges = new HashMap<Vertex<VertexType>, Edge<VertexType>>();
    outgoingEdges = new HashMap<Vertex<VertexType>, Edge<VertexType>>();
  }

  public ArrayList<Edge<VertexType>> getOutgoingEdges() {
    ArrayList<Edge<VertexType>> ret = new ArrayList<Edge<VertexType>>();
    for (Edge<VertexType> e : outgoingEdges.values())
      ret.add(e);
    return ret;
  }

  public ArrayList<Edge<VertexType>> getIncomingEdges() {
    ArrayList<Edge<VertexType>> ret = new ArrayList<Edge<VertexType>>();
    for (Edge<VertexType> e : incomingEdges.values())
      ret.add(e);
    return ret;
  }
  
  public ArrayList<Vertex<VertexType>> getOutgoingNeighborsAsVertex() {
    ArrayList<Vertex<VertexType>> ret = new ArrayList<Vertex<VertexType>>();
    for (Vertex<VertexType> v : outgoingEdges.keySet())
      ret.add(v);
    return ret;
  }

  // This method should only be used with undirected graphs
  public ArrayList<VertexType> getOutgoingNeighborsAsVertexType() {
    ArrayList<VertexType> ret = new ArrayList<VertexType>();
    for (Vertex<VertexType> v : outgoingEdges.keySet())
      ret.add(v.data);
    return ret;
  }
  

  // This method should only be used with undirected graphs
  public ArrayList<VertexType> getIncomingNeighborsAsVertexType() {
    ArrayList<VertexType> ret = new ArrayList<VertexType>();
    for (Vertex<VertexType> v : incomingEdges.keySet())
      ret.add(v.data);
    return ret;
  }

  //This method should only be used with undirected graphs.
  public ArrayList<Vertex<VertexType>> getIncomingNeighborsAsVertex() {
    ArrayList<Vertex<VertexType>> ret = new ArrayList<Vertex<VertexType>>();
    for (Vertex<VertexType> v : incomingEdges.keySet())
      ret.add(v);
    return ret;
  }
 
  public Vertex<VertexType> addOutgoingEdge(Edge<VertexType> e) {
    Vertex<VertexType> u = e.opposite(this);
    if (u != null) {
      outgoingEdges.put(u, e);
    }
    return u;
  }

  public Vertex<VertexType> addIncomingEdge(Edge<VertexType> e) {
    Vertex<VertexType> u = e.opposite(this);
    if (u != null) {
      incomingEdges.put(u, e);
    }
    return u;
  }
  
  //This method should only be used with undirected graphs.
  public ArrayList<Vertex<VertexType>> getNeighborsAsVertex() {
    return getOutgoingNeighborsAsVertex();
  }

  // This method should only be used with undirected graphs
  public ArrayList<VertexType> getNeighborsAsVertexType() {
    return getOutgoingNeighborsAsVertexType();
  }
  
  // This method should only be used with undirected graphs
  public Vertex<VertexType> addEdge(Edge<VertexType> e)
  {
    return addOutgoingEdge(e);
  }
  
  
  /**
   * 
   * The below is overridden so that different vertex objects with the same data 
   * map to the same bucket
   * 
   */
  @Override
  public int hashCode() {
    return data.hashCode();
  }

  /**
   * 
   * The below is overridden so that different vertex objects with the same data 
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
      return false;
    Vertex<?> tmp = (Vertex<?>) obj;
    return (this.data.equals(tmp.data));
  }

  @Override
  public String toString() {
    return data.toString();
  }
}