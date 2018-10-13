package Lab2;
import java.util.ArrayList;
import java.util.List;

import Lab2.Node.VALUE;

import static Lab2.Node.VALUE.*;

public class WeirdBinaryTree {
	Node root;

	WeirdBinaryTree()
	{
		root = new Node(R,null);
	}

	
	
	public ArrayList<String> search(VALUE[] inputString){
		
		  return search(inputString,0,root,new ArrayList<String>());
	}
	
	
	//recursive search for each character of the string return the path if found
	public ArrayList<String> search(VALUE[] inputString, int index, Node head, ArrayList<String> path){
		
		if(index==inputString.length-1){
			
			head=search(inputString[index],head);
			if(head!=null && head.isEndCharacter()){
				path.add(head.getKey().toString());
				//if the final element of the input string is found, and is endCharacter return the path
				return path;
			}
		}

		else{ 
			head=search(inputString[index],head);
			if(head!=null){
				path.add(head.getKey().toString());
				return search(inputString, ++index, head, path);

			}

		}

		//return an empty array if string not found
		return new ArrayList<String>();
	}
	
	
	public ArrayList<String> searchNN(VALUE[] inputString){
		
		  return searchNN(inputString,0,root,new ArrayList<String>());
	}
	
	
	public ArrayList<String> searchNN(VALUE[] inputString, int index, Node head, ArrayList<String> path){
		//the only difference from search is that we keep track of the current head's parent so that we can find its NN
		Node parent = head;
		if(index==inputString.length-1){
			
			head=search(inputString[index],head);
			if(head!=null){
				path.add(head.getKey().toString());
				return path;
			}
			else{
				//if the last element of the input string not found 
				if(parent.getLeftChild()!=null)
					path.add(parent.getLeftChild().getKey().toString());
				else if(parent.getRightChild()!=null)
					path.add(parent.getRightChild().getKey().toString());
				else
					path.add(parent.getKey().toString());
				return path;
			}
		}

		else{ 
			head=search(inputString[index],head);
			if(head!=null){
				path.add(head.getKey().toString());
				return searchNN(inputString, ++index, head, path);

			}

		}

		//return an empty array if string not found
		return new ArrayList<String>();
	}

	//search a single element in the (sub)tree starting from head
	private Node search(VALUE key, Node head) {

		if(head.getLeftChild()!=null && head.getLeftChild().getKey()==key)
			return  head.getLeftChild();

		if(head.getRightChild()!=null && head.getRightChild().getKey()==key)
			return  head.getRightChild();
		
		return null;

	}
	public Node getRoot() {
		return root;
	}

	public void printInOrder()
	{
	  printInOrder(root);
	}
	

	
	public void printInOrder(Node node)
	{
		
	 if(node.getLeftChild() != null)
	   printInOrder(node.getLeftChild());
	 
	 if(!node.isRoot())
	   System.out.println(node);
	
	 if(node.getRightChild() != null)
     printInOrder(node.getRightChild());
	}
	
  public void insert(VALUE[] inputArray)
  {
	  Node currentNode = root;
    for(VALUE key : inputArray)
    {
      if(currentNode == null)
        currentNode = new Node(key, null);
      else if (key == S)
      {
        if (currentNode.getLeftChild() != null)
        {
        //if node exists only increment its frequency
          currentNode.getLeftChild().incrementFrequency();
        }
        else
        {
          //if it does not exist create it
          currentNode.setLeftChild(new Node(key, currentNode));
        }
        currentNode = currentNode.getLeftChild();
      } 
      else if (key == B)
      {
        if (currentNode.getRightChild() != null)
        {
          //if node exists only increment its frequency
          currentNode.getRightChild().incrementFrequency();
        }
        else
        {
          //if it does not exist create it
          currentNode.setRightChild(new Node(key, currentNode));
        }
        currentNode = currentNode.getRightChild();
      }
    }
    //set the string's last element endCharacter to true
    currentNode.setEndCharacter(true);
   
  }
	
}