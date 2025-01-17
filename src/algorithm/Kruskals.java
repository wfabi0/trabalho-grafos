package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;
import java.util.stream.Stream;

public class Kruskals {
    private final Graph graph;

    public Kruskals(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<Edge> MST() {
        ArrayList<Edge> mst = new ArrayList<>();
        ArrayList<Edge> edges = graph.getAllEdges();
        edges.sort(Comparator.comparingDouble(edge -> edge.weight));
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();
        for (Integer v : graph.getAdjVertices().keySet()) {
            parent.put(v, v);
            rank.put(v, 0);
        }
        for (Edge edge : edges) {
            int source = findRoot(parent, edge.source);
            int destination = findRoot(parent, edge.destination);
            if (source != destination) {
                mst.add(edge);
                union(parent, rank, source, destination);
                if (mst.size() == graph.getAdjVertices().size() - 1) {
                    break;
                }
            }
        }
        return mst;
    }

    private void union(Map<Integer, Integer> parent, Map<Integer, Integer> rank, int v1, int v2) {
        int root1 = findRoot(parent, v1);
        int root2 = findRoot(parent, v2);
        if (root1 != root2) {
            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }

    private Integer findRoot(Map<Integer, Integer> parent, int vertex) {
        if (parent.get(vertex) != vertex) {
            parent.put(vertex, findRoot(parent, parent.get(vertex)));
        }
        return parent.get(vertex);
    }

    public Double totalWeight(ArrayList<Edge> mst) {
        System.out.println(mst);
        double sum = 0;
        for (Edge edge : mst) {
            sum += edge.weight;
        }
        return sum;
    }
}
