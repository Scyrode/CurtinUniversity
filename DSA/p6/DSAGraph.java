import java.util.Iterator;
import java.util.Arrays;
import java.io.*;

public class DSAGraph<E> {

    // PRIVATE INNER CLASS: DSAGraphVertex
    public class DSAGraphVertex<E> {

	// CLASSFIELDS:
	private String label;
	private E value;
	private DSALinkedList<DSAGraphVertex<E>> links;
	private Boolean visited;

	// DEFAULT CONSTRUCTOR:
	public DSAGraphVertex(String inLabel, E inValue) {

	    if (inLabel == null) {
		throw new IllegalArgumentException("ERROR: Imported label must be present");
	    }

	    label = inLabel;
	    value = inValue;
	    links = new DSALinkedList<DSAGraphVertex<E>>();
	    visited = false;

	}


	// ACCESSORS:
	public String getLabel() {
	    return label;
	}

	public E getValue() {
	    return value;
	}

	public DSALinkedList<String> getAdjacent() {

	    Iterator<DSAGraphVertex<E>> iterator = links.iterator();
	    DSAGraphVertex<E> vertex;
	    DSALinkedList<String> vertexList = new DSALinkedList<String>();

	    while (iterator.hasNext()) {
		vertex = iterator.next();
		vertexList.insertLast(vertex.getLabel());
	    }

	    return vertexList;

	}

	public Boolean getVisited() {
	    return visited;
	}


	// MUTATORS:
	public void addEdge(DSAGraphVertex<E> vertex) {
	    links.insertLast(vertex);
	}

	public void setVisited() {
	    visited = true;
	}

	public void clearVisited() {
	    visited = false;
	}

	// toString:
	public String toString() {

	    String str = "label: " + label + ", value (optional): " + value + ", Links: ";
	    DSALinkedList<String> vertexList = getAdjacent();
	    
	    while (!(vertexList.isEmpty())) {
		str += vertexList.removeFirst();
		str += ", ";
	    }

	    str += "isVisited: " + visited.toString();

	    return str;

	}

    }

    // CLASSFIELDS:
    private DSALinkedList<DSAGraphVertex<E>> vertices;
    private boolean isDirected;

    // DEFAULT CONSTRUCTOR:
    public DSAGraph(boolean choice) {
	vertices = new DSALinkedList<DSAGraphVertex<E>>();
	if (choice) {
	    isDirected = true;
	} else {
	    isDirected = false;
	}
    }

    // ALTERNATE CONSTRUCTOR:
    public DSAGraph(String filename, boolean choice) {

	vertices = new DSALinkedList<DSAGraphVertex<E>>();
	if (choice) {
	    isDirected = true;
	} else {
	    isDirected = false;
	}

	constructFromFile(filename);

    }

    //MUTATORS:
    public void addVertex(String inLabel, E inValue) {

	DSAGraphVertex<E> tempVertex = this.getVertex(inLabel);
	
	if (tempVertex == null) {
	    tempVertex = new DSAGraphVertex<E>(inLabel, inValue);
	    vertices.insertLast(tempVertex);
	    // test
	    // System.out.println("created vertex: " + tempVertex.getLabel());
	}
	    
    }

    public void addEdge(DSAGraphVertex<E> vertex1, DSAGraphVertex<E> vertex2) {

	addVertex(vertex1.getLabel(), null);
	addVertex(vertex2.getLabel(), null);
	boolean isAdjacent = this.isAdjacent(vertex1, vertex2);

	// test
	// System.out.println(isAdjacent);

	if ((isDirected) && !(isAdjacent)) {
	    vertex1.addEdge(vertex2);
	    // test
	    // System.out.println("Edge: " + vertex1.getLabel() + " -> " + vertex2.getLabel());
	} else if (!(isDirected) && !(isAdjacent)) {
	    vertex1.addEdge(vertex2);
	    vertex2.addEdge(vertex1);
	    // test
	    // System.out.println("Edge: " + vertex1.getLabel() + " -> " + vertex2.getLabel());
	}
	    
    }

    // ACCESSORS:
    public int getVertexCount() {

	int count = 0;

	Iterator<DSAGraphVertex<E>> iterator = vertices.iterator();

	while (iterator.hasNext()) {
	    iterator.next();
	    count++;
	}

	return count;
	    
    }

    public int getEdgeCount() {

	int count = 0;
	DSAGraphVertex<E> tempVertex;
	DSALinkedList<String> tempVertexLinks;
	Iterator<DSAGraphVertex<E>> iterator = vertices.iterator();

	while (iterator.hasNext()) {

	    tempVertex = iterator.next();
	    tempVertexLinks = tempVertex.getAdjacent();

	    while (!(tempVertexLinks.isEmpty())) {
		tempVertexLinks.removeFirst();
		count++;
	    }

	}

	return count;
	    
    }

    public DSAGraphVertex<E> getVertex(String inLabel) {

	DSAGraphVertex<E> tempVertex = null;
	Iterator<DSAGraphVertex<E>> iterator = vertices.iterator();

	while (iterator.hasNext()) {

	    // test
	    // System.out.print("Looking for: " + inLabel);
	    if (tempVertex == null) {
		tempVertex = iterator.next();
	    } else if(!(tempVertex.getLabel().equals(inLabel))) {
		tempVertex = iterator.next();
	    } else {
		iterator.next();
	    }

	}

	if ((tempVertex == null) || !(tempVertex.getLabel().equals(inLabel))) {
	    tempVertex = null;
	}

	// FOR DEBUGGING:
	/*if (tempVertex == null || !(tempVertex.getLabel().equals(inLabel))) {
	    throw new IllegalArgumentException("ERROR: No vertex with given label. returning null");
	} */

	return tempVertex;
	    
    }

    public DSALinkedList<String> getAdjacent(DSAGraphVertex<E> vertex) {

	DSALinkedList<String> linksTemp;
	String inLabel = vertex.getLabel();
	DSAGraphVertex<E> tempVertex = this.getVertex(inLabel);

	if (tempVertex == null) {
	    throw new IllegalArgumentException("ERROR: No vertex with given label. returning null");
	} else {
	    linksTemp = tempVertex.getAdjacent();
	}

	return linksTemp;

    }

    public boolean isAdjacent(DSAGraphVertex<E> vertex1, DSAGraphVertex<E> vertex2) {

	boolean result = false;
	String label = vertex2.getLabel();
	DSALinkedList<String> linksTemp = vertex1.getAdjacent();
	String temp = "NULL";

	while (!linksTemp.isEmpty()) {
	    if (temp.equals("NULL")) {
		temp = linksTemp.removeFirst();
	    } else if (!(temp.equals(label))) {
		temp = linksTemp.removeFirst();
	    }
	}

	if (temp.equals(label)) {
	    result = true;
	}

	return result;
	
    }

    public void displayList() {

	    Iterator<DSAGraphVertex<E>> iterator = vertices.iterator();
	    DSAGraphVertex<E> tempVertex;
	    DSALinkedList<String> tempLinks;

	    while (iterator.hasNext()) {

		tempVertex = iterator.next();
		tempLinks = tempVertex.getAdjacent();

		System.out.print(tempVertex.getLabel() + " | ");
		
		while (!(tempLinks.isEmpty())) {
		    System.out.print(tempLinks.removeFirst() + " ");
		}

		System.out.println();

	    }
	    
    }

    public void displayMatrix() {

	Iterator<DSAGraphVertex<E>> iterator = vertices.iterator();
	DSAGraphVertex<E> tempVertex;
	int vertexCount = this.getVertexCount();
	String[] vertexList = new String[vertexCount];
	int[] currentConnections = new int[vertexCount];
	DSALinkedList<String> tempLinks = new DSALinkedList<String>();
	String label = "";
	int i = 0;

	while (iterator.hasNext()) {

	    tempVertex = iterator.next();
	    vertexList[i] = tempVertex.getLabel();
	    i++;

	}

	i = 0;

	iterator = null;
	iterator = vertices.iterator();

	System.out.print("  ");
	for (int ii = 0; ii < vertexCount; ii++) {
	    System.out.print(" | " + vertexList[ii]);
	}
	System.out.println(" |");

	while (i < vertexCount) {

	    tempVertex = this.getVertex(vertexList[i]);
	    tempLinks = tempVertex.getAdjacent();

	    System.out.print(" " + tempVertex.getLabel() + " | ");

	    while (!(tempLinks.isEmpty())) {

		label = tempLinks.removeFirst();

		for (int ii = 0; ii < vertexCount; ii++) {
		    if (label.equals(vertexList[ii])) {
			currentConnections[ii] = 1;
		    }
		}

	    }

	    for (int ii = 0; ii < vertexCount; ii++) {
		System.out.print(currentConnections[ii] + " | ");
	    }
	    System.out.println();

	    Arrays.fill(currentConnections, 0);

	    i++;

	}

    }

    // PRIVATE METHODS:
    private void constructFromFile(String filename) {

	try {

	    FileReader in = new FileReader(filename);
	    String delims = " ";
	    String text = "";
	    String[] tokens;
	    String label1;
	    String label2;
	    DSAGraphVertex<E> tempVertex1;
	    DSAGraphVertex<E> tempVertex2;
	    int i = 0;
	    int c;

	    do {

		c = in.read();
		if (c != -1) {
		    text += (char) c;
		}

	    } while(c != -1);

	    text = text.replace("\n", " ");
	    tokens = text.split(delims);

	    while (i < (tokens.length/2)) {

		label1 = tokens[i*2];
		label2 = tokens[i*2 + 1];

		// test
		// System.out.println(label1 + " " + label2);

		this.addVertex(label1, null);
		this.addVertex(label2, null);

		tempVertex1 = this.getVertex(label1);
		tempVertex2 = this.getVertex(label2);

		this.addEdge(tempVertex1, tempVertex2);

		i++;

	    }
		    
	    in.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
		    
    }

}
