package ule.edi.recursiveList;

import java.util.NoSuchElementException;


public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		//	Vacía
	}
	
	public UnorderedLinkedListImpl(T ... v) {
		//	Añadir en el mismo orden que en 'v'
		for (T Vi : v) {
			addLast(Vi);
		}
	}
	
	@Override
	public void addFirst(T element) {
     //TODO
	}
	
	
	@Override
	public void addLast(T element) {
		// TODO RECURSIVO
		
	
	}	

	
	@Override
	public void addBefore(T element, T target) {
		// TODO RECURSIVO
			
			
	}

		
}
