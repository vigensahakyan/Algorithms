package com.cormen.sorting;

import java.util.ArrayList;

public class TestSort {

	public static void main(String[] args) {
		SortStrategy<Integer> in = new SortStrategy<Integer>();
		in.SetStrategy(new CountingSort<Integer>());

		ArrayList<Integer> list = new ArrayList<Integer>() {
			{
				add(4);
				add(0);
				// add(1);
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
				add(11);
				add(12);
				add(13);
				add(17);
				add(14);
				add(15);
				add(16);
			}
		};
		System.out.println(list.toString());
		in.Sort(list);
		System.out.println(list.toString() + "Cost : ");
		Integer[] a = { 4, 6, 7, 8, 3, 2, 5, 7, 8, 10, 11, 400, 800, 77 };

	}

}
