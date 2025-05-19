import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distTo;
    private Map<V, V> edgeTo;
    private PriorityQueue<VertexDist<V>> pq;
    private final V source;

    private static class VertexDist<V> implements Comparable<VertexDist<V>> {
        V vertex;
        double dist;

        VertexDist(V vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(VertexDist<V> other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        this.source = source;
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.pq = new PriorityQueue<>();

        for (V v : graph.getVertices()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        pq.add(new VertexDist<>(source, 0.0));
        while (!pq.isEmpty()) {
            relax(graph, pq.poll().vertex);
        }
    }

    private void relax(WeightedGraph<V> graph, V v) {
        for (Map.Entry<Vertex<V>, Double> entry : graph.getAdjacentVertices(v).entrySet()) {
            V w = entry.getKey().getData();
            double weight = entry.getValue();

            if (distTo.get(w) > distTo.get(v) + weight) {
                distTo.put(w, distTo.get(v) + weight);
                edgeTo.put(w, v);
                pq.add(new VertexDist<>(w, distTo.get(w)));
            }
        }
    }

    @Override
    public List<V> pathTo(V target) {
        if (!edgeTo.containsKey(target) && !target.equals(source))
            return Collections.emptyList();

        List<V> path = new LinkedList<>();
        for (V x = target; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, source);
        return path;
    }

    public double distTo(V target) {
        return distTo.getOrDefault(target, Double.POSITIVE_INFINITY);
    }
}