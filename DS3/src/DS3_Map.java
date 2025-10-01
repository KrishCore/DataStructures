import java.util.ArrayList;

public class DS3_Map<Integer, String> implements MapInterface<Integer, String>
{
    ArrayList<MapEnt> array = new ArrayList<>();

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public boolean containsKey(Integer key) {
        for (int i = 0; i < array.size(); i++) {
            return array.get(i).getKey().equals(key);
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < array.size(); i++) {
            return array.get(i).getValue().equals(value);
        }
        return false;    }

    @Override
    public DS3_Set<MapEnt<Integer, String>> entrySet() {
        return null;
    }

    @Override
    public String get(Integer o) {
        return (String) " ";
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String put(Integer key, String value) { // needs work
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(key))
            {
                String string = (String) array.get(i).getValue();
                array.get(i).setValue(value);
                return string;
            }
        }
        array.add(new MapEnt(key, value));
        return (String) array.get(array.size()-1);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public DS3_Set<Integer> keySet() {
        DS3_Set keySet = new DS3_Set();
        for (int i = 0; i < array.size(); i++) {
            keySet.add(array.get(i).getKey());
        }
        return keySet;
    }

    @Override
    public ArrayList<String> values() {
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            values.add((String) array.get(i).getValue());
        }
        return values;
    }

    @Override
    public String remove(Integer key) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(key))
                return (String) array.remove(i);
        }
        return (String) "";
    }
}
