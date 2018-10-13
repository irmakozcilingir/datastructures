
public class StdDoubleLL {
	StdNode head;
	StdNode tail;
	StdNode median;

	public void insert(StdNode std) throws DuplicateIDException {

		/**
		 * Write throwing the exception statement here.
		 * 
		 * First check if there exist a student having a same ID with std
		 * student. If an exception occurs, throw DuplicateIDException. If it
		 * doesn't, continue.
		 * 
		 */

		if (search(std.getID()) != null) {
			throw new DuplicateIDException(std.getID());
		}

		System.out.println("Adding          :  " + std + "\n");
		System.out.println("DB before adding:  " + "\n");
		print();
		System.out.println();

		/**
		 * Write your code below
		 */
		StdNode temp;
		// The list is empty
		if (head == null) {
			head = std;
			median = std;
			tail = std;

		}

		// medianID is less than stdID
		else if (median.getID().compareTo(std.getID()) < 0) {
			temp = tail;
			if ((temp.getID()).compareTo(std.getID()) < 0) {
				insertToEnd(std);
			} else {
				// Finding the right position in the portion between median and tail
				temp = findRightPosition(temp, std);
				insertToMiddle(std, temp);

			}
			if (size(head) % 2 != 0) {
				median = median.next;
			}

		}
		// medianID is greater then stdID

		else {
			temp = median;
			if ((head.getID()).compareTo(std.getID()) > 0) {
				insertFront(std);
			} else {
				// Finding the right position in the portion between median and head
				temp = findRightPosition(temp, std);
				insertToMiddle(std, temp);

			}
			if (size(head) % 2 == 0) {
				median = median.prev;
			}

		}

		System.out.println("DB after  adding:   " + "\n");
		print();
		System.out.println();
		// Comment out if you want to see if the median is evaluated correctly
		// System.out.println(median.getID() + " is median");

	}

	public StdNode search(String ID) {
		/**
		 * Write your code here. Remember you should not scan all the list. Use
		 * the median. Otherwise you risk to get 0 from this part.
		 */

		if (head == null)
			return null;

		else if ((median.getID()).compareTo(ID) < 0) {

			StdNode temp = median;
			while (temp != null) {
				if (temp.getID().equals(ID)) {
					return temp;
				}
				temp = temp.next;
			}

		} else {
			StdNode temp = median;
			while (temp != null) {
				if (temp.getID().equals(ID)) {
					return temp;
				}
				temp = temp.prev;
			}

		}

		return null;

	}

	public void print(String id1, String id2) {

		/***
		 * Write your code below
		 */
		StdNode std1 = search(id1);
		StdNode std2 = search(id2);

		StdNode temp = std1;
		while (temp != std2) {
			System.out.println(temp);
			temp = temp.next;
		}
		System.out.println(temp);

	}

	public void print(String major) {
		/***
		 * Write your code below
		 */
		if (head == null)
			System.out.println("There is no one");
		else {
			StdNode temp = head;
			while (temp != null) {
				if (temp.getMajor().equals(major))
					System.out.println(temp);
				temp = temp.next;
			}
		}
	}

	public void print() {
		/***
		 * Write your code below
		 */
		//The first if clause is to show you which student is located in the middle/median position.
		if (median != null)
			System.out.println(median.getID() + " is the median");
		//----------------------------------------------------------//
		
		
		if (head == null)
			System.out.println("There is no one");
		else {
			StdNode temp = head;
			while (temp != null) {
				System.out.println(temp);
				temp = temp.next;
			}
		}

	}

	public boolean delete(String id) {

		/**
		 * delete the ID from the list. Return true if it is deleted, return
		 * false if ID is not in the list.
		 */
		if (search(id) == null)
			return false;

		else {
			StdNode std1 = search(id);
			if (std1 == head) {
				std1.next.prev = null;
				head = std1.next;
				if (size(head) % 2 != 0) {
					median = median.next;
				}

			} else if (std1 == tail) {
				std1.prev.next = null;
				tail = std1.prev;
				if (size(head) % 2 == 0) {
					median = median.prev;
				}

			} else if (std1 == median) {
				StdNode leftStdNode = std1.prev;
				StdNode rightStdNode = std1.next;
				leftStdNode.next = rightStdNode;
				rightStdNode.prev = leftStdNode;
				if (size(head) % 2 != 0) {
					median = rightStdNode;
				} else {
					median = leftStdNode;
				}

			}

			else {

				StdNode leftStdNode = std1.prev;
				StdNode rightStdNode = std1.next;

				leftStdNode.next = rightStdNode;
				rightStdNode.prev = leftStdNode;
				if (std1.getID().compareTo(median.getID()) < 0) {
					if (size(head) % 2 != 0) {
						median = median.next;
					}
				} else {
					if (size(head) % 2 == 0)
						median = median.prev;
				}

			}
			// if you want to check if the new median is correctly changed
			// System.out.println("New median "+median);
			return true;
		}

	}

	// Helper methods for the simplicity of understanding the implementation
	// This is simply just insertion of a DLL with a little bit change

	private void insertToMiddle(StdNode std, StdNode temp) {
		std.next = temp;
		temp.prev.next = std;
		std.prev = temp.prev;
		temp.prev = std;
	}

	private void insertFront(StdNode std) {
		StdNode temp = head;
		std.next = temp;
		temp.prev = std;
		head = std;

	}

	private int size(StdNode currentNode) {
		StdNode temp = currentNode;
		int size = 0;
		while (temp != null) {
			temp = temp.next;
			size++;

		}
		return size;
	}

	private void insertToEnd(StdNode std) {
		StdNode temp = tail;
		std.prev = temp;
		temp.next = std;
		tail = std;

	}

	private StdNode findRightPosition(StdNode temp, StdNode std) {
		// TODO Auto-generated method stub
		while (temp.prev != null && temp.prev.getID().compareTo(std.getID()) > 0) {
			temp = temp.prev;
		}

		return temp;
	}

}
