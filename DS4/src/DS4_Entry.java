
public class DS4_Entry<K, V> {
        final K key;
        V value;
        DS4_Entry(K k, V v) { this.key = k; this.value = v; }

        public String toString()
        {
                return key+":"+value;
        }


}
