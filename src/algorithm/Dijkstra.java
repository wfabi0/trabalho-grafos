package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class Dijkstra {
    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public List<String> findShortestPath(int startVertex, int targetVertex) {
        System.out.println("Iniciando algoritmo de Dijkstra de " + startVertex + " para " + targetVertex);

        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair[1]));

        // Inicializa todas as distâncias como infinito
        for (Integer vertex : graph.getAdjVertices().keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);
        System.out.println("Adicionado no heap vértice " + startVertex);
        pq.add(new int[]{startVertex, 0});

        // Lista para armazenar a distância de cada vértice em relação ao inicial
        List<String> result = new ArrayList<>();
        double totalDistance = 0.0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentVertex = current[0];
            double currentDist = current[1];

            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);

            // Adiciona o vértice e sua distância ao resultado
            result.add("Vértice " + currentVertex + " (" + startVertex + ", " + currentDist + ")");

            if (currentVertex == targetVertex) {
                totalDistance = currentDist;  // Armazena a distância total ao encontrar o destino
                System.out.println("Vértice de destino " + targetVertex + " encontrado com distância " + currentDist);
                break;  // Interrompe a execução assim que o destino é encontrado
            }

            // Percorrendo os vizinhos do vértice atual
            for (Edge edge : graph.getAdjVertices().get(currentVertex)) {
                int neighbor = edge.destination;
                double weight = edge.weight;
                double newDist = currentDist + weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new int[]{neighbor, (int) newDist});
                    System.out.println("Vértice " + neighbor + " atualizado com nova distância " + newDist);
                }
            }
        }

        if (totalDistance == 0.0) {
            System.out.println("Vértice de destino " + targetVertex + " não alcançado.");
        }

        // Adiciona a distância total ao resultado final
        result.add("Distância total: " + totalDistance);
        return result;  // Retorna a lista com os vértices, distâncias e a distância total
    }
}
