package a4;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiseaseTreeTest {
	@Test
	public void testadd() {
       Network n= new Network();     
       Person a = new Person("A", n, 10);
       Person b = new Person("B", n, 10);
       Person c = new Person("C", n, 10);
       Person d = new Person("D", n, 10);
       Person e = new Person("E", n, 10);
       Person f = new Person("F", n, 10);
       Person g = new Person("G", n, 10);
       DiseaseTree dt = new DiseaseTree(a);
       dt.add(a, c);
       dt.add(c, b);
       dt.add(a, d);
       dt.add(b, e);
       dt.add(e, g);
       dt.add(d, f);
       assertEquals( 7 , dt.size());
       
       
       assertEquals(2, dt.depthOf(f));
       assertEquals(3, dt.depthOf(e));
       assertEquals(0, dt.depthOf(a));
       
       assertEquals(1, dt.widthAtDepth(0));
       assertEquals(2, dt.widthAtDepth(1));
       assertEquals(1, dt.widthAtDepth(3));
       System.out.println(dt.getDiseaseRouteTo(e));       
	}
	
    @Test
    public void testgetshared(){
        Network n= new Network();     
        Person a = new Person("A", n, 10);
        Person b = new Person("B", n, 10);
        Person c = new Person("C", n, 10);
        Person d = new Person("D", n, 10);
        Person e = new Person("E", n, 10);
        Person f = new Person("F", n, 10);
        Person g = new Person("G", n, 10);
        Person h = new Person("H", n, 10);
        DiseaseTree dt = new DiseaseTree(a);
        DiseaseTree dt2 = dt.add(a, b);
        dt.add(a, c);
        dt.add(b, d);
        dt.add(d, g);
        dt.add(c, e);
        dt.add(c, f);
        dt.add(f, h);
        assertEquals(a, dt.getSharedAncestorOf(b, a));
        assertEquals(b, dt.getSharedAncestorOf(b, b));
        assertEquals(a, dt.getSharedAncestorOf(b, c));
        assertEquals(c, dt.getSharedAncestorOf(e, f));
        assertEquals(f, dt.getSharedAncestorOf(h, f));
        assertEquals(a, dt.getSharedAncestorOf(g, f));
        assertEquals(null, dt.getSharedAncestorOf(null, f));
        assertEquals(null, dt2.getSharedAncestorOf(d, f));
    }
    public void testequal(){
        Network n= new Network();     
        Person a = new Person("A", n, 10);
        Person b = new Person("B", n, 10);
        Person c = new Person("C", n, 10);
        Person d = new Person("D", n, 10);
        Person e = new Person("E", n, 10);
        Person f = new Person("F", n, 10);
        Person g = new Person("G", n, 10);
        Person h = new Person("H", n, 10);
        DiseaseTree dt = new DiseaseTree(a);
        DiseaseTree dt2 = dt.add(a, b);
        dt.add(a, c);
        dt.add(b, d);
        dt.add(d, g);
        dt.add(c, e);
        dt.add(c, f);
        dt.add(b, h);
        
        DiseaseTree dt3 = new DiseaseTree(b);
        dt3.add(b, d);
        dt3.add(b, h);
        assertEquals(true, dt.equals(dt));
        assertEquals(false, dt2.equals(dt));
        assertEquals(true, dt2.equals(dt3));
    }
    
}
