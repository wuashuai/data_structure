package a3;

/* NetId(s): zw377, ____. Time spent: 6 hours, 00 minutes.

 * Name(s): Zhongru Wu
 * What I thought about this assignment:
 * Writing the code cost a little time, but the debug is a long process.
 *
 */

/** An instance is a doubly linked list. */
public class DLinkedList<E> {
    private int size;  // Number of nodes in the linked list.
    private Node head; // first node of linked list (null if none)
    private Node tail; // last node of linked list (null if none)

    /** Constructor: an empty linked list. */
    public DLinkedList() {
    }

    /** Return the number of values in this list. */
    public int size() {
        return size;
    }

    /** Return the first node of the list (null if the list is empty). */
    public Node getHead() {
        return head;
    }

    /** Return the last node of the list (null if the list is empty). */
    public Node getTail() {
        return tail;
    }

    /** Return the data in node n of this list.
     * Precondition: n is a node of this list; it may not be null. */
    public E getData(Node n) {
        assert n != null;
        return n.data;
    }

    /** Return a representation of this list: its data, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * 
     * E.g. for the list containing 6 3 8 in that order, the result it "[6, 3, 8]". */
    public String toString() {
        //TODO: Write this method first. Do NOT use field size
    	String s = "[";
    	if( head !=null){ 
    		Node n = head.succ();
        	s += head.getData(); 
        	while(n !=null){
        		s = s +", "+ n.getData();
        		n = n.succ();
        	}
        }
    	return s+"]";
    }

    /** Return a representation of this list: its values in reverse, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * 
     * E.g. for the list containing 6 3 8 in that order, the result it "[8, 3, 6]".*/
    public String toStringRev() {
        //TODO: Write this method second. Do NOT use field size
    	String s = "["; 
    	if(tail != null){
    		Node n = tail.pred();
        	s += tail.getData();
        	while(n != null){
        		s = s +", "+ n.getData();
        		n = n.pred() ;
        	}	
    	}
    	return s+"]"; 
    }

    /** Place data v in a new node at the beginning of the list and
     * return the new node */
    public Node prepend(E v) {
        //TODO: This is the third method to write. Then you can begin testing
    	Node n;
    	if (size == 0){
    		n = new Node(null, v, null);
    		head=n;
    		tail=n;
    	}else{
        	n = new Node(null, v, head);
        	head.pred = n;        	
        	head = n;
        }
    	size ++;
        return n; 
    }

    /** Place data v in a new node at the end of the list and
     * return the new node. */
    public Node append(E v) {
        //TODO:This is the fourth method to write.
    	Node n;
    	if (size == 0){
    		n = new Node(null, v, null);
    		head=n;
    		tail=n;
    	}else{
        	n = new Node(tail, v, null);
        	tail.succ = n;        	
        	tail = n;
        }
    	size ++;
        return n;   
    }

    /** Place data v in a new node after node n and
     * return the new node.
     * Precondition: n must be a node of this list; it may not be null. */
    public Node insertAfter(E v, Node n) {
        //TODO: This is the fifth method to write and test

    	Node m = new Node(n, v, n.succ);
    	if (n==tail) {
    		tail = m;
    		n.succ = m;
    	}else{
    		n.succ.pred= m;
    		n.succ = m;
    	}
    	size ++;
		return m;
    }

    /** Place data v in a new node before node n and
     * return the new node.
     * Precondition: n must be a node of this list; it may not be null. */
    public Node insertBefore(E v, Node n) {
        //TODO: This is the sixth method to write and test
    	Node m = new Node(n.pred, v, n);
    	if (n==head) {
    		head = m;
    		n.pred = m;
    	}else{
    		n.pred.succ = m;
    		n.pred = m;
    	}
    	size ++;
		return m;
    }

    /** Remove node n from this list.
     * Precondition: n must be a node of this list; it may not be null. */
    public void remove(Node n) {
        //TODO: This is the seventh method to write and test
    	if(size>1){
    	if(n==head){
    		head = n.succ;
    		n.succ.pred = null;
    	}
    	else if(n==tail) {
    		tail = n.pred;
    		n.pred.succ = null;
		}else{
    	n.pred.succ = n.succ;
    	n.succ.pred = n.pred;
    	}
    	}else{
    		head = null;
    		tail = null;
    	}
    	size --;
    } 


    /*********************/

    /** An instance is a node of this list. */
    public class Node {
        /** Predecessor of this node on list (null if this is the first node). */
        private Node pred;

        /** The data in this element. */
        private E data; 

        /** Successor of this node on list. (null if is the last node). */
        private Node succ; 

        /** Constructor: an instance with predecessor node p (can be null),
         * successor node s (can be null), and data v. */
        private Node(Node p, E v, Node s) {
            pred= p;
            succ= s;
            data= v;
        }

        /** Return the data in this node. */
        public E getData() {
            return data;
        }

        /** Return the predecessor of this node (null if this is the
         * first node of the list). */
        public Node pred() {
            return pred;
        }

        /** Return the successor of this node (null if this is the
         * last node of this list). */
        public Node succ() {
            return succ;
        }
    }

}
