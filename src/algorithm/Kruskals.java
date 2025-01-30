package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class Kruskals {
    private final Graph graph;

    public Kruskals(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<Edge> MST() { // MOVIMENTO SEM TERRA / Arvore Geradora Minima
        System.out.println("Iniciando algoritmo de Kruskals");
        ArrayList<Edge> mst = new ArrayList<>();
        ArrayList<Edge> edges = graph.getAllEdges();
        System.out.println("Ordenando vértices por peso");
        edges.sort(Comparator.comparingDouble(edge -> edge.weight));
        System.out.println("Arestas ordenadas: ");
        for (Edge edge : edges) {
            System.out.println("Aresta: " + edge.source + " - " + edge.destination + " | Peso: " + edge.weight);
        }
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();
        for (Integer v : graph.getAdjVertices().keySet()) {
            parent.put(v, v);
            rank.put(v, 0);
            System.out.println("Inicializando vértice " + v + " -> Pai: " + v + ", Rank: 0");
        }
        for (Edge edge : edges) {
            int source = findRoot(parent, edge.source);
            int destination = findRoot(parent, edge.destination);
            System.out.println("Verificando aresta " + edge.source + " - " + edge.destination);
            System.out.println("Raiz de " + edge.source + ": " + source + ", Raiz de " + edge.destination + ": " + destination);
            if (source != destination) {
                mst.add(edge);
                System.out.println("Aresta " + edge.source + " - " + edge.destination + " adicionada ao MST");
                union(parent, rank, source, destination);
                if (mst.size() == graph.getAdjVertices().size() - 1) {
                    System.out.println("Árvore geradora mínima formada, número de arestas: " + mst.size());
                    break;
                }
            } else {
                System.out.println("Aresta " + edge.source + " - " + edge.destination + " descartada (ciclo detectado)");
            }
        }
        return mst;
    }

    private void union(Map<Integer, Integer> parent, Map<Integer, Integer> rank, int v1, int v2) {
        System.out.println("Unindo conjuntos " + v1 + " e " + v2);
        int root1 = findRoot(parent, v1);
        int root2 = findRoot(parent, v2);
        System.out.println("Comparando conjuntos " + root1 + " e " + root2);
        if (root1 != root2) {
            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
                System.out.println("Conjunto " + root2 + " agora tem " + root1 + " como pai");
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
                System.out.println("Conjunto " + root1 + " agora tem " + root2 + " como pai");
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
                System.out.println("Conjuntos têm o mesmo rank. " + root2 + " agora tem " + root1 + " como pai, rank de " + root1 + " incrementado para " + rank.get(root1));
            }
        } else {
            System.out.println("Os vértices " + v1 + " e " + v2 + " já estão no mesmo conjunto");
        }
    }

    private Integer findRoot(Map<Integer, Integer> parent, int vertex) {
        System.out.println("Procurando raiz de " + vertex);
        if (parent.get(vertex) != vertex) {
            parent.put(vertex, findRoot(parent, parent.get(vertex)));
            System.out.println("Raiz de " + vertex + " atualizada para " + parent.get(vertex));
        }
        return parent.get(vertex);
    }

    public Double totalWeight(ArrayList<Edge> mst) {
        double sum = 0;
        System.out.println("Calculando peso total da MST");
        for (Edge edge : mst) {
            sum += edge.weight;
            System.out.println("Aresta " + edge.source + " - " + edge.destination + " | Peso: " + edge.weight);
        }
        System.out.println("Peso total da MST: " + sum);
        return sum;
    }
}
