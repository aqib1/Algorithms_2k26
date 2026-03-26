package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }
    
}
