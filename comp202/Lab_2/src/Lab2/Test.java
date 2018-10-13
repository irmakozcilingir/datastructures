package Lab2;
import java.util.ArrayList;
import Lab2.Node.VALUE;
import static Lab2.Node.VALUE.*;
public class Test {
	
	public static void main(String[] args) {

		VALUE[] firstString={S,S,B,S,B}; 
		VALUE[] secondString={B,S,B,B,B,S}; 
		
		VALUE[] thirdString={B,B,B,S,B};
		VALUE[] fourthString={B,B,B,S,B};
		VALUE[] fifthString={S,S};
		VALUE[] sixthString={S,B,S,B,B,S,B};
		WeirdBinaryTree b = new WeirdBinaryTree();

		System.out.println("Inserting first sequence: S,S,B,S,B");
		b.insert(firstString);
		System.out.println("Inserting second sequence: B,S,B,B,B,S ");
		b.insert(secondString);
		System.out.println("Inserting third sequence: B,B,B,S,B");
		b.insert(thirdString);
		System.out.println("Inserting fourth sequence: B,B,B,S,B ");
		b.insert(fourthString);
		System.out.println("Inserting fifth sequence: S,S ");
		b.insert(fifthString);
		System.out.println("Inserting sixth sequence: S,B,S,B,B,S,B ");
		b.insert(sixthString);
		System.out.println("In Order traversal of tree");
		b.printInOrder();     
		System.out.println();
		VALUE[] seventhString={S,S,S};
		System.out.println(b.search(thirdString) + " is found in the tree");
		System.out.println("S,S,S" + b.search(seventhString)+ " is not found in the tree");
		System.out.println("S,S,S" + b.search(seventhString)+ " is not found in the tree, but the NN is "+ b.searchNN(seventhString));
	}   
}
