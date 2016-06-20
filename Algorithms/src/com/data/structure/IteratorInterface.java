package com.data.structure;

public interface IteratorInterface<T> {
	boolean hasNext();

	T Next();

	boolean remove(T rm);
}
