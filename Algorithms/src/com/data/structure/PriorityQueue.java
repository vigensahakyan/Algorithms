package com.data.structure;

public interface PriorityQueue<T> {

	boolean Insert(T obj);

	T Minimum();

	T ExtractMin();

	void DecreaseKey(T key, int m);

	boolean Delete(T obj);

}
