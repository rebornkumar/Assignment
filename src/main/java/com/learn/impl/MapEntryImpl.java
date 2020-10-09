package com.learn.impl;

public class MapEntryImpl<K, V> {

    private K key;
    private V value;
    int hashCode;
    MapEntryImpl<K,V>next;
    public MapEntryImpl() {

    }
    public MapEntryImpl(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if(key != null) {
            return key.toString();
        }
        return null;
    }
    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        } else {
            if (o instanceof MapEntryImpl) {
                MapEntryImpl<?, ?> e = (MapEntryImpl)o;
                if(this.key != null) {
                    if(e.getKey() != null && e.getKey() instanceof String) {
                        if(((String) e.getKey()).equals(this.key)){
                            return true;
                        }
                    }
                    else {
                        if(e.getKey().equals(this.key)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.key == null) ? 0 : key.hashCode());
        return result;
    }
}
