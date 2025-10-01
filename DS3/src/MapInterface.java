
import java.util.ArrayList;

public interface MapInterface<K,V>
{
    public void clear();//

    public boolean containsKey(K key); //
    public boolean containsValue(V value); //

    public DS3_Set<MapEnt<K,V>> entrySet();

    public V get(K o);//

    public boolean isEmpty();//

    public V put(K key, V value);//

    public int size();//

    public DS3_Set<K> keySet();

    public ArrayList<V> values();

    public V remove(K key);
}
