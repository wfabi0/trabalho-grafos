package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Integer, List<Edge>> adjVertices;
    private Map<Integer, int[]> coordinates;
    private boolean directed;

    public Graph() {
        this.adjVertices = new HashMap<>();
        this.coordinates = new HashMap<>();
    }

    public void createEmptyGraph(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            addVertex(i, 0, 0);
        }
    }

    public void addVertex(int vertex, int x, int y) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
        coordinates.put(vertex, new int[]{x, y});
    }

    public String printAdjacencies() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, List<Edge>> entry : adjVertices.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean isAdjacant(int v1, int v2) {
        return adjVertices.containsKey(v1) && adjVertices.get(v1).stream().anyMatch(edge -> edge.destination == v2);
    }

    public void addEdge(int source, int destination, double weight) {
        adjVertices.putIfAbsent(source, new ArrayList<>());
        adjVertices.putIfAbsent(destination, new ArrayList<>());
        adjVertices.get(source).add(new Edge(destination, weight));
        if (!directed) adjVertices.get(destination).add(new Edge(source, weight));
    }

    public void addEdges(int source, List<Edge> edges) {
        for (Edge edge : edges) {
            addEdge(source, edge.destination, edge.weight);
        }
    }

    public void removeEdge(int source, int destination) {
        adjVertices.getOrDefault(source, new ArrayList<>()).removeIf(edge -> edge.destination == destination);
        if (!directed)
            adjVertices.getOrDefault(destination, new ArrayList<>()).removeIf(edge -> edge.destination == source);
    }

    public void setVertexCoordinates(int vertex, int x, int y) {
        coordinates.put(vertex, new int[]{x, y});
    }

    public Edge getFirstAdjacent(int vertex) {
        List<Edge> edges = adjVertices.getOrDefault(vertex, new ArrayList<>());
        return edges.isEmpty() ? null : edges.getFirst();
    }

    public Edge getNextAdjacent(int vertex, int currentDestination) {
        List<Edge> edges = adjVertices.getOrDefault(vertex, new ArrayList<>());
        for (int i = 0; i < edges.size() - 1; i++) {
            if (edges.get(i).destination == currentDestination) return edges.get(i + 1);
        }
        return null;
    }

    public List<Edge> getAdjacents(int vertex) {
        return adjVertices.getOrDefault(vertex, new ArrayList<>());
    }

    public Map<Integer, List<Edge>> getAdjVertices() {
        return adjVertices;
    }

    public Map<Integer,int[]> getCoordinates() {
        return coordinates;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }
}
