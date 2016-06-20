package com.data.structure;

import java.sql.Blob;

// 	Properties of Red Black Tree
//	1. Every node is either red or black.
//	2. The root is black.
//	3. Every leaf (NIL) is black.
//	4. If a node is red, then both its children are black.
//	5. For each node, all simple paths from the node to descendant leaves contain the
//	same number of black nodes.
public class RedBlackTree<T extends Comparable<T>> implements Set<T> {

	class RedBlackNode<T extends Comparable<T>> {

		// Possible color for this node 
		public static final boolean BLACK = true;
		// Possible color for this node
		public static final boolean RED = false;
		// the key of each node
		public T data;

		// Parent of node 
		RedBlackNode<T> parent;
		// Left child 
		RedBlackNode<T> left;
		// Right child
		RedBlackNode<T> right;

		// the color of a node
		public boolean color;

		RedBlackNode() {
			color = BLACK;
			parent = null;
			left = null;
			right = null;
		}

		// Constructor which sets key to the argument.
		RedBlackNode(T ndata) {
			this();
			this.data = ndata;
		}
	}

	private RedBlackNode<T> nil = new RedBlackNode<T>();
	private RedBlackNode<T> root = nil;

	public RedBlackTree() {
		root.left = nil;
		root.right = nil;
		root.parent = nil;
	}

	@Override
	public boolean Insert(T obj) {
		boolean inserted = false;
		RedBlackNode<T> NodeY = nil;
		RedBlackNode<T> NodeX = this.root;
		RedBlackNode<T> NodeZ = new RedBlackNode<T>();
		NodeZ.data = obj;
		NodeZ.left = nil;
		NodeZ.right = nil;
		NodeZ.parent = nil;

		while (NodeX != nil) {
			NodeY = NodeX;
			if (0 > (NodeZ.data).compareTo(NodeX.data)) {
				NodeX = NodeX.left;
			} else {
				NodeX = NodeX.right;
			}
		}

		NodeZ.parent = NodeY;

		if (NodeY == nil) {
			this.root = NodeZ;
			NodeZ.parent = nil;
			this.root = NodeZ;
			inserted = true;
		} else if (0 > (NodeZ.data).compareTo(NodeY.data)) {
			NodeY.left = NodeZ;
			inserted = true;
		} else {
			NodeY.right = NodeZ;
			inserted = true;
		}

		NodeZ.color = RedBlackNode.RED;

		RedBlacTreeInsertFixUp(NodeZ);

		return false;
	}

	@Override
	public boolean Search(T obj) {

		boolean found = false;
		RedBlackNode<T> ro = root;
		if (SearchInternal(obj) != nil && SearchInternal(obj) != null) {
			found = true;
		}
		return found;
	}

	private RedBlackNode<T> SearchInternal(T obj) {

		boolean found = false;
		RedBlackNode<T> ro = root;

		while (ro != nil) {
			if (ro.data.equals(obj)) {
				found = true;
				break;
			} else if (0 > (obj).compareTo(ro.data)) {
				ro = ro.left;
			} else {
				ro = ro.right;
			}
		}
		return ro;
	}

	@Override
	public boolean Remove(T obj) {
		boolean removed = false;

		RedBlackNode<T> y;
		RedBlackNode<T> z = SearchInternal(obj);
		if (z != nil && z != null) {
			if ((z.left != nil) || (z.right != nil)) {
				RedBlackNode<T> fixUpNode = null;
				y = z;
				boolean yOriginalColor = y.color;
				if (z.left == nil) {
					fixUpNode = z.right;
					Transplant(z, z.right);
				} else if (z.right == nil) {
					fixUpNode = z.left;
					Transplant(z, z.left);
				} else {
					y = MinElement(z.right);
					yOriginalColor = y.color;
					fixUpNode = y.right;
					if (y.parent == z) {
						fixUpNode.parent = y;
					} else {
						Transplant(y, y.right);
						y.right = z.right;
						y.right.parent = y;
					}
					Transplant(z, y);
					y.left = z.left;
					y.left.parent = y;
					y.color = z.color;

				}
				if (yOriginalColor == RedBlackNode.BLACK) {
					RedBlacTreeRemoveFixUp(fixUpNode);
				}

			} else {
				if (z == z.parent.left) {
					z.parent.left = nil;
				} else {
					z.parent.right = nil;
				}
			}
		}

		return removed;
	}

	@Override
	public IteratorInterface Iterator() {

		IteratorInterface<T> it = new IteratorInterface<T>() {
			int i = 0;
			private RedBlackNode<T> currentNode = MinElement(root);

			@Override
			public boolean hasNext() {
				if (i == 0) {
					return true;
				}
				return (TreeSuccessor(currentNode) != null && TreeSuccessor(currentNode) != nil) ? true : false;
			}

			@Override
			public T Next() {
				// TODO Auto-generated method stub
				if (i == 0) {
					i++;
					return currentNode.data;
				}
				T obj = null;
				RedBlackNode<T> tmpCurrentNode = TreeSuccessor(currentNode);
				obj = tmpCurrentNode.data;
				currentNode = tmpCurrentNode;
				return obj;
			}

			@Override
			public boolean remove(T rm) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		// TODO 
		return it;

	}

	public RedBlackNode<T> MaxElement(RedBlackNode<T> node) {
		RedBlackNode<T> tmpNode = node;
		while (tmpNode.right.data != null) {
			tmpNode = tmpNode.right;
		}
		return tmpNode;

	}

	public RedBlackNode<T> MinElement(RedBlackNode<T> node) {
		RedBlackNode<T> tmpNode = node;
		while (tmpNode.left.data != null) {
			tmpNode = tmpNode.left;
		}
		return tmpNode;
	}

	// Natural order
	public void InorderTreeWalking(RedBlackNode<T> node) {

		if (node != nil) {
			InorderTreeWalking(node.left);
			System.out.print(node.data);
			System.out.print(" ");
			InorderTreeWalking(node.right);
			if (node == root) {
				System.out.println();
			}
		}
	}

	public void PreorderTreeWalking(RedBlackNode<T> node) {

		if (node != nil) {
			System.out.print(node.data);
			System.out.print(" ");
			InorderTreeWalking(node.left);
			InorderTreeWalking(node.right);
			if (node == root) {
				System.out.println();
			}
		}

	}

	public void PostOrderTreeWalking(RedBlackNode<T> node) {

		if (node != nil) {
			InorderTreeWalking(node.left);
			InorderTreeWalking(node.right);
			System.out.print(node.data);
			System.out.print(" ");
			if (node == root) {
				System.out.println();
			}
		}
	}

	public boolean IterativeTreeSearch(T obj) {
		return false;
	}

	public RedBlackNode<T> GetRoot() {
		return root;
	}

	// Tree Successor in ordered tree
	public RedBlackNode<T> TreeSuccessor(RedBlackNode<T> obj) {
		if (obj.right != nil) {
			return MinElement(obj.right);
		}
		RedBlackNode<T> y = obj.parent;
		while (y != nil && obj == y.right) {
			obj = y;
			y = y.parent;
		}
		return y;
	}

	public RedBlackNode<T> TreePredecessor(RedBlackNode<T> obj) {
		if (obj.left != nil) {
			return MaxElement(obj.right);
		}
		RedBlackNode<T> y = obj.parent;
		while (y != nil && obj == y.left) {
			obj = y;
			y = y.parent;
		}
		return y;
	}

	private void Transplant(RedBlackNode<T> y, RedBlackNode<T> x) {
		if (y.parent == nil) {
			root = x;
		} else if (y == y.parent.left) {
			y.parent.left = x;
		} else {
			y.parent.right = x;
			x.parent = y.parent;
		}
	}

	private void LeftRotate(RedBlackNode<T> NodeX) {
		if (NodeX != nil) {
			RedBlackNode<T> NodeY = NodeX.right;
			NodeX.right = NodeY.left;

			if (NodeY.left != nil) {
				NodeY.left.parent = NodeX;
			}
			NodeY.parent = NodeX.parent;

			if (NodeX.parent == nil) {
				root = NodeY;
			} else if (NodeX == NodeX.parent.left) {
				NodeX.parent.left = NodeY;
			} else {
				NodeX.parent.right = NodeY;
			}
			NodeY.left = NodeX;
			NodeX.parent = NodeY;
		}
	}

	private void RightRotate(RedBlackNode<T> NodeY) {
		if (NodeY != nil) {
			RedBlackNode<T> NodeX = NodeY.left;
			NodeY.left = NodeX.right;

			if (NodeX.right != nil) {
				NodeX.right.parent = NodeY;
			}

			NodeX.parent = NodeY.parent;

			if (NodeY.parent == nil) {
				root = NodeX;
			} else if (NodeY == NodeY.parent.right) {
				NodeY.parent.right = NodeX;
			} else {
				NodeY.parent.left = NodeX;
			}
			NodeX.right = NodeY;
			NodeY.parent = NodeX;
		}
	}

	private void RedBlacTreeInsertFixUp(RedBlackNode<T> Node) {
		// Node.parent is never null because we have nil root which is black.
		while (Node.parent.color == RedBlackNode.RED) {
			// Case when node parent is left child of his parent(grandfather of
			// node)
			if (Node.parent == Node.parent.parent.left) {
				RedBlackNode<T> uncle = Node.parent.parent.right;
				// Case 1 uncle is RED, we will pain parent and uncle to BLACK
				// and grandfather to RED and then iterate again on grandfather
				if (uncle.color == RedBlackNode.RED) {
					uncle.color = RedBlackNode.BLACK;
					Node.parent.color = RedBlackNode.BLACK;
					Node.parent.parent.color = RedBlackNode.RED;
					Node = Node.parent.parent;
					// Case 2 uncle is black and node is right child of parent
					// node
				} else if (Node == Node.parent.right && uncle.color == RedBlackNode.BLACK) {
					Node = Node.parent;
					LeftRotate(Node); // After this rotation we get the case 3
										// because of specific of this case
					// Case 3 uncle is black and node is left child of parent
					// node
				} else if (Node == Node.parent.left && uncle.color == RedBlackNode.BLACK) {
					Node.parent.color = RedBlackNode.BLACK;
					Node.parent.parent.color = RedBlackNode.RED;
					Node = Node.parent.parent;
					RightRotate(Node);

				}

				// Case when node parent is right child of his
				// parent(grandfather of node)
			} else if (Node.parent == Node.parent.parent.right) {
				RedBlackNode<T> uncle = Node.parent.parent.left;
				// Case 1 uncle is RED, we will pain parent and uncle to BLACK
				// and grandfather to RED and then iterate again on grandfather
				if (uncle.color == RedBlackNode.RED) {
					uncle.color = RedBlackNode.BLACK;
					Node.parent.color = RedBlackNode.BLACK;
					Node.parent.parent.color = RedBlackNode.RED;
					Node = Node.parent.parent;
				} else if (Node == Node.parent.left && uncle.color == RedBlackNode.BLACK) {
					Node = Node.parent;
					RightRotate(Node); // After this rotation we get the case 3
										// because of specific of this case
					// Case 3 uncle is black and node is left child of parent
					// node
				} else if (Node == Node.parent.right && uncle.color == RedBlackNode.BLACK) {
					Node.parent.color = RedBlackNode.BLACK;
					Node.parent.parent.color = RedBlackNode.RED;
					Node = Node.parent.parent;
					LeftRotate(Node);

				}
			}
		}
		root.color = RedBlackNode.BLACK;
	}

	private void RedBlacTreeRemoveFixUp(RedBlackNode<T> x) {

		while (x != root && x.color == RedBlackNode.BLACK) {
			// Case when node is left child of his parent
			if (x == x.parent.left) {
				RedBlackNode<T> brother = x.parent.right;
				// Case 1 when brother has RED color. Since Brother must have
				// black children, we can switch the
				// colors of Brother and x.parent and then perform a
				// left-rotation on x.parent
				// The new Brother of x, which is one of Brothers children
				// prior to the rotation, is now black, and thus we have
				// converted case 1 into case 2,
				// 3, or 4.
				if (brother.color == RedBlackNode.RED) {
					brother.color = RedBlackNode.BLACK;
					x.parent.color = RedBlackNode.RED;
					LeftRotate(x.parent);
					// We get new brother
					brother = x.parent.right;

					// Cases 2, 3, and 4 occur when node Brother is black; they
					// are distinguished by the
					// colors of Brothers children.
					// Case 1 xs sibling Brother is black, and both of
					// Brothers children are black
				}
				if (brother.color == RedBlackNode.BLACK && brother.left.color == RedBlackNode.BLACK
						&& brother.right.color == RedBlackNode.BLACK) {
					brother.color = RedBlackNode.RED;
					x = x.parent;
					// Case 3 xs sibling Brother is black, Brothers left child
					// is red, and Brothers right child is black
				} else if (brother.color == RedBlackNode.BLACK && brother.left.color == RedBlackNode.RED
						&& brother.right.color == RedBlackNode.BLACK) {
					brother.left.color = RedBlackNode.BLACK;
					brother.color = RedBlackNode.RED;
					RightRotate(brother);
					brother = x.parent.right;

					// Case 4 xs sibling Brother is black, and Brothers right
					// child is red
					brother.color = x.parent.color;
					x.parent.color = RedBlackNode.BLACK;
					brother.right.color = RedBlackNode.BLACK;
					LeftRotate(x.parent);
					x = root;
				}
				// Case when node is right child of his parent
			} else if (x == x.parent.right) {

				RedBlackNode<T> brother = x.parent.left;
				// Case 1 when brother has RED color. Since Brother must have
				// black children, we can switch the
				// colors of Brother and x.parent and then perform a
				// right-rotation on x.parent
				// The new Brother of x, which is one of Brothers children
				// prior to the rotation, is now black, and thus we have
				// converted case 1 into case 2,
				// 3, or 4.
				if (brother.color == RedBlackNode.RED) {
					brother.color = RedBlackNode.BLACK;
					x.parent.color = RedBlackNode.RED;
					RightRotate(x.parent);
					// We get new brother
					brother = x.parent.left;

					// Cases 2, 3, and 4 occur when node Brother is black; they
					// are distinguished by the
					// colors of Brothers children.
					// Case 1 xs sibling Brother is black, and both of
					// Brothers children are black
				}
				if (brother.color == RedBlackNode.BLACK && brother.left.color == RedBlackNode.BLACK
						&& brother.right.color == RedBlackNode.BLACK) {
					brother.color = RedBlackNode.RED;
					x = x.parent;
					// Case 3 xs sibling Brother is black, Brothers right
					// child is red, and Brothers left child is black
				} else if (brother.color == RedBlackNode.BLACK && brother.right.color == RedBlackNode.RED
						&& brother.left.color == RedBlackNode.BLACK) {
					brother.right.color = RedBlackNode.BLACK;
					brother.color = RedBlackNode.RED;
					LeftRotate(brother);
					brother = x.parent.right;

					// Case 4 xs sibling Brother is black, and Brothers left
					// child is red
					brother.color = x.parent.color;
					x.parent.color = RedBlackNode.BLACK;
					brother.left.color = RedBlackNode.BLACK;
					RightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color = RedBlackNode.BLACK;
	}
}
