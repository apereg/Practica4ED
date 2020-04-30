package ule.edi.recursiveList;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends
		AbstractLinkedListImpl<T> implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		// Vacía
	}

	public OrderedLinkedListImpl(T... v) {
		// Añade todos los elementos del array 'v'
		for (T Vi : v) {
			add(Vi);
		}
	}

	

	
	@Override
	public void add(T element) {
		//TODO RECURSIVO
		
	
	}
	
	

	@Override
	public int removeDuplicates() {
  // TODO RECURSIVE 
  // Redefinir para listas ordenadas (los duplicados estarán consecutivos)
	
    return 0;
		}

	
	



	

		

}
