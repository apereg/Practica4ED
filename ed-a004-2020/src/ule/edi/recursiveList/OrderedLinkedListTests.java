package ule.edi.recursiveList;


import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.model.Person;

public class OrderedLinkedListTests {

	private OrderedLinkedListImpl<String> lA4B2;
	private OrderedLinkedListImpl<String> listaConElems;
	
	private Person person1, person2, person3, person4;
	


	@Before
	public void setupFixture() {
		lA4B2 = new OrderedLinkedListImpl<String>("A", "B", "A", "A", "B", "A");
		listaConElems=new OrderedLinkedListImpl<String>("C", "X", "A", "D", "B", "Z");
		person1=new Person("10203040A", "Ana", 20);
		person2=new Person("20304050A", "Pedro", 18);
		person3=new Person("01020304A", "Sara", 16);
		person4=new Person("30405060A", "Pablo", 30);

	}
	
	@Test
	public void testConstructsEmpty() {
		Assert.assertTrue(new OrderedLinkedListImpl<>().isEmpty());
	}
	
	

	// tests isOrdered
	// todas las listas OrderedLinkedListImpl deben ser ordenadas
	@Test
	public void testIsOrdered() {
		// todas las orderedLists deben estar ordenadas
		Assert.assertEquals(lA4B2.toString(),"(A A A A B B )");
		Assert.assertTrue(lA4B2.isOrdered());
		Assert.assertTrue((new OrderedLinkedListImpl<String>("A","B")).isOrdered());
		Assert.assertTrue((new OrderedLinkedListImpl<String>()).isOrdered());		
	}

	// Tests removeDuplicates en ordenada
	@Test
	public void testRemoveDuplicates() {
		Assert.assertEquals(lA4B2.removeDuplicates(),4); // 3 A + 1B repetidas
		Assert.assertEquals(lA4B2.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lA4B2.toString(), "(A B )");	
	}

	// Tests con personas
	@Test
	public void testInsertPersons() {
		OrderedLinkedListImpl<Person> lista=new OrderedLinkedListImpl<Person>(person1, person2, person3, person4);
		Assert.assertEquals(lista.toString(),"({01020304A, Sara, 16} {20304050A, Pedro, 18} {10203040A, Ana, 20} {30405060A, Pablo, 30} )");
		Assert.assertTrue(lista.isOrdered());
	}
	
	
	// AÑADIR MAS TESTS para el resto de casos especiales 

	
}
