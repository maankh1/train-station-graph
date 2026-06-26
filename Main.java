import java.io.*;
import java.util.*;

public class Main {


    public static Graph importGraphFromFile(String filePath) throws IOException {
        Graph graph = new Graph(true, false);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;


            String[] parts = line.split(" -> ");
            String cityName = parts[0].trim();


            Vertex startVertex = graph.getVertexByValue(cityName);
            if (startVertex == null) {
                startVertex = graph.addVertex(cityName);
            }


            String[] neighbors = parts[1].split(", ");
            for (String neighbor : neighbors) {

                String neighborName = neighbor.substring(0, neighbor.indexOf("(")).trim();
                int weight = Integer.parseInt(neighbor.substring(neighbor.indexOf("(") + 1, neighbor.indexOf(")")));


                Vertex endVertex = graph.getVertexByValue(neighborName);
                if (endVertex == null) {
                    endVertex = graph.addVertex(neighborName);
                }

                boolean alreadyConnected = false;
                for (Edge e : startVertex.getEdges()) {
                    if (e.getEndVertex().getData().equals(neighborName)) {
                        alreadyConnected = true;
                        break;
                    }
                }
                if (!alreadyConnected) {
                    graph.addEdge(startVertex, endVertex, weight);
                }
            }
        }

        reader.close();
        return graph;
    }

    public static void exportToFile(Graph graph, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Vertex v : graph.getVertices()) {
            if (v.getEdges().isEmpty()) continue;

            String line = v.getData() + " -> ";

            for (int i = 0; i < v.getEdges().size(); i++) {
                Edge e = v.getEdges().get(i);
                line += e.getEndVertex().getData() + "(" + e.getWeight() + ")";
                if (i != v.getEdges().size() - 1) {
                    line += ", ";
                }
            }

            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    public static void dijkstra(Graph graph, String startName, String endName) {

        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previousVertex = new HashMap<>();
        List<Vertex> unvisited = new ArrayList<>();


        for (Vertex v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
            previousVertex.put(v, null);
            unvisited.add(v);
        }


        Vertex startVertex = graph.getVertexByValue(startName);
        Vertex endVertex = graph.getVertexByValue(endName);

        if (startVertex == null || endVertex == null) {
            System.out.println("One or both cities not found.");
            return;
        }


        distances.put(startVertex, 0);

        while (!unvisited.isEmpty()) {

            Vertex current = null;
            for (Vertex v : unvisited) {
                if (current == null || distances.get(v) < distances.get(current)) {
                    current = v;
                }
            }


            if (current == endVertex) break;


            if (distances.get(current) == Integer.MAX_VALUE) break;

            unvisited.remove(current);


            for (Edge e : current.getEdges()) {
                Vertex neighbor = e.getEndVertex();
                int newDistance = distances.get(current) + e.getWeight();

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousVertex.put(neighbor, current);
                }
            }
        }


        List<String> path = new ArrayList<>();
        Vertex step = endVertex;

        while (step != null) {
            path.add(0, step.getData());
            step = previousVertex.get(step);
        }


        if (distances.get(endVertex) == Integer.MAX_VALUE) {
            System.out.println("No path found between " + startName + " and " + endName);
        } else {
            System.out.println("Shortest path: " + String.join(" -> ", path));
            System.out.println("Total distance: " + distances.get(endVertex) + " km");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String DBPath = "C:\\Users\\Bishr\\Desktop\\college project\\trainStation\\DataBase.txt";
        Graph syria = importGraphFromFile(DBPath);
        syria.print();
        String outputPath = "C:\\Users\\Bishr\\Desktop\\college project\\trainStation\\export Tree.txt";
        exportToFile(syria, outputPath);
        System.out.println("Enter Start City : ");
        String Start = scanner.nextLine();
        System.out.println("Enter destination City : ");
        String End = scanner.nextLine();
        dijkstra(syria, Start, End);

    }
}