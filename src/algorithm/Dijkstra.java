package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class Dijkstra {
    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public List<String> findShortestPath(String startName, String targetName) {
        Integer startVertex = null, targetVertex = null;
        Map<Integer, String> names = graph.getNames();
        for (Map.Entry<Integer, String> entry : names.entrySet()) {
            if (entry.getValue().equals(startName)) {
                startVertex = entry.getKey();
            }
            if (entry.getValue().equals(targetName)) {
                targetVertex = entry.getKey();
            }
        }
        if (startVertex == null) {
            System.out.println("Vértice inicial com nome '" + startName + "' não encontrado.");
            return Collections.singletonList("Vértice inicial não encontrado.");
        }
        if (targetVertex == null) {
            System.out.println("Vértice de destino com nome '" + targetName + "' não encontrado.");
            return Collections.singletonList("Vértice de destino não encontrado.");
        }
        System.out.println("\n-----------------------------\n");
        System.out.println("Iniciando algoritmo de Dijkstra de " + startName + " (" + startVertex + ") para " + targetName + " (" + targetVertex + ")");
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair[1]));
        for (Integer vertex : graph.getAdjVertices().keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);
        System.out.println("Adicionado no heap vértice " + startVertex + " (" + startName + ") com distância 0.0");
        pq.add(new double[]{startVertex, 0.0});
        List<String> result = new ArrayList<>();
        double totalDistance = 0.0;
        while (!pq.isEmpty()) {
            double[] current = pq.poll();
            int currentVertex = (int) current[0];
            double currentDist = current[1];
            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);
            result.add("Vértice " + currentVertex + "  (" + names.get(currentVertex) + ") finalizado - Distância: " + currentDist + "\n");
            if (currentVertex == targetVertex) {
                totalDistance = currentDist;
                System.out.println("Vértice de destino " + targetVertex + " (" + targetName + ") encontrado com distância " + currentDist);
                break;
            }
            for (Edge edge : graph.getAdjVertices().get(currentVertex)) {
                int neighbor = edge.destination;
                double weight = edge.weight;
                double newDist = currentDist + weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new double[]{neighbor, newDist});
                    predecessors.put(neighbor, currentVertex);
                    System.out.println("Vértice " + neighbor + " (" + names.get(neighbor) + ") atualizado com nova distância " + newDist + " via vértice " + currentVertex + " e adicionado ao heap");
                }
            }
        }
        if (totalDistance == 0.0 && startVertex != targetVertex) {
            System.out.println("Vértice de destino " + targetVertex + " (" + targetName + ") não foi alcançado.");
        }
        result.add("Distância total: " + totalDistance);
        System.out.println("Distância total: " + totalDistance);
        System.out.println("\n-----------------------------\n");
        return result;
    }
}
