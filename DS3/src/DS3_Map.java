import java.util.ArrayList;

public class DS3_Map<K, V> implements MapInterface<K, V> {
    ArrayList<MapEnt> array = new ArrayList<>();

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getValue().equals(value))
                return true;
        }
        return false;
    }

    @Override
    public DS3_Set<MapEnt<K, V>> entrySet() {
        DS3_Set set1 = new DS3_Set();
        for (int i = 0; i < array.size(); i++) {
            set1.add(array.get(i));
        }
        return set1;
    }

    @Override
    public V get(K o) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(o))
                return (V) array.get(i).getValue();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public V put(K key, V value) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(key)) {
                V v = (V) array.get(i).getValue();
                array.get(i).setValue(value);
                return v;
            }
        }
        array.add(new MapEnt(key, value));
        return null;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public DS3_Set<K> keySet() {
        DS3_Set set = new DS3_Set();
        for (int i = 0; i < array.size(); i++) {
            set.add(array.get(i).getKey());
        }
        return set;
    }

    @Override
    public ArrayList<V> values() {
        ArrayList<V> arr = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            arr.add((V) array.get(i).getValue());
        }
        return arr;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(key)) {
                V v = (V) array.get(i).getValue();
                array.remove(i);
                return v;
            }
        }
        return null;
    }
}
