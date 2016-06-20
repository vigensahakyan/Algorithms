package com.data.structure;

import java.util.ArrayList;
import java.util.Iterator;
//import java.util.LinkedList;
import java.util.List;

public class TestCollections {

	public static void main(String[] args) {
		// TestCollections.StackTest();
		// LinkedListTest();
		// VectorListTest();
		// OAHashSetTest();
		// ChainedHastTableTest();
		// RedBlackTreeTest();
		// BPlusTreeTest();
		FibonacciHeapTreeTest();
		List<Integer> ss;
		ArrayList<Integer> arl = new ArrayList<>();

	}

	public static void FibonacciHeapTreeTest() {

		FibonacciHeap<Integer> st = new FibonacciHeap<Integer>();

		st.insert(80, 80);
		st.insert(50, 50);
		st.insert(120, 120);
		st.insert(45, 45);
		st.insert(130, 45);
		st.insert(47, 47);
		st.insert(45, 45);
		System.out.println(st.size());
	}

	public static void BPlusTreeTest() {

		Set<Integer> st = new BPlusTree<Integer>();

		st.Insert(80);
		st.Insert(50);
		st.Insert(120);
		st.Insert(45);
		st.Insert(130);
		st.Insert(47);
		st.Insert(45);
		st.Insert(180);
		st.Insert(137);
		st.Insert(45);
		st.Insert(180);
		st.Insert(137);
		st.Insert(80);
		st.Insert(50);
		st.Insert(120);
		st.Insert(45);
		st.Insert(130);
		st.Insert(47);
		st.Insert(45);
		st.Insert(180);
		st.Insert(137);
		st.Insert(45);
		st.Insert(180);
		st.Insert(137);

		IteratorInterface<Integer> it = st.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}
	}

	public static void RedBlackTreeTest() {

		Set<Integer> st = new RedBlackTree<Integer>();

		st.Insert(80);
		st.Insert(50);
		st.Insert(120);
		st.Insert(45);
		st.Insert(130);
		st.Insert(47);
		st.Insert(45);
		st.Insert(180);
		st.Insert(137);
		st.Remove(50);
		st.Remove(45);
		st.Remove(120);
		st.Remove(137);

		System.out.println(st.Search(45));

		IteratorInterface<Integer> it = st.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}
	}

	public static void ChainedHastTableTest() {

		Set<String> st = new ChainedHashTable<String>();

		st.Insert("1");
		st.Insert("1");
		st.Insert("1");
		st.Insert("2");
		st.Insert("2");
		st.Insert("3");
		st.Insert("4");
		st.Remove("2");
		st.Search("3");
		st.Remove("5");
		IteratorInterface<Integer> it = st.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}

	}

	public static void OAHashSetTest() {

		Set<String> st = new HashSetOpenAddressed<String>();

		st.Insert("1");
		st.Insert("1");
		st.Insert("1");
		st.Insert("2");
		st.Insert("2");
		st.Insert("3");
		st.Insert("4");
		st.Remove("2");
		st.Search("3");
		st.Remove("5");

		IteratorInterface<Integer> it = st.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}

	}

	public static void VectorListTest() {
		ListInterface<Integer> list = new VectorList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.set(5, 22);
		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(6));
		System.out.println(list.get(7));
		list.remove(1);
		System.out.println("=============================================");
		IteratorInterface<Integer> it = list.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}

	}

	public static void LinkedListTest() {
		ListInterface<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.set(5, 22);
		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(6));
		System.out.println(list.get(7));
		list.remove(1);
		System.out.println("=============================================");
		IteratorInterface<Integer> it = list.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}

	}

	public static void StackTest() {
		DynamicStack<Integer> sk = new DynamicStack<Integer>();
		sk.Push(1);
		sk.Push(2);
		sk.Push(2);
		sk.Push(2);
		sk.Push(2);
		sk.Push(2);
		sk.Push(2);
		sk.Push(2);
		sk.Push(2);
		sk.Push(10);

		IteratorInterface<Integer> it = sk.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}
		sk.Pop();
		sk.Pop();
		sk.Pop();
		sk.Pop();
		sk.Pop();
		System.out.println("=============================================");
		it = sk.Iterator();
		while (it.hasNext()) {
			System.out.println(it.Next());
		}
	}

}
