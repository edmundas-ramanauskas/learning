package learning.data.structures.impl;

import java.util.*;

/**
 * Created by ramanaue on 10/06/2015.
 */
public class HashMap<K, V> implements Map<K,V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 16

    private Entry<K, V>[] table;
    private int size;
    private int mask;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public HashMap(int capacity) {
        initTable(roundUpToPowerOf2(capacity));
    }

    private final void initTable(int capacity) {
        table = new Entry[capacity];
        mask = table.length-1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        return table[index(key)] != null;
    }

    public boolean containsValue(Object value) {
        for(int i = 0; i < table.length; i++) {
            if(table[i] == null) continue;
            if(table[i].getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        if(key == null) return getForNullKey();

        int hash = hash(key);
        for (Entry<K,V> entry = table[index(hash)]; entry != null; entry = entry.next) {
            Object k = entry.key;
            if(entry.hash == hash && key.equals(k)) {
                return entry.value;
            }
        }
        return null;
    }

    private final V getForNullKey() {
        for (Entry<K,V> entry = table[0]; entry != null; entry = entry.next) {
            if(entry.key == null) {
                return entry.value;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        if(key == null) return putForNullKey(value);

        int hash = hash(key);
        int index = index(hash);

        for(Entry<K,V> entry = table[index]; entry != null; entry = entry.next) {
            Object k = entry.key;
            if(entry.hash == hash && key.equals(k)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }

        addEntry(key, value, hash, index);
        return null;
    }

    private final V putForNullKey(V value) {
        for (Entry<K,V> entry = table[0]; entry != null; entry = entry.next) {
            if(entry.key == null) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }

        addEntry(null, value, 0, 0);
        return null;
    }

    private final void addEntry(K key, V value, int hash, int index) {
        createEntry(key, value, hash, index);
        if(++size >= table.length) {
            resize();
        }
    }

    private final void createEntry(K key, V value, int hash, int index) {
        Entry<K,V> e = table[index];
        table[index] = new Entry(key, value, hash, e);
    }

    private final void resize() {
        int capacity = table.length<<1; // capacity is always power of two
        Entry[] oldTable = table;

        initTable(capacity);

        for(int i = 0; i < oldTable.length; i++) {
            for(Entry<K,V> entry = oldTable[i]; entry != null; entry = entry.next) {
                int hash = hash(entry.key);
                int index = index(hash);
                createEntry(entry.key, entry.value, hash, index);
            }
        }
    }

    public V remove(Object key) {
        Entry<K,V> entry = removeEntry(key);
        return (entry == null ? null : entry.value);
    }

    private final Entry<K, V> removeEntry(Object key) {
        if(key == null) return removeForNullKey();

        int hash = hash(key);
        int index = index(hash);

        Entry<K,V> entry = table[index];

        if(entry.key.hashCode() == hash && key.equals(entry.key)) {
            table[index] = entry.next;
            --size;
            return entry;
        }

        for(Entry<K,V> curr = entry.next; curr != null; curr = entry.next) {
            if(curr.key.hashCode() == hash && key.equals(curr.key)) {
                entry.next = curr.next;
                --size;
                return curr;
            }
        }

        return null;
    }

    private final Entry<K, V> removeForNullKey() {
        Entry<K,V> entry = table[0];

        if(entry.key == null) {
            table[0] = entry.next;
            --size;
            return entry;
        }

        for(Entry<K,V> curr = entry.next; curr != null; curr = entry.next) {
            if(curr.key == null) {
                entry.next = curr.next;
                --size;
                return curr;
            }
        }

        return null;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for(Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public Set<K> keySet() {
        // TODO: improve this method
        Set<K> keys = new HashSet<K>(size);
        for(int i = 0; i < table.length; i++) {
            for(Entry<K,V> entry = table[i]; entry != null; entry = entry.next) {
                keys.add(entry.key);
            }
        }
        return keys;
    }

    public Collection<V> values() {
        // TODO: improve this method
        V[] values = (V[]) new Object[size];
        int n = 0;
        for(int i = 0; i < table.length; i++) {
            for(Entry<K,V> entry = table[i]; entry != null; entry = entry.next) {
                values[n++] = entry.value;
            }
        }
        return Arrays.asList(values);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        // TODO: improve this method
        Set<Map.Entry<K, V>> keys = new HashSet<Map.Entry<K, V>>(size);
        for(int i = 0; i < table.length; i++) {
            for(Entry<K,V> entry = table[i]; entry != null; entry = entry.next) {
                keys.add(entry);
            }
        }
        return keys;
    }

    static class Entry<K,V> implements Map.Entry<K,V> {

        final K key;
        V value;
        int hash;
        Entry<K,V> next;

        public Entry(K key, V value, int hash, Entry entry) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            next = entry;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    private final int index(int hash) {
        return hash & mask;
    }

    private final int index(Object key) {
        return index(hash(key));
    }

    private static final int roundUpToPowerOf2(int number) {
        int rounded =  (rounded = Integer.highestOneBit(number)) != 0
                ? (Integer.bitCount(number) > 1) ? rounded << 1 : rounded : 1;
        return rounded;
    }

    private static final int hash(Object key) {
        // TODO: improve this method to ensure better hash distribution
        return (key == null ? 0 : key.hashCode());
    }
}