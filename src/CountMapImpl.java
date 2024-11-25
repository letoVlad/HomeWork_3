import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private final Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    @Override
    public int getCount(T key) {
        return map.getOrDefault(key, 0);
    }

    @Override
    public int remove(T key) {
        var removeCounter = map.remove(key);
        return removeCounter != null ? removeCounter : 0;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        //map.merge(key, value, (oldValue, newValue) -> ...);
        source.toMap().forEach((key, value) -> map.merge(key, value, Integer::sum)); //
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(map);
    }
}
