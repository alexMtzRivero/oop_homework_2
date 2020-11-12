package space.harbour.java.hw3;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        System.out.println(myHashMap.size());

        myHashMap.put("hello", 10);

        System.out.println(myHashMap.size());
        System.out.println(myHashMap.isEmpty());
        System.out.println(myHashMap.entrySet());

        }

    public static class Pair<K, V> {
        private final K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
            }
        }

    private final int bucketSize = 16;
    private final LinkedList<Pair<K, V>>[] buckets = new LinkedList[bucketSize];

    public MyHashMap() {
        clear();
        }

    @Override
    public int size() {
        int result = 0;

        for (LinkedList<Pair<K, V>> bucket : buckets) {
            result += bucket.size();
            }
        return result;
        }

    @Override
    public boolean isEmpty() {
        return size() == 0;
        }

    private int keyToBucketIndex(Object key) {
        return Math.abs(key.hashCode() % bucketSize);
        //return key.hashCode() >> 27;
        }

    @Override
    public boolean containsKey(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.key.equals(key)) {
                return true;
                }
            }

        return false;
        }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < bucketSize; i++) {
            for (Pair<K, V> pair : buckets[i]) {
                if (pair.value.equals(value)) {
                    return true;
                    }
                }
            }
        return false;
        }

    @Override
    public V get(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.key.equals(key)) {
                return pair.value;
                }
            }
        return null;
        }

    @Override
    public V put(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        int i = keyToBucketIndex(key);
        if (buckets[i].contains(pair)) {
            for (Pair<K, V> oldPair : buckets[i]) {
                if (oldPair.key.equals(key)) {
                    oldPair.value = value;
                    }
                }
            } else {
            buckets[i].add(pair);
            }

        return value;
        }

    @Override
    public V remove(Object key) {
        int i = keyToBucketIndex(key);
        buckets[i].removeIf(pair -> pair.key.equals(key));
        return null;
        }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
            }
        }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
            }
        }

    // star task
    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                result.add(pair.key);
                }
            }
        return result;
        }

    @Override
    public Collection<V> values() {
        Collection<V> result = new HashSet<>();
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                result.add(pair.value);
                }
            }
        return result;
        }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> result = new HashSet<>();
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                result.add(new AbstractMap.SimpleEntry<K, V>(pair.key, pair.value));
                }
            }
        return result;
        }
    }
