package graph;

public class Edge {
    public int source;
    public int destination;
    public double weight;
    public Edge(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(Destino: " + destination + ", Peso: " + weight + ")";
    }

    public String toKruskalString() {
        return "(Source: " + source + ", Destino: " + destination + ", Peso: " + weight + ")";
    }
}
