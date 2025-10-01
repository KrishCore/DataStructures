
public class MapEnt<K,V>
{
    private K key;
    private V value;

    public MapEnt(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }

    public String toString()
    {
        return "("+key+" - "+value+")";
    }

    public boolean equals(Object o)
    {
        if(! (o instanceof MapEnt))
            return false;
        MapEnt<K,V> e = (MapEnt<K,V>)o;
        return value.equals(e.value) && key.equals(e.key);
    }
}
