package com.data.structure;

import java.lang.reflect.Array;

public class DynamicStack<T> {

	private static int allocationFactor = 2;
	private int currentalloctionDegree = 0;
	private T[] data = (T[]) new Object[(int) Math.pow(allocationFactor, currentalloctionDegree)];
	private int stackTop = -1;

	public boolean IsEmpty() {
		return (stackTop == (-1)) ? true : false;
	}

	public boolean Push(T obj) {
		boolean inserted = false;
		if (data.length != 0 && stackTop != (data.length - 1)) {
			if (stackTop != (data.length - 1)) {
				stackTop++;
				data[stackTop] = obj;
				inserted = true;
			}
		} else {
			T[] tmpData = data.clone();
			currentalloctionDegree++;
			data = (T[]) new Object[(int) Math.pow(allocationFactor, currentalloctionDegree)];
			copyArray(tmpData, data);
			tmpData = null;
			stackTop++;
			data[stackTop] = obj;
			inserted = true;
		}
		return inserted;
	}

	public T Pop() {
		T obj = null;
		if (!IsEmpty()) {
			obj = data[stackTop];
			stackTop--;
			if ((stackTop + 1) == (data.length / 2)) {
				T[] tmpData = data.clone();
				currentalloctionDegree--;
				data = (T[]) new Object[(int) Math.pow(allocationFactor, currentalloctionDegree)];
				copyBigArrayToSmall(tmpData, data);
			}
		}

		return obj;
	}

	public IteratorInterface Iterator() {
		IteratorInterface<T> it = new IteratorInterface<T>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {

				// TODO Auto-generated method stub

				return (currentIndex < (stackTop + 1)) ? true : false;
			}

			@Override
			public T Next() {
				// TODO Auto-generated method stub

				T obj = data[currentIndex];
				currentIndex++;

				return obj;
			}

			@Override
			public boolean remove(T rm) {
				// TODO Auto-generated method stub
				return false;
			}

		};

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
