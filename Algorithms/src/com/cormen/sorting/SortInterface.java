package com.cormen.sorting;

import java.util.ArrayList;

public interface SortInterface<T extends Comparable<T>> {
	public void Sort(ArrayList<T> array);
}
