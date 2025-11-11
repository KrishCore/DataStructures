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
        int start = hash(key);
        int index = start;
        for (int i = 0; i < tableSize; i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(index);
            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> temp = bucket.get(j);
                if (temp != null && temp.key!= null && temp.key.equals(key))
                    return true;
            }
            if (bucket.size() < bucketCapacity)
                return false;
            index = (index+1) % tableSize;
        }
        return false;
    }

    @Override
    public V insert(K key, V value) {
        V val = null;
        int start = hash(key);
        int index = start;
        boolean rep = false;
        boolean ins = false;


        for (int i = 0; i < tableSize; i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(index);
            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> temp = bucket.get(j);
                if (temp != null && temp.key.equals(key))
                {
                    val = temp.value;
                    temp.value = value;
                    rep = true;
                    break;
                }
            }
            if (rep) break;
            if (bucket.size() < bucketCapacity)
            {
                bucket.add(new DS4_Entry<>(key, value));
                size++;
                ins = true;
                break;
            }
            index = (index + 1) % tableSize;
        }

        if (!rep && !ins) {
            rebuild();
            return insert(key, value);
        }
        if ((size + tombstones) >= loadFactor)
            rebuild();
        return val;
    }

    @Override
    public V remove(K key) {
        int start = hash(key);
        int index = start;

        for (int i = 0; i < tableSize; i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(index);

            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> temp = bucket.get(j);
                if (temp != null && temp.key != null && temp.key.equals(key)) {
                    V val = temp.value;
                    bucket.set(j, null);
                    tombstones++;
                    size--;
                    return val;
                }
            }
            if (bucket.size() < bucketCapacity)
                return null;
            index = (index + 1) % tableSize;
        }
        return null;
    }

    public Iterator<K> iterator() {
        ArrayList<K> kal = new ArrayList<>();
        for (ArrayList<DS4_Entry<K, V>> bucket : table) {
            for (DS4_Entry<K, V> entry : bucket) {
                if (entry != null && entry.value != null)
                    kal.add(entry.key);
            }
        }
        return kal.iterator();
    }


    private int hash(K key)
    {
        return Math.abs(key.hashCode()) % tableSize;
    }

    private void rebuild()
    {
        ArrayList<DS4_Entry<K, V>> temp = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            ArrayList<DS4_Entry<K, V>> bucket = table.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                DS4_Entry<K, V> entry = bucket.get(j);
                if (entry != null && entry.key != null && entry.value != null)
                    temp.add(entry);
            }
        }
        tableSize *= 2;
        table = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            table.add(new ArrayList<>());
        }
        size = 0;
        tombstones = 0;
        for (int i = 0; i < temp.size(); i++) {
            insert(temp.get(i).key, temp.get(i).value);
        }
    }
}
