package graphs.helpers;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    public int id;
    public int index;
    public T value;
    public List<Vertex<T>> neighbors;

    public Vertex(int id, int index, T value) {
        this.id = id;
        this.index = index;
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    public Vertex(int id, int index) {
        this.id = id;
        this.index = index;
        this.neighbors = new ArrayList<>();
    }
}