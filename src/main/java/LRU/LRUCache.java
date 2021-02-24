package LRU;

import java.util.*;

public class LRUCache {
    private int capacity;
    private Set<Integer> cache;
    private Map<Integer, Integer> values;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.cache = new LinkedHashSet<>();
        this.values = new HashMap<>();
    }

    /**
     * Return the value of the key if the key exists, otherwise return -1.
     * @param key
     * @return
     */
    public int get(int key){
        if (cache.contains(key)){
            cache.remove(key);
            cache.add(key);
            return values.get(key);
        }
        return -1;
    }

    /**
     * Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * @param key
     * @param value
     */
    public void put(int key, int value){
        if(get(key) == -1){
            if (cache.size() == capacity){
                int firstKeyInserted = cache.iterator().next(); // returns elements in the order they were inserted
                cache.remove(firstKeyInserted);
                values.remove(firstKeyInserted);
            }
            cache.add(key);
            values.put(key, value);
        }
        else {
            values.put(key, value);
        }
    }
}
