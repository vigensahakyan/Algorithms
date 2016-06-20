package com.cormen.sorting;

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> implements SortInterface {

	static public int numOperation = 0;

	@Override
	public void Sort(ArrayList array) {
		// TODO Auto-generated method stub
		Merge_Sort(array, 0, (array.size() - 1));
	}

	private void Merge_Sort(ArrayList<T> array, int p, int r) {
		if (p < r) {
			int q = (int) Math.floor((p + r) / 2);
			Merge_Sort(array, p, q);
			Merge_Sort(array, q + 1, r);
			Merge(array, p, q, r);
		}
	}

	private void Merge(ArrayList<T> array, int p, int q, int r) {
		int n1 = (q - p) + 1;
		int n2 = r - q;
		T[] left = (T[]) new Comparable[n1 + 1];
		T[] right = (T[]) new Comparable[n2 + 1];
		for (int i = 0; i < n1; i++) {
			left[i] = array.get(p + i);
		}
		for (int i = 0; i < n2; i++) {
			right[i] = array.get(q + i + 1);
		}

		int i = 0;
		int j = 0;

		for (int z = p; z <= r; z++) {
			if (i == n1) {
				array.set(z, right[j]);
				j++;
			} else if (j == n2) {
				array.set(z, left[i]);
				i++;
			} else if (left[i].compareTo(right[j]) <= 0) {
				array.set(z, left[i]);
				i++;
			} else {
				array.set(z, right[j]);
				j++;
			}

		}

	}

	public static void main(String[] args) {
		MergeSort<Integer> in = new MergeSort<Integer>();

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
