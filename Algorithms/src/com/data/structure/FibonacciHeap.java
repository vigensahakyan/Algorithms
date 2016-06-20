package com.data.structure;

import java.util.ArrayList;


//  The amortized running time of most of these methods is O(1), making
//  it a very fast data structure. Several have an actual running time
//  of O(1). removeMin() and delete() have O(log n) amortized running
//  times because they do the heap consolidation.
// 
public class FibonacciHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    // logb(n) = log(n) / log(b)
	// b = (1+ sqrt(5))/2 ;
	// n = Integer.MAX_VALUE;
	// Dn equal almost 45 for large Integer.MAX_VALUE
	//private static final int Dn = Math.ce ( Math.log(Integer.MAX_VALUE) / Math.log( (1.0 + Math.sqrt(5.0))/2.0 ) );
	
	// Points to the minimum node in the heap.
    private Node<T> min;
    
    // number of element
    private int n;
    

	@Override
	public boolean Insert(T obj) {
		Node<T> node = new Node<T>(obj);
        // concatenate node into min list
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if ((node.key).compareTo(min.key) < 0) {
                min = node;
            }
        } else {
            min = node;
        }
        n++;
        return (node != null) ? true : false;

	}

	@Override
	public T Minimum() {
		return min().data; 
	}

	@Override
	public T ExtractMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DecreaseKey(T key, int m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean Delete(T obj) {
		// TODO Auto-generated method stub
		return false;
	}
    
    

    //Removes all elements from this heap. Running time: O(1)
    public void clear() {
        min = null;
        n = 0;
    }

   
    private void consolidate() {
        // The magic 45 comes from log base phi of Integer.MAX_VALUE,
        // which is the most elements we will ever hold, and log base
        // phi represents the largest degree of any root list node.
        Node<T>[] A = new Node[45];

        // For each root list node look for others of the same degree.
        Node<T> start = min;
        Node<T> w = min;
        do {
            Node<T> x = w;
            // Because x might be moved, save its sibling now.
            Node<T> nextW = w.right;
            int d = x.degree;
            while (A[d] != null) {
                // Make one of the nodes a child of the other.
                Node<T> y = A[d];
                if (x.key.compareTo(y.key) > 0) {
                    Node<T> temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) {
                    // Because removeMin() arbitrarily assigned the min
                    // reference, we have to ensure we do not miss the
                    // end of the root node list.
                    start = start.right;
                }
                if (y == nextW) {
                    // If we wrapped around we need to check for this case.
                    nextW = nextW.right;
                }
                // Node y disappears from root list.
                y.link(x);
                // We've handled this degree, go to next one.
                A[d] = null;
                d++;
            }
            // Save this node for later when we might encounter another
            // of the same degree.
            A[d] = x;
            // Move forward through list.
            w = nextW;
        } while (w != start);

        // The node considered to be min may have been changed above.
        min = start;
        // Find the minimum key again.
        for (Node<T> a : A) {
            if (a != null && a.key.compareTo(min.key) < 0) {
                min = a;
            }
        }
    }

    
    public void decreaseKey(Node<T> x, T newData, Integer k) {
        decreaseKey(x, newData, k, false);
    }

   
    private void decreaseKey(Node<T> x, T newData, Integer k, boolean delete) {
        if (!delete && k.compareTo(x.key) > 0) {
            throw new IllegalArgumentException("cannot increase key value");
        }
        x.key = k;
		x.data = newData;
        Node<T> y = x.parent;
        if (y != null && (delete || k.compareTo(y.key) < 0)) {
            y.cut(x, min);
            y.cascadingCut(min);
        }
        if (delete || k.compareTo(min.key) < 0) {
            min = x;
        }
    }

   
    public void delete(Node<T> x) {
        // make x as small as possible
        decreaseKey(x, x.data, 0 , true);
        // remove the smallest, which decreases n also
        removeMin();
    }

   
    public boolean isEmpty() {
        return min == null;
    }

    public Node insert(T x, Integer key) {
        Node<T> node = new Node<T>(x);
        // concatenate node into min list
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (key.compareTo(min.key) < 0) {
                min = node;
            }
        } else {
            min = node;
        }
        n++;
        return node;
    }

  
    public Node<T> min() {
        return min;
    }

   
    public T removeMin() {
        Node<T> z = min;
        if (z == null) {
            return null;
        }
        if (z.child != null) {
            z.child.parent = null;
            // for each child of z do...
            for (Node<T> x = z.child.right; x != z.child; x = x.right) {
                // set parent[x] to null
                x.parent = null;
            }
            // merge the children into root list
            Node<T> minleft = min.left;
            Node<T> zchildleft = z.child.left;
            min.left = zchildleft;
            zchildleft.right = min;
            z.child.left = minleft;
            minleft.right = z.child;
        }
        // remove z from root list of heap
        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right) {
            min = null;
        } else {
            min = z.right;
            consolidate();
        }
        // decrement size of heap
        n--;
        return z.data;
    }

    public int size() {
        return n;
    }

	public int count() {
		return n;
	}

  
    public static FibonacciHeap union(FibonacciHeap H1, FibonacciHeap H2) {
        FibonacciHeap H = new FibonacciHeap();
        if (H1 != null && H2 != null) {
            H.min = H1.min;
            if (H.min != null) {
                if (H2.min != null) {
                    H.min.right.left = H2.min.left;
                    H2.min.left.right = H.min.right;
                    H.min.right = H2.min;
                    H2.min.left = H.min;
                    if (H2.min.key.compareTo(H1.min.key) < 0) {
                        H.min = H2.min;
                    }
                }
            } else {
                H.min = H2.min;
            }
            H.n = H1.n + H2.n;
        }
        return H;
    }

	public ArrayList<Node<T>> nodeList() {
		ArrayList<Node<T>> l = new ArrayList<Node<T>>();
		if (min != null) min.addToList(l);
		return l;
	}

    public static class Node<T extends Comparable<T>> {
    	// Data object for this node, holds the key value. 
        private T data;
        // Key value for this node
        private Integer key;
        // Parent node
        private Node<T> parent;
        // First child node 
        private Node<T> child;
        // Right sibling node 
        private Node<T> right;
        // Left sibling node 
        private Node<T> left;
        // Number of children of this node 
        private int degree;
        // True if this node has had a child removed since this node was added to its parent 
        private boolean mark;

       // Node Constructor
        public Node(T data) {
            this.data = data;
            this.key = data.hashCode();
            right = this;
            left = this;
        }

		public Integer getKey() {return key;}
		public T getData() {return data;}

        
        public void cascadingCut(Node<T> min) {
            Node<T> z = parent;
            // if there's a parent...
            if (z != null) {
                if (mark) {
                    // it's marked, cut it from parent
                    z.cut(this, min);
                    // cut its parent as well
                    z.cascadingCut(min);
                } else {
                    // if y is unmarked, set it marked
                    mark = true;
                }
            }
        }

        
        public void cut(Node<T> x, Node<T> min) {
            // remove x from childlist and decrement degree
            x.left.right = x.right;
            x.right.left = x.left;
            degree--;
            // reset child if necessary
            if (degree == 0) {
                child = null;
            } else if (child == x) {
                child = x.right;
            }
            // add x to root list of heap
            x.right = min;
            x.left = min.left;
            min.left = x;
            x.left.right = x;
            // set parent[x] to nil
            x.parent = null;
            // set mark[x] to false
            x.mark = false;
        }

        
        public void link(Node<T> parent) {
            // Note: putting this code here in Node makes it 7x faster
            // because it doesn't have to use generated accessor methods,
            // which add a lot of time when called millions of times.
            // remove this from its circular list
            left.right = right;
            right.left = left;
            // make this a child of x
            this.parent = parent;
            if (parent.child == null) {
                parent.child = this;
                right = this;
                left = this;
            } else {
                left = parent.child;
                right = parent.child.right;
                parent.child.right = this;
                right.left = this;
            }
            // increase degree[x]
            parent.degree++;
            // set mark false
            mark = false;
        }

		public void addToList(ArrayList<Node<T>> l) {
			Node<T> cur = this;
			do {
				l.add(cur);
				if (cur.child != null) cur.child.addToList(l);
				cur = cur.right;
			} while (cur != this);
		}
    }

}
