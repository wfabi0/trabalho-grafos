package graph;

import algorithm.Dijkstra;
import algorithm.Kruskals;
import algorithm.Prim;

import java.util.*;

public class Graph {

    private Map<Integer, List<Edge>> adjVertices;
    private Map<Integer, int[]> coordinates;
    private Map<Integer, String> names;
    private boolean directed;
    private final Kruskals kruskals = new Kruskals(this);
    private final Prim prim = new Prim(this);
    private final Dijkstra dijkstra = new Dijkstra(this);

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
        System.out.println("\n-----------------------------\n");
        Set<Integer> visiteds = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        int[] timeCounter = {1};
        DFSRec(vertex, visiteds, stringBuilder, timeCounter);
        for (Integer vx : adjVertices.keySet()) {
            if (!visiteds.contains(vx)) {
                stringBuilder.append("\n\n");
                DFSRec(vx, visiteds, stringBuilder, timeCounter);
            }
        }
        System.out.println("\n-----------------------------\n");
        return stringBuilder.toString();
    }

    private void DFSRec(int vertex, Set<Integer> visiteds, StringBuilder stringBuilder, int[] timeCounter) {
        int startTime = timeCounter[0]++;
        visiteds.add(vertex);
        stringBuilder.append(vertex);
        System.out.println("Descoberto vértice: " + vertex + " no tempo " + startTime + (names.get(vertex).length() > 0 ? " (" + names.get(vertex) + ")" : ""));
        for (Edge edge : adjVertices.getOrDefault(vertex, new ArrayList<>())) {
            if (!visiteds.contains(edge.destination)) {
                stringBuilder.append(" -> ");
                DFSRec(edge.destination, visiteds, stringBuilder, timeCounter);
            }
        }
        int finishTime = timeCounter[0]++;
        System.out.println("Finalizado vértice: " + vertex + (names.get(vertex).length() > 0 ? " (" + names.get(vertex) + ") " : "") + "no tempo " + finishTime);
    }

    public String BFS(int vertex) {
        System.out.println("\n-----------------------------\n");
        Set<Integer> visiteds = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distanceMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Iniciando BFS a partir do vértice " + vertex);
        distanceMap.put(vertex, 0);
        BFSRec(vertex, visiteds, queue, stringBuilder, distanceMap);
        for (Integer vx : adjVertices.keySet()) {
            if (!visiteds.contains(vx)) {
                System.out.println("Vértice " + vx + " não visitado, iniciando BFS para novo componente conexo.");
                stringBuilder.append("\n\n");
                BFSRec(vx, visiteds, queue, stringBuilder, distanceMap);
            }
        }
        for (Map.Entry<Integer, Integer> entry : distanceMap.entrySet()) {
            stringBuilder.append("\nDistância do vértice " + entry.getKey() + " ao vértice inicial: " + entry.getValue());
        }
        System.out.println("BFS finalizada.");
        System.out.println("\n-----------------------------\n");
        return stringBuilder.toString();
    }

    private void BFSRec(int vertex, Set<Integer> visiteds, Queue<Integer> queue, StringBuilder stringBuilder, Map<Integer, Integer> distanceMap) {
        System.out.println("Adicionando na fila vértice: " + vertex);
        queue.add(vertex);
        visiteds.add(vertex);
        System.out.println("Descoberto vértice: " + vertex + " (distância: " + distanceMap.get(vertex) + ")");
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            stringBuilder.append(curr);
            System.out.println("Processando vértice: " + curr);
            for (Edge neighbor : adjVertices.getOrDefault(curr, new ArrayList<>())) {
                if (!visiteds.contains(neighbor.destination)) {
                    queue.add(neighbor.destination);
                    visiteds.add(neighbor.destination);
                    distanceMap.put(neighbor.destination, distanceMap.get(curr) + 1);
                    System.out.println("Descoberto vizinho: " + neighbor.destination);
                }
            }
            if(!queue.isEmpty()) {
                stringBuilder.append(" -> ");
            }
            System.out.println("Finalizado processamento do vértice: " + curr);
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

    public Map<Integer, String> getNames() {
        return new HashMap<>(this.names);
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

    public Prim getPrim() {
        return prim;
    }

    public Dijkstra getDijkstra() {
        return dijkstra;
    }
}
