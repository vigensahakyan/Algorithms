package com.cormen.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSort<T extends Comparable<T>> implements SortInterface {

	static public int numOperation = 0;

	@Override
	public void Sort(ArrayList array) {

		Quick_Sort(array, 0, array.size() - 1);
	}

	private void Quick_Sort(ArrayList<T> array, int p, int r) {
		if (p < r) {
			int q = Randomized_Partition(array, p, r);
			Quick_Sort(array, p, q - 1);
			Quick_Sort(array, q + 1, r);
		}
	}

	private int Randomized_Partition(ArrayList<T> array, int p, int r) {

		// Math.random() return value form uniform distribution within 0-1 then
		// we transform it into range [p,r]
		int rR = (int) (Math.random() * (r - p)) + p;
		Collections.swap(array, r, rR);
		int i = p - 1;
		T obj = array.get(r);
		for (int j = p; j <= (r - 1); j++) {
			if (obj.compareTo(array.get(j)) > 0) {
				i++;
				Collections.swap(array, i, j);
				numOperation++;
			}
		}
		Collections.swap(array, i + 1, r);
		numOperation++;
		return i + 1;
	}

	private int Partition(ArrayList<T> array, int p, int r) {

		int i = p - 1;
		T obj = array.get(r);
		for (int j = p; j <= (r - 1); j++) {
			if (obj.compareTo(array.get(j)) > 0) {
				i++;
				Collections.swap(array, i, j);
				numOperation++;
			}
		}
		Collections.swap(array, i + 1, r);
		numOperation++;
		return i + 1;
	}

	public static void main(String[] args) {
		QuickSort<Integer> in = new QuickSort<Integer>();

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
