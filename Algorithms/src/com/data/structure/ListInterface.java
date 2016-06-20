package com.data.structure;

public interface ListInterface<T> {

	T get(int i);

	boolean add(T obj);

	boolean remove(T obj);

	boolean set(int i, T obj);

	IteratorInterface Iterator();
}
