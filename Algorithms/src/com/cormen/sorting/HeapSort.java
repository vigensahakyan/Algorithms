package com.cormen.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class HeapSort<T extends Comparable<T>> implements SortInterface {

	static public int numOperation = 0;

	public void Sort(ArrayList array) {
		Heap_Sort(array);
	}

	private void Heap_Sort(ArrayList<T> array) {
		int heap_size = array.size();
		BuildMaxHeap(array);
		for (int i = (array.size() - 1); i >= 1; i--) {
			Collections.swap(array, 0, i);
			heap_size--;
			MaxHeapIFY(array, heap_size, 0);
		}
	}

	private void BuildMaxHeap(ArrayList<T> array) {
		int middleOfArray = (int) Math.floor((array.size()) / 2) - 1;
		for (int i = middleOfArray; i >= 0; i--) {
			MaxHeapIFY(array, array.size(), i);
		}

	}

	private void MaxHeapIFY(ArrayList<T> array, int heap_size, int i) {
		int largest = 0;
		int l = Left(i);
		int r = Right(i);

		if (l <= heap_size - 1 && array.get(l).compareTo(array.get(i)) > 0) {
			largest = l;
		} else {
			largest = i;
		}
		if (r <= heap_size - 1 && array.get(r).compareTo(array.get(largest)) > 0) {
			largest = r;
		}
		if (largest != i) {
			Collections.swap(array, i, largest);
			numOperation++;
			MaxHeapIFY(array, heap_size, largest);
		}
	}

	private int Parent(int i) {
		return (int) Math.floor(i / 2);
	}

	private int Left(int i) {
		// original is 2*i, but because of indexing of array start with 0, we
		// had to modify it to (2*(i+1)-1)
		return (2 * (i + 1)) - 1;
	}

	private int Right(int i) {
		// original is (2*i + 1), but beacuse o indexing of array start with 0,
		// we had to modify it to (2*(i+1))
		return (2 * (i + 1));
	}

	public static void main(String[] args) {
		HeapSort<Integer> in = new HeapSort<Integer>();

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
