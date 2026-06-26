import java.util.ArrayList;
import java.util.Iterator;

public class Vertex {
    private String data;
    private ArrayList<Edge> edges;

    public Vertex(String inputData) {
        this.data = inputData;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Vertex endVertex, int weight) {
        this.edges.add(new Edge(this, endVertex, weight));
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public String getData() {
        return this.data;
    }

    public void removeEdge(Vertex endVertex) {
        Iterator<Edge> iterator = this.edges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getEndVertex().equals(endVertex)) {
                iterator.remove();
            }
        }
    }

    public void print(boolean showWeight) {
        int size = this.edges.size();
        String message = this.data + " --> ";

        if (size == 0) {
            System.out.println(message);
            return;
        }

        for (int i = 0; i < size; i++) {
            message += this.edges.get(i).getEndVertex().getData();
            if (showWeight) {
                message += " (" + this.edges.get(i).getWeight() + ")";
            }
            if (i != size - 1) {
                message += ", ";
            }
        }
        System.out.println(message);
    }

}
