package ule.edi.recursiveList;

import ule.edi.exceptions.EmptyCollectionException;

public interface ListADT<T> extends Iterable<T>{
	/**
	 * Indica si esta lista está vacía
	 * 
	 * @return <code>true</code> si no contiene elementos
	 */
	boolean isEmpty();
	
	/**
	 * RECURSIVO 
	 * 
	 * Devuelve el número total de elementos en esta lista. <br>
	 * 
	 * Ejemplo:<br>
     * Si una lista l contiene (A B C B D A B ): <br>
	 *  l.size() -> 7
	 *   
	 * @return número total de elementos en esta lista
	 */
	public int size();
	
	/**
	 * Devuelve el primer elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C D E ): <br>
	 * l.getFirst -> A <br>
	 * 
	 * @return elem : primer elemento de la lista
	 * 
     * @throws EmptyCollectionException si la lista es vacía 	
	 */
	public T getFirst() throws EmptyCollectionException;
	
	/**
	 * RECURSIVO
	 * 
	 * Devuelve el último elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C D E ): <br>
	 * l.getLast -> E <br>
	 * 
	 * @return elem : último elemento de la lista
	 * 
     * @throws EmptyCollectionException si la lista es vacía 	
	 */
	public T getLast() throws EmptyCollectionException;
	
	/**
	 * RECURSIVO
	 * 
	 * Indica si el elemento está en esta lista. <br>
	 * 
	 * Devuelve <code>true</code> si al menos existe una
	 * instancia del elemento dado en esta lista (es decir,
	 * un elemento 'x' tal que <code>x.equals(element)</code>)
	 * y <code>false</code> en caso contrario.
	 * 
	 * @param elem elemento a buscar en esta lista
	 * @return <code>true</code>/<code>false</code> según el resultado
	 * 
	 * @throws NullPointerException el elemento indicado es <code>null</code>
	 */
	boolean contains(T target);
	
	/**
	 * RECURSIVO 
	 * 
	 * Devuelve el número total de elementos iguales al pasado como parámetro. <br>
	 * 
	 * Ejemplo:<br>
     * Si una lista l contiene (A B C B D A B ): <br>
	 *  l.count("A") -> 2
	 *  l.count("B") -> 3
	 * 
	 * @param element elemento a contar sus apariciones
	 *  
	 * @return número total de elementos en esta lista
	 */
	int count(T element);
	
	/**
	 * RECURSIVO 
	 * 
	 * Elimina la primera aparición del elemento y devuelve el elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.remove("A") -> A, dejando la lista (B C B D A B ) <br>
	 * l.remove("B") -> B, dejando la lista (A C B D A B ) <br>
 	 * l.remove("Z") -> NoSuchElementException 
 	 * 
     * @param element elemento a eliminar.
     * 
	 * @throws NullPointerException si element es <code>null</code>
	 * @throws NoSuchElementException si element no está en la lista.
     * @throws EmptyCollectionException si la lista está vacía.
	 * 
	 */
	public T remove(T element) throws EmptyCollectionException;
	
	/**
	 *  
	 *  RECURSIVO 
	 *  
	 * Elimina la última aparición del elemento y devuelve el elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.removeLast("A") -> A, dejando la lista (A B C B D B ) <br>
	 * l.removeLast("B") -> B, dejando la lista (A B C B D A ) <br>
 	 * l.removeLast("Z") -> NoSuchElementException 
 	 * 
     * @param element elemento a eliminar.
     * 
	 * @throws NullPointerException si element es <code>null</code>
	 * @throws NoSuchElementException si element no está en la lista.
     * @throws EmptyCollectionException  si la lista está vacía.
	 * 
	 */
	public T removeLast(T element) throws EmptyCollectionException;
	
	/**
	 * RECURSIVO
	 *
	 * Devuelve el toString de la sublista formada por los elementos situados entre las posiciones from hasta until incluidas. 
	 * 
	 * <br> Si until > size() se muestra hasta el final de la lista.
	 * <br> Si from > size() se muestra () (toString de la lista vacía)
	 * 
	 * <br> Ejemplos:<br>
	 * l1=(A B C D E ) ; l1.toSringFromUntil(1,3)  -> (A B C ) <br>
	 * l1=(A B C D E ) ; l1.toSringFromUntil(3,10)  -> (C D E ) <br>
	 * l1=(A B C D E ) ;l1.toStringFromUntil(10,20) -> () <br>
	 * 
	 * @param from posición desde la que se empieza a considerar la lista (incluida)
	 * @param until posición hasta la que se incluyen elementos (incluida)
	 *  
	 * @return String de la sublista formada por los elementos en el rango establecido por los dos parámetros.
	 *
	 *@throws IllegalArgumentException si from o until son <=0 ; o si until < from
	 *
	 */
	public String toStringFromUntil(int from, int until);
	
	/**
	 * RECURSIVO
	 * 
	 * Crea y devuelve un String con el contenido de la lista empezando por el final hasta el principio.
	 * 
	 * <br> Si esta lista es vacía devuelve el toString() de la lista vacía -> (). <br>
	 * 
	 * <br> Ejemplo:<br>
	 * Si una lista l contiene (A B C ): <br>
	 *  l.toStringReverse() -> (C B A ) 
	 * @return recorrido inverso de la lista (desde el final al principio)
	 */
	public String toStringReverse();
	
	/**
	 * RECURSIVO
	 *  
	 * Devuelve <tt>true</tt> si la lista es ordenada de menor a mayor
	 * 
	 * La lista vacía está ordenada.
	 * 
	 * @return <tt>true</tt> si los elementos están ordenados de menor a mayor; false en caso contrario.
	 */
	public boolean isOrdered();
	
	/**
	 * RECURSIVO 
	 * 
	 * Elimina todos los duplicados de cada elemento, dejando solamente una aparición de cada elemento y devuelve el elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.removeDuplicates() -> 3 , dejando la lista (A B C D ) <br>
	 * 
	 * Si una lista l contiene (A B C ): <br> 
	 * l.removeDuplicates() -> 0, dejando la lista (A B C ) <br>
 	 *  
     * @return el número de elementos que elimina
     * 
	 * @throws EmptyCollectionException si la lista está vacía.
	 * 
	 */
	public int removeDuplicates() throws EmptyCollectionException;
	
}
