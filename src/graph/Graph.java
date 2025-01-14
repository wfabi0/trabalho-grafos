package graph;

import java.util.*;

public class Graph {

    private Map<Integer, List<Edge>> adjVertices;
    private Map<Integer, int[]> coordinates;
    private Map<Integer, String> names;
    private boolean directed;

    public Graph() {
        this.adjVertices = new HashMap<>();
        this.coordinates = new HashMap<>();
        this.names = new HashMap<>();
    }

    public void createEmptyGraph(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            addVertex(i, 0, 0, null);
        }
    }

    public void addVertex(int vertex, int x, int y, String name) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
        coordinates.put(vertex, new int[]{x, y});
        names.put(vertex, name);
    }

    public String printAdjacencies() {
        if (adjVertices.isEmpty()) {
            return "Grafo vazio, crie um antes de utilizar essa função.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Map.Entry<Integer, List<Edge>> entry : adjVertices.entrySet()) {
            String name = names.get(entry.getKey()).toString();
            stringBuilder.append(entry.getKey()).append(name.length() > 0 ? (" (" + name  + ")") : "").append(" -> ").append(entry.getValue()).append("\n");
            i++;
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

    public void setVertexName(int vertex, String name) {
        names.put(vertex, name);
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

    public Set<Integer> DFS(int vertex) {
        Set<Integer> visiteds = new HashSet<>();
        for (int i = 0; i < adjVertices.size(); i++) {
            System.out.println(adjVertices.get(i));
        }
        DFSRec(vertex, visiteds);
        return visiteds;
    }

    private void DFSRec(int vertex, Set<Integer> visiteds) {
        visiteds.add(vertex);
        System.out.println("Visitando vértice: " + vertex + (names.get(vertex) != null ? "(" + names.get(vertex) + ") " : ""));
        for (Edge edge : adjVertices.getOrDefault(vertex, new ArrayList<>())) {
            if (!visiteds.contains(edge.destination)) {
                DFSRec(edge.destination, visiteds);
            }
        }
    }

    public List<Edge> getAdjacents(int vertex) {
        return adjVertices.getOrDefault(vertex, new ArrayList<>());
    }

    public Map<Integer, List<Edge>> getAdjVertices() {
        return adjVertices;
    }

    public Map<Integer, int[]> getCoordinates() {
        return coordinates;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public void clear() {
        adjVertices.clear();
        coordinates.clear();
        names.clear();
    }
}
