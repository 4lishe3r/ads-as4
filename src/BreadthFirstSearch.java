import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo;
    private Set<V> marked;
    private final V source;

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        this.source = source;
        this.edgeTo = new HashMap<>();
        this.marked = new HashSet<>();
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            V v = queue.remove();
            for (Vertex<V> w : graph.getAdjacentVertices(v).keySet()) {
                V wData = w.getData();
                if (!marked.contains(wData)) {
                    edgeTo.put(wData, v);
                    marked.add(wData);
                    queue.add(wData);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V target) {
        if (!marked.contains(target)) return Collections.emptyList();

        List<V> path = new LinkedList<>();
        for (V x = target; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, source);
        return path;
    }
}