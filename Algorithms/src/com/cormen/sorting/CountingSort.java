package com.cormen.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

// Counting sort can only operate on integer array;
public class CountingSort<T extends Integer> implements SortInterface {

	@Override
	public void Sort(ArrayList array) {
		int k = MaxElement(array);
		Counting_Sort(array, k);
	}

	private void Counting_Sort(ArrayList<T> array, int k) {

		int[] count = new int[k + 1];
		T[] sortedArray = (T[]) new Integer[array.size()];
		for (int i = 0; i < k; i++) {
			count[i] = 0;
		}
		for (int i = 0; i < array.size(); i++) {
			count[(int) array.get(i)] = count[(int) array.get(i)] + 1;
		}
		for (int i = 1; i < count.length; i++) {
			count[i] = count[i] + count[i - 1];
		}
		for (int i = 0; i < array.size(); i++) {
			sortedArray[count[(int) array.get(i)] - 1] = array.get(i);
			count[(int) array.get(i)] = count[(int) array.get(i)] - 1;
		}
		for (int i = array.size() - 1; i >= 0; i--) {
			array.set(i, sortedArray[i]);
		}
	}

	private int MaxElement(ArrayList<T> values) {
		TreeSet<T> maxValue = new TreeSet<T>(values);
		return (int) Collections.max(values);
	}

}
