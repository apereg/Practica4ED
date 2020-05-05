package ule.edi.recursiveList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.ClassNotComparableException;
import ule.edi.exceptions.EmptyCollectionException;

public class UnorderedLinkedListTests {

	private UnorderedLinkedListImpl<String> lS;
	private UnorderedLinkedListImpl<String> lSABC;

	@Before
	public void setUp() {
		this.lS = new UnorderedLinkedListImpl<String>();

		this.lSABC = new UnorderedLinkedListImpl<String>("A", "B", "C");
	}

	@Test
	public void constructorElemens() {
		lS = new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
		Assert.assertEquals("(A B C D )", lS.toString());
	}

// TESTS DE addFirst
	@Test
	public void addFirstTest() {

		lS.addFirst("D");
		Assert.assertEquals("(D )", lS.toString());
		lS.addFirst("C");
		Assert.assertEquals("(C D )", lS.toString());
		lS.addFirst("B");
		Assert.assertEquals("(B C D )", lS.toString());
		lS.addFirst("A");
		Assert.assertEquals("(A B C D )", lS.toString());
	}

	// TESTS DE addBefore

	@Test
	public void addBeforeTest() {

		lS.addFirst("D");
		Assert.assertEquals("(D )", lS.toString());
		lS.addBefore("C", "D");
		Assert.assertEquals("(C D )", lS.toString());
		lS.addBefore("A", "C");
		Assert.assertEquals("(A C D )", lS.toString());
		lS.addBefore("B", "C");
		Assert.assertEquals("(A B C D )", lS.toString());
	}

	// Tests toStringReverse

	@Test
	public void toStringReverse() {
		lS = new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
		Assert.assertEquals("(A B C D )", lS.toString());
		Assert.assertEquals("(D C B A )", lS.toStringReverse());

	}
// Tests eliminar duplicados

	@Test
	public void testRemoveDuplicates() throws EmptyCollectionException {
		UnorderedLinkedListImpl<String> lista = new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C");
		Assert.assertEquals(lista.removeDuplicates(), 4);
		Assert.assertEquals(lista.toString(), "(A B C )");
		Assert.assertEquals(lSABC.removeDuplicates(), 0); // 0 repetids
		Assert.assertEquals(lSABC.toString(), "(A B C )");

	}

// AÑADIR MAS TESTS para el resto de casos especiales y para el resto de métodos
	// de las clases AbstractLinkedListImpl y UnorderedLinkedListImpl
	@Test
	public void testAddBeforeRec() {
		lSABC.addLast("W");
		lSABC.addLast("Y");
		lSABC.addLast("Z");
		assertEquals(lSABC.size(), 6);
		assertEquals(lSABC.toString(), "(A B C W Y Z )");
		lSABC.addBefore("X", "Y");
		assertEquals(lSABC.size(), 7);
		assertEquals(lSABC.toString(), "(A B C W X Y Z )");
	}

	@Test(expected = NullPointerException.class)
	public void testAddFirstNullElement() {
		lS.addFirst(null);
	}

	@Test(expected = NullPointerException.class)
	public void testAddLastNullElement() {
		lS.addLast(null);
	}

	@Test(expected = NullPointerException.class)
	public void testAddBeforeNullElement() {
		lSABC.addBefore(null, "A");
	}

	@Test(expected = NullPointerException.class)
	public void testAddBeforeNullTarget() {
		lSABC.addBefore("Z", null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testAddBeforeNonContainedTarget() {
		lSABC.addBefore("Z", "Y");
	}

	/* TESTS PARA ABSTRACT */
	@Test
	public void testToStrings() {
		assertEquals(lSABC.toString(), "(A B C )");
		assertEquals(lSABC.toStringReverse(), "(C B A )");
		lSABC.addFirst("Z");
		lSABC.addBefore("Y", "B");
		assertEquals(lSABC.toString(), "(Z A Y B C )");
		assertEquals(lSABC.toStringReverse(), "(C B Y A Z )");
	}

	@Test
	public void testContains() throws EmptyCollectionException {
		assertTrue(lSABC.contains("A"));
		assertFalse(lSABC.contains("Z"));
		lSABC.addFirst("Z");
		lSABC.remove("A");
		assertTrue(lSABC.contains("Z"));
		assertFalse(lSABC.contains("A"));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsNullElement() throws EmptyCollectionException {
		lSABC.contains(null);
	}

	@Test
	public void testCount() {
		assertEquals(lSABC.size(), 3);
		lSABC.addFirst("Z");
		assertEquals(lSABC.size(), 4);
	}

	@Test
	public void testGetLast() throws EmptyCollectionException {
		assertEquals(lSABC.getLast(), "C");
		lSABC.remove("C");
		assertEquals(lSABC.getLast(), "B");
		lSABC.addLast("Z");
		assertEquals(lSABC.getLast(), "Z");
	}

	@Test(expected = EmptyCollectionException.class)
	public void testGetLastEmptyCollection() throws EmptyCollectionException {
		assertEquals(lS.getLast(), "C");
	}

	@Test
	public void testIsOrdered() throws ClassNotComparableException {
		assertTrue(lS.isOrdered());
		assertTrue(lSABC.isOrdered());
		lSABC.addLast("A");
		assertFalse(lSABC.isOrdered());
	}

	@Test(expected = ClassNotComparableException.class)
	public void testIsOrderedNonComparable() throws ClassNotComparableException {
		UnorderedLinkedListImpl<Boolean> lista = new UnorderedLinkedListImpl<>(true, false, true);
		lista.isOrdered();
	}

	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		assertEquals(lSABC.toString(), "(A B C )");
		assertEquals(lSABC.size(), 3);
		lSABC.remove("A");
		assertEquals(lSABC.toString(), "(B C )");
		assertEquals(lSABC.size(), 2);
		lSABC.remove("B");
		assertEquals(lSABC.toString(), "(C )");
		assertEquals(lSABC.size(), 1);
		lSABC.remove("C");
		assertEquals(lSABC.toString(), "()");
		assertEquals(lSABC.size(), 0);
	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveEmptyList() throws EmptyCollectionException {
		lS.remove("Z");
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveNullElement() throws EmptyCollectionException {
		lSABC.remove(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveNonContainedElement() throws EmptyCollectionException {
		lSABC.remove("Z");
	}

	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		lSABC.addBefore("A", "C");
		lSABC.addBefore("B", "C");
		assertEquals(lSABC.toString(), "(A B A B C )");
		assertEquals(lSABC.size(), 5);
		lSABC.removeLast("A");
		assertEquals(lSABC.toString(), "(A B B C )");
		assertEquals(lSABC.size(), 4);
		lSABC.removeLast("B");
		assertEquals(lSABC.toString(), "(A B C )");
		assertEquals(lSABC.size(), 3);
		lSABC.removeLast("C");
		assertEquals(lSABC.toString(), "(A B )");
		assertEquals(lSABC.size(), 2);
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveLastNullElement() throws EmptyCollectionException {
		lSABC.removeLast(null);
	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastEmptyCollection() throws EmptyCollectionException {
		lS.removeLast("A");
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastNonContainedElement() throws EmptyCollectionException {
		lSABC.removeLast("Z");
	}
	
	@Test
	public void testIsEmpty() throws EmptyCollectionException {
		assertTrue(lS.isEmpty());
		lS.addFirst("A");
		assertFalse(lS.isEmpty());
		assertFalse(lSABC.isEmpty());
		lSABC.remove("A");
		lSABC.remove("B");
		lSABC.remove("C");
		assertTrue(lSABC.isEmpty());
	}
	
	@Test
	public void testSize() throws EmptyCollectionException {
		assertEquals(lSABC.size(), 3);
		lSABC.remove("A");
		assertEquals(lSABC.size(), 2);
		lSABC.remove("B");
		assertEquals(lSABC.size(), 1);
		lSABC.remove("C");
		assertEquals(lSABC.size(), 0);
	}
	
	@Test
	public void testGetFirst() throws EmptyCollectionException {
		assertEquals(lSABC.getFirst(), "A");
		lSABC.remove("A");
		assertEquals(lSABC.getFirst(), "B");
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void testGetFirstEmptyCollecion() throws EmptyCollectionException {
		lS.getFirst();
	}
	
	@Test
	public void testToStringFromUntil() {
		lSABC.addLast("Z");
		assertEquals(lSABC.toStringFromUntil(2, 3), "(B C )");
	}
	
	@Test
	public void testToStringFromUntilBiggerUntilThanSize() {
		lSABC.addLast("Z");
		assertEquals(lSABC.toStringFromUntil(2, 30), "(B C Z )");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testToStringFromUntilFromNegative() {
		lSABC.toStringFromUntil(-1, 3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testToStringFromUntilUntilLowerFrom() {
		lSABC.toStringFromUntil(5, 3);
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void testRemoveDuplicatesEmptyCollection() throws EmptyCollectionException {
		lS.removeDuplicates();
	}

}
