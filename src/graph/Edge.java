package graph;

public class Edge {
    public int destination;
    public double weight;
    public Edge(int destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(Destino: " + destination + ", Peso: " + weight + ")";
    }
}
