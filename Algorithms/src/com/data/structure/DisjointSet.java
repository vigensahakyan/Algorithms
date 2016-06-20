package com.data.structure;

import java.util.HashMap;

// DisjointSet with union rank and path compression.
// If you have M sequential operations (make_set, find and union), 
// the overall complexity will be O(M + a(n)) where a(n) asymptoticly equal 4;
// hens that you have got linear(Amortized) time for M operations
public class DisjointSet<T extends Comparable<T>> {

	class Node<T> {
		T element;
		Node<T> parent;
		int rank;

		public Node(T el) {
			element = el;
			rank = 0;
			parent = this;
		}

		public T getElement() {
			return element;
		}

		public Node<T> getParetn() {
			return parent;
		}
	}

	private final HashMap<T, Node<T>> objectsToNodesMap = new HashMap<>();

	public Node<T> getNodeByObject(T obj) {
		return objectsToNodesMap.get(obj);
	}

	public Node<T> makeSet(T obj) {
		Node<T> ret = new Node<T>(obj);
		objectsToNodesMap.put(obj, ret);
		return ret;
	}

	public Node<T> findSet(Node<T> node) {
		// DisjointSet.Node<T> node = objectsToNodes.get(o);
		if (node == null) {
			return null;
		}
		if (node != node.parent) {
			node.parent = findSet(node.parent);
		}
		return node.parent;
	}

	public void union(Node<T> x, Node<T> y) {
		Node<T> setX = findSet(x);
		Node<T> setY = findSet(y);
		if (setX == null || setY == null || setX == setY) {
			return;
		}

		if (setX.rank > setY.rank) {
			setY.parent = x;
		} else {
			setX.parent = y;
			if (setX.rank == setY.rank) {
				setY.rank++;
			}
		}
	}

	public static void main(String[] args) {

		DisjointSet<Integer> ds = new DisjointSet<Integer>();
		DisjointSet<Integer>.Node<Integer> ret1 = ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		DisjointSet<Integer>.Node<Integer> ret5 = ds.makeSet(5);
		ds.makeSet(6);
		ds.makeSet(7);
		ds.makeSet(8);
		ds.union(ds.getNodeByObject(2), ret1);
		ds.union(ret1, ds.getNodeByObject(3));
		ds.union(ret1, ds.getNodeByObject(4));
		ds.union(ds.getNodeByObject(6), ret5);
		ds.union(ret5, ds.getNodeByObject(7));
		ds.union(ret5, ds.getNodeByObject(8));
		ds.union(ret5, ret1);

		System.out.println(ds.findSet(ds.getNodeByObject(8)).getElement() + " "
				+ ds.findSet(ds.getNodeByObject(8)).getParetn().getElement());

	}
}
