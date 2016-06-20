package com.data.structure;

public class VectorList<T> implements ListInterface<T> {

	private static int allocationFactor = 2;
	private int currentalloctionDegree = 0;
	private T[] data = (T[]) new Object[(int) Math.pow(allocationFactor, currentalloctionDegree)];
	private int numberOfElement = 0;

	@Override
	public T get(int i) {
		return null;
	}

	@Override
	public boolean add(T obj) {

		boolean inserted = false;
		if (data.length != 0 && numberOfElement != (data.length)) {
			if (numberOfElement != (data.length)) {
				data[(numberOfElement)] = obj;
				numberOfElement++;
				inserted = true;
			}
		} else {
			T[] tmpData = data.clone();
			currentalloctionDegree++;
			data = (T[]) new Object[(int) Math.pow(allocationFactor, currentalloctionDegree)];
			copyArray(tmpData, data);
			tmpData = null;
			data[numberOfElement] = obj;
			numberOfElement++;
			inserted = true;
		}
		return inserted;
	}

	@Override
	public boolean remove(T obj) {
		// TODO
		return false;
	}

	@Override
	public boolean set(int i, T obj) {
		// TODO
		return false;
	}

	@Override
	public IteratorInterface Iterator() {
		IteratorInterface<T> it = new IteratorInterface<T>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {

				// TODO

				return (currentIndex < (numberOfElement)) ? true : false;
			}

			@Override
			public T Next() {
				// TODO
				T obj = null;
				obj = (T) data[currentIndex];
				currentIndex++;

				return obj;
			}

			@Override
			public boolean remove(T rm) {
				// TODO
				return false;
			}
		};
		// TODO
		return it;
	}

	private void copyArray(T[] from, T[] to) {
		for (int i = 0; i < from.length; ++i) {
			to[i] = from[i];
		}
	}

	private void copyBigArrayToSmall(T[] from, T[] to) {
		for (int i = 0; i < to.length; ++i) {
			to[i] = from[i];
		}
	}

}
