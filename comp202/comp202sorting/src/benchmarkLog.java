import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that holds benchmarking data
 * 
 * @author baakgun
 *
 */
class benchmarkLog 
{
  class runInfo
  {
    int _runDataLength;
    String _type;
    String _algName;
    
    runInfo(String algorithm, String dataType, int runDataLength)
    {
      _runDataLength = runDataLength;
      _type = dataType;
      _algName = algorithm;
    }
    
    @Override
    public int hashCode()
    {
      return (Integer.toString(_runDataLength) + _type + _algName).hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
     if (obj == null)
        return false;
     if (getClass() != obj.getClass())
       return false;
     runInfo tmp = (runInfo) obj;
     return (this._algName.equals(tmp._algName)) && (this._type.equals(tmp._type)) && (this._runDataLength == tmp._runDataLength);
    }
    
    @Override
    public String toString()
    {
      return "Algorithm: " + _algName + ", Data Type: " + _type + ", Data Length: " + Integer.toString(_runDataLength);
    }
  }
    
  HashMap<runInfo, ArrayList<Long>> runResults;

  // Could be <runInfo, runStats> when we add more statistics!
  HashMap<runInfo, Double> runStats;
  
  benchmarkLog()
  {
    runResults = new HashMap<runInfo, ArrayList<Long>>();
    runStats = new HashMap<runInfo, Double>();
  }
  
  void add(String algorithm, String type, int runDataLength, long result)
  {
    runInfo tmpRI = new runInfo(algorithm, type, runDataLength); 
    ArrayList<Long> tmp = runResults.get(tmpRI);

    if(tmp == null)
    {
      tmp = new  ArrayList<Long>();
      runResults.put(tmpRI, tmp);
    }
    tmp.add(result);
  }
  
  public void updateMean(String algorithm, String type, int runDataLength)
  {
    runInfo tmpRI = new runInfo(algorithm, type, runDataLength);
    ArrayList<Long> tmp = runResults.get(tmpRI);;
    if(tmp == null || tmp.isEmpty())
    {
      return;
    }
    double sum = 0;
    for (Long mark : tmp) {
        sum += mark;
    }
    
    double mean = sum / tmp.size();
    runStats.put(tmpRI, mean);
  }
  
  public double getMean(String algorithm, String type, int runDataLength)
  {
    runInfo tmpRI = new runInfo(algorithm, type, runDataLength);
    return runStats.get(tmpRI);
  }
  
  public ArrayList<Long> get(String algorithm, String type, int runDataLength)
  {
    runInfo tmpRI = new runInfo(algorithm, type, runDataLength);
    return runResults.get(tmpRI);
  }
  
  public benchmarkLog getByAlgorithm(String algorithm)
  {
    benchmarkLog ret = new benchmarkLog();
    for(runInfo key : runResults.keySet())
    {
      if(key._algName.equals(algorithm))
        for(Long res:runResults.get(key))
          ret.add(key._algName, key._type, key._runDataLength, res);
    }
    return ret;
  }
  
  public benchmarkLog getByRunLength(int runDataLength)
  {
    benchmarkLog ret = new benchmarkLog();
    for(runInfo key : runResults.keySet())
    {
      if(key._runDataLength == runDataLength)
        for(Long res:runResults.get(key))
          ret.add(key._algName, key._type, key._runDataLength, res);
    }
    return ret;
  }
  
  public benchmarkLog getByDataType(String type)
  {
    benchmarkLog ret = new benchmarkLog();
    for(runInfo key : runResults.keySet())
    {
      if(key._type.equals(type))
        for(Long res:runResults.get(key))
          ret.add(key._algName, key._type, key._runDataLength, res);
    }
    return ret;
  }
  
  @Override
  public String toString()
  {
    StringBuffer strBuff = new StringBuffer();
    for(runInfo ri : runResults.keySet())
    {
      strBuff.append(ri.toString());
      ArrayList<Long> arrRes = runResults.get(ri);
      for(Long res : arrRes)
      {
        strBuff.append(" ");
        strBuff.append(res.toString());
      }
      strBuff.append(System.lineSeparator());
    }
    return strBuff.toString();
  }
}

