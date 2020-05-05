package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.ClassNotComparableException;
import ule.edi.exceptions.EmptyCollectionException;

public class AbstractLinkedListImpl<T> implements ListADT<T> {

	// Estructura de datos, lista simplemente enlazada
	//
	// Este es el primer nodo de la lista
	protected Node<T> front = null;

	// Clase para cada nodo en la lista
	protected class Node<T> {

		T elem;

		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		/*
		 * No necesario e intesteable
		 * 
		 * @Override public String toString() { return "(" + elem + ")"; }
		 */

	}

	private class IteratorImpl implements Iterator<T> {
		Node<T> node;

		public IteratorImpl(Node<T> node) {
			/* Se guarda el front */
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			/* Se comprueba que se puede avanzar */
			return this.node != null;
		}

		@Override
		public T next() {
			/* Se comprueba por si se esta haciendo un mal uso de la coleccion */
			if (!hasNext())
				throw new NoSuchElementException();

			/* Se almacena el elemento y se salta al siguiente */
			T elemReturn = this.node.elem;
			this.node = this.node.next;
			/* Se devuelve el elemento */
			return elemReturn;
		}

		@Override
		public void remove() {
			/* Esta coleccion no implementa la opcion de borrar en el iterador */
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String toString() {
		/* Llamada al metodo recursivo */
		return "(" + this.toStringRec(this.front);
	}

	private String toStringRec(Node<T> node) {
		/* Si ya se acabo se introduce el parentesis */
		if (node == null)
			return ")";
		/*
		 * Si no se vuelve a llamar avanzando al siguiente insertando el elemento actual
		 */
		return node.elem.toString() + " " + this.toStringRec(node.next);
	}

	@Override
	public boolean contains(T target) {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (target == null)
			throw new NullPointerException();

		/* Llamada al metodo recursivo */
		return this.containsRec(this.front, target);
	}

	private boolean containsRec(Node<T> node, T target) {
		/* Si se llego al final y no se encontro se devuelve false */
		if (node == null)
			return false;
		/* Si el elemento coincide con el target si se contiene */
		if (node.elem.equals(target))
			return true;

		/* Si no pasa ninguna de las dos anteriores se avanza recursivamente */
		return this.containsRec(node.next, target);
	}

	@Override
	public int count(T element) {
		/* Llamada al metodo recursivo */
		return this.countRec(this.front, element);
	}

	private int countRec(Node<T> node, T target) {
		/* Si ya se llega al final se para la recursividad */
		if (node == null)
			return 0;
		/* Si esta el elemento se avanza recursivamente incrementando el resultado */
		if (node.elem.equals(target))
			return 1 + this.countRec(node.next, target);

		/* Si no se avanza recursivamente sin incremento */
		return this.countRec(node.next, target);
	}

	@Override
	public T getLast() throws EmptyCollectionException {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedListImpl");

		/* Llamada al metodo recursivo */
		return this.getLastRec(this.front);
	}

	private T getLastRec(Node<T> node) {
		/* Si se llega al final se devuelve el elemento */
		if (node.next == null)
			return node.elem;

		/* Si no se avanza recursivamente */
		return this.getLastRec(node.next);
	}

	@Override
	public boolean isOrdered() throws ClassNotComparableException {
		/* Cualquier lista vacia esta ordenada */
		if (this.isEmpty())
			return true;

		/* Llamada al metodo recursivo */
		return this.isOrderedRec(this.front);
	}

	private boolean isOrderedRec(Node<T> node) throws ClassNotComparableException {
		/*
		 * Si el siguiente nodo es nulo y no ha saltado el false antes la lista esta
		 * ordenada
		 */
		if (node.next == null)
			return true;
		/*
		 * Se compara si estan ordenados comtemplando la excepcion de que no sean
		 * comparables
		 */
		try {
			@SuppressWarnings("unchecked") // Si esta comprobado por el catch pero aun así salta el aviso
			Comparable<T> nodeC = (Comparable<T>) node.elem;
			if (nodeC.compareTo(node.next.elem) > 0)
				return false;
		} catch (ClassCastException e) {
			/*
			 * He creado una nueva excepcion en el paquete en el caso que los objetos no
			 * sean comparables
			 */
			throw new ClassNotComparableException("AbstractLinkedListImpl");
		}
		/* Si estan ordenados y aun no es el final se comprueba el siguiente */
		return this.isOrderedRec(node.next);
	}

	@Override
	public T remove(T element) throws EmptyCollectionException {
		/* Se contemplan los casos para lanzar excepciones de la documentacion */
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		if (element == null)
			throw new NullPointerException();
		if (!this.contains(element))
			throw new NoSuchElementException();

		/* Llamada al metodo recursivo */
		return this.removeRec(this.front, element);
	}

	private T removeRec(Node<T> node, T element) {
		/* If solo para el primer elemento */
		if (node.elem.equals(element)) {
			if (this.front.next != null)
				this.front = this.front.next;
			else
				this.front = null;
			return element;
		}
		/*
		 * Este .next es seguro ya que siempre si el actual no es el siguiente va a ser
		 * porque la lista contiene el elemento
		 */
		if (node.next.elem.equals(element)) {
			/* Si no es el ultimo se linkea al siguiente */
			if (node.next.next != null)
				node.next = node.next.next;
			else
				node.next = null;
			return element;
		}

		/*
		 * Si aun no se encontro ni es el final se avanza al siguiente nodo
		 * recursivamente
		 */
		return this.removeRec(node.next, element);
	}

	@Override
	public T removeLast(T element) throws EmptyCollectionException {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (element == null)
			throw new NullPointerException();
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		if (!this.contains(element))
			throw new NoSuchElementException();

		/* Se busca la posicion a borrar recursivamente */
		int lastPos = this.findLastRec(this.front, element, 0, 0);
		/* Se borra esa posicion resursivamente */
		return this.removeLastRec(this.front, element, 0, lastPos);
	}

	private int findLastRec(Node<T> node, T element, int actual, int pos) {
		/*
		 * Pos siempre se va a actualizar por que tiene que contener el elemento para
		 * llegar aqui, se guarda la posicion anterior a la que se busca para poder
		 * borrarlo despues
		 */

		/* Si ya se ha llegado al final se devuelve la ultima posicion */
		if (node.next == null)
			return pos;
		/*
		 * Si se encuentra el elemento se actualiza la posicion en la que esta el ultimo
		 */
		if (node.next.elem.equals(element))
			pos = actual;

		/* Se avanza recursivamente en busca nuevamente del elemento */
		return this.findLastRec(node.next, element, ++actual, pos);
	}

	private T removeLastRec(Node<T> node, T element, int actual, int lastPos) {
		/* Si aun no se llego a la posicion se avanza recursivamente */
		if (actual < lastPos)
			return this.removeLastRec(node.next, element, ++actual, lastPos);

		/*
		 * Cuando se llega a la posicion se guarda el elemento para devolverlo y se
		 * borra de la lista
		 */
		T elemReturn = node.next.elem;
		/* Si no es el ultimo se linkea al siguiente */
		if (node.next.next != null)
			node.next = node.next.next;
		else
			node.next = null;
		return elemReturn;
	}

	@Override
	public boolean isEmpty() {
		/* Siempre que el front no sea nulo la lista no estara vacia */
		return this.front == null;
	}

	@Override
	public int size() {
		/* Llamada al metodo recursivo */
		return this.sizeRec(this.front);
	}

	private int sizeRec(Node<T> node) {
		/* Si se llega al final de la lista se para la recursividad */
		if (node == null)
			return 0;

		/* Se incrementa el valor avanzando recursivamente al siguiente nodo */
		return 1 + this.sizeRec(node.next);
	}

	@Override
	public T getFirst() throws EmptyCollectionException {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");

		/* Se devuelve el front */
		return this.front.elem;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (from <= 0 || until < from)
			throw new IllegalArgumentException();

		/* Llamada al metodo recursivo */
		return "(" + this.toStringFromUntilRec(this.front, --from, --until, 0);
	}

	private String toStringFromUntilRec(Node<T> node, int from, int until, int actual) {
		/*
		 * Si se llega al final se corta la recursividad devolviendo el final del
		 * formato de string
		 */
		if (actual == until + 1 || node == null)
			return ")";
		/* Si aun no se llego al from se avanza recursivamente */
		if (actual < from)
			return this.toStringFromUntilRec(node.next, from, until, ++actual);
		/* Se avanza recursivamente añadiendo el elemento en formato de string */
		return node.elem.toString() + " " + this.toStringFromUntilRec(node.next, from, until, ++actual);
	}

	@Override
	public String toStringReverse() {
		/* Llamada al metodo recursivo */
		return this.toStringReverseRec(this.front) + ")";
	}

	private String toStringReverseRec(Node<T> node) {
		/* Si ya se llego al final (el principio inverso) se corta la recursividad */
		if (node == null)
			return "(";

		/* Se avanza recursivamente añadiendo (despues de la recursion) el elemento */
		return this.toStringReverseRec(node.next) + node.elem.toString() + " ";
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");

		/* Llamada al metodo recursivo */
		return this.removeDuplicatesRec(this.front);
	}

	private int removeDuplicatesRec(Node<T> node) throws EmptyCollectionException {
		/* Si ya se ha llegado al final se corta la recursividad */
		if (node == null)
			return 0;
		/* Si el elemento esta mas de una vez se borra la ultima iteraccion */
		if (this.count(node.elem) > 1) {
			this.removeLast(node.elem);
			/* Se avanza recursivamente incrementando los borrados */
			return 1 + this.removeDuplicatesRec(node.next);
		}
		/* Si el elemento no estaba repetido se avanza recursivamente sin mas */
		return this.removeDuplicatesRec(node.next);
	}

	@Override
	public Iterator<T> iterator() {
		/* Se devuelve el iterador */
		return new IteratorImpl(this.front);
	}

}
