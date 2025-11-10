import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Iterator;

public class DS4_HashTable<K, V> implements DS4_HashTable_Interface<K, V>
{

    int bucketCapacity;
    int loadFactor;
    int size;
    int tableSize;
    int tombstones;
    ArrayList< ArrayList< DS4_Entry<K, V> > > arr;

    public DS4_HashTable(int bucketCapacity,int loadFactor, int tableSize) {
        this.bucketCapacity = bucketCapacity;
        this.loadFactor = loadFactor;
        this.tableSize = tableSize;
        arr = new ArrayList<>();
        size = 0;
    }

    @Override
    public void clear() {
        for (int i = size-1; i >= 0; i--) {
            for (int j = arr.get(i).size()-1; j >=0 ; j--) {
                arr.get(i).remove(j);
            }
        }
        size = 0;
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
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                if (arr.get(i).get(j).key == key)
                    return true;
            }
        }
        return false;
    }

    @Override
    public V insert(K key, V value) {
        V val = null;
        if (contains(key)) {

            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < arr.get(i).size(); j++) {
                    if (arr.get(i).get(j).key == key)
                        val = arr.get(i).get(j).value;
                }
            }
//            return val; // old value
        }
        else {

            size++;
            // adds to hash table
//            return null; // returns null since
        }
        return val;
    }

    @Override
    public V remove(K key) {
//        V val = null;
//        if (!contains(key))
//            return null;
//        else
//        if {
            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < arr.get(i).size(); j++) {
                    if (arr.get(i).get(j).key == key)
                    {
                        V val = arr.get(i).get(j).value;
                        arr.get(i).set(j, new DS4_Entry<>(arr.get(i).get(j).key, null));
                        tombstones++;
                        return (V) "X"; // change this
                    }
                }
            }
//        }
        return null;
    }

    public Iterator<K> iterator() {
        ArrayList<K> kal = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                if (contains(arr.get(i).get(j).key))
                    kal.add(arr.get(i).get(j).key);
            }
        }
        return kal.iterator();
    }


    private int hash(K key)
    {
        return 0;
    }

    private void rebuild()
    {
        tableSize *= 2;
        loadFactor *= 2;
        ArrayList<ArrayList<DS4_Entry<K, V>>> temp = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                temp.add(arr.get(i));
            }
        }
        // double the arraylist
        arr.clear();
        for (int i = 0; i < temp.size(); i++) {
            arr.add(temp.get(i));
        }
    }
}
