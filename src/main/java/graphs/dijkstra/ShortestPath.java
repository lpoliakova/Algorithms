package graphs.dijkstra;

import graphs.dataStructures.DijkstraVertice;
import graphs.dataStructures.Edge;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestPath {

    public static Integer[] findShortest(Map<Integer, List<Edge>> reverseGraph) {
        Integer[] distances = initDistances(reverseGraph.size());
        Boolean[] visited = initVisited(reverseGraph.size());
        PriorityQueue<DijkstraVertice> heap = initHeap(reverseGraph, distances);
        while(!heap.isEmpty()) {
            DijkstraVertice vertice = heap.poll();
            visited[vertice.getNumber()] = true;
            reorderHeap(heap, reverseGraph, vertice, distances, visited);
        }
        return distances;
    }

    private static Integer[] initDistances(Integer size) {
        Integer[] distances = new Integer[size + 1];
        distances[0] = -1;
        distances[1] = 0;
        for (int node = 2; node < distances.length; node++) {
            distances[node] = -1;
        }
        return distances;
    }

    private static Boolean[] initVisited(Integer size){
        Boolean[] visited = new Boolean[size + 1];
        visited[1] = true;
        for (int node = 2; node < visited.length; node++) {
            visited[node] = false;
        }
        return visited;
    }

    private static PriorityQueue<DijkstraVertice> initHeap(Map<Integer, List<Edge>> graph, Integer[] distances) {
        PriorityQueue<DijkstraVertice> heap = new PriorityQueue<>();
        for (Edge edge : graph.get(1)) {
            DijkstraVertice vertice = new DijkstraVertice(edge.getHead(), edge.getLength());
            if (!heap.contains(vertice)) {
                heap.add(vertice);
                distances[vertice.getNumber()] = vertice.getDistance();
            } else if (edge.getLength() < distances[vertice.getNumber()]) {
                heap.remove(vertice);
                heap.add(vertice);
                distances[vertice.getNumber()] = vertice.getDistance();
            }
        }
        return heap;
    }

    private static void reorderHeap(PriorityQueue<DijkstraVertice> heap, Map<Integer, List<Edge>> reverseGraph,
                                    DijkstraVertice extractedVertice, Integer[] distances, Boolean[] visited) {
        for (Edge edge : reverseGraph.get(extractedVertice.getNumber())) {
            DijkstraVertice vertice = new DijkstraVertice(edge.getHead(), extractedVertice.getDistance() + edge.getLength());
            if (!heap.contains(vertice) && !visited[vertice.getNumber()]) {
                heap.add(vertice);
                distances[vertice.getNumber()] = vertice.getDistance();
            } else if (vertice.getDistance() < distances[vertice.getNumber()]) {
                heap.remove(vertice);
                heap.add(vertice);
                distances[vertice.getNumber()] = vertice.getDistance();
            }
        }
    }
}
