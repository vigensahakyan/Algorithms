package com.cormen.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class InsertionSort<T extends Comparable<T>> implements SortInterface {

	static public int numOperation = 0;

	public void Sort(ArrayList array) {

		Insertion_Sort(array);
	}

	private void Insertion_Sort(ArrayList<T> array) {
		for (int j = 1; j < array.size(); j++) {
			T key = array.get(j);
			int i = j - 1;
			while (i >= 0 && (array.get(i).compareTo(key) > 0)) {
				array.set(i + 1, array.get(i));
				i--;
			}
			array.set(i + 1, key);
		}
	}

	public static void main(String[] args) {
		InsertionSort<Integer> in = new InsertionSort<Integer>();

		ArrayList<Integer> list = new ArrayList<Integer>() {
			{
				add(4);
				add(1);
				add(3);
				add(2);
				add(16);
				add(9);
				add(10);
				add(14);
				add(8);
				add(7);
				add(6);
				add(5);
				add(17);
				add(14);
				add(15);
				add(16);
			}
		};
		System.out.println(list.toString());
		in.Sort(list);
		System.out.println(list.toString() + "Cost : " + in.numOperation);
	}
}
