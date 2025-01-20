package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class Prim {
    private final Graph graph;

    public Prim(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<Edge> MST(int startVertex) {
        System.out.println("Iniciando algoritmo de Prim do vertex " + startVertex);
        ArrayList<Edge> mst = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        System.out.println("Iniciando heap do vertex " + startVertex);
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.weight));
        visited.add(startVertex);
        System.out.println("Adicionando todos os adjacentes de " + startVertex + " no heap");
        minHeap.addAll(graph.getAdjVertices().get(startVertex));
        while (!minHeap.isEmpty() && mst.size() < graph.getAdjVertices().size() - 1) {
            Edge edge = minHeap.poll();
            if (visited.contains(edge.destination)) {
                continue;
            }
            System.out.println("Aresta " + edge.source + " - " + edge.destination + " finalizada");
            mst.add(edge);
            visited.add(edge.destination);
            for (Edge adjacentEdge : graph.getAdjVertices().get(edge.destination)) {
                if (!visited.contains(adjacentEdge.destination)) {
                    System.out.println("Adicionado no heap " + adjacentEdge.source + " - " + adjacentEdge.destination);
                    minHeap.add(adjacentEdge);
                }
            }

        }
        return mst;
    }

    public Double totalWeight(ArrayList<Edge> mst) {
        double sum = 0;
        for (Edge edge : mst) {
            sum += edge.weight;
        }
        return sum;
    }
}
