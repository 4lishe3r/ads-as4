import java.util.List;

public interface Search<V> {
    List<V> pathTo(V target);
}