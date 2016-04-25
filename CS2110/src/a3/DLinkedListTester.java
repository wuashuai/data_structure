package a3;

import static org.junit.Assert.*;

import org.junit.Test;


public class DLinkedListTester {
	
	@Test
	/** Test append*/
	public void testappend() {
		DLinkedList<String> ll= new DLinkedList<String>();
		assertEquals("[]", ll.toString());
		assertEquals("[]", ll.toStringRev());
		assertEquals(0, ll.size());

		DLinkedList<String>.Node n= ll.append("Foster");
		assertEquals("[Foster]", ll.toString());
		assertEquals("[Foster]", ll.toStringRev());
		assertEquals(1, ll.size());
		assertEquals(ll.getTail(), n);

		n= ll.append("and");
		assertEquals("[Foster, and]", ll.toString());
		assertEquals("[and, Foster]", ll.toStringRev());
		assertEquals(2, ll.size());
		assertEquals(ll.getTail(), n);

		ll.append(null);
		n= ll.append("Gries");
		assertEquals("[Foster, and, null, Gries]", ll.toString());
		assertEquals("[Gries, null, and, Foster]", ll.toStringRev());
		assertEquals(4, ll.size());
		assertEquals(ll.getTail(), n);
	}
	
	@Test
	/** Test prepend, toString, and toStringReverse. */
	public void testFirstThree() {
		DLinkedList<String> ll= new DLinkedList<String>();
		assertEquals("[]", ll.toString());
		assertEquals("[]", ll.toStringRev());
		assertEquals(0, ll.size());

		DLinkedList<String>.Node n= ll.prepend("Foster");
		assertEquals("[Foster]", ll.toString());
		assertEquals("[Foster]", ll.toStringRev());
		assertEquals(1, ll.size());
		assertEquals(ll.getHead(), n);

		n= ll.prepend("and");
		assertEquals("[and, Foster]", ll.toString());
		assertEquals("[Foster, and]", ll.toStringRev());
		assertEquals(2, ll.size());
		assertEquals(ll.getHead(), n);

		ll.prepend(null);
		n= ll.prepend("Gries");
		assertEquals("[Gries, null, and, Foster]", ll.toString());
		assertEquals("[Foster, and, null, Gries]", ll.toStringRev());
		assertEquals(4, ll.size());
		assertEquals(ll.getHead(), n);
	}
	
	
	@Test
	/** Test prepend, append, toString and toStringReverse*/
	public void testToString() {
		DLinkedList<Integer> ll = new DLinkedList<Integer>();
		assertEquals("[]", ll.toString());
		ll.append(6);
		ll.append(3);
		ll.append(8);
		assertEquals("[6, 3, 8]", ll.toString());
		ll.prepend(6);
		assertEquals("[8, 3, 6, 6]", ll.toStringRev());	
	}

	@Test
	public void testinsertAfter() {
		DLinkedList<String> ll= new DLinkedList<String>();
		assertEquals("[]", ll.toString());
		assertEquals("[]", ll.toStringRev());
		assertEquals(0, ll.size());

		DLinkedList<String>.Node n0= ll.prepend("Foster");
		assertEquals("[Foster]", ll.toString());
		assertEquals("[Foster]", ll.toStringRev());
		assertEquals(1, ll.size());
		assertEquals(ll.getTail(), n0);

		DLinkedList<String>.Node n1= ll.insertAfter("and",n0);
		assertEquals("[Foster, and]", ll.toString());
		assertEquals("[and, Foster]", ll.toStringRev());
		assertEquals(2, ll.size());
		assertEquals(ll.getTail(), n1);

		DLinkedList<String>.Node n2 = ll.insertAfter(null,n1);
		DLinkedList<String>.Node n3= ll.insertAfter("Gries",n0);
		assertEquals("[Foster, Gries, and, null]", ll.toString());
		assertEquals("[null, and, Gries, Foster]", ll.toStringRev());
		assertEquals(4, ll.size());
		assertEquals(ll.getTail(), n2);
	}
	
	@Test
	public void testinsertBefore() {
		DLinkedList<String> ll= new DLinkedList<String>();

		DLinkedList<String>.Node n0= ll.prepend("Foster");
		assertEquals("[Foster]", ll.toString());
		assertEquals("[Foster]", ll.toStringRev());
		assertEquals(1, ll.size());
		assertEquals(ll.getHead(), n0);

		DLinkedList<String>.Node n1= ll.insertBefore("and",n0);
		assertEquals("[and, Foster]", ll.toString());
		assertEquals("[Foster, and]", ll.toStringRev());
		assertEquals(2, ll.size());
		assertEquals(ll.getHead(), n1);

		DLinkedList<String>.Node n2 = ll.insertBefore(null,n1);
		DLinkedList<String>.Node n3= ll.insertBefore("Gries",n1);
		assertEquals("[null, Gries, and, Foster]", ll.toString());
		assertEquals("[Foster, and, Gries, null]", ll.toStringRev());
		assertEquals(4, ll.size());
		assertEquals(ll.getHead(), n2);
	}
	
	
	@Test
	public void testremove() {
		DLinkedList<String> ll= new DLinkedList<String>();
		DLinkedList<String>.Node n0= ll.append("Foster");
		DLinkedList<String>.Node n1= ll.append("and");
		DLinkedList<String>.Node n2 = ll.append(null);
		DLinkedList<String>.Node n3= ll.append("Gries");
		
		ll.remove(n1);
		assertEquals("[Foster, null, Gries]", ll.toString());
		assertEquals("[Gries, null, Foster]", ll.toStringRev());
		assertEquals(3, ll.size());
		assertEquals(ll.getHead(), n0);
		assertEquals(ll.getTail(), n3);
		
		
		ll.remove(n0);
		assertEquals("[null, Gries]", ll.toString());
		assertEquals("[Gries, null]", ll.toStringRev());
		assertEquals(2, ll.size());
		assertEquals(ll.getHead(), n2);
		assertEquals(ll.getTail(), n3);
		
		ll.remove(n3);
		assertEquals("[null]", ll.toString());
		assertEquals("[null]", ll.toStringRev());
		assertEquals(1, ll.size());
		assertEquals(ll.getHead(), n2);
		assertEquals(ll.getTail(), n2);	
		
		ll.remove(n2);
		assertEquals("[]", ll.toString());
		assertEquals("[]", ll.toStringRev());
		assertEquals(0, ll.size());
		assertEquals(ll.getHead(), null);
		assertEquals(ll.getTail(), null);	
	}
}
