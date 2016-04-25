package a4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class DiseaseTreeTester {
	private static Network n; 
	private static Person[] people;

	@BeforeClass
	public static void setup() {
		Network n= new Network();
		people= new Person[]{
				new Person("A", n, 0), new Person("B", n, 0), 
				new Person("C", n, 0), new Person("D", n, 0), 
				new Person("E", n, 0), new Person("F", n, 0), 
				new Person("G", n, 0), new Person("H", n, 0),
				new Person("I", n, 0), new Person("J", n, 0),
				new Person("K", n, 0), new Person("L", n, 0)
		}; 
	}
	
	
	/*
	 * test add and size
	 */
	@Test
	public void testOneNodeTree() {
		DiseaseTree t1= new DiseaseTree(people[0]);
		assertEquals("A", toStringBrief(t1));
		assertEquals(1,t1.size());}
	
	
	@Test
	public void testAddAtLevel0() {
		DiseaseTree t1= new DiseaseTree(people[0]);
		t1.add(people[0], people[1]); 
		assertEquals("A[B]", toStringBrief(t1));
		assertEquals(2,t1.size());
		
		t1.add(people[0], people[2]); 
		assertEquals("A[B C]", toStringBrief(t1));
		assertEquals(3,t1.size());
		
		t1.add(people[0], people[3]);
		assertEquals("A[B C D]", toStringBrief(t1)); 
		assertEquals(4,t1.size());}
	
	@Test
	public void testAddAtLevel1() {
		DiseaseTree t1= new DiseaseTree(people[0]);
		t1.add(people[0], people[1]); 
		t1.add(people[0], people[2]);
		t1.add(people[0], people[3]);
		
		t1.add(people[1], people[5]);
		assertEquals("A[B[F] C D]", toStringBrief(t1));
		assertEquals(5,t1.size());
		
		t1.add(people[1], people[4]); 
		assertEquals("A[B[E F] C D]", toStringBrief(t1));
		assertEquals(6,t1.size());
		
		t1.add(people[4], people[6]); 
		assertEquals("A[B[E[G] F] C D]", toStringBrief(t1));
		assertEquals(7,t1.size());
		
		t1.add(people[0], people[7]);
		assertEquals("A[B[E[G] F] C D H]", toStringBrief(t1));
		assertEquals(8,t1.size());}
	
	/*
	 * test depthOf, widthAtDepth and getDiseaseRouteTo
	 */
	@Test
	public void testdepthOf() {
		DiseaseTree t1= new DiseaseTree(people[0]);
		t1.add(people[0], people[1]); 
		t1.add(people[0], people[2]);
		t1.add(people[0], people[3]);
		t1.add(people[1], people[5]);
		t1.add(people[1], people[4]); 
		t1.add(people[4], people[6]); 
		t1.add(people[0], people[7]);

		//test depthOf
		assertEquals(0,t1.depthOf(people[0]));
		assertEquals(1,t1.depthOf(people[1]));
		assertEquals(2,t1.depthOf(people[4]));
		assertEquals(3,t1.depthOf(people[6]));
		assertEquals(1,t1.depthOf(people[7]));
		assertEquals(0,t1.getTree(people[1]).depthOf(people[1]));
		assertEquals(1,t1.getTree(people[1]).depthOf(people[4]));

		//test widthAtDepth
		assertEquals(1,t1.widthAtDepth(0));
		assertEquals(4,t1.widthAtDepth(1));
		assertEquals(2,t1.widthAtDepth(2));
		assertEquals(1,t1.widthAtDepth(3));
		assertEquals(0,t1.widthAtDepth(4));
		assertEquals(2,t1.getTree(people[1]).widthAtDepth(1));
		assertEquals(1,t1.getTree(people[1]).widthAtDepth(2));

		//test getDiseaseRouteTo
		ArrayList<Person> p1 = new ArrayList<Person>();p1.add(people[0]);
		assertEquals(p1,t1.getDiseaseRouteTo(people[0]));
		ArrayList<Person> p2 = new ArrayList<Person>();
		p2.add(people[0]);p2.add(people[1]);p2.add(people[4]);p2.add(people[6]);
		assertEquals(p2,t1.getDiseaseRouteTo(people[6]));
		ArrayList<Person> p3 = new ArrayList<Person>();
		p3.add(people[0]);p3.add(people[7]);
		assertEquals(p3,t1.getDiseaseRouteTo(people[7]));
		assertEquals(null,t1.getTree(people[1]).getDiseaseRouteTo(people[0]));		
	}
	
	/*
	 * Test getSharedAncestorOf with the examples showed in DiseaseTree.java
	 */
	@Test
	public void getSharedAncestorOf() {
		DiseaseTree t1= new DiseaseTree(people[0]);
		t1.add(people[0], people[1]); t1.add(people[0], people[2]); 
		t1.add(people[1], people[3]); t1.add(people[3], people[6]); 
		t1.add(people[2], people[4]); t1.add(people[2], people[5]); 
		
		assertEquals(people[0],t1.getSharedAncestorOf(people[0], people[1]));
		assertEquals(people[1],t1.getSharedAncestorOf(people[1], people[1]));
		assertEquals(people[0],t1.getSharedAncestorOf(people[1], people[2]));
		assertEquals(people[0],t1.getSharedAncestorOf(people[0], people[2]));
		assertEquals(people[2],t1.getSharedAncestorOf(people[4], people[5]));
		assertEquals(people[0],t1.getSharedAncestorOf(people[5], people[6]));
		assertEquals(null,t1.getTree(people[1]).getSharedAncestorOf(people[3], people[5]));
		assertEquals(null,t1.getTree(people[1]).getSharedAncestorOf(people[3], people[7]));
		assertEquals(null,t1.getSharedAncestorOf(null, people[2]));	
	}

	@Test
	public void testequals() {
		DiseaseTree t1= new DiseaseTree(people[0]);
		t1.add(people[0], people[1]); t1.add(people[0], people[2]); 
		t1.add(people[1], people[3]); t1.add(people[3], people[6]); 
		t1.add(people[2], people[4]); t1.add(people[2], people[5]); 
		DiseaseTree t2= new DiseaseTree(people[1]);
		t2.add(people[1], people[2]); 
		t2.add(people[2], people[3]); t2.add(people[2], people[4]); t2.add(people[2], people[5]);
		t2.add(people[3], people[6]); 
		DiseaseTree t3= new DiseaseTree(people[0]);
		t3.add(people[0], people[1]); 
		t3.add(people[0], people[2]);
		t3.add(people[0], people[3]);
		t3.add(people[1], people[5]);
		t3.add(people[1], people[4]); 
		t3.add(people[4], people[6]); 
		t3.add(people[0], people[7]);
		
		assertEquals(true,t1.equals(t1));
		assertEquals(false,t1.equals(t2));
		assertEquals(false,t1.equals(t3));
		assertEquals(false,t3.equals(t2));
	}


	
	
	
	/** Return a representation of this tree. This representation is: * (1) the name of the person at the root, followed by
	 * (2) followed by the representations of the children.
	 * There are two cases concerning the children. *
	 * No children? Their representation is the empty string.
	 * Children? Their representation is the representation of each child, with
	 * a blank between adjacent ones, and delimited by "[" and "]".
	 * Examples:
	 * One-node tree: "A"
	 * A root A with children B, C,D: "A[B C D]"
	 * A root A with children B, C, D and B has a child F: "A[B[F] C D]" */
	public String toStringBrief(DiseaseTree t) {
		String res= t.getRoot().getName();
		Object[] childs= t.getChildren().toArray(); if (childs.length == 0) return res;
		res= res + "[";
		selectionSort1(childs);
		for (int k= 0; k < childs.length; k= k+1) {
			if (k > 0) res= res + " ";
			res= res + toStringBrief(((DiseaseTree)childs[k]));
		}
		return res + "]";
	}
	
	/** Sort b --put its elements in ascending order.
	 * Sort on the name of the person at the root of the DiseaseTree
	 * Throw cast-class exception of b's elements are not DiseaseTree */
	public static void selectionSort1(Object[] b) { int j= 0;
	// {inv P: b[0..j-1] is sorted and b[0..j-1] <= b[j..]}
	// 0---------------j--------------- b.length //inv:b| sorted,<= | >= |
	// --------------------------------
	while (j != b.length) {
		// Put into p the index of smallest element in b[j..] 
		int p= j;
		for (int i= j+1; i != b.length; i++) {
			String bi= ((DiseaseTree)b[i]).getRoot().getName(); 
			String bp= ((DiseaseTree)b[p]).getRoot().getName(); 
			if (bi.compareTo(bp) < 0) {
				p= i;
			}
		}
		// Swap b[j] and b[p]
		Object t= b[j]; b[j]= b[p]; b[p]= t; j= j+1;
	} 
	}



}
