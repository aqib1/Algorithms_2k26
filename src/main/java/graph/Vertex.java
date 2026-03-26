package graph;

import java.util.Objects;

public class Vertex {
    private String label;

    public Vertex(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(label);
    }
}
