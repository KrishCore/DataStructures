public interface DS4_HashTable_Interface<K, V> {

    public void clear();
    public int size();
    public int tombstones();
    public boolean contains(K key);
    public V insert(K key, V value);
    public V remove(K key);

}
