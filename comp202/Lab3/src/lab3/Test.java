package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Scanner;

public class Test {
  
  public static <K extends AdaptablePriorityQueue<Integer, String>> long test(Class<K> adaptablePriorityQue, boolean  output, String testType)
  {
    String folderName;
    if(testType.equals("easy"))
      folderName = "Exchange";
    else
      folderName = "Exchange3";
    
    File exchangeList = new File (folderName);
    
    int numberOfExchanges = exchangeList.list().length;
    
    Constructor<K> constructor;
    K sellPQ;
    K buyPQ;
    
    try {
      constructor = adaptablePriorityQue.getConstructor();
      sellPQ = constructor.newInstance();
      buyPQ = constructor.newInstance();
      buyPQ.setType(false);
    
      Date startTime = new Date();
      for ( int i=0; i<numberOfExchanges; i++ ) {
      
        File exchange = new File (folderName + "/Exchange_" + i + ".txt");
        
        try ( Scanner exchangeScanner = new Scanner(exchange) ) {
          if(output)
            System.out.println("Iteration: " + i);
          while ( exchangeScanner.hasNextLine() ) {
            String nextAction = exchangeScanner.nextLine();
            String [] entry = nextAction.split(" ");
            switch(entry[1])
            {
            case "Sell":
              if(output)
                System.out.println( entry[0] + " offered to sell for " + entry[2] + "." );
              sellPQ.insert( Integer.parseInt(entry[2]), entry[0] );
              break;
            case "Buy":
              if(output)
               System.out.println( entry[0] + " offered to buy for " + entry[2] + "." );
              buyPQ.insert( Integer.parseInt(entry[2]), entry[0] );
              break;
            case "Change":
              if ( entry[2].equals("Sell") ) {
                if(output)
                  System.out.println( entry[0] + " changed his/her sell offer to " + entry[3] + "." );
                sellPQ.replaceKey( Integer.parseInt(entry[3]), entry[0] );
              }
              else if ( entry[2].equals("Buy") ) {
                if(output)
                  System.out.println( entry[0] + " changed his/her buy offer to " + entry[3] + "." );
                buyPQ.replaceKey( Integer.parseInt(entry[3]), entry[0] );
              }
              break;
            case "Remove":
              if ( entry[2].equals("Sell") ) {
                if(output)
                  System.out.println( entry[0] + " removed his/her sell offer of " + entry[3] + "." );
                sellPQ.remove( Integer.parseInt(entry[3]) );
              }
              else if ( entry[2].equals("Buy") ) {
                if(output)
                  System.out.println( entry[0] + " removed his/her buy offer of " + entry[3] + "." );
                buyPQ.remove( Integer.parseInt(entry[3]) );
              }
              break;
            }
          }
          
          //while (sellPQ.topKey() != null && buyPQ.topKey() != null && sellPQ.topKey().compareTo(buyPQ.topKey()) <= 0 ) {
          while (true) {
            if(sellPQ.top() != null && buyPQ.top() != null) {
              if(sellPQ.topKey().compareTo(buyPQ.topKey()) <= 0) {
                //if(output)
                  System.out.println(sellPQ.topValue() + " sold to " + buyPQ.topValue() + " for " + sellPQ.topKey());
                sellPQ.pop();
                buyPQ.pop();
              }
              else
                break;
            }
            else
              break;
          }
        }
        
        catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      return (new Date().getTime() - startTime.getTime());
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e1) {
      e1.printStackTrace();
    }
    return -1;
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    String testType = "hard"; //"hard"
    boolean verboseOutput = false;
    
    //if(testType.equals("easy"))
    //  verboseOutput = true;

    //test(HeapBasedPriorityQueue.class, false, testType);
    //test(TreeBasedPriorityQueue.class, false, testType);
    
    //System.out.println("Heap based execution started.");
    //long heapTime = test(HeapBasedPriorityQueue.class, verboseOutput, testType);
    //System.out.println("Heap based execution took " + heapTime+ " miliseconds.");

    System.out.println();
    
    System.out.println("Tree based execution started.");
    long treeTime = test(TreeBasedPriorityQueue.class, verboseOutput, testType);
    System.out.println("Tree based execution took " + treeTime + " miliseconds.");

    // For more fun, uncomment the following code
   /* System.out.println();
    heapTime = 0;
    treeTime = 0;
    for(int i = 0; i < 100; i++)
    {
      heapTime += test(HeapBasedPriorityQueue.class, false, testType);
      treeTime += test(TreeBasedPriorityQueue.class, false, testType);
    }
    System.out.println("100 Heap based executions took " + heapTime + " miliseconds.");
    System.out.println("100 Tree based executions took " + treeTime + " miliseconds.");
*/
  }
}
