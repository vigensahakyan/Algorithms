package com.data.structure;

public interface Set<T> {

	boolean Insert(T obj);

	boolean Search(T obj);

	boolean Remove(T obj);

	IteratorInterface Iterator();

}
