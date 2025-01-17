package graph;

import algorithm.Kruskals;

import java.util.*;

public class Graph {

    private Map<Integer, List<Edge>> adjVertices;
    private Map<Integer, int[]> coordinates;
    private Map<Integer, String> names;
    private boolean directed;
    private final Kruskals kruskals = new Kruskals(this);

    public Graph() {
        this.adjVertices = new HashMap<>();
        this.coordinates = new HashMap<>();
        this.names = new HashMap<>();
    }

    public void createEmptyGraph(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            addVertex(i, 0, 0, "");
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
        adjVertices.get(source).add(new Edge(source, destination, weight));
        if (!directed) adjVertices.get(destination).add(new Edge(destination, source, weight));
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

    public String DFS(int vertex) {
        Set<Integer> visiteds = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        DFSRec(vertex, visiteds, stringBuilder);
        for (Integer vx : adjVertices.keySet()) {
            if (!visiteds.contains(vx)) {
                stringBuilder.append("\n\n");
                DFSRec(vx, visiteds, stringBuilder);
            }
        }
        return stringBuilder.toString();
    }

    private void DFSRec(int vertex, Set<Integer> visiteds, StringBuilder stringBuilder) {
        visiteds.add(vertex);
        stringBuilder.append(vertex).append((names.get(vertex).length() > 0 ? " (" + names.get(vertex) + ") " : ""));
//        System.out.println("Visitando vértice: " + vertex + (names.get(vertex).length() > 0 ? " (" + names.get(vertex) + ") " : ""));
        for (Edge edge : adjVertices.getOrDefault(vertex, new ArrayList<>())) {
            if (!visiteds.contains(edge.destination)) {
                stringBuilder.append(" -> ");
                DFSRec(edge.destination, visiteds, stringBuilder);
            }
        }
    }

    public String BFS(int vertex) {
        Set<Integer> visiteds = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        BFSRec(vertex, visiteds, queue, stringBuilder);
        for (Integer vx : adjVertices.keySet()) {
            if (!visiteds.contains(vx)) {
                stringBuilder.append("\n\n");
                BFSRec(vx, visiteds, queue, stringBuilder);
            }
        }
        return stringBuilder.toString();
    }

    private void BFSRec(int vertex, Set<Integer> visiteds, Queue<Integer> queue, StringBuilder stringBuilder) {
        queue.add(vertex);
        visiteds.add(vertex);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            stringBuilder.append(curr);
            for (Edge neighbor : adjVertices.getOrDefault(curr, new ArrayList<>())) {
                if (!visiteds.contains(neighbor.destination)) {
                    queue.add(neighbor.destination);
                    visiteds.add(neighbor.destination);
                }
            }
            if(queue.size() > 0) {
                stringBuilder.append(" -> ");
            }
        }
    }

    public List<Edge> getAdjacents(int vertex) {
        return adjVertices.getOrDefault(vertex, new ArrayList<>());
    }

    public Map<Integer, List<Edge>> getAdjVertices() {
        return adjVertices;
    }

    public ArrayList<Edge> getAllEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (Map.Entry<Integer, List<Edge>> entry : adjVertices.entrySet()) {
            int source = entry.getKey();
            for (Edge edge : entry.getValue()) {
                if (!edges.contains(edge)) {
                    edges.add(new Edge(source, edge.destination, edge.weight));
                }
            }
        }
        return edges;
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

    public Kruskals getKruskals() {
        return kruskals;
    }
}
