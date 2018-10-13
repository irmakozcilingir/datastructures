package comp202graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An Adjacency Map implementation of Graphs. 
 * Implemented for education purposes, do not use in production code.
 * Allows for modifying graph without calling graph methods.
 * There are more efficient ways of doing things
 * 
 * Make sure you do not mix undirected edges and directed edges.
 * 
 * You probaby do not need to modify this file unless there is a bug
 * 
 * @author baakgun
 *
 * @param <VertexType>
 */
public class AbstractGraph<VertexType> 
{
  protected HashMap<VertexType, Vertex<VertexType>> vertexMap;
  protected HashSet<Edge<VertexType>> edges;
  protected float DEFAULT_EDGE_W;
  
  protected boolean directedEdges;
  protected boolean weightedEdges;
  
  public boolean containsVertex(Vertex<VertexType> v) 
  {
    return containsVertex(v.data);
  }
  
  public boolean containsVertex(VertexType v) 
  {
    return vertexMap.get(v) != null;
  }
 
  public boolean containsEdge(Edge<VertexType> e)
  {
    return edges.contains(e);
  }
  
  // Slow, since we are iterating over the edge array. Could be made faster by using HashMaps
  public boolean containsEdge(VertexType u, VertexType v)
  {
    Edge<VertexType> e = new Edge<VertexType>(u,v,directedEdges);
    return containsEdge(e);
  }
  
  public boolean isEmpty()
  {
    return vertexMap.isEmpty();
  }
  
  public int numVertices()
  {
    return vertexMap.size();
  }
  
  public int numEdges()
  {
    return edges.size();
  }
  
  public ArrayList<VertexType> getVertices()
  {
    ArrayList<VertexType> ret = new ArrayList<VertexType>();
    ret.addAll(vertexMap.keySet());
    return ret;
  }
  
  public ArrayList<Edge<VertexType>> getEdges()
  {
    ArrayList<Edge<VertexType>> ret = new ArrayList<Edge<VertexType>>();
    ret.addAll(edges);
    return ret;
  }
  public Vertex<VertexType> getVertex(VertexType v)
  {
    return vertexMap.get(v);
  }
  
  public Edge<VertexType> addEdge(Vertex<VertexType> start, Vertex<VertexType> end, float w)
  {
    if(start == null || end == null)
      return null;
    Edge<VertexType> e = new Edge<VertexType>(start, end, w, directedEdges);
    edges.add(e);
    if(directedEdges)
    {
      start.addOutgoingEdge(e);
      end.addIncomingEdge(e);
    }
    else //default is outgoing...
    {
      start.addOutgoingEdge(e);
      end.addOutgoingEdge(e);
    }
    return e;
  }
  
  public Edge<VertexType> addEdge(VertexType start, VertexType end, float w)
  {
    Vertex<VertexType> v1 = vertexMap.get(start);
    Vertex<VertexType> v2 = vertexMap.get(end);
    
    return addEdge(v1,v2,w);
  }
  
  public Edge<VertexType> addEdge(Vertex<VertexType> start, Vertex<VertexType> end)
  {
    return addEdge(start,end, DEFAULT_EDGE_W);
  }
  
  public Edge<VertexType> addEdge(VertexType start, VertexType end)
  {
   return addEdge(start,end, DEFAULT_EDGE_W);
  }
  
  private void _removeEdge(Vertex<VertexType> u, Vertex<VertexType> v)
  {
    if(directedEdges)
    {
      u.outgoingEdges.remove(v);
      v.incomingEdges.remove(u);
    }
    else
    {
      u.outgoingEdges.remove(v);
      v.outgoingEdges.remove(u);
    }
  }
  
  public Edge<VertexType> removeEdge(Edge<VertexType> e)
  {
    if(e == null)
      return null;
    
    Vertex<VertexType> start = e.startVertex;
    Vertex<VertexType> end = e.endVertex;
    
    if(start == null || end == null)
      return null;
    
    _removeEdge(start,end);
    
    return e;
    
  }
  
  public Edge<VertexType> removeEdge(Vertex<VertexType> u, Vertex<VertexType> v)
  {
    if(u == null || v == null)
      return null;

    
    Edge<VertexType> e = u.outgoingEdges.get(v);
    
    if(e == null)
      return null;
    
    _removeEdge(u,v);
    edges.remove(e);
    
    return e; 
  }
  
  public Edge<VertexType> removeEdge(VertexType u, VertexType v)
  {
    Vertex<VertexType> v1 = vertexMap.get(u);
    Vertex<VertexType> v2 = vertexMap.get(v);
    
    return removeEdge(v1,v2);
  }
  
  public Vertex<VertexType> addVertex(VertexType v)
  {
    Vertex<VertexType> v1 = vertexMap.get(v);
    if(v1 == null)
    {
      v1 = new Vertex<VertexType>(v);
      vertexMap.put(v, v1);
    }
    return v1;
  }
  
  public Vertex<VertexType> removeVertex(VertexType v)
  {
    Vertex<VertexType> u = vertexMap.get(v);
    if(u != null)
    {
      if(directedEdges)
      {
        for(Vertex<VertexType> w : u.incomingEdges.keySet())
        {
          w.outgoingEdges.remove(u);
        }
      }
      else
      {
        for(Vertex<VertexType> w : u.outgoingEdges.keySet())
        {
          w.outgoingEdges.remove(u);
        }
      }
      vertexMap.remove(v);
    }
    return u;
  }
  
  /**
   * If directed check u->v, else checks both
   * 
   * @param u
   * @param v
   * @return
   */
  public boolean areAdjacent(Vertex<VertexType> u, Vertex<VertexType> v)
  {
    if(u == null || v == null)
      return false;
    else
      return u.outgoingEdges.containsKey(v);
  }
  
  public boolean areAdjacent(VertexType u, VertexType v)
  {
    Vertex<VertexType> v1 = vertexMap.get(u);
    Vertex<VertexType> v2 = vertexMap.get(v);

    return areAdjacent(v1,v2);
  }
  
}
