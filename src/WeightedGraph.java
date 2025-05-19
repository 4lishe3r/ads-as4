import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(dest);

        if (sourceVertex != null && destVertex != null) {
            sourceVertex.addAdjacentVertex(destVertex, weight);
            destVertex.addAdjacentVertex(sourceVertex, weight); // for undirected graph
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public boolean hasVertex(V data) {
        return vertices.containsKey(data);
    }

    public Set<V> getVertices() {
        return vertices.keySet();
    }

    public Map<Vertex<V>, Double> getAdjacentVertices(V data) {
        Vertex<V> vertex = vertices.get(data);
        return vertex != null ? vertex.getAdjacentVertices() : Collections.emptyMap();
    }
}