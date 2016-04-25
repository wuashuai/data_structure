package a6;

/* Time spent on a6:  6 hours and 30 minutes.

 * Name(s): Zhongru Wu, Yiwen Wang
 * Netid(s): zw377, yw748 
 * What I thought about this assignment: 
 * I think test is not complete. We we poll a node in without deleting it in map, 
 * we still pass the test.
 *
 */

import java.util.*;

/** An instance is a priority queue of elements of type E
 * implemented as a min-heap. */
public class Heap<E> implements PCue<E> {

    private int size; // number of elements in the priority queue (and heap)

    /** The heap invariant is given below. Note that / denotes int division.
     * 
     *  b[0..size-1] is viewed as a min-heap, i.e. 
     *  1. Each array element in b[0..size-1] contains a value of the heap.
     *  2. The children of each b[i] are b[2i+1] and b[2i+2].
     *  3. The parent of each b[i] (except b[0]) is b[(i-1)/2].
     *  4. The priority of the parent of each b[i] is <= the priority of b[i].
     *  5. Priorities for the b[i] used for the comparison in point 4
     *     are given in map. map contains one entry for each element of
     *     the heap, and map and b have the same size.
     *     For each element e in the heap, the map entry contains in the
     *     Info object the priority of e and its index in b.
     */
    private ArrayList<E> b= new ArrayList<E>();
    private HashMap<E, Info> map= new HashMap<E, Info>();

    /** Constructor: an empty heap. */
    public Heap() {
    }

    /** Return a string that gives this priority queue, in the format:
     * [item0:priority0, item1:priority1, ..., item(N-1):priority(N-1)]
     * Thus, the list is delimited by '['  and ']' and ", " (i.e. a
     * comma and a space char) separate adjacent items. */
    @Override public String toString() {
        String s= "[";
        for (E t : b) {
            if (s.length() > 1) {
                s = s + ", ";
            }
            s = s + t + ":" + map.get(t).priority;
        }
        return s + "]";
    }

    /** Return a string that gives the priorities in this priority queue,
     * in the format: [priority0, priority1, ..., priority(N-1)]
     * Thus, the list is delimited by '['  and ']' and ", " (i.e. a
     * comma and a space char) separate adjacent items. */
    public String toStringPriorities() {
        String s= "[";
        for (E t : b) {
            if (s.length() > 1) {
                s = s + ", ";
            }
            s = s + map.get(t).priority;
        }
        return s + "]";
    }

    /** Return the number of elements in the priority queue.
     *  This operation takes constant time. */
    @Override public int size() {
        return size;
    }

    /** Add e with priority p to the priority queue.
     *  Throw an illegalArgumentException if e is already in the queue.
     *  The expected time is O(log N) and the worst-case time is O(N). */ 
    @Override public void add(E e, double p) throws IllegalArgumentException {
        // TODO  First: Do add and bubbleUp together.
    	if(map.containsKey(e)) {
    		throw new IllegalArgumentException(" e is already in the queue");
    	}
    	else {
    		b.add(e);
    		this.size++;
    		Info info = new Info(b.indexOf(e),p);
    		map.put(e, info);
    		this.bubbleUp(size-1);
    	}

    }

    /** Return the element of the priority queue with lowest priority, without
     *  changing the queue. This operation takes constant time.
     *  Throw a PCueException with message "priority queue is empty" if the
     *     priority queue is empty. */
    @Override public E peek() {
        // TODO  Second: Do peek.
    	if(b.isEmpty()) {
    		throw new PCueException("priority queue is empty");
    	}
    	else {
    		return b.get(0);
    	}
    }

    /** Remove and return the element of the priority queue with lowest priority.
     *  The expected time is O(log n) and the worst-case time is O(N).
     *  Throw a PCueException with message "priority queue is empty" if the
     *     priority queue is empty. */
    @Override public E poll() {
        // TODO  THIRD: Do poll and bubbleDown together.
    	if(size<=0||b.isEmpty()) {
    		throw new PCueException("priority queue is empty");
    	}
    	
    	else {
    		E val = b.get(0);
        	
        	if(this.size()==1) {
        		size = 0;
        		map.remove(val);
        		b.remove(0);
        		return val;
        	}
        	else {
        		E end = b.get(size-1);
        		b.remove(size-1);
        		map.remove(val);
        		b.set(0,end);
        		map.replace(end, new Info(0,map.get(end).priority));
        		size--;
        		
        		this.bubbleDown(0);
        		
        		return val;
        	}
    	}
    }

    /** Change the priority of element e to p.
     *  The expected time is O(log N) and the worst-case is time O(N).
     *  Throw an illegalArgumentException if e is not in the priority queue. */
    @Override public void changePriority(E e, double p) {
        // TODO  FOURTH: Do changePriority.
        if(!b.contains(e)) {
        	throw new IllegalArgumentException("e is not in the priority queue");
        }
        else {
        	map.get(e).priority = p;
        	int k = map.get(e).index;
        	this.bubbleDown(k);
        	this.bubbleUp(k);
        }

    }


    /** Bubble b[k] up in heap to its right place.
     * Precondition: Every b[i] satisfies the heap property except perhaps
     *       k's priority < parent's priority */
    private void bubbleUp(int k) {
        // TODO  First: Do add and bubbleUp together.
        // Do not use recursion; iteration is best.
    	E bk= b.get(k);
    	double kpriority = map.get(bk).priority;
    	
    	while(k>0) {
    		int p = (k-1)/2;
    		E bp = b.get(p);
    		double ppriority = map.get(bp).priority;
    		
    		if(kpriority>=ppriority) {
    			b.set(k, bk);
    			map.replace(bk,new Info(k,kpriority));
    			return;
    		}
    		
    		b.set(k, bp);
    		map.replace(bp, new Info(k,ppriority));
    		map.replace(bk, new Info(p,ppriority));
    		k=p;
    		
//    		b.set(k, bp);
//    		//map.replace(bp, new Info(k,ppriority));
//    		map.replace(bk, new Info(p,ppriority));
//    		k=p;
    	}
        //k=0
    	b.set(k, bk);
		map.replace(bk,new Info(k,kpriority));

    }

    /** Bubble b[k] down in heap until it finds the right place.
     * Precondition: Every b[i] satisfies the heap property except perhaps
     * k's priority > a child's priority. */
    private void bubbleDown(int k) {
        // TODO  THIRD: Do poll and bubbleDown together.
        // Do not use recursion; iteration is best.
    	E bk= b.get(k);
    	double kpriority = map.get(bk).priority;
    	
    	while(2*k+1<size) {
    		int c = this.getSmallerChild(k);
    		E bc = b.get(c);
    		double cpriority = map.get(bc).priority;
    		
    		if(kpriority<=cpriority) {
    			b.set(k, bk);
    			map.replace(bk, new Info(k,kpriority));
    			return;
    		}
    		
    		b.set(k, bc);
    		map.replace(bc, new Info(k,cpriority));
//    		b.set(c, bk);
//    		map.replace(bk, new Info(c,kpriority));
    		k=c;
    		//retu rn;
    	}
    	b.set(k, bk);
    	map.replace(bk, new Info(k,kpriority));

    }

    /** Return the index of the smaller child of b[q]
     * Precondition: left child exists: 2q+1 < size of heap */
    private int getSmallerChild(int q) {
    	int lChild = 2*q+1;
    	if(lChild<size) {
    		if (lChild + 1  ==  size) return lChild;
            
            if (map.get( b.get(lChild)).priority<map.get(b.get(lChild+1)).priority)
                return lChild;
            return lChild+1;
    	}
    	else {
    		return -1;
    	}
    }

    /** An instance contains the index, value, and priority of an element of the heap. */
    private static class Info {
        private int index;  // index of this element in map
        private double priority; // priority of this element

        /** Constructor: an instance in b[i] with priority p. */
        private Info(int i, double p) {
            index= i;
            priority= p;
        }
        
    }
}