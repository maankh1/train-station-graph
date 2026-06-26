import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public boolean isWeighted() {
        return this.isWeighted;
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public Vertex addVertex(String data) {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex firstVertex, Vertex secondVertex, int weight) {
        if (!this.isWeighted) {
            weight = -1;
        }
        firstVertex.addEdge(secondVertex, weight);
        if (!this.isDirected) {
            secondVertex.addEdge(firstVertex, weight);
        }
    }

    public Vertex getVertexByValue(String userValue) {
        for (Vertex vertex : this.vertices) {
            if (vertex.getData().equals(userValue)) {
                return vertex;
            }
        }
        return null;
    }

    public void removeEdge(Vertex firstVertex, Vertex secondVertex) {
        firstVertex.removeEdge(secondVertex);
        if (!this.isDirected) {
            secondVertex.removeEdge(firstVertex);
        }
    }

    public void removeVertex(Vertex vertex) {
        this.vertices.remove(vertex);
    }

    public void print() {
        for (Vertex vertex : this.vertices) {
            vertex.print(isWeighted);
        }
    }
}
