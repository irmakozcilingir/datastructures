/**
 * The Edge class. Pretty standard. 
 * 
 * @author baakgun
 *
 */
public class Edge {
  protected double edgeWeight;

  Vertex startVertex;
  Vertex endVertex;
  
  
  public Vertex opposite(Vertex v)
  {
    if(startVertex.equals(v))
      return endVertex;
    else if(endVertex.equals(v))
      return startVertex;
    else
      return null;
  }
  
  public double getWeight()
  {
    return edgeWeight;
  }
  
  public void setWeight(double weight)
  {
    edgeWeight = weight;
  }
  
  @Override
  public String toString()
  {
    return "<" + startVertex.toString() + ", " + endVertex.toString() + ">, " + edgeWeight;
  }
    
  Edge(Vertex start, Vertex end, double weight)
  {
    startVertex = start;
    endVertex = end;
    edgeWeight = weight;
  }
  
  Edge(String start, String end, double weight)
  {
    startVertex = new Vertex(start);
    endVertex = new Vertex(end);;
    edgeWeight = weight;
  }
  
  
  /**
   * 
   * The below is overridden so that different edge objects with the same data 
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
    Edge tmp = (Edge) obj;
    /*if (this.edgeWeight != tmp.edgeWeight)
      return false;
    else*/
    {
      if (this.startVertex.equals(tmp.startVertex) &&  this.endVertex.equals(tmp.endVertex))
        return true;
      else
        return false;
    }
  }
}
