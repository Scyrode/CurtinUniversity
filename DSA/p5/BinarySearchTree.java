import java.util.*;
import java.io.Serializable;

public class BinarySearchTree<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private class TreeNode<E> implements Serializable { // Put inside Tree class

	private static final long serialVersionUID = 2L;
	private String m_key;
	private E m_value;
	private TreeNode<E> m_leftChild;
	private TreeNode<E> m_rightChild;

	public TreeNode(String inKey, E inVal) {

	    if (inKey == null)
	       throw new IllegalArgumentException("Key cannot be null");
	    m_key = inKey; //consider “owning” the key
	    m_value = inVal;
	    m_rightChild = null;
	    m_leftChild = null;
	       
	}

	public String getKey() { return m_key;  }
	public E getValue() { return m_value;  }
	public TreeNode<E> getLeft() { return m_leftChild;  }
	public void setLeft(TreeNode<E> newLeft) { m_leftChild = newLeft;  }
	public TreeNode<E> getRight() { return m_rightChild;  }
	public void setRight(TreeNode<E> newRight) { m_rightChild = newRight;  }
    }

    private TreeNode<E> m_root;
    public BinarySearchTree() {
       m_root = null; // Start with an empty tree
	
    }
    //wrapper methods, will call private recursive implementations
    public E find(String key) { return findRecursive(key, m_root); }

    private E findRecursive(String key, TreeNode<E> currNode)
    {
	 E val = null;
	 
	 if (currNode == null)
	       throw new NoSuchElementException("Key " + key + " not found"); // Base case: not found
	 else if (key.equals(currNode.getKey()))
	     val = currNode.getValue(); // Base case: found
	 else if (key.compareTo(currNode.getKey()) < 0) {
	     val = findRecursive(key, currNode.getLeft()); // Go left (recursive)
	 }
	 else {
	     val = findRecursive(key, currNode.getRight()); // Go right (recursive)
	 }
	 
	 return val;

    }

    public void insert(String key, E value) { m_root = insertRec(key, value, this.m_root); }

    private TreeNode<E> insertRec(String key, E value, TreeNode<E> curr) {

	TreeNode<E> updateNode = curr;

	if (curr == null) {
	    TreeNode<E> newNode = new TreeNode<E>(key, value);
	    updateNode = newNode;
	} else if (key.equals(curr.getKey())) {
	    throw new IllegalArgumentException("Value already exists in tree\n");
	} else if ((key.compareTo(curr.getKey())) < 0) {
	    curr.setLeft(insertRec(key, value, curr.getLeft()));
	} else {
	    curr.setRight(insertRec(key, value, curr.getRight()));
	}

	return updateNode;

    }

    public void delete(String key) { deleteRec(key, this.m_root); }

    private TreeNode<E> deleteRec(String key, TreeNode<E> curr) {

	TreeNode<E> updateNode = curr;

	if (curr == null) {
	    throw new IllegalArgumentException("value not in tree\n");
	} else if (key.equals(curr.getKey())) {
	    updateNode = deleteNode(key, curr);
	} else if ((key.compareTo(curr.getKey())) < 0) {
	    curr.setLeft(deleteRec(key, curr.getLeft()));
	} else {
	    curr.setRight(deleteRec(key, curr.getRight()));
	}

	return updateNode;

    }

    private TreeNode<E> deleteNode(String key, TreeNode<E> delNode) {

	TreeNode<E> updateNode = null;

	if ((delNode.getLeft() == null) && (delNode.getRight() == null))
	{
	    updateNode = null;
	} else if ((delNode.getLeft() != null) && (delNode.getRight() == null)) {
	    updateNode = delNode.getLeft();
	} else if ((delNode.getLeft() == null) && (delNode.getRight() != null)) { 
	    updateNode = delNode.getRight();
	} else {

	    updateNode = promoteSuccessor(delNode.getRight());

	    if (updateNode != delNode.getRight()) {
		updateNode.setRight(delNode.getRight());
	    }

	    updateNode.setLeft(delNode.getLeft());

	}

	return updateNode;

    }

    private TreeNode<E> promoteSuccessor(TreeNode<E> curr) {

	TreeNode<E> successor = curr;

	if (curr.getLeft() != null) {

	    successor = promoteSuccessor(curr.getLeft());
	    
	    if (successor == curr.getLeft()) {
		curr.setLeft(successor.getRight());
	    }

	}

	return successor;

    }

    public int height() { return heightRec(this.m_root); }

    private int heightRec (TreeNode<E> currNode) {

	int htSoFar, iLeftHt, iRightHt;

	if (currNode == null) {
	    htSoFar = -1;
	} else {

	    iLeftHt = heightRec(currNode.getLeft());
	    iRightHt = heightRec(currNode.getRight());

	    // Get highest of left vs right branches
	    if (iLeftHt > iRightHt) {
		htSoFar = iLeftHt + 1;
	    } else {
		htSoFar = iRightHt + 1;
	    }

	}

	return htSoFar;

    }
    
    public void inOrderTrav(DSAQueue<String> queue) { inOrderRec(this.m_root, queue); }
    
    private void inOrderRec(TreeNode<E> curr, DSAQueue<String> queue) {

	if (curr != null) {

	    inOrderRec(curr.getLeft(), queue);

	    queue.enqueue(curr.getKey());
	    queue.enqueue(curr.getValue().toString());

	    inOrderRec(curr.getRight(), queue);

	}

    }

    public void preOrderTrav(DSAQueue<String> queue) { preOrderRec(this.m_root, queue); }
	
    private void preOrderRec(TreeNode<E> curr, DSAQueue<String> queue) {

	if (curr != null) {

	    queue.enqueue(curr.getKey());
	    queue.enqueue(curr.getValue().toString());

	    preOrderRec(curr.getLeft(), queue);

	    preOrderRec(curr.getRight(), queue);

	}

    }

    public void postOrderTrav(DSAQueue<String> queue) { postOrderRec(this.m_root, queue); }
	
    private void postOrderRec(TreeNode<E> curr, DSAQueue<String> queue) {

	if (curr != null) {

	    postOrderRec(curr.getLeft(), queue);

	    postOrderRec(curr.getRight(), queue);

	    queue.enqueue(curr.getKey());
	    queue.enqueue(curr.getValue().toString());

	}

    }

	
} 
