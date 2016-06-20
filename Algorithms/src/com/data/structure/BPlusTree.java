package com.data.structure;

import com.data.structure.RedBlackTree.RedBlackNode;

public class BPlusTree<T extends Comparable<T>> implements Set<T>  {
    // max children per B-tree node = M
    // (must be even and greater than 2)
    private static final int M = 4;

    private BPlusTreeNode root;       // root of the B-tree
    private int height;      // height of the B-tree
    private int N;           // number of key-value pairs in the B-tree

    // helper B-tree node data type
    // M number of reference your key_size(M-1) + reference_size*M <= block_size
 	// E.g key is integer(4 byte) reference is (6 byte) and block size is 4kbyte hence 4*(M-1) + 6*M <=4096 ==> your M<=500
 	// Root of B+ tree has minimum 1 key up to (M-1) keys, and it has [2,M] children node
 	// Internal nodes store up to M-1 key, and have between floor(M/2) and M children.
 	// Every non-leaf, non-root hence inner node has at least floor(M / 2) children.
 	// Every key from the table appears in a leaf, in left-to-right sorted order.
    private static final class BPlusTreeNode {
        private int m;                             // number of children
        private KeyValueElement[] childs = new KeyValueElement[M];   // the array of childs
        private BPlusTreeNode left = null;
        private BPlusTreeNode right = null;

        // create a node with k childs
        private BPlusTreeNode(int k) {
            m = k;
        }
    }

    // internal nodes: only use key and next
	// external nodes: only use key and value
    private static class KeyValueElement {
        private Comparable key;
        private Object val;
        private BPlusTreeNode next;     // helper field to iterate over array entries
        public KeyValueElement(Comparable key, Object val, BPlusTreeNode next) {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    public BPlusTree() {
        root = new BPlusTreeNode(0);
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public int height() {
        return height;
    }
    
    @Override
	public boolean Insert(T obj) {

    	if (obj == null) throw new NullPointerException("obj must not be null");
        BPlusTreeNode u = insert(root, obj, obj, height); 
        N++;
        if (u == null) return true;

        // need to split root
        BPlusTreeNode t = new BPlusTreeNode(2); // change 2 to 1;
        t.childs[0] = new KeyValueElement(root.childs[0].key, null, root);
        t.childs[1] = new KeyValueElement(u.childs[0].key, null, u);
        root = t;
        root.childs[0].next.right = root.childs[1].next;
        root.childs[1].next.left = root.childs[0].next;
        height++;
    	
		return true;
	}

	@Override
	public boolean Search(T obj) {
		if (obj == null) throw new NullPointerException("obj must not be null");
        return searchInternal(root, obj, height)!=null;
	}

	@Override
	public boolean Remove(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IteratorInterface Iterator() {
		IteratorInterface<T> it = new IteratorInterface<T>(){
			int i = 0;
			private BPlusTreeNode currentNode = minKey(root);
			
			@Override
			public boolean hasNext() {
				boolean hasNext = false;
				if(currentNode!=null){
					if(i< currentNode.m){
						hasNext = true; 
					}
				}
				return hasNext;
			}

			@Override
			public T Next() {
				// TODO Auto-generated method stub
				T obj = null;
				if(hasNext()){
					obj = (T) currentNode.childs[i].key;
					i++;
				}
				if (i >= currentNode.m){
					currentNode = currentNode.right;
					i=0;
				}
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
    
    private T searchInternal(BPlusTreeNode x, T key, int ht) {
        KeyValueElement[] childs = x.childs;

        // external node
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (keyeq(key, childs[j].key)) return (T) childs[j].val;
            }
        }
        // internal node
        else {
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || lessthan(key, childs[j+1].key))
                    return searchInternal(childs[j].next, key, ht-1);
            }
        }
        return null;
    }
    
    public void put(T key, T val) {
        if (key == null) throw new NullPointerException("key must not be null");
        BPlusTreeNode u = insert(root, key, val, height); 
        N++;
        if (u == null) return;

        // need to split root
        BPlusTreeNode t = new BPlusTreeNode(2);
        t.childs[0] = new KeyValueElement(root.childs[0].key, null, root);
        t.childs[1] = new KeyValueElement(u.childs[0].key, null, u);
        root = t;
        height++;
    }

    private BPlusTreeNode insert(BPlusTreeNode h, T key, T val, int ht) {
        int j;
        KeyValueElement t = new KeyValueElement(key, val, null);

        // leaf node
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (lessthan(key, h.childs[j].key)) break;
            }
        }

        // internal node
        else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || lessthan(key, h.childs[j+1].key)) {
                    BPlusTreeNode u = insert(h.childs[j++].next, key, val, ht-1);
                    if (u == null){	
                    	return null;
                    // if leaf has been split
                    }else if((ht-1)== 0){
                		u.right = h.childs[j-1].next.right;
                		h.childs[j-1].next.right = u;
                		u.left = h.childs[j-1].next;
                		//break;
                	}
                    // if split
                    t.key = u.childs[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--)
            h.childs[i] = h.childs[i-1];
        h.childs[j] = t;
        h.m++;
        if (h.m < M) return null;
        else         return split(h);
    }

    // split node in half
    private BPlusTreeNode split(BPlusTreeNode h) {
        BPlusTreeNode t = new BPlusTreeNode(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
            t.childs[j] = h.childs[M/2+j];
        return t;    
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(BPlusTreeNode h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        KeyValueElement[] childs = h.childs;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + childs[j].key + " " + childs[j].val + "\n");
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s.append(indent + "(" + childs[j].key + ")\n");
                s.append(toString(childs[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean lessthan(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean keyeq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
    
    private BPlusTreeNode minKey(BPlusTreeNode x){
    	BPlusTreeNode min = x;
    	while(min.childs[0].next != null){
    		min = min.childs[0].next;
    	}
    	return min;
    }

}
