package graph;

import java.util.ArrayList;

/**
 * This class implements general operations on a graph as specified by
 * UndirectedGraphADT. It implements a graph where data is contained in Vertex
 * class instances. Edges between verticies are unweighted and undirected. A
 * graph coloring algorithm determines the chromatic number. Colors are
 * represented by integers. The maximum number of vertices and colors must be
 * specified when the graph is instantiated. You may implement the graph in the
 * manner you choose. See instructions and course material for background.
 */

  public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
    // private class variables here.
    // TODO: Declare class variables here.
    private int MAX_VERTICES;
    private int MAX_COLORS;
    private ArrayList<Vertex<T>> vertices;
    private int numEdges;
    private boolean edge[][];

  /**
   * Initialize all class variables and data structures.
   */
  public UndirectedUnweightedGraph(int maxVertices, int maxColors) {
    // TODO: Implement the rest of this method.
    MAX_VERTICES = maxVertices;
    MAX_COLORS = maxColors;
    vertices = new ArrayList<Vertex<T>>();
    numEdges = 0;
    edge = new boolean[MAX_VERTICES][MAX_VERTICES];
  }

  /**
   * Add a vertex containing this data to the graph. Throws Exception if trying to
   * add more than the max number of vertices.
   */
  public void addVertex(T data) throws Exception {
    // TODO: Implement this method.
    if (getNumVertices() == MAX_VERTICES) {
      throw new Exception("Exception thrown: cannot add more vertices.");
    }
    vertices.add(new Vertex<T>(data));
  }

  /**
   * Return true if the graph contains a vertex with this data, false otherwise.
   */
  public boolean hasVertex(T data) {
    // TODO: Implement this method.
    int i = 0;
    while (i < getNumVertices()) {
      Vertex<T> temp = vertices.get(i);
      if (temp.getData().equals(data)) {
        return true;
      }
      i++;
    }
    return false;
  }

  /**
   * Add an edge between the vertices that contain these data. Throws Exception if
   * one or both vertices do not exist.
   */
  public void addEdge(T data1, T data2) throws Exception {
    // TODO: Implement this method.
    int index1 = 0;
    int index2 = 0;
    int i = 0;
    int j = 0;
    if (!hasVertex(data1) || !hasVertex(data2)) {
      throw new Exception("Exception thrown: one or both vertices do not exist.");
    }
    while (i < getNumVertices()) {
      Vertex<T> temp = vertices.get(i);
      if (temp.getData().equals(data1)) {
        index1 = i;
      }
      i++;
    }
    while (j < getNumVertices()) {
      Vertex<T> temp = vertices.get(j);
      if (temp.getData().equals(data2)) {
        index2 = j;
      }
      j++;
    }
    edge[index1][index2] = true;
    edge[index2][index1] = true;
    numEdges++;
  }

  /**
   * Get an ArrayList of the data contained in all vertices adjacent to the vertex
   * that contains the data passed in. Returns an ArrayList of zero length if no
   * adjacencies exist in the graph. Throws Exception if a vertex containing the
   * data passed in does not exist.
   */
  public ArrayList<T> getAdjacentData(T data) throws Exception {
    // TODO: Implement this method.
    if (!hasVertex(data)) {
      throw new Exception("Exception thrown: vertex containing data passed in does not exist.");
    }
    ArrayList<T> adj = new ArrayList<T>();
    int index = 0;
    int i = 0;
    int j= 0;
    while (i < getNumVertices()) {
      Vertex<T> temp = vertices.get(i);
      if (temp.getData().equals(data)) {
        index = i;
      }
      i++;
    }
    while (j < getNumVertices()) {
      if (edge[index][j]) {
        Vertex<T> temp = vertices.get(j);
        adj.add(temp.getData());
      }
      j++;
    }
    return adj;
  }

  /**
   * Returns the total number of vertices in the graph.
   */
  public int getNumVertices() {
    // TODO: Implement this method.
    return vertices.size();
  }

  /**
   * Returns the total number of edges in the graph.
   */
  public int getNumEdges() {
    // TODO: Implement this method.
    return numEdges;
  }

  /**
    * Returns the minimum number of colors required for this graph as 
    * determined by a graph coloring algorithm.
    * Throws an Exception if more than the maximum number of colors are required
    * to color this graph.
   */   
   
   
   public int getChromaticNumber() throws Exception{
    // TODO: Implement this method.
    int highestColorUsed = -1;
    int colorNeeded = -1;
    int i = 0;
    while (i < getNumVertices()) {
      Vertex<T> temp = vertices.get(i);
      if (temp.getColor() == -1) {
        colorNeeded = getColorNeeded(temp);
        temp.setColor(colorNeeded);
        if (colorNeeded > highestColorUsed) {
          highestColorUsed = colorNeeded;
        }
      }
      i++;
    }
    if (highestColorUsed > MAX_COLORS) {
      throw new Exception("Exception thrown: More than the maximum number of colors are required to color this graph.");
    }
    return highestColorUsed;
  }

   private int getColorNeeded(Vertex<T> temp) {
     int colorNeeded = -1;
     Boolean[] adjColors = new Boolean[MAX_COLORS];
     ArrayList<Vertex<T>> adjColorsList = getAdjColorsList(temp);
     int i = 0;
     while (i < adjColorsList.size()) {
       if (adjColorsList.get(i).getColor() != -1) {
         adjColors[i] = true;
       }
       i++;
     }
     colorNeeded = i;
    return colorNeeded;
   }

   private ArrayList<Vertex<T>> getAdjColorsList(Vertex<T> colorsVertex) {
    ArrayList<Vertex<T>> adjVert = new ArrayList<Vertex<T>>();
    int index = -1;
    int i = 0;
    int j = 0;
    while (i < getNumVertices()) {
      Vertex<T> temp = vertices.get(i);
      if (temp.equals(colorsVertex)) {
        index = i;
      }
      i++;
    }
    while (j < getNumVertices()) {
      Vertex<T> temp = vertices.get(j);
      if (edge[index][j]) {
        adjVert.add(temp);
      }
      j++;
    }
    return adjVert;
   }
}