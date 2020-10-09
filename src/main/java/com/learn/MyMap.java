package com.learn;

public interface MyMap<K, V> {

    public void insert(K key, V value); // It does the usual insertion. If an element with the same key already exists
					// in the table, this operation replaces its previous value with the value in
					// the parameter (like a changing the definition of a word that already exists).

    public void delete(K key); // It does the usual deletion

    public V contains(K key); // It returns the 'value' associated with the key parameter, if it exists in the
			      // table. If it does not exist, it returns null.

}
