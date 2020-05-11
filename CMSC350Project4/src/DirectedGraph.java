/**
 * DirectedGraph.java
 * Brian Yu
 * 5/10/2020
 * This class defines a generic DirectedGraph object that takes constructs a directed graph from inputted parameters.
 * The class also contains a method to perform a depth first traversal to find a specificed vertex and its dependencies.  It also constructs
 * a hashmap to correlate vertex names with integer keys, and a reverse hashmap in order to print the results of the depth first traversal to a String.
 */
import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph<T> {
    private int vertices;
    private Map<T, Integer> vertexIndex;
    private Map<Integer, T> inverseVertexIndex;
    private ArrayList<LinkedList<Integer>> adjacencyList;
    private ArrayList<T> duplicateCheck;
    //constructor initializes the main attributes used by all DirectedGraph objects
    public DirectedGraph() {
        this.vertices = 0;
        this.vertexIndex = new HashMap<>();
        this.adjacencyList = new ArrayList<>();
        this.duplicateCheck = new ArrayList<>();
    }
    //method populates both the vertexIndex hashmap (which links vertex names and keys) and the adjacencyList arraylist (which represents the directed graph)
    public void createGraph(List<T> input) {
        for (int i = 0; i < input.size(); i++) {
            if (!duplicateCheck.contains(input.get(i))) {
                vertexIndex.put(input.get(i), vertices);
                duplicateCheck.add(input.get(i));
                adjacencyList.add(vertices,new LinkedList<>());
                vertices++;
            }
            if(i != 0) {
                int vertex = vertexIndex.get(input.get(0));
                int edge = vertexIndex.get(input.get(i));
                addEdge(vertex, edge);
            }
        }
    }
    //method adds an edge to the directed graph
    public void addEdge(int vertex, int edge) {
        adjacencyList.get(vertex).add(edge);
    }
    //method traverses directed graph for inputted vertex
    public void generateTopologicalOrder(int input, List<Integer> visited, List<Integer> finished, Stack<Integer> resultStack) throws DirectedGraphCycleException {
        if (visited.contains(input)) {
            throw new DirectedGraphCycleException();
        }
        if(finished.contains(input)) {
            return;
        }
        visited.add(input);
        for(int edge: adjacencyList.get(input)) {
            generateTopologicalOrder(edge,visited, finished, resultStack);
        }
        finished.add(input);
        resultStack.push(input);
    }
    //method returns key integer tied to vertex name
    public int toKey(T value) throws InvalidClassNameException {
        try{
            return vertexIndex.get(value);
        } catch (NullPointerException npex) {
            throw new InvalidClassNameException();
        }
    }
    //method populates reverse hashmap in order to print out results of depth first traversal with vertex names as Strings
    public String toString(Stack<Integer> integerStack) {
        this.inverseVertexIndex = vertexIndex.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        StringBuilder resultString = new StringBuilder();
        while(!integerStack.empty()) {
            resultString.append(inverseVertexIndex.get(integerStack.pop())+ " ");
        }
        return resultString.toString();
    }
}
