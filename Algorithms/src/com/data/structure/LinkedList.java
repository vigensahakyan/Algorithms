package com.data.structure;

public class LinkedList<T> implements ListInterface<T> {

	private T data;
	private LinkedList<T> next = null;
	private LinkedList<T> lastElement = null;
	private LinkedList<T> firstElement = null;

	private int size = 0;

	public LinkedList() {
	}

	public LinkedList(T pData) {
		data = pData;
	}

	@Override
	public T get(int i) {
		T obj = null;
		if (i < size) {
			LinkedList<T> listIt = this;
			for (int it = 0; it <= i; ++it) {
				if (it == i) {
					obj = listIt.data;
				}
				listIt = listIt.next;
			}
		} else {
			System.out.println("Index out of list bound");
		}
		return obj;
	}

	@Override
	public boolean add(T obj) {
		boolean added = false;
		if (size != 0) {
			lastElement.next = new LinkedList<T>(obj);
			lastElement = lastElement.next;
			size++;
			added = true;
		} else {
			data = obj;
			lastElement = this;
			firstElement = this;
			size++;
			added = true;
		}
		return added;
	}

	@Override
	public boolean remove(T obj) {

		boolean removed = false;
		int count = 0;
		LinkedList<T> listIt = this;
		if (listIt.data.equals(obj)) {
			firstElement = listIt.next;
			listIt = listIt.next;
			size--;
		} else {
			for (int it = 0; it < (size - 1); ++it) {
				if (listIt.next.data.equals(obj)) {
					listIt.next = listIt.next.next;
					removed = true;
					size--;
				}
				listIt = listIt.next;
			}
		}
		return false;
	}

	@Override
	public boolean set(int i, T obj) {
		boolean set = false;
		if (i < size) {
			LinkedList<T> listIt = this;
			for (int it = 0; it <= i; ++it) {
				if (it == i) {
					LinkedList<T> tmpObj = listIt.next;
					listIt.next = new LinkedList<T>(obj);
					listIt.next.next = tmpObj;
					tmpObj = null;
					set = true;
					size++;
				}
				listIt = listIt.next;
			}
		} else {
			System.out.println("Index out of list bound");
		}
		return set;
	}

	@Override
	public IteratorInterface Iterator() {

		IteratorInterface<T> it = new IteratorInterface<T>() {

			private LinkedList<T> currentElement = firstElement;
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return (currentIndex < (size)) ? true : false;
			}

			@Override
			public T Next() {
				T obj = currentElement.data;
				currentElement = currentElement.next;
				currentIndex++;

				return obj;
			}

			@Override
			public boolean remove(T rm) {
				return false;
			}

		};

		return it;
	}

}
