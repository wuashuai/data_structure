package a1;
import static org.junit.Assert.*;

import org.junit.Test;

public class PhDTester {

	@Test
	public void testConstructor1() {
		PhD p = new PhD("Andreas", 'M', 1817, 10);
		assertEquals("Andreas", p.getName());
		assertEquals(1817, p.getYear());
		assertEquals(10, p.getMonth());
		assertEquals(true, p.isMale());
		assertEquals(null, p.getFirstAdvisor());
		assertEquals(null, p.getSecondAdvisor());
		assertEquals(0, p.numAdivsee());

		PhD y = new PhD("Christian", 'F', 1713, 8);
		assertEquals(false, y.isMale());
	}

	@Test
	public void testConstructor2() {
		PhD p = new PhD("Otto", 'M', 1778, 5);
		PhD p1 = new PhD("Theodor", 'M', 1787, 9);
		PhD p2 = new PhD("Angelo", 'F', 1890, 7);
		p.addFirstAdvisor(p1);
		p.addSecondAdvisor(p2);
		assertEquals(p1, p.getFirstAdvisor());
		assertEquals(p2, p.getSecondAdvisor());
	}	


	@Test
	public void testConstructor3() {

		PhD p1 = new PhD("Demetrios", 'M', 1544, 1);
		PhD p2 = new PhD("Theodoros", 'M', 1594, 3, p1);
		assertEquals("Theodoros", p2.getName());
		assertEquals(1594, p2.getYear());
		assertEquals(3, p2.getMonth());
		assertEquals(true, p2.isMale());
		assertEquals(p1, p2.getFirstAdvisor());
		assertEquals(null, p2.getSecondAdvisor());

		PhD p3 = new PhD("Jan", 'F', 1672, 7);
		PhD p4 = new PhD("Alexander", 'M', 1706, 11, p1, p3);
		assertEquals("Alexander", p4.getName());
		assertEquals(1706, p4.getYear());
		assertEquals(11, p4.getMonth());
		assertEquals(true, p4.isMale());
		assertEquals(p1, p4.getFirstAdvisor());
		assertEquals(p3, p4.getSecondAdvisor());

	}
	@Test
	public void testConstructor4() {
		PhD p1 = new PhD("Ulrich", 'M', 1680, 3);
		PhD p2 = new PhD("Philipp", 'F', 1682, 2);
		PhD p3 = new PhD("Christoph", 'M', 1687, 5);
		PhD p4 = new PhD("Georg", 'F', 1690, 1);
		PhD p5 = new PhD("Ambrosius", 'M', 1693, 6, p1, p2);
		PhD p6 = new PhD("Domenico", 'F', 1692, 2, p3, p4);
		assertEquals(false,p5.isYoungerThan(p6));
		assertEquals(true,p5.isPhDSibling(p6));
	}	


}
