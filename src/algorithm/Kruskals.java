package algorithm;

import graph.Graph;

public class Kruskals {
    Graph graph;
    public Kruskals(Graph graph) {
        this.graph = graph;
    }

    private static class Subset {
        int parent, rank;
        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    public void MST(int vertex) {
        graph.getAdjVertices();
    }
}
