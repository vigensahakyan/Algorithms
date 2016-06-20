package com.data.structure;

public class HashSetOpenAddressed<T> implements Set<T> {

	private int size = 37;

	private HashElement<T>[] table = new HashElement[size];

	@Override
	public boolean Insert(T obj) {
		boolean inserted = false;
		HashElement<T> elem = new HashElement(obj.hashCode(), obj);
		int hash = hash(elem.getKey());
		int hashInit = hash(elem.getKey());
		int startHash = -1;
		DeletedElement<T> singleton = null;
		int i = 1;
		int indexOfDeletedElement = -1;
		while (table[hash] != null && table[hash] != singleton.getInstanceOfDeletedEntry() && startHash != hash
				&& hash < (size)) {
			hash = hash(hashInit, i);
			if (i == 1) {
				startHash = hashInit;
			}
			i++;
		}
		if (table[hash] == null) {
			table[hash] = elem;
			inserted = true;
		} else if (table[hash] == singleton.getInstanceOfDeletedEntry()) {
			table[hash] = elem;
			inserted = true;
		} else if (startHash == hash || hash >= size) {
			System.out.println("Hash Table overflow");
		}

		return inserted;
	}

	@Override
	public boolean Search(T obj) {

		boolean found = false;
		HashElement<T> elem = new HashElement(obj.hashCode(), obj);
		int hash = hash(elem.getKey());
		int hashInit = hash(elem.getKey());
		int startHash = -1;
		DeletedElement<T> singleton = null;
		int i = 1;
		int indexOfDeletedElement = -1;
		while ((table[hash] != null && startHash != hash && hash < size)) {
			if (table[hash].getKey() == elem.getKey()) {
				found = true;
				break;
			}
			hash = hash(hashInit, i);
			if (i == 1) {
				startHash = hashInit;
			}
			i++;
		}
		if (startHash == hash || hash >= size) {
			System.out.println("Hash Table overflow");
		}

		return found;
	}

	@Override
	public boolean Remove(T obj) {

		boolean removed = false;
		HashElement<T> elem = new HashElement(obj.hashCode(), obj);
		int hash = hash(elem.getKey());
		int hashInit = hash(elem.getKey());
		int startHash = -1;
		DeletedElement<T> singleton = null;
		int i = 1;
		int indexOfDeletedElement = -1;
		while ((table[hash] != null && startHash != hash && hash < size)) {
			if (table[hash].getKey() == elem.getKey()) {
				table[hash] = singleton.getInstanceOfDeletedEntry();
				removed = true;
				break;
			}
			hash = hash(hashInit, i);
			if (i == 1) {
				startHash = hashInit;
			}
			i++;
		}
		if (startHash == hash || hash >= size) {
			System.out.println("Hash Table overflow: There isn't such element in table");
		}

		return removed;

	}

	@Override
	public IteratorInterface Iterator() {

		IteratorInterface<T> it = new IteratorInterface<T>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {

				// TODO Auto-generated method stub

				return (currentIndex < (size)) ? true : false;
			}

			@Override
			public T Next() {
				// TODO Auto-generated method stub
				T obj = null;
				if (table[currentIndex] != null) {
					obj = (T) table[currentIndex].getValue();
				}
				currentIndex++;

				return obj;
			}

			@Override
			public boolean remove(T rm) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		// TODO Auto-generated method stub
		return it;
	}

	private int hash(int k) {
		return k % size;
	}

	private int hash(int k, int i) {
		return (hash(k) + i) % size;
	}

}

class HashElement<T> {
	private int key;
	private T value;

	HashElement(int key, T value) {
		this.key = key;
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getKey() {
		return key;
	}

}

class DeletedElement<T> extends HashElement {
	private static DeletedElement element = null;

	private DeletedElement() {
		super(-1, -1);
	}

	public static DeletedElement getInstanceOfDeletedEntry() {
		if (element == null)
			element = new DeletedElement();
		return element;
	}
}
