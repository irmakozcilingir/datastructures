package comp202graphs;

public class Edge<VertexType> {
  protected float edgeWeight;
  private boolean isDirected;

  Vertex<VertexType> startVertex;
  Vertex<VertexType> endVertex;
  
  
  public Vertex<VertexType> opposite(Vertex<VertexType> v)
  {
    if(startVertex.equals(v))
      return endVertex;
    else if(endVertex.equals(v))
      return startVertex;
    else
      return null;
  }
  
  public float getWeight()
  {
    return edgeWeight;
  }
  
  public void setWeight(float weight)
  {
    edgeWeight = weight;
  }
  
  @Override
  public String toString()
  {
    return "<" + startVertex.toString() + ", " + endVertex.toString() + ">";
  }
  
  Edge(Vertex<VertexType> start, Vertex<VertexType> end, boolean isDirected)
  {
    this(start,end,Float.MAX_VALUE,isDirected);
  }

  
  Edge(Vertex<VertexType> start, Vertex<VertexType> end, float weight, boolean isDirected)
  {
    startVertex = start;
    endVertex = end;
    edgeWeight = weight;
    this.isDirected = isDirected;
  }
  
  Edge(VertexType start, VertexType end, float weight, boolean isDirected)
  {
    startVertex = new Vertex<VertexType>(start);
    endVertex = new Vertex<VertexType>(end);;
    edgeWeight = weight;
    this.isDirected = isDirected;
  }
  
  Edge( VertexType start, VertexType end, boolean isDirected)
  {
    this(start,end, Float.MAX_VALUE, isDirected);
  }
  
  /**
   * 
   * The below is overridden so that different vertex objects with the same data 
   * map to the same bucket. 
   * 
   * The idea is to get the same hashcode if the startVertices and endVertices are reversed
   * 
   */
  @Override
  public int hashCode() {
    if(startVertex.hashCode() > endVertex.hashCode())
      return startVertex.hashCode() + endVertex.hashCode();
    else
      return endVertex.hashCode() + startVertex.hashCode();
  }

  /**
   * 
   * The below is overridden so that different edge objects with the same vertices 
   * are treated as being equal. Also needed for hashing. Not checking the weights!
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
    Edge<?> tmp = (Edge<?>) obj;
    /*if (this.edgeWeight != tmp.edgeWeight)
      return false;
    else*/
    {
      if(isDirected)
      {
        if (this.startVertex.equals(tmp.startVertex) &&  this.endVertex.equals(tmp.endVertex))
          return true;
        else
          return false;
      }
      else
      {
        if ((this.startVertex.equals(tmp.startVertex) &&  this.endVertex.equals(tmp.endVertex)) || (this.endVertex.equals(tmp.startVertex) &&  this.startVertex.equals(tmp.endVertex)))
          return true;
        else
          return false;
      }
    }
  }
}
