package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class Dijkstra {
    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public Map<Integer, Double> sortPath(int startVertex) {
        System.out.println("Iniciando algoritmo de Dijkstra");
        Map<Integer, Double> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair[1]));
        for (Integer vertex : graph.getAdjVertices().keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);
        System.out.println("Adicionado no heap vértice " + startVertex);
        pq.add(new int[]{startVertex, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentVertex = current[0];
            System.out.println("Percorrendo vértice " + currentVertex);
            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);
            System.out.println("Percorrendo adjacentes de " + currentVertex);
            for (Edge edge : graph.getAdjVertices().get(currentVertex)) {
                int neighbor = edge.destination;
                double weight = edge.weight;
                double newDist = distances.get(currentVertex) + weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    System.out.println("Adicionado no heap vértice " + neighbor);
                    pq.add(new int[]{neighbor, (int) newDist});
                    System.out.println("Vértice " + neighbor + " finalizado");
                }
            }
        }
        return distances;
    }
}
