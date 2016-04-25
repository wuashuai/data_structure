package a2;

import static org.junit.Assert.*;

import org.junit.Test;

public class A2Test {

	@Test
	public void testSumDif() { // assertEquals(expected value, computed value);
	assertEquals(8, A2.sumDif(true, 5, 3)); 
	assertEquals(2, A2.sumDif(false, 5, 3)); 
	//assertEquals(0, A2.sumDif(true, 5, 3));
	}
	
	@Test
	public void testSameBackAndForth(){
		assertEquals(true, A2.sameBackAndForth("madamimadam"));
	}
	
	@Test
	public void testNumOccurrences(){
		assertEquals(3, A2.numOccurrences("aaaa","aa"));
		assertEquals(2, A2.numOccurrences("Luke Skywalker","ke"));
		assertEquals(3, A2.numOccurrences("abababab","aba"));
	}
	
	@Test
	public void testDecompress(){
		assertEquals("bbbcxx111bb", A2.decompress("b3c1x2a013b2"));

	}
	
	@Test
	public void testFixName(){
		assertEquals("James Gosling", A2.fixName("GOSLING, JAMES  "));
		assertEquals("James Arthur Gosling", A2.fixName("GOSLING, JAMES   ARTHUR"));
	}
	
	@Test
	public void testReplaceConsonants(){
		assertEquals("_iNeCRaFT", A2.replaceConsonants("Minecraft"));
		assertEquals("ALaN _uRiNG", A2.replaceConsonants("Alan Turing"));
	}
	
	@Test
	public void testAreAnagrams(){
		assertEquals(true, A2.areAnagrams("i am lordvoldemort","tom marvolo riddle"));
		assertEquals(false, A2.areAnagrams("tommarvoloriddle", "i am lordvoldemort"));
	}

}
