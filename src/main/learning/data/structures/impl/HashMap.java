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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return table[index(key)] != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i < table.length; i++) {
            if(table[i] == null) continue;
            if(table[i].getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
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

    @Override
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
        Entry[] oldTable = table;
        initTable(table.length<<1);
        for(int i = 0; i < oldTable.length; i++) {
            for(Entry<K,V> entry = oldTable[i]; entry != null; entry = entry.next) {
                int hash = hash(entry.key);
                int index = index(hash);
                createEntry(entry.key, entry.value, hash, index);
            }
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        V[] values = (V[]) new Object[size];
        int n = 0;
        for(int i = 0; i < table.length; i++) {
            for(Entry<K,V> entry = table[i]; entry != null; entry = entry.next) {
                if(entry != null) {
                    values[n++] = entry.getValue();
                }
            }
        }
        return Arrays.asList(values);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
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

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    public final int capacity() {
        return table.length;
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
        return key.hashCode();
    }
}