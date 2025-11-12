import java.util.ArrayList;
import java.util.Iterator;

public class DS4_HashTable<K, V> implements DS4_HashTable_Interface<K, V>
{
    int bucketCapacity;
    int loadFactor;
    int size;
    int tombstones;
    int tableSize;
    ArrayList< ArrayList< DS4_Entry<K, V> > > table;

    public DS4_HashTable(int bucketCapacity,int loadFactor, int tableSize) {
        this.bucketCapacity = bucketCapacity;
        this.loadFactor = loadFactor;
        this.tableSize = tableSize;
        size = 0;
        tombstones = 0;

        table = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            table.add(new ArrayList<>());
        }
    }

    @Override
    public void clear() {
        table.clear();
        for (int i = 0; i < tableSize; i++) {
            table.add(new ArrayList<>());
        }
        size = 0;
        tombstones = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int tableSize() {
        return tableSize;
    }

    @Override
    public int tombstones() {
        return tombstones;
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        for (int i = 0; i < tableSize; i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(index);
            boolean hasNull = false;
            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> temp = bucket.get(j);
                if (temp != null && temp.key.equals(key))
                    return true;
                if (temp == null)
                    hasNull = true;
            }
            if (bucket.size() < bucketCapacity && !hasNull)
                return false;

            index = (index + 1) % tableSize;
        }
        return false;
    }

    private int hash(K key)
    {
        return Math.abs(key.hashCode()) % tableSize;
    }

    @Override
    public V insert(K key, V value) {
        V val = null;
        int index = hash(key);
        for (int i = 0; i < tableSize; i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(index);
            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> temp = bucket.get(j);
                if (temp != null && temp.key.equals(key)) {
                    val = temp.value;
                    temp.value = value;
                    return val;
                }
            }
            if (bucket.size() < bucketCapacity) {
                bucket.add(new DS4_Entry<>(key, value));
                size++;
                if ((size + tombstones) >= loadFactor)
                    rebuild();
                return null;
            }
            index = (index + 1) % tableSize;
        }
        rebuild();
        return insert(key, value);
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        for (int i = 0; i < tableSize; i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(index);
            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> temp = bucket.get(j);
                if (temp != null && temp.key.equals(key)) {
                    V val = temp.value;
                    bucket.set(j, null);
                    tombstones++;
                    size--;
                    return val;
                }
            }
            index = (index + 1) % tableSize;
        }
        return null;
    }

    public Iterator<K> iterator() {
        ArrayList<K> kal = new ArrayList<>();
        for (ArrayList<DS4_Entry<K, V>> bucket : table) {
            for (DS4_Entry<K, V> entry : bucket) {
                if (entry != null)
                    kal.add(entry.key);
            }
        }
        return kal.iterator();
    }

    private void rebuild()
    {
        ArrayList<DS4_Entry<K, V>> temp = new ArrayList<>();
        for (ArrayList<DS4_Entry<K, V>> bucket : table) {
            for (DS4_Entry<K, V> entry : bucket) {
                if (entry != null)
                    temp.add(entry);
            }
        }
        tableSize *= 2;
        loadFactor *= 2;
        size = 0;
        tombstones = 0;

        table = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            table.add(new ArrayList<>());
        }
        for (DS4_Entry<K, V> entry : temp) {
            insert(entry.key, entry.value);
        }
    }
}
