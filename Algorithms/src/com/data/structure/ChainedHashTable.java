package com.data.structure;

import java.util.TreeSet;

public class ChainedHashTable<T> implements Set<T> {

	private int size = 37;
	// to get the better speed we use TreeSet instead of LinkedList, that
	// guarantee log(n) cost for finding element in chain
	private TreeSet<T>[] table = new TreeSet[size];

	@Override
	public boolean Insert(T obj) {
		boolean inserted = false;
		int hash = hash(obj.hashCode());

		if (table[hash] != null) {
			if (table[hash].add(obj)) {
				inserted = true;
			}
		} else {
			table[hash] = new TreeSet<T>();
			table[hash].add(obj);
			inserted = true;
		}
		return inserted;
	}

	@Override
	public boolean Search(T obj) {
		boolean found = false;
		int hash = hash(obj.hashCode());

		if (table[hash] != null) {
			if (table[hash].contains(obj)) {
				found = true;
			}
		}
		return found;
	}

	@Override
	public boolean Remove(T obj) {
		boolean removed = false;
		int hash = hash(obj.hashCode());

		if (table[hash] != null) {
			if (table[hash].remove(obj)) {
				removed = true;
			}
		}
		return removed;
	}

	@Override
	public IteratorInterface Iterator() {
		return null;
	}

	private int hash(int k) {
		return k % size;
	}

}
