package ule.edi.recursiveList;


public interface UnorderedListADT<T> extends ListADT<T>{
	/**
	 * Añade un elemento como primer elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B D )  y hacemos l.addFirst("C") 
	 * la lista quedará (C A B D )
	 * 
	 * @param element el elemento a añadir
	 * 
	 * @throws NullPointerException si element es <code>null</code>
	 */
	void addFirst(T element);
	
	/**
	 * Añade un elemento como último elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B D )  y hacemos l.addLast("C") 
	 * la lista quedará (A B D C)
	 * 
	 * @param element el elemento a añadir
	 * 
	 * @throws NullPointerException si element es <code>null</code>
	 */
	void addLast(T element);
	
	/**
	 * Añade un elemento delante de la primera aparición del elemento pasado como 
	 * 2º parámetro desplazando los elementos que estén a partir de ese elemento.
	 * <p>
	 * Si una lista l =(A B C B )  <br>
	 *  l.addBefore("D", "B") ;   la lista quedará (A D B C B ).
	 * 
	 * 
	 * @param elem el elemento a añadir
	 * @param target el elemento delante del cual insertará elem
	 * 
	 * @throws NullPointerException  si elem o target son <code>null</code>
	 * @throws NoSuchElementException  si target no está en la lista
	 * 
	 */
	void addBefore(T element, T target);	
}
