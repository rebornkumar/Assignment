package com.learn.impl;


import com.learn.ArrayWithPublishedSize;
import com.learn.MyMap;

public class MyHashTableImpl<K, V> implements MyMap<K, V>, ArrayWithPublishedSize {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    private MapEntryImpl<K,V>[]array;
    transient int size;
    int threshold;
    final double loadFactor;
    private int capacity = 16;
    // This is the load factor that the table can never exceed. However, it
    // may happen that an insertion fails before reaching this load factor.
    // the internal rehash() function should be called when either the load
    // factor is higher than a limit passed as an argument in the
    // constructor of MyHashTable or when an insertion fails (this is, when
    // an insertion does not find an empty cell

    public MyHashTableImpl(double MAX_LOAD_FACTOR) {
        // Here you need to create the array. It is not possible to create a new array
        // of generic type in Java. You can use any of the methods to simulate the
        // generic-like array; this assignment does not restrict the method to use for that.
        int capacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = MAX_LOAD_FACTOR;
        array = new MapEntryImpl[capacity];
    }

    @Override
    public int getLengthOfArray() {
        return array.length;
    }
    @Override
    public void insert(K key, V value) {
        if (key == null){
            MapEntryImpl<K,V>mapEntry = array[0];
            if(mapEntry != null) {
                mapEntry.setValue(value);
            }
            else {
                mapEntry = new MapEntryImpl<K,V>(key,value);
                mapEntry.hashCode = 0;
                array[0] = mapEntry;
            }
            return;
        }
        int hashCode = key.hashCode();
        int arrayIndex = indexFor(hashCode);

        for (MapEntryImpl<K,V> mapEntry = array[arrayIndex]; mapEntry != null; mapEntry = mapEntry.next) {
            Object nodeKey;
            if (mapEntry.getKey().hashCode() == hashCode && ((nodeKey = mapEntry.getKey()) == key || key.equals(nodeKey))) {
                //update the key-value pair
                mapEntry.setValue(value);
                return;
            }
        }
        if (this.size + 1 > this.threshold) {
            resize();
        }
        //add new node at the end
        addEntry(key, value);

        return;
    }

    @Override
    public void delete(K key) {
        if(key == null) {
            MapEntryImpl<K,V>mapEntry = array[0];
            if(mapEntry != null) {
                array[0] = null;
                size--;
            }
            return;
        }
        int hashCode = key.hashCode();
        int arrayIndex = indexFor(hashCode);
        MapEntryImpl<K, V> currentNode = array[arrayIndex];
        MapEntryImpl<K,V>previousNode = currentNode;

        /* If the hash exists in the HashMap, traverse until it is found */
        K nodeKey = null;
        while (currentNode != null) {
            if (currentNode.hashCode == hashCode && ((nodeKey = currentNode.getKey()) == key || key.equals(nodeKey))) {
                if(previousNode == currentNode) {
                    array[arrayIndex] = null;
                }
                previousNode.next = currentNode.next;
                currentNode = null;
                size--;
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
    }
    @Override
    public V contains(K key) {
        //if key is null
        if (key == null) {
            for (MapEntryImpl<K,V> mapEntry = array[0]; mapEntry != null; mapEntry = mapEntry.next) {
                if (mapEntry.getKey() == null)
                    return mapEntry.getValue();
            }
            return null;
        }
        //when key is not null
        int hashCode = key.hashCode();
        int arrayIndex = indexFor(hashCode);
        MapEntryImpl<K, V> bucket = array[arrayIndex];

        /* If the hash exists in the HashMap, traverse until it is found */
        K nodeKey = null;
        while (bucket != null) {

            if (bucket.getKey().hashCode() == hashCode && ((nodeKey = bucket.getKey()) == key || key.equals(nodeKey))) {
                return bucket.getValue();
            }
            bucket = bucket.next;
        }
        /* Does not exist in the HashMap, return null per spec. */
        return null;
    }

    int indexFor(int hashValue) {
        int index = hashValue % capacity;
        return index;
    }
    void addEntry(K key,V value) {
        MapEntryImpl<K,V> newMapEntry = new MapEntryImpl(key,value);
        newMapEntry.hashCode = key.hashCode();
        int arrayIndex = indexFor(newMapEntry.hashCode);
        if(array[arrayIndex] != null) {
            MapEntryImpl<K,V> mapEntry = array[arrayIndex];
            newMapEntry.next = mapEntry;
        }
        else {
            array[arrayIndex] = newMapEntry;
        }
        size++;
    }
    final void resize() {
        MapEntryImpl<K, V>[] oldTab = this.array;
        int oldCap = capacity;
        int newCap = 2*oldCap;
        int newThr = (int)(newCap*loadFactor);
        this.threshold = newThr;
        this.capacity = newCap;
        MapEntryImpl<K, V>[] newTab = new MapEntryImpl[newCap];
        this.array = newTab;
        size = 0;
        if (oldTab != null) {
            for(int j = 0; j < oldCap; ++j) {
                MapEntryImpl<K,V> mapEntry;
                if ((mapEntry = oldTab[j]) != null) {
                    oldTab[j] = null;
                    while(mapEntry!=null)
                    {
                        int arrayIndex = indexFor(mapEntry.getKey().hashCode());
                        addEntry(mapEntry.getKey(),mapEntry.getValue());
                        mapEntry=mapEntry.next;
                    }
                }
            }
        }
    }
    public int size() {
        return this.size;
    }

}

