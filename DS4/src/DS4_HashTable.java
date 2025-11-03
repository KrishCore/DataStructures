public class DS4_HashTable<K, V> implements DS4_HashTable_Interface<K, V>
{

    int size = 0;

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int tombstones() {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V insert(K key, V value) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }
}
