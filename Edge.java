public class Edge {
    private Vertex startVertex;
    private Vertex endVertex;
    private int weight;

    public Edge(Vertex start, Vertex end, int inputWeight) {
        this.startVertex = start;
        this.endVertex = end;
        this.weight = inputWeight;
    }

    public Vertex getStartVertex() {
        return this.startVertex;
    }

    public Vertex getEndVertex() {
        return this.endVertex;
    }

    public int getWeight() {
        return this.weight;
    }
}
