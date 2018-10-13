package lab3;

// Modified version of the code here https://rosettacode.org/wiki/AVL_tree#Java
public class AvlTree<Key extends Comparable<Key>, Value> {

  protected Node root;

  class Node {
    Key key;
    Value value;
    int balance;
    int height;
    Node left, right, parent;

    Node(Key k, Value v, Node p) {
      key = k;
      value = v;
      parent = p;
    }

    Key getKey() {
      return key;
    }

    Value getValue() {
      return value;
    }

    public String toString() {
      return "(" + key.toString() + ", " + value.toString() + ")";
    }
  }

  // Insert the given key value pair to the tree.
  public void insert(Key key, Value value) {
    if (root == null)
      root = new Node(key, value, null);
    else {
      Node n = root;
      Node parent;
      while (true) {
        if (n.key == key) {
          n.value = value;
          return;
        }

        parent = n;

        boolean goLeft = n.key.compareTo(key) > 0;
        n = goLeft ? n.left : n.right;

        if (n == null) {
          if (goLeft) {
            parent.left = new Node(key, value, parent);
          } else {
            parent.right = new Node(key, value, parent);
          }
          rebalance(parent);
          break;
        }
      }
    }
    return;
  }

  // Delete the node with given key form the tree.
  private Node delete(Node root, Key delKey) {
    if (root == null)
      return null;
    Node node = root;
    Node child = root;

    while (child != null) {
      node = child;
      child = node.key.compareTo(delKey) <= 0 ? node.right : node.left;
      if (delKey.equals(node.key)) {
        Node tmp = node;
        delete(node);
        return tmp;
      }
    }
    return null;
  }

  // Delete the given node from the tree.
  private void delete(Node node) {
    if (node.left == null && node.right == null) {
      if (node.parent == null)
        root = null;
      else {
        Node parent = node.parent;
        if (parent.left == node) {
          parent.left = null;
        } else
          parent.right = null;
        rebalance(parent);
      }
      return;
    }
    if (node.left != null) {
      Node child = node.left;
      while (child.right != null)
        child = child.right;
      node.key = child.key;
      node.value = child.value;
      delete(child);
    } else {
      Node child = node.right;
      while (child.left != null)
        child = child.left;
      node.key = child.key;
      node.value = child.value;
      delete(child);
    }
  }

  // Delete the node with given key form the tree.
  public void delete(Key delKey) {
    delete(root, delKey);
  }

  // Rebalance the tree after insertion or deletion.
  @SuppressWarnings("unchecked")
  private void rebalance(Node n) {
    setBalance(n);

    if (n.balance == -2) {
      if (height(n.left.left) >= height(n.left.right))
        n = rotateRight(n);
      else
        n = rotateLeftThenRight(n);

    } else if (n.balance == 2) {
      if (height(n.right.right) >= height(n.right.left))
        n = rotateLeft(n);
      else
        n = rotateRightThenLeft(n);
    }

    if (n.parent != null) {
      rebalance(n.parent);
    } else {
      root = n;
    }
  }

  // Perform left rotation to maintain balance in the tree.
  @SuppressWarnings("unchecked")
  private Node rotateLeft(Node a) {

    Node b = a.right;
    b.parent = a.parent;

    a.right = b.left;

    if (a.right != null)
      a.right.parent = a;

    b.left = a;
    a.parent = b;

    if (b.parent != null) {
      if (b.parent.right == a) {
        b.parent.right = b;
      } else {
        b.parent.left = b;
      }
    }

    setBalance(a, b);

    return b;
  }

  // Perform right rotation to maintain balance in the tree.
  @SuppressWarnings("unchecked")
  private Node rotateRight(Node a) {

    Node b = a.left;
    b.parent = a.parent;

    a.left = b.right;

    if (a.left != null)
      a.left.parent = a;

    b.right = a;
    a.parent = b;

    if (b.parent != null) {
      if (b.parent.right == a) {
        b.parent.right = b;
      } else {
        b.parent.left = b;
      }
    }

    setBalance(a, b);

    return b;
  }

  // Perform double rotation ( Left then right ) to maintain balance in the
  // tree.
  private Node rotateLeftThenRight(Node n) {
    n.left = rotateLeft(n.left);
    return rotateRight(n);
  }

  // Perform double rotation ( Right then left ) to maintain balance in the
  // tree.
  private Node rotateRightThenLeft(Node n) {
    n.right = rotateRight(n.right);
    return rotateLeft(n);
  }

  // Get height of given node.
  private int height(Node n) {
    if (n == null)
      return -1;
    return n.height;
  }

  // Recalculate height and balance factors of given nodes after rotation
  // operations.
  @SuppressWarnings("unchecked")
  private void setBalance(Node... nodes) {
    for (Node n : nodes) {
      reheight(n);
      n.balance = height(n.right) - height(n.left);
    }
  }

  // Find a value in the tree from its key.
  public Value get(Key key) {
    Node searchNode = root;
    while (searchNode != null) {
      if (key.compareTo(searchNode.key) < 0)
        searchNode = searchNode.left;
      else if (key.compareTo(searchNode.key) > 0)
        searchNode = searchNode.right;
      else
        return searchNode.value;
    }
    return null;
  }

  // Display key, value, balance and height of all nodes in the tree, using in
  // order traversal
  public void printTree() {
    printTree(root);
  }

  // Display key, value, balance and height of given node.
  private void printTree(Node n) {
    if (n != null) {
      printTree(n.left);
      System.out.printf("(%s %s %s %s), ", n.key, n.value, n.balance, n.height + 1);
      printTree(n.right);
    }
  }

  // Calculate height of a given node.
  private void reheight(Node node) {
    if (node != null) {
      node.height = 1 + Math.max(height(node.left), height(node.right));
    }
  }
}