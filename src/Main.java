public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 2);

        System.out.println("BFS Path from A to E:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, "A");
        System.out.println(bfs.pathTo("E"));

        System.out.println("\nDijkstra's Path from A to E:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, "A");
        System.out.println(dijkstra.pathTo("E"));
        System.out.println("Distance: " + dijkstra.distTo("E"));
    }
}