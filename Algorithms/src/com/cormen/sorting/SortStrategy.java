package com.cormen.sorting;

import java.util.ArrayList;

public class SortStrategy<T extends Comparable<T>> {
	private SortInterface inter = null;

	public void Sort(ArrayList<T> array) {
		inter.Sort(array);
	}

	public void SetStrategy(SortInterface<T> in) {
		inter = in;
	}
}
